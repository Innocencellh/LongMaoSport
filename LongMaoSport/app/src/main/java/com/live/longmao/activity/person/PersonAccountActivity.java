package com.live.longmao.activity.person;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.live.longmao.R;
import com.live.longmao.activity.PersonCoinActivity;
import com.live.longmao.activity.RechargeActivity;
import com.live.longmao.model.BInfo;
import com.live.longmao.model.BeanInfo;
import com.live.longmao.model.CInfo;
import com.live.longmao.model.CoinInfo;
import com.live.longmao.model.MySelfInfo;
import com.live.longmao.presenters.BeanHelper;
import com.live.longmao.presenters.CoinHelper;
import com.live.longmao.presenters.viewinface.BeanView;
import com.live.longmao.presenters.viewinface.CoinView;
import com.live.longmao.views.BaseActivity;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/9 0009.
 */
public class PersonAccountActivity extends BaseActivity implements CoinView, View.OnClickListener, BeanView {
    private TextView phone, coinAccount, beanCount;
    private CoinHelper coinHelper;
    private Button btn_recharge, btn_exchange;
    private BeanHelper beanHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("账户充值");
        setView(R.layout.activity_reaccount);

/*
        phone = (TextView) findViewById(R.id.phone);
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DlgTellUtil.startDlgCall(AccountActivity.this,"18164017508");
            }
        });
        */
        setLineGone();
        initViews();
    }

    @Override
    protected void onStart() {
        super.onStart();
        coinHelper.getCoin(MySelfInfo.getInstance().getId());
        beanHelper.getBean(MySelfInfo.getInstance().getId());//获取龙猫豆
    }

    private void initViews() {
        coinHelper = new CoinHelper(this);
        beanHelper = new BeanHelper(this);
        coinHelper.getCoin(MySelfInfo.getInstance().getId());
        beanHelper.getBean(MySelfInfo.getInstance().getId());//获取龙猫豆

        coinAccount = (TextView) findViewById(R.id.tv_lm_coin);
        btn_recharge = (Button) findViewById(R.id.btn_recharge);
        btn_exchange = (Button) findViewById(R.id.btn_exchange);
        beanCount = (TextView) findViewById(R.id.tv_lm_bean);

        btn_recharge.setOnClickListener(this);
        btn_exchange.setOnClickListener(this);

    }

    @Override
    public void onCoinSucc(CoinInfo result) {
        if (null != result && null != result.getBody()) {
            List<CInfo> body = result.getBody();
            CInfo cInfo = body.get(0);
            coinAccount.setText(String.valueOf(cInfo.getLmCoinNum()));
        }else {
            coinAccount.setText("0");
        }

    }

    @Override
    public void onError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_recharge:
                startActivity(new Intent(this, PersonRechargeActivity.class));
                break;
            case R.id.btn_exchange:
                startActivity(new Intent(this, ExchangeLongMaoDouActivity.class));
                break;
        }
    }

    @Override
    public void onBeanSucc(BeanInfo result) {
        if (null != result && null != result.getBody()) {
            ArrayList<BInfo> body = result.getBody();
            BInfo bInfo = body.get(0);//取第一个
            Log.i("BeanActivity", bInfo.getLmBeanNum() + "");
            beanCount.setText(String.valueOf(bInfo.getLmBeanNum()));
        }else {
            beanCount.setText("0");
        }
    }

    @Override
    public void onBeanError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("个人中心账户查看界面");
        MobclickAgent.onResume(this);
        coinHelper.getCoin(MySelfInfo.getInstance().getId());
        beanHelper.getBean(MySelfInfo.getInstance().getId());

    }

    @Override
    protected void onPause() {
        MobclickAgent.onPageEnd("个人中心账户查看界面");
        super.onPause();
        MobclickAgent.onPause(this);
    }

}
