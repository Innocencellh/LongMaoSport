package com.live.longmao.activity.login;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.live.longmao.R;
import com.live.longmao.activity.ForgetPasswordActivity;
import com.live.longmao.activity.RegisterActivity;
import com.live.longmao.dlg.logindig.RegisterDialog;
import com.live.longmao.model.MySelfInfo;
import com.live.longmao.presenters.LoginHelper;
import com.live.longmao.presenters.viewinface.LoginView;
import com.live.longmao.util.ChangeHeight;
import com.live.longmao.util.MD5;
import com.live.longmao.views.BaseActivity;
import com.live.longmao.views.HomeActivity;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by HPDN on 2017/2/15.
 */
public class NewLoginActivity extends AppCompatActivity implements LoginView, View.OnClickListener {

    private Button mBtnLogin, mNoBtnLogin;
    private TextView mTvForgetPassword, tv_back,tv_code;
    private EditText mPassWord, mUserName;
    private RelativeLayout rl_tittle;
    private ImageView iv_eyes, iv_clear, iv_password_clear;
    private boolean is_eyes = true;
    private LoginHelper mLoginHeloper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        mLoginHeloper = new LoginHelper(this, this);
        //获取个人数据本地缓存
        MySelfInfo.getInstance().getCache(getApplicationContext());
        setContentView(R.layout.activity_new_login);

        mNoBtnLogin = (Button) findViewById(R.id.btn_no_login);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mUserName = (EditText) findViewById(R.id.et_phone);
        mPassWord = (EditText) findViewById(R.id.et_password);
        mTvForgetPassword = (TextView) findViewById(R.id.tv_forget_password);
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_code = (TextView) findViewById(R.id.tv_code);
        iv_eyes = (ImageView) findViewById(R.id.iv_eyes);
        iv_clear = (ImageView) findViewById(R.id.iv_clear);
        iv_password_clear = (ImageView) findViewById(R.id.iv_password_clear);
        rl_tittle = (RelativeLayout) findViewById(R.id.rl_tittle);
        mBtnLogin.setOnClickListener(this);
        mTvForgetPassword.setOnClickListener(this);
        iv_eyes.setOnClickListener(this);
        tv_code.setOnClickListener(this);
        tv_back.setOnClickListener(this);

        mUserName.addTextChangedListener(PhoneWatcher);
        mPassWord.addTextChangedListener(PasswordWatcher);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ChangeHeight.changeRH(this, rl_tittle);
        }

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
            //忘记密码
            case R.id.tv_forget_password:
                Intent intent1 = new Intent(NewLoginActivity.this, ForgetPasswordActivity.class);
                intent1.putExtra("var", "forget");
                startActivity(intent1);
                finish();
                break;
            case R.id.tv_back:
                startActivity(new Intent(NewLoginActivity.this,NewGradePagerActivity.class));
                this.finish();
                break;
            //验证码登录
            case R.id.tv_code:
                Intent intent2 = new Intent(NewLoginActivity.this, RegisterActivity.class);
                intent2.putExtra("var", "register");
                startActivity(intent2);
                finish();
                break;
            case R.id.iv_eyes:
                if (!is_eyes) {
                    is_eyes = true;
                    iv_eyes.setImageResource(R.mipmap.icon_eyes_no_register);
                    mPassWord.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    mPassWord.setSelection(mPassWord.getText().length());
                } else {
                    is_eyes = false;
                    iv_eyes.setImageResource(R.mipmap.icon_eyes_yes_register);
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
        Intent intent = new Intent(NewLoginActivity.this, HomeActivity.class);
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
                registerDialog.setTextTittle("msg");
            }
        }, 10);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                registerDialog.dismiss();
            }
        }, 2000);

    }

    private TextWatcher PhoneWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            if (mUserName.length() != 0 && mPassWord.length() != 0 && mUserName.length() == 11 && mPassWord.length() == 6) {
                mBtnLogin.setClickable(true);
                mBtnLogin.setVisibility(View.VISIBLE);
                mNoBtnLogin.setVisibility(View.INVISIBLE);
            } else if (mUserName.length() == 0 || mPassWord.length() == 0 || mUserName.length() != 11 || mPassWord.length() != 6) {
                mBtnLogin.setClickable(false);
                mBtnLogin.setVisibility(View.INVISIBLE);
                mNoBtnLogin.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private TextWatcher PasswordWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            if (mUserName.length() != 0 && mPassWord.length() != 0 && mUserName.length() == 11 && mPassWord.length() == 6) {
                mBtnLogin.setClickable(true);
                mBtnLogin.setVisibility(View.VISIBLE);
                mNoBtnLogin.setVisibility(View.INVISIBLE);

            } else if (mUserName.length() == 0 || mPassWord.length() == 0 || mUserName.length() != 11 || mPassWord.length() != 6) {
                mBtnLogin.setClickable(false);
                mBtnLogin.setVisibility(View.INVISIBLE);
                mNoBtnLogin.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


}
