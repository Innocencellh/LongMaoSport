package com.live.longmao.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.live.longmao.R;
import com.live.longmao.model.BInfo;
import com.live.longmao.model.BeanInfo;
import com.live.longmao.model.MySelfInfo;
import com.live.longmao.presenters.BeanHelper;
import com.live.longmao.presenters.BeanToCoinHelper;
import com.live.longmao.presenters.viewinface.BeanToCoinView;
import com.live.longmao.presenters.viewinface.BeanView;
import com.live.longmao.views.BaseActivity;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/9/28 0028.
 */
public class ExchangeLmbActivity extends BaseActivity implements BeanView, BeanToCoinView, View.OnClickListener {
    private TextView tv_bean, tv_b1, tv_b2, tv_b3, tv_b4, tv_b5, tv_b6, tv_b7, tv_b8;
    private BeanHelper beanHelper;
    private BeanToCoinHelper beanToCoinHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_bean_to_coin);
        setTitle("兑换龙猫币");
        initViews();
    }

    private void initViews() {
        beanHelper = new BeanHelper(this);
        beanToCoinHelper = new BeanToCoinHelper(this);

        tv_bean = (TextView) findViewById(R.id.tv_bean);
        tv_b1 = (TextView) findViewById(R.id.tv_b1);
        tv_b2 = (TextView) findViewById(R.id.tv_b2);
        tv_b3 = (TextView) findViewById(R.id.tv_b3);
        tv_b4 = (TextView) findViewById(R.id.tv_b4);
        tv_b5 = (TextView) findViewById(R.id.tv_b5);
        tv_b6 = (TextView) findViewById(R.id.tv_b6);
        tv_b7 = (TextView) findViewById(R.id.tv_b7);
        tv_b8 = (TextView) findViewById(R.id.tv_b8);

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
    protected void onStart() {
        super.onStart();
        beanHelper.getBean(MySelfInfo.getInstance().getId());
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_b1:
                beanToCoinHelper.getData(MySelfInfo.getInstance().getId(), String.valueOf(100));
                break;
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
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBeanToCoinSucc(BeanInfo result) {
        Toast.makeText(this, "兑换成功", Toast.LENGTH_SHORT).show();
        beanHelper.getBean(MySelfInfo.getInstance().getId());
    }

    @Override
    public void onBeanToCoinError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("ExchangeLmbActivity");
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("ExchangeLmbActivity");
        MobclickAgent.onPause(this);
    }
}
