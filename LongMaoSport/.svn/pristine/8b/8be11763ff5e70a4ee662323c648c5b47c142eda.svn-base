package com.live.longmao.views;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.live.longmao.BaseApp;
import com.live.longmao.LocationManager;
import com.live.longmao.R;
import com.live.longmao.activity.ForgetPasswordActivity;
import com.live.longmao.activity.RegisterActivity;
import com.live.longmao.dlg.logindig.RegisterDialog;
import com.live.longmao.model.MySelfInfo;
import com.live.longmao.presenters.LoginHelper;
import com.live.longmao.presenters.viewinface.LoginView;
import com.live.longmao.util.MD5;
import com.live.okhttp.utils.L;
import com.umeng.analytics.MobclickAgent;

import java.lang.reflect.Type;

/**
 * 登录类
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener,LoginView {
    private static final String TAG = LoginActivity.class.getSimpleName();
    Button mBtnLogin;
    TextView mTvRegister, mTvForgetPassword;
    EditText mPassWord, mUserName;
    private ImageView iv_eyes, iv_clear, iv_password_clear;
    private boolean is_eyes = true;
    private LoginHelper mLoginHeloper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoginHeloper = new LoginHelper(this, this);
        //获取个人数据本地缓存
        MySelfInfo.getInstance().getCache(getApplicationContext());
        setContentView(R.layout.activity_login);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mUserName = (EditText) findViewById(R.id.et_phone);
        mPassWord = (EditText) findViewById(R.id.et_password);
        mTvRegister = (TextView) findViewById(R.id.tv_register);
        mTvForgetPassword = (TextView) findViewById(R.id.tv_forget_password);
        iv_eyes = (ImageView) findViewById(R.id.iv_eyes);
        iv_clear = (ImageView) findViewById(R.id.iv_clear);
        iv_password_clear = (ImageView) findViewById(R.id.iv_password_clear);
        mTvRegister.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        mTvRegister.setOnClickListener(this);
        mBtnLogin.setOnClickListener(this);
        mTvForgetPassword.setOnClickListener(this);
        iv_eyes.setOnClickListener(this);


        mUserName.setFocusable(true);
        mUserName.setFocusableInTouchMode(true);
        mUserName.requestFocus();
        mUserName.requestFocusFromTouch();

        mUserName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    iv_clear.setVisibility(View.VISIBLE);
                    iv_password_clear.setVisibility(View.GONE);
                } else {
                    iv_clear.setVisibility(View.GONE);
                    iv_password_clear.setVisibility(View.VISIBLE);
                }
            }
        });

        iv_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserName.setText("");
            }
        });

        iv_password_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPassWord.setText("");
            }
        });

    }

    @Override
    protected void onDestroy() {
        mLoginHeloper.onDestory();
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btn_login) {
            if (isEmptyView()) {
                mLoginHeloper.getLogin(mUserName.getText().toString().trim(), MD5.md5(mPassWord.getText().toString().trim()));
                MobclickAgent.onProfileSignIn("userID");
                Log.i("登录", MD5.md5(mPassWord.getText().toString().trim()));
            }
        }

        switch (view.getId()) {
            case R.id.tv_forget_password:
                Intent intent1 = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
                intent1.putExtra("var", "forget");
                startActivity(intent1);
                finish();
                break;
            case R.id.tv_register:
                Intent intent2 = new Intent(LoginActivity.this, RegisterActivity.class);
                intent2.putExtra("var", "register");
                startActivity(intent2);
                finish();
                break;
            case R.id.iv_eyes:
                if (!is_eyes) {
                    is_eyes = true;
                    iv_eyes.setImageResource(R.mipmap.icon_login_eyes_un);
                    mPassWord.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    mPassWord.setSelection(mPassWord.getText().length());
                } else {
                    is_eyes = false;
                    iv_eyes.setImageResource(R.mipmap.icon_login_eyes_on);
                    mPassWord.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    mPassWord.setSelection(mPassWord.getText().length());
                }
                break;
        }

    }

    private boolean isEmptyView() {
        if (!TextUtils.isEmpty(mUserName.getText().toString().trim()) && !TextUtils.isEmpty(mPassWord.getText().toString().trim())) {
            return true;
        }
        Toast.makeText(this, "请完善内容", Toast.LENGTH_SHORT).show();
        return false;
    }

    /**
     * 直接跳转主界面
     */
    private void jumpIntoHomeActivity() {
        //initLocation();
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void loginSucc() {
        jumpIntoHomeActivity();
    }

    private RegisterDialog registerDialog;

    @Override
    public void loginFail(final String msg) {
        registerDialog = new RegisterDialog();
        registerDialog.show(getFragmentManager(), "");
        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                registerDialog.setTextTittle(msg);
            }
        }, 10);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                registerDialog.dismiss();
            }
        }, 2000);

    }

}
