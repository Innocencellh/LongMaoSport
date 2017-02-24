package com.live.longmao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.live.longmao.BaseApp;
import com.live.longmao.R;
import com.live.longmao.dlg.AlertDialog;
//import com.live.longmao.html.TestView;
import com.live.longmao.model.MySelfInfo;
import com.live.longmao.presenters.LoginHelper;
import com.live.longmao.presenters.viewinface.LogoutView;
import com.live.longmao.util.DlgTellUtil;
import com.live.longmao.util.ScreenManager;
import com.live.longmao.views.BaseActivity;
import com.live.longmao.views.LoginActivity;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by Administrator on 2016/7/10 0010.
 */
public class SettingActivity extends BaseActivity implements LogoutView {
    private Button btnBack;
    private LoginHelper mLoginHeloper;
    private RelativeLayout settingBlacklist, settingAdress, settingTreaty, settingPolicy, setting_callus, setting_provision;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("更多");
        setView(R.layout.activity_setting);
        mLoginHeloper = new LoginHelper(this, this);
        init();
        initBackDialog();
    }

    private void init() {
        btnBack = (Button) findViewById(R.id.setting_back);
        settingBlacklist = (RelativeLayout) findViewById(R.id.setting_blacklist);
        settingAdress = (RelativeLayout) findViewById(R.id.setting_address);
        // settingTreaty = (RelativeLayout) findViewById(R.id.setting_treaty);
        // settingPolicy = (RelativeLayout) findViewById(R.id.setting_policy);
        setting_callus = (RelativeLayout) findViewById(R.id.setting_callus);
        setting_provision = (RelativeLayout) findViewById(R.id.setting_provision);
        setting_callus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  startActivity(new Intent(SettingActivity.this,CityActivity.class));
                DlgTellUtil.startDlgCall(SettingActivity.this, "18164017508");
            }
        });
       /* settingPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this, CheckInfoActivity.class));
            }
        });*/
      /*  settingTreaty.setOnClickListener(new View.OnClickListener() {//测试用的后期要换
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this, CheckStoreActivity.class));
            }
        });*/
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quiteLiveByPurpose();
            }
        });
        settingBlacklist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this, BlacklistActivity.class));
            }
        });
        settingAdress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this, AddAddressAtivity.class));
            }
        });
        setting_provision.setOnClickListener(this);
    }


    private void quiteLiveByPurpose() {
        backDlg.show();
    }


    private AlertDialog backDlg;

    private void initBackDialog() {
        backDlg = new AlertDialog(this).builder();
        backDlg.setTitle("确认退出登录?");
        backDlg.setPositiveButton("确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoginHeloper.imLogout();
            }
        });
        backDlg.setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.setting_provision:
//                startActivity(new Intent(SettingActivity.this, TestView.class));
//                finish();
                break;
        }
    }

    @Override
    public void logoutSucc() {
        ScreenManager.getScreenManager().popAllActivity();
    }

    @Override
    public void logoutFail() {

    }

    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("个人中心更多界面");
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("个人中心更多界面");
        MobclickAgent.onPause(this);
    }

}
