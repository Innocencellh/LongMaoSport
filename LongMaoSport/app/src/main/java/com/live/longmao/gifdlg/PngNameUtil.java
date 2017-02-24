package com.live.longmao.gifdlg;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.live.longmao.bean.GiftBean;
import com.live.longmao.model.GiftInfo;
import com.live.longmao.model.GiftListInfo;
import com.live.longmao.model.ListGiftInfo;
import com.live.longmao.model.ListGuessingInfo;
import com.live.longmao.model.LiveInfoJson;
import com.live.okhttp.JsonGenericsSerializator;
import com.live.okhttp.OkHttpUtils;
import com.live.okhttp.callback.GenericsCallback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/6/21.
 */
public class PngNameUtil {
    private List<GiftBean> list;

    public List<GiftBean> getList() {
        if(null==list)
        {
            list = new ArrayList<>();
        }
        return list;
    }

    public String returnPngName(int id) {
        String pngName = "";
        if (null != list) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getId() == id) {
                    pngName = list.get(i).getPngName();
                }
            }
        }
        return pngName;
    }

    private PngNameUtil() {
    }

    static PngNameUtil instance;

    public static PngNameUtil getInstance() {
        if (instance == null) {
            synchronized (PngNameUtil.class) {
                if (instance == null) {
                    instance = new PngNameUtil();
                }
            }
        }
        return instance;
    }

    public void init(Context context) {
        String json = FileUtil.readAssets(context, "gift.json");
        Type listType = new TypeToken<ArrayList<GiftBean>>() {
        }.getType();
        list = new Gson().fromJson(json, listType);
    }

    public void getGiftList() {
        OkHttpUtils
                .post()
                .url(OkHttpUtils.GIFTLIST_URL)
                //.url("http://121.40.65.153:8080/gwcommon/virtualGift/queryPageByCondition.do")
                .addParams("currentPage", "1")
                .addParams("pageSize", "10")
                .build()
                .execute(new GenericsCallback<GiftListInfo>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("onError","");
                    }

                    @Override
                    public void onResponse(GiftListInfo response, int id) {
                        if (response.isSuccess()) {
                            Collections.sort(response.getBody().getList(), new Comparator<GiftInfo>() {
                                @Override
                                public int compare(GiftInfo lhs, GiftInfo rhs) {
                                    //按照学生的年龄进行升序排列
                                    if(Integer.parseInt(lhs.getId()) > Integer.parseInt(rhs.getId())){
                                        return 1;
                                    }
                                    if(Integer.parseInt(lhs.getId()) == Integer.parseInt(rhs.getId())){
                                        return 0;
                                    }
                                    return -1;
                                }
                            });
                            list = new ArrayList<>();
                            for (GiftInfo info:response.getBody().getList()) {
                                GiftBean bean = new GiftBean();
                                bean.setGiftName(info.getGiftName());
                                bean.setPngName(info.getPngName());
                                bean.setId(Integer.parseInt(info.getId()));
                                //判断大小礼物的状态
                                bean.setIsSend(info.getGiftType());
                                bean.setExperience(String.valueOf(info.getExpVal()));
                                bean.setMoney(String.valueOf(info.getCoin()));
                                bean.setGiftName(info.getGiftName());
                                bean.setPngName(info.getPngName());
                                list.add(bean);
                            }

                        } else {

                        }
                    }
                });
    }
}
