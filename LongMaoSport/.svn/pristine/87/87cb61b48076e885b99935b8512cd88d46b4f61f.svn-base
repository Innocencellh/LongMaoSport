package com.live.longmao.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.live.longmao.R;
import com.live.longmao.views.BaseActivity;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by HPDN on 2016/7/9.
 */
public class  AttestationActivity extends BaseActivity implements View.OnClickListener {

    private EditText name, identity_card, phone;
    private String use_name, use_identity, use_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_attestation);
        setTitle("申请认证");
        //setRigthImg(R.mipmap.search);
        init();
    }

    private void init() {
        name = (EditText) findViewById(R.id.name);
        identity_card = (EditText) findViewById(R.id.identity_card);
        phone = (EditText) findViewById(R.id.phone);
        findViewById(R.id.succeed).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.succeed:
                use_name = name.getText().toString().trim();
                use_identity = identity_card.getText().toString().trim();
                use_phone = phone.getText().toString().trim();
                if (TextUtils.isEmpty(use_name)){
                    Toast.makeText(AttestationActivity.this, "姓名不能为空", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(use_identity)){
                    Toast.makeText(AttestationActivity.this, "身份证号不能为空", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(use_phone)){
                    Toast.makeText(AttestationActivity.this, "手机号码不能为空", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(AttestationActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("个人中心申请认证界面");
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("个人中心申请认证界面");
        MobclickAgent.onPause(this);
    }

}
