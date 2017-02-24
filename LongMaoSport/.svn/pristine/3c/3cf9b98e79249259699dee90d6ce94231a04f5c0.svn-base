package com.live.longmao.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.live.longmao.R;
import com.live.longmao.activity.login.NewLoginActivity;
import com.live.longmao.dlg.logindig.RegisterDialog;
import com.live.longmao.model.CodeTimeInfo;
import com.live.longmao.model.ForgetInfo;
import com.live.longmao.model.ForgetPasswordInfo;
import com.live.longmao.model.ResListUserInfo;
import com.live.longmao.presenters.CodeTimeHelper;
import com.live.longmao.presenters.LoginHelper;
import com.live.longmao.presenters.RegisterHelper;
import com.live.longmao.presenters.viewinface.CodeTimeView;
import com.live.longmao.presenters.viewinface.LoginView;
import com.live.longmao.presenters.viewinface.RegisterView;
import com.live.longmao.service.MyService;
import com.live.longmao.util.ChangeHeight;
import com.live.longmao.util.MD5;
import com.live.longmao.views.BaseActivity;
import com.live.longmao.views.HomeActivity;
import com.live.longmao.views.LoginActivity;
import com.live.longmao.webview.UserViewActivity;

/**
 * Created by HPDN on 2016/8/18.
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener, RegisterView, CodeTimeView, LoginView {
    private RelativeLayout activity_base_title_rl, relativeLayout;
    private EditText et_phone, et_code, et_password;
    private Button btn_register, submit, btn_no_getCode, btn_no_register;
    private TextView mTvRegister, xieyi;
    private ImageButton iv_back;
    private ImageView iv_eyes, iv_clear, iv_password_clear;
    private RegisterHelper registerHelper;
    private CodeTimeHelper codeTimeHelper;
    private boolean is_eyes = true;

    private RegisterDialog registerDialog;

    private Handler mHandler;

    private LoginHelper mLoginHeloper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_register);

        mLoginHeloper = new LoginHelper(this, this);

        setLineGone();
        initView();
        if (null != getIntent()) {
            if (getIntent().getStringExtra("var").equals("register")) {
                setTitle("注册");
                btn_register.setText("注册");
                btn_no_register.setText("注册");
            } else {
                setTitle("忘记密码");
                btn_register.setText("完成");
                btn_no_register.setText("完成");
                mTvRegister.setVisibility(View.GONE);
                relativeLayout.setVisibility(View.GONE);
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ChangeHeight.changeRH(this, activity_base_title_rl);
        }
        filter();
    }

    private void initView() {
        activity_base_title_rl = (RelativeLayout) findViewById(R.id.activity_base_title_rl);
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_code = (EditText) findViewById(R.id.et_code);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_register = (Button) findViewById(R.id.btn_register);
        btn_no_register = (Button) findViewById(R.id.btn_no_register);
        submit = (Button) findViewById(R.id.btn_getCode);
        btn_no_getCode = (Button) findViewById(R.id.btn_no_getCode);
        mTvRegister = (TextView) findViewById(R.id.register_tv);
        mTvRegister.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        iv_back = (ImageButton) findViewById(R.id.iv_back);
        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        iv_eyes = (ImageView) findViewById(R.id.iv_eyes);
        iv_clear = (ImageView) findViewById(R.id.iv_clear);
        iv_password_clear = (ImageView) findViewById(R.id.iv_password_clear);
        xieyi = (TextView) findViewById(R.id.xieyi);
        xieyi.setOnClickListener(this);
        iv_eyes.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        mTvRegister.setOnClickListener(this);
        submit.setOnClickListener(this);
        btn_register.setOnClickListener(this);
        registerHelper = new RegisterHelper(this);
        codeTimeHelper = new CodeTimeHelper(this);


//        et_phone.setFocusable(true);
//        et_phone.setFocusableInTouchMode(true);
//        et_phone.requestFocus();
//        et_phone.requestFocusFromTouch();
//
//        et_phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (hasFocus) {
//                    iv_clear.setVisibility(View.VISIBLE);
//                    iv_password_clear.setVisibility(View.GONE);
//                } else {
////                    iv_clear.setVisibility(View.GONE);
////                    iv_password_clear.setVisibility(View.GONE);
//                }
//            }
//        });
//
//        iv_clear.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                et_phone.setText("");
//            }
//        });
//
//        iv_password_clear.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                et_password.setText("");
//            }
//        });
//


        et_phone.addTextChangedListener(PhoneWatcher);
        et_password.addTextChangedListener(PasswordWatcher);
        et_code.addTextChangedListener(CodeWatcher);

    }

    private boolean isEmptyView() {
        if (!TextUtils.isEmpty(et_phone.getText().toString().trim()) && !TextUtils.isEmpty(et_password.getText().toString().trim()) && !TextUtils.isEmpty(et_code.getText().toString().trim())) {
            return true;
        }
        Toast.makeText(this, "请完善资料", Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                startActivity(new Intent(RegisterActivity.this, NewLoginActivity.class));
                finish();
                break;
            case R.id.btn_register:
                if (et_password.length() < 6) {
                    // Toast.makeText(RegisterActivity.this, "信息填写不正确", Toast.LENGTH_SHORT).show();
                    initDlg();
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            registerDialog.setTextTittle("密码至少6位");
                        }
                    }, 10);
                } else if (isEmptyView()) {
                    registerHelper.getReg(et_phone.getText().toString().trim(), MD5.md5(et_password.getText().toString().trim()), et_code.getText().toString().trim());
                    Log.i("RegisterActivity", "    " + MD5.md5(et_password.getText().toString().trim()));
                }
                break;
            case R.id.btn_getCode:
                if (!TextUtils.isEmpty(et_phone.getText().toString().trim())) {
                    submit.setClickable(false);
                    codeTimeHelper.getCodeTime(et_phone.getText().toString().trim());
                } else {
                    Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.register_tv:
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
                break;
            case R.id.iv_eyes:
                if (!is_eyes) {
                    is_eyes = true;
                    iv_eyes.setImageResource(R.mipmap.icon_eyes_no_register);
                    et_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    et_password.setSelection(et_password.getText().length());
                } else {
                    is_eyes = false;
                    iv_eyes.setImageResource(R.mipmap.icon_eyes_yes_register);
                    et_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    et_password.setSelection(et_password.getText().length());
                }
                break;
            case R.id.xieyi:
                startActivity(new Intent(RegisterActivity.this, UserViewActivity.class));
                break;
        }
    }

    private void initDlg() {
        registerDialog = new RegisterDialog();
        registerDialog.show(getFragmentManager(), "");
    }


    private TextWatcher PhoneWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            if (et_phone.length() != 0 && et_code.length() != 0 && et_password.length() != 0 && et_phone.length() == 11 && et_password.length() == 6) {
                btn_register.setClickable(true);
                btn_register.setVisibility(View.VISIBLE);
                btn_no_register.setVisibility(View.INVISIBLE);
            } else if (et_phone.length() == 0 || et_code.length() == 0 || et_password.length() == 0 || et_phone.length() != 11 || et_password.length() != 6) {
                btn_register.setClickable(false);
                btn_register.setVisibility(View.INVISIBLE);
                btn_no_register.setVisibility(View.VISIBLE);
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

            if (et_phone.length() != 0 && et_code.length() != 0 && et_password.length() != 0 && et_phone.length() == 11 && et_password.length() == 6) {
                btn_register.setClickable(true);
                btn_register.setVisibility(View.VISIBLE);
                btn_no_register.setVisibility(View.INVISIBLE);

            } else if (et_phone.length() == 0 || et_code.length() == 0 || et_password.length() == 0 || et_phone.length() != 11 || et_password.length() != 6) {
                btn_register.setClickable(false);
                btn_register.setVisibility(View.INVISIBLE);
                btn_no_register.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    private TextWatcher CodeWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            if (et_phone.length() != 0 && et_code.length() != 0 && et_password.length() != 0 && et_phone.length() == 11 && et_password.length() == 6) {
                btn_register.setClickable(true);
                btn_register.setVisibility(View.VISIBLE);
                btn_no_register.setVisibility(View.INVISIBLE);
            } else if (et_phone.length() == 0 || et_code.length() == 0 || et_password.length() == 0 || et_phone.length() != 11 || et_password.length() != 6) {
                btn_register.setClickable(false);
                btn_register.setVisibility(View.INVISIBLE);
                btn_no_register.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    @Override
    public void onRegisterSucc(ResListUserInfo result) {
        mLoginHeloper.getLogin(et_phone.getText().toString().trim(), MD5.md5(et_password.getText().toString().trim()));
//        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
//        this.finish();
    }

    @Override
    public void onRegisterError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFprgetPassword(ForgetInfo result) {

    }


    @Override
    public void onCodeTimeViewSucc(CodeTimeInfo result) {
        startService(new Intent(RegisterActivity.this, MyService.class));
    }

    @Override
    public void onForgetTimeViewSucc(ForgetPasswordInfo result) {

    }

    @Override
    public void onError(String msg) {
        submit.setClickable(true);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private MyReceiver myReceiver;

    public void filter() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("tick_time");
        myReceiver = new MyReceiver();
        registerReceiver(myReceiver, filter);
    }

    @Override
    public void loginSucc() {
        Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void loginFail(String msg) {

    }

    public class MyReceiver extends BroadcastReceiver {
        public void onReceive(Context arg0, Intent arg1) {
            if ("on".equals(arg1.getStringExtra("flag"))) {
                btn_no_getCode.setText(arg1.getStringExtra("time") + "后重新获取");
                //    submit.setBackgroundColor(Color.parseColor("#808080"));
                btn_no_getCode.setVisibility(View.VISIBLE);
                submit.setVisibility(View.GONE);
                submit.setClickable(false);
            } else {
                submit.setText("获取验证码");
                submit.setClickable(true);
                //   submit.setBackgroundColor(Color.parseColor("#24c789"));
                submit.setVisibility(View.VISIBLE);
                btn_no_getCode.setVisibility(View.GONE);
                stopService(new Intent(RegisterActivity.this, MyService.class));
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
    }
}
