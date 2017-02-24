package com.live.longmao.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.live.longmao.BaseApp;
import com.live.longmao.R;
import com.live.longmao.dlg.AlertDialog;
import com.live.longmao.dlg.VipDialog;
import com.live.longmao.model.MySelfInfo;
import com.live.longmao.views.BaseActivity;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by HPDN on 2016/10/10.
 */
public class MemberActivity extends BaseActivity {

    private TextView tv_renew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_member);
        //未开通会员的普通用户界面
        // setView(R.layout.activity_vip_member);
        setTitle("会员");
        initBackDialog();
        initInfo();
    }

    private void initInfo() {
        tv_renew = (TextView) findViewById(R.id.tv_renew);

        tv_renew.setOnClickListener(this);
    }

    private VipDialog backDlg;

    private void initBackDialog() {
        backDlg = new VipDialog(this).builder();
        backDlg.setTitle("99999元/年");
        backDlg.setPositiveButton("联系我们", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        backDlg.setNegativeButton("我在想想", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_renew:
                backDlg.show();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("个人中心会员查看界面");
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("个人中心会员查看界面");
        MobclickAgent.onPause(this);
    }

}
