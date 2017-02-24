package com.live.longmao.presenters;

import android.util.Log;

import com.live.longmao.BaseApp;
import com.live.longmao.model.AttentionInfo;
import com.live.longmao.model.ChangeEntayInfo;
import com.live.longmao.model.MySelfInfo;
import com.live.longmao.presenters.viewinface.AttentionView;
import com.live.longmao.presenters.viewinface.ModificationMessageView;
import com.live.okhttp.JsonGenericsSerializator;
import com.live.okhttp.OkHttpUtils;
import com.live.okhttp.callback.GenericsCallback;

import okhttp3.Call;

/**
 * Created by HPDN on 2016/10/8.
 */
public class ModificationMessageHelper {

    private ModificationMessageView modificationMessageView;

    public ModificationMessageHelper(ModificationMessageView modificationMessageView) {
        this.modificationMessageView = modificationMessageView;
    }

    public void getAll(String city, String nickName, String sex, String signed) {
        OkHttpUtils
                .post()
                .url(OkHttpUtils.ModificationUser_URL)
                .addParams("userId", MySelfInfo.getInstance().getId() + "")
                .addParams("hobbies", city + "")
                .addParams("nickName", nickName + "")
                .addParams("sex", sex + "")
                .addParams("signed", signed + "")
                .addParams("id", BaseApp.getInstance().getLMId() + "")
                .build()
                .execute(new GenericsCallback<ChangeEntayInfo>(new JsonGenericsSerializator()) {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        modificationMessageView.onModificationMessageError("服务器未响应");
                    }

                    @Override
                    public void onResponse(ChangeEntayInfo response, int id) {
                        if (response.isSuccess()) {
                            modificationMessageView.onModificationMessageSucc(response);
                            Log.e("onResponse", String.valueOf(response));
                        } else {
                            modificationMessageView.onModificationMessageError(response.getErrMsg());
                        }
                    }
                });
    }


//    public void getNoNameAttention(String hobbies,String sex,String signed,String profession,String phoneId,String sports) {
//        OkHttpUtils
//                .post()
//                .url(OkHttpUtils.ModificationUser_URL)
//                .addParams("userId", MySelfInfo.getInstance().getId())
//                .addParams("hobbies",hobbies)
//                .addParams("sex", sex)
//                .addParams("signed", signed)
//                .addParams("profession", profession)
//                .addParams("phoneId", phoneId)
//                .addParams("sports", sports)
//                .addParams("id",BaseApp.getInstance().getLMId())
//                .build()
//                .execute(new GenericsCallback<ChangeEntayInfo>(new JsonGenericsSerializator()) {
//
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//                        modificationMessageView.onModificationMessageError("服务器未响应");
//                    }
//
//                    @Override
//                    public void onResponse(ChangeEntayInfo response, int id) {
//                        if (response.isSuccess()) {
//                            modificationMessageView.onModificationMessageSucc(response);
//                            Log.e("onResponse",String.valueOf(response));
//                        } else {
//                            modificationMessageView.onModificationMessageError(response.getErrMsg());
//                        }
//                    }
//                });
//    }

    //修改信息的请求
    public void getModifyMsg(String nickName, String sex, String signed) {
        OkHttpUtils
                .post()
                .url(OkHttpUtils.ModificationUser_URL)
                .addParams("userId", MySelfInfo.getInstance().getId())
                .addParams("nickName", nickName)
                .addParams("sex", sex)
                .addParams("signed", signed)
                .addParams("id", BaseApp.getInstance().getLMId())
                .build()
                .execute(new GenericsCallback<ChangeEntayInfo>(new JsonGenericsSerializator()) {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        modificationMessageView.onModificationMessageError("服务器未响应");
                    }

                    @Override
                    public void onResponse(ChangeEntayInfo response, int id) {
                        if (response.isSuccess()) {
                            modificationMessageView.onModificationMessageSucc(response);
                            Log.e("onResponse", String.valueOf(response));
                        } else {
                            modificationMessageView.onModificationMessageError(response.getErrMsg());
                        }
                    }
                });

    }


    public void getAttentionTwo(String hobbies, String nickName, String sex, String signed, String profession, String sports) {
        OkHttpUtils
                .post()
                .url(OkHttpUtils.ModificationUser_URL)
                .addParams("userId", MySelfInfo.getInstance().getId())
                .addParams("hobbies", hobbies)
                .addParams("nickName", nickName)
                .addParams("sex", sex)
                .addParams("signed", signed)
                .addParams("profession", profession)
                .addParams("sports", sports)
                .addParams("id", BaseApp.getInstance().getLMId())
                .build()
                .execute(new GenericsCallback<ChangeEntayInfo>(new JsonGenericsSerializator()) {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        modificationMessageView.onModificationMessageError("服务器未响应");
                    }

                    @Override
                    public void onResponse(ChangeEntayInfo response, int id) {
                        if (response.isSuccess()) {
                            modificationMessageView.onModificationMessageSucc(response);
                            Log.e("onResponse", String.valueOf(response));
                        } else {
                            modificationMessageView.onModificationMessageError(response.getErrMsg());
                        }
                    }
                });
    }


    public void getProfession(String profession) {
        OkHttpUtils
                .post()
                .url(OkHttpUtils.ModificationUser_URL)
                .addParams("userId", MySelfInfo.getInstance().getId())
                .addParams("profession", profession)
                .addParams("id", BaseApp.getInstance().getLMId())
                .build()
                .execute(new GenericsCallback<ChangeEntayInfo>(new JsonGenericsSerializator()) {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        modificationMessageView.onModificationMessageError("服务器未响应");
                    }

                    @Override
                    public void onResponse(ChangeEntayInfo response, int id) {
                        if (response.isSuccess()) {
                            modificationMessageView.onModificationMessageSucc(response);
                            Log.e("onResponse", String.valueOf(response));
                        } else {
                            modificationMessageView.onModificationMessageError(response.getErrMsg());
                        }
                    }
                });
    }


    public void getName(String hobbies) {
        OkHttpUtils
                .post()
                .url(OkHttpUtils.ModificationUser_URL)
                .addParams("userId", MySelfInfo.getInstance().getId())
                .addParams("hobbies", hobbies)
                .addParams("id", BaseApp.getInstance().getLMId())
                .build()
                .execute(new GenericsCallback<ChangeEntayInfo>(new JsonGenericsSerializator()) {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        modificationMessageView.onModificationMessageError("服务器未响应");
                    }

                    @Override
                    public void onResponse(ChangeEntayInfo response, int id) {
                        if (response.isSuccess()) {
                            modificationMessageView.onModificationMessageSucc(response);
                            Log.e("onResponse", String.valueOf(response));
                        } else {
                            modificationMessageView.onModificationMessageError(response.getErrMsg());
                        }
                    }
                });
    }


    public void getMyName(String nickName) {
        OkHttpUtils
                .post()
                .url(OkHttpUtils.ModificationUser_URL)
                .addParams("userId", MySelfInfo.getInstance().getId() + "")
                .addParams("nickName", nickName + "")
                .addParams("id", BaseApp.getInstance().getLMId() + "")
                .build()
                .execute(new GenericsCallback<ChangeEntayInfo>(new JsonGenericsSerializator()) {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        modificationMessageView.onModificationMessageError("服务器未响应");
                    }

                    @Override
                    public void onResponse(ChangeEntayInfo response, int id) {
                        if (response.isSuccess()) {
                            modificationMessageView.onModificationMessageSucc(response);
                            Log.e("onResponse", String.valueOf(response));
                        } else {
                            modificationMessageView.onModificationMessageError(response.getErrMsg());
                        }
                    }
                });
    }

    public void getSig(String sig) {
        OkHttpUtils
                .post()
                .url(OkHttpUtils.ModificationUser_URL)
                .addParams("userId", MySelfInfo.getInstance().getId())
                .addParams("signed", sig)
                .addParams("id", BaseApp.getInstance().getLMId())
                .build()
                .execute(new GenericsCallback<ChangeEntayInfo>(new JsonGenericsSerializator()) {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        modificationMessageView.onModificationMessageError("服务器未响应");
                    }

                    @Override
                    public void onResponse(ChangeEntayInfo response, int id) {
                        if (response.isSuccess()) {
                            modificationMessageView.onModificationMessageSucc(response);
                            Log.e("onResponse", String.valueOf(response));
                        } else {
                            modificationMessageView.onModificationMessageError(response.getErrMsg());
                        }
                    }
                });
    }


    public void getSex(String sex) {
        OkHttpUtils
                .post()
                .url(OkHttpUtils.ModificationUser_URL)
                .addParams("userId", MySelfInfo.getInstance().getId())
                .addParams("sex", sex)
                .addParams("id", BaseApp.getInstance().getLMId())
                .build()
                .execute(new GenericsCallback<ChangeEntayInfo>(new JsonGenericsSerializator()) {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        modificationMessageView.onModificationMessageError("服务器未响应");
                    }

                    @Override
                    public void onResponse(ChangeEntayInfo response, int id) {
                        if (response.isSuccess()) {
                            modificationMessageView.onModificationMessageSucc(response);
                            Log.e("onResponse", String.valueOf(response));
                        } else {
                            modificationMessageView.onModificationMessageError(response.getErrMsg());
                        }
                    }
                });
    }

    public void getPhoto(String photo) {
        OkHttpUtils
                .post()
                .url(OkHttpUtils.ModificationUser_URL)
                .addParams("userId", MySelfInfo.getInstance().getId() + "")
                .addParams("phoneId", photo + "")
                .addParams("id", BaseApp.getInstance().getLMId() + "")
                .build()
                .execute(new GenericsCallback<ChangeEntayInfo>(new JsonGenericsSerializator()) {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        modificationMessageView.onModificationMessageError("服务器未响应");
                    }

                    @Override
                    public void onResponse(ChangeEntayInfo response, int id) {
                        if (response.isSuccess()) {
                            modificationMessageView.onModificationMessageSucc(response);
                            Log.e("onResponse", String.valueOf(response));
                        } else {
                            modificationMessageView.onModificationMessageError(response.getErrMsg());
                        }
                    }
                });
    }

}
