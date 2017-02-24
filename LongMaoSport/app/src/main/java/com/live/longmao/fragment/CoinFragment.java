package com.live.longmao.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.live.longmao.R;
import com.live.longmao.model.BInfo;
import com.live.longmao.model.BeanInfo;
import com.live.longmao.model.MySelfInfo;
import com.live.longmao.presenters.BeanHelper;
import com.live.longmao.presenters.BeanToCoinHelper;
import com.live.longmao.presenters.CoinToBeanHelper;
import com.live.longmao.presenters.viewinface.BeanToCoinView;
import com.live.longmao.presenters.viewinface.BeanView;
import com.live.longmao.util.DlgTellUtil;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;

/**
 * Created by HPDN on 2016/7/9.
 */
public class CoinFragment extends Fragment implements View.OnClickListener, BeanView, BeanToCoinView {
    private TextView tv_bean, tv_b1, tv_b2, tv_b3, tv_b4, tv_b5, tv_b6, tv_b7, tv_b8;
    private BeanHelper beanHelper;
    private BeanToCoinHelper beanToCoinHelper;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.activity_bean_to_coin, null);
            initView();
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        return view;
    }

    private void initView() {
        beanHelper = new BeanHelper(this);
        beanToCoinHelper = new BeanToCoinHelper(this);
        tv_bean = (TextView) view.findViewById(R.id.tv_bean);
//        tv_b1 = (TextView) view.findViewById(R.id.tv_b1);
        tv_b2 = (TextView) view.findViewById(R.id.tv_b2);
        tv_b3 = (TextView) view.findViewById(R.id.tv_b3);
        tv_b4 = (TextView) view.findViewById(R.id.tv_b4);
        tv_b5 = (TextView) view.findViewById(R.id.tv_b5);
        tv_b6 = (TextView) view.findViewById(R.id.tv_b6);
        tv_b7 = (TextView) view.findViewById(R.id.tv_b7);
        tv_b8 = (TextView) view.findViewById(R.id.tv_b8);

//        tv_b1.setOnClickListener(this);
        tv_b2.setOnClickListener(this);
        tv_b3.setOnClickListener(this);
        tv_b4.setOnClickListener(this);
        tv_b5.setOnClickListener(this);
        tv_b6.setOnClickListener(this);
        tv_b7.setOnClickListener(this);
        tv_b8.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        beanHelper.getBean(MySelfInfo.getInstance().getId());
    }

    @Override
    public void onBeanToCoinSucc(BeanInfo result) {
        Toast.makeText(getContext(), "兑换成功", Toast.LENGTH_SHORT).show();
        beanHelper.getBean(MySelfInfo.getInstance().getId());
    }

    @Override
    public void onBeanToCoinError(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBeanSucc(BeanInfo result) {
        if (null != result && null != result.getBody()) {
            ArrayList<BInfo> body = result.getBody();
            BInfo bInfo = body.get(0);//取第一个
            Log.i("BeanActivity", bInfo.getLmBeanNum() + "");
            tv_bean.setText(result.getBody().get(0).getLmBeanNum() + "");
        }
    }

    @Override
    public void onBeanError(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.tv_b1:
//                beanToCoinHelper.getData(MySelfInfo.getInstance().getId(), String.valueOf(100));
//                break;
            case R.id.tv_b2:
                beanToCoinHelper.getData(MySelfInfo.getInstance().getId(), String.valueOf(1000));
                break;
            case R.id.tv_b3:
                beanToCoinHelper.getData(MySelfInfo.getInstance().getId(), String.valueOf(3000));
                break;
            case R.id.tv_b4:
                beanToCoinHelper.getData(MySelfInfo.getInstance().getId(), String.valueOf(5000));
                break;
            case R.id.tv_b5:
                beanToCoinHelper.getData(MySelfInfo.getInstance().getId(), String.valueOf(10000));
                break;
            case R.id.tv_b6:
                beanToCoinHelper.getData(MySelfInfo.getInstance().getId(), String.valueOf(50000));
                break;
            case R.id.tv_b7:
                beanToCoinHelper.getData(MySelfInfo.getInstance().getId(), String.valueOf(100000));
                break;
            case R.id.tv_b8:
                beanToCoinHelper.getData(MySelfInfo.getInstance().getId(), String.valueOf(1000000));
                break;
        }
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("CoinFragment");
        beanHelper.getBean(MySelfInfo.getInstance().getId());
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("CoinFragment");
    }

}
