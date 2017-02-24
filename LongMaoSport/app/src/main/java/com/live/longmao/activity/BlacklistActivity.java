package com.live.longmao.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.live.longmao.R;
import com.live.longmao.adapter.PersonAttentionAdapter;
import com.live.longmao.adapter.PersonBlackAdapter;
import com.live.longmao.adapter.PersonFansAdapter;
import com.live.longmao.views.BaseActivity;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by Administrator on 2016/7/11 0011.
 */
public class BlacklistActivity extends BaseActivity {
    private ListView lv ;
    private PersonBlackAdapter personBlackAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_black);
        setTitle("黑名单");
        init();
    }

    private void init() {
        lv = (ListView) findViewById(R.id.blacklist_lv);
        personBlackAdapter = new PersonBlackAdapter();
        lv.setAdapter(personBlackAdapter);
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
