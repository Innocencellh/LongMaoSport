package com.live.longmao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.live.longmao.R;
import com.live.longmao.model.MySelfInfo;
import com.live.longmao.presenters.LoginHelper;
import com.live.longmao.presenters.viewinface.LoginView;
import com.live.longmao.util.GlideUtil;
import com.live.longmao.views.BaseActivity;
import com.live.longmao.views.HomeActivity;
import com.live.longmao.views.LoginActivity;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by Administrator on 2016/8/2.
 */
public class SplashActivity extends BaseActivity implements LoginView, View.OnClickListener {

    private static final int GO_HOME = 1000;
    private static final int GO_GUIDE = 1001;
    private LoginHelper mLoginHeloper;
    // 延迟2秒
    private static final long SPLASH_DELAY_MILLIS = 2000;

    /**
     * Handler:跳转到不同界面
     */
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GO_HOME:
                    goHome();
                    break;
                case GO_GUIDE:
                    goGuide();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mLoginHeloper = new LoginHelper(this, this);
        mHandler.sendEmptyMessageDelayed(GO_HOME, SPLASH_DELAY_MILLIS);
        MobclickAgent.openActivityDurationTrack(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("欢迎界面");
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        MobclickAgent.onPageEnd("欢迎界面");
        super.onPause();
        MobclickAgent.onPause(this);
    }

    private void goHome() {
        if (needLogin() == true) {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            //有账户登录直接IM登录
            mLoginHeloper.imLogin(MySelfInfo.getInstance().getId(), MySelfInfo.getInstance().getUserSig());
        }
    }

    private void goGuide() {

    }
    /**
     * 判断是否需要登录
     * @return true 代表需要重新登录
     */
    public boolean needLogin() {
        if (MySelfInfo.getInstance().getId() != null) {
            return false;//有账号不需要登录
        } else {
            return true;//需要登录
        }
    }

    @Override
    public void loginSucc() {
        Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void loginFail(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
