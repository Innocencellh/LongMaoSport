package com.live.longmao.views;


import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.live.longmao.BaseApp;
import com.live.longmao.R;
import com.live.longmao.activity.ExchangeCentreActivity;
import com.live.longmao.adapter.FindActiviyAdapter;
import com.live.longmao.adapter.FindAdapter;
import com.live.longmao.dlg.AlertDialog;
import com.live.longmao.find.FindSecondActivity;
import com.live.longmao.model.BInfo;
import com.live.longmao.model.BeanInfo;
import com.live.longmao.model.LiveInfo;
import com.live.longmao.model.MySelfInfo;
import com.live.longmao.presenters.BeanHelper;
import com.live.longmao.presenters.viewinface.BeanView;
import com.live.longmao.util.ChangeHeight;
import com.live.longmao.util.GlideUtil;
import com.live.longmao.view.BottomScrollView;
import com.live.longmao.view.GridViewForScrollView;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;


/**
 * 活动页面
 */
public class FragmentPublish extends Fragment implements View.OnClickListener, BeanView {
    private static final String TAG = "FragmentPublish";
    private View v;
    private ConvenientBanner banner;
    View headerView;
    private List<String> urlList = null;
    private TextView activitytitle, tv_bean;
    RelativeLayout rl_near;
    private LinearLayout ll_tell;
    private BeanHelper beanHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (v == null) {
            //   v = inflater.inflate(R.layout.fragment_publishfragment, container, false);
            v = inflater.inflate(R.layout.fragment_publish_text, container, false);
            initBanner();
            initInfo();
            initBackDialog();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                ChangeHeight.changeLH(getContext(), activitytitle);
            }
        }
        ViewGroup parent = (ViewGroup) v.getParent();
        if (parent != null) {
            parent.removeView(v);
        }
        return v;
    }


    private void initInfo() {
        //   more = (TextView) v.findViewById(R.id.more);
        beanHelper = new BeanHelper(this);
        activitytitle = (TextView) v.findViewById(R.id.activitytitle);
        tv_bean = (TextView) v.findViewById(R.id.tv_bean);
        ll_tell = (LinearLayout) v.findViewById(R.id.ll_tell);
        ll_tell.setOnClickListener(this);
        //    more.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        beanHelper.getBean(MySelfInfo.getInstance().getId());//获取龙猫豆
    }

    private void initBanner() {
        //  headerView = v.findViewById(R.id.include_banner);
        banner = (ConvenientBanner) v.findViewById(R.id.banner);
        //    rl_near = (RelativeLayout) v.findViewById(R.id.rl_near);
        //   rl_near.setVisibility(View.GONE);
        urlList = new ArrayList<>();
        urlList.add("http://www.18183.com/uploads/140115/21-140115192239125.jpg");
        urlList.add("http://upload.shunwang.com/2015/1224/1450927480194.jpg");
        urlList.add("http://img5.duitang.com/uploads/blog/201405/12/20140512161725_J2zBw.jpeg");
        urlList.add("http://pic.baike.soso.com/p/20131221/20131221161155-597537905.jpg");
        urlList.add("http://g.hiphotos.baidu.com/zhidao/pic/item/b17eca8065380cd7d708b425a144ad34588281e7.jpg");
        banner.setPages(new CBViewHolderCreator<LocalImageHolderView>() {
            @Override
            public LocalImageHolderView createHolder() {
                return new LocalImageHolderView();
            }
        }, urlList);
        //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
        banner.setPageIndicator(new int[]{R.mipmap.white_dot, R.mipmap.blue_dot})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
        banner.startTurning(4000);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //      case R.id.more:
            //         startActivity(new Intent(getActivity(), ExchangeCentreActivity.class));
            //           break;
            case R.id.ll_tell:
                Toast.makeText(getContext(), "兑换请联系客服", Toast.LENGTH_SHORT).show();
                backDlg.show();
                break;
        }
    }


    private AlertDialog backDlg;

    private void initBackDialog() {
        backDlg = new AlertDialog(getContext()).builder();
        backDlg.setTitle("客服QQ:1918368406");
        backDlg.setPositiveButton("确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
//        backDlg.setNegativeButton("取消", new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }


    public class LocalImageHolderView implements Holder<String> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, final int position, final String data) {
            GlideUtil.getInstance().load(context, imageView, data);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e(TAG, "OnClickListener" + position);
                }
            });
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("发现界面");
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("发现界面");
    }

    @Override
    public void onBeanSucc(BeanInfo result) {
        if (null != result && null != result.getBody()) {
            ArrayList<BInfo> body = result.getBody();
            BInfo bInfo = body.get(0);//取第一个
            Log.i("BeanActivity", bInfo.getLmBeanNum() + "");
            tv_bean.setText(String.valueOf(bInfo.getLmBeanNum()));
        }
    }

    @Override
    public void onBeanError(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

}
