package com.live.longmao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.live.longmao.R;
import com.live.longmao.adapter.ExchangeCentreAdapter;
import com.live.longmao.views.BaseActivity;
import com.umeng.analytics.MobclickAgent;


/**
 * Created by HPDN on 2016/9/12.
 */
public class ExchangeCentreActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_exchange_centre);
        setTitle("兑换中心");
        setRigthText("收货地址");
        findViewById(R.id.ll_conversion_record).setOnClickListener(this);
        findViewById(R.id.ll_num).setOnClickListener(this);
        GridView gv = (GridView) findViewById(R.id.GridView1);
        //为GridView设置适配器
        gv.setAdapter(new ExchangeCentreAdapter(this));
        //注册监听事件
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    @Override
    protected void setRigthClick(View v) {
        super.setRigthClick(v);
        startActivity(new Intent(this, AddAddressAtivity.class));
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.ll_num:
                startActivity(new Intent(ExchangeCentreActivity.this, RechargeActivity.class));
                break;
            case R.id.ll_conversion_record:
                startActivity(new Intent(ExchangeCentreActivity.this, ConversionRecordActivity.class));
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("SplashScreen");
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        MobclickAgent.onPageEnd("SplashScreen");
        super.onPause();
        MobclickAgent.onPause(this);
    }

}
