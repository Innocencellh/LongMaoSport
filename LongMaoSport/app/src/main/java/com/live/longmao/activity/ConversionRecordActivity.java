package com.live.longmao.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.live.longmao.R;
import com.live.longmao.adapter.ConversionRecordAdapter;
import com.live.longmao.views.BaseActivity;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by HPDN on 2016/7/12.
 */
public class ConversionRecordActivity extends BaseActivity implements View.OnClickListener{

    private ListView listView;
    private ConversionRecordAdapter conversionRecordAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_conversion_record);
        setTitle("兑换记录");

        init();

    }

    private void init() {
        listView = (ListView) findViewById(R.id.conversion_record_lv);
        listView.setDividerHeight(0);
        listView.setDivider(null);

        conversionRecordAdapter = new ConversionRecordAdapter();
        listView.setAdapter(conversionRecordAdapter);
        listView.setSelection(conversionRecordAdapter.getCount());
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
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
