package com.live.longmao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.live.longmao.R;
import com.live.longmao.adapter.ExchangeAdapter;
import com.live.longmao.find.FindSecondActivity;
import com.live.longmao.views.BaseActivity;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by HPDN on 2016/7/7.
 */
public class ExchangeActivity extends BaseActivity implements View.OnClickListener {
    private ExchangeAdapter exchangeAdapter;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.exchange_centre);
        setTitle("兑换中心");
        setRigthText("收货地址");
        findViewById(R.id.ll_conversion_record).setOnClickListener(this);
        findViewById(R.id.ll_num).setOnClickListener(this);
        listView = (ListView) findViewById(R.id.exchange_lv);
        exchangeAdapter = new ExchangeAdapter();
        listView.setAdapter(exchangeAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(ExchangeActivity.this, FindSecondActivity.class));
            }
        });
    }

    @Override
    protected void setRigthClick(View v) {
        startActivity(new Intent(this, AddAddressAtivity.class));
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.ll_conversion_record:
                startActivity(new Intent(ExchangeActivity.this, ConversionRecordActivity.class));
                break;
            case R.id.ll_num:
                startActivity(new Intent(ExchangeActivity.this, RechargeActivity.class));
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
