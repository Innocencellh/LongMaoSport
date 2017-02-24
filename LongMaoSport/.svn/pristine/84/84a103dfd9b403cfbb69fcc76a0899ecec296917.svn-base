package com.live.longmao.activity;

import android.os.Bundle;
import android.view.View;

import com.live.longmao.R;
import com.live.longmao.views.BaseActivity;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by Administrator on 2016/9/28 0028.
 */
public class BindingZfbActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_binding_zfb);
        setTitle("绑定支付宝");
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
