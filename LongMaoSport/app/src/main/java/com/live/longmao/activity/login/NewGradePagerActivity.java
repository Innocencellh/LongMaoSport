package com.live.longmao.activity.login;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.VideoView;

import com.live.longmao.R;
import com.live.longmao.model.MySelfInfo;
import com.live.longmao.presenters.LoginHelper;
import com.live.longmao.presenters.viewinface.LoginView;
import com.live.longmao.views.BaseActivity;
import com.live.longmao.views.HomeActivity;
import com.live.longmao.views.LoginActivity;

/**
 * Created by HPDN on 2017/2/15.
 */
public class NewGradePagerActivity extends BaseActivity implements LoginView {

    private VideoView videoView;
    private LinearLayout ll_phone;
    private LoginHelper mLoginHeloper;

    private static final int GO_HOME = 1000;
    private static final int GO_GUIDE = 1001;
    // 延迟2秒
    private static final long SPLASH_DELAY_MILLIS = 500;


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

                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_grade_pager);
        mLoginHeloper = new LoginHelper(this, this);
        mHandler.sendEmptyMessageDelayed(GO_HOME, SPLASH_DELAY_MILLIS);
        videoView = (VideoView) findViewById(R.id.video_view);
        ll_phone = (LinearLayout) findViewById(R.id.ll_phone);
        initfed();
        ll_phone.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.ll_phone:
                startActivity(new Intent(NewGradePagerActivity.this,NewLoginActivity.class));
                finish();
                break;
        }
    }

    private void initfed() {

        videoView.setVideoURI(Uri.parse("android.resource://com.live.longmao/" + R.raw.koulan));
        videoView.start();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
                mp.setLooping(true);
            }
        });

        videoView
                .setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        videoView.setVideoURI(Uri.parse("android.resource://com.live.longmao/" + R.raw.koulan));
                        videoView.start();
                    }
                });

    }

    private void goHome() {
        if (needLogin() == true) {
//            Intent intent = new Intent(NewGradePagerActivity.this, LoginActivity.class);
//            startActivity(intent);
//            finish();
        } else {
            //有账户登录直接IM登录
            mLoginHeloper.imLogin(MySelfInfo.getInstance().getId(), MySelfInfo.getInstance().getUserSig());
        }
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
        Intent intent = new Intent(NewGradePagerActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void loginFail(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
