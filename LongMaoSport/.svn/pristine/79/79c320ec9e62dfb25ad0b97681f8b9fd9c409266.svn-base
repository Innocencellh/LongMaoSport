package com.live.longmao.fragment;

import android.content.Intent;
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

import com.live.longmao.model.CInfo;
import com.live.longmao.model.CoinInfo;
import com.live.longmao.model.CointoBeanInfo;
import com.live.longmao.model.CtoBInfo;
import com.live.longmao.model.MySelfInfo;

import com.live.longmao.presenters.CoinHelper;
import com.live.longmao.presenters.CoinToBeanHelper;

import com.live.longmao.presenters.viewinface.CoinToBeanView;
import com.live.longmao.presenters.viewinface.CoinView;
import com.live.longmao.util.DlgTellUtil;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HPDN on 2016/7/9.
 */
public class BeanFragment extends Fragment implements View.OnClickListener,CoinToBeanView,CoinView {
    private TextView tv_bean, tv_b1, tv_b2, tv_b3, tv_b4, tv_b5, tv_b6, tv_b7, tv_b8;
    private CoinToBeanHelper coinToBeanHelper;
    private CoinHelper coinHelper;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_bean, null);
            initView();
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        return view;
    }

    private void initView() {
        coinToBeanHelper = new CoinToBeanHelper(this);
        coinHelper = new CoinHelper(this);
        tv_bean = (TextView) view.findViewById(R.id.tv_bean);
        tv_b1 = (TextView) view.findViewById(R.id.tv_b1);
        tv_b2 = (TextView) view.findViewById(R.id.tv_b2);
        tv_b3 = (TextView) view.findViewById(R.id.tv_b3);
        tv_b4 = (TextView) view.findViewById(R.id.tv_b4);
        tv_b5 = (TextView) view.findViewById(R.id.tv_b5);
        tv_b6 = (TextView) view.findViewById(R.id.tv_b6);
        tv_b7 = (TextView) view.findViewById(R.id.tv_b7);
        tv_b8 = (TextView) view.findViewById(R.id.tv_b8);

        tv_b1.setOnClickListener(this);
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
        coinHelper.getCoin(MySelfInfo.getInstance().getId());
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_b1:
                coinToBeanHelper.getCoinToBean(MySelfInfo.getInstance().getId(), String.valueOf(100));
                break;
            case R.id.tv_b2:
                coinToBeanHelper.getCoinToBean(MySelfInfo.getInstance().getId(), String.valueOf(1000));
                break;
            case R.id.tv_b3:
                coinToBeanHelper.getCoinToBean(MySelfInfo.getInstance().getId(), String.valueOf(3000));
                break;
            case R.id.tv_b4:
                coinToBeanHelper.getCoinToBean(MySelfInfo.getInstance().getId(), String.valueOf(5000));
                break;
            case R.id.tv_b5:
                coinToBeanHelper.getCoinToBean(MySelfInfo.getInstance().getId(), String.valueOf(10000));
                break;
            case R.id.tv_b6:
                coinToBeanHelper.getCoinToBean(MySelfInfo.getInstance().getId(), String.valueOf(50000));
                break;
            case R.id.tv_b7:
                coinToBeanHelper.getCoinToBean(MySelfInfo.getInstance().getId(), String.valueOf(100000));
                break;
            case R.id.tv_b8:
                coinToBeanHelper.getCoinToBean(MySelfInfo.getInstance().getId(), String.valueOf(1000000));
                break;
        }
    }

    @Override
    public void onCoinToBeanSucc(CointoBeanInfo result) {
        Toast.makeText(getActivity(),"兑换成功",Toast.LENGTH_SHORT).show();
        if (null != result && null != result.getBody()) {
            ArrayList<CtoBInfo> body = (ArrayList<CtoBInfo>) result.getBody();
            CtoBInfo ctoBInfo = body.get(0);//取第一个
            Log.i("BeanActivity", ctoBInfo.getLmCoinNum() + "");
            tv_bean.setText(String.valueOf(ctoBInfo.getLmCoinNum()));
        }

    }

    @Override
    public void onCtoBError(String msg) {
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCoinSucc(CoinInfo result) {
        if (null != result && null != result.getBody()) {
            ArrayList<CInfo> body = (ArrayList<CInfo>) result.getBody();
            CInfo cInfo = body.get(0);//取第一个
            Log.i("BeanActivity", cInfo.getLmCoinNum() + "");
            tv_bean.setText(String.valueOf(cInfo.getLmCoinNum()));
        }
    }

    @Override
    public void onError(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("BeanFragment");
        coinHelper.getCoin(MySelfInfo.getInstance().getId());
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("BeanFragment");
    }


}
