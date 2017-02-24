package com.live.longmao.activity.person;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.TouchDelegate;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.live.longmao.R;
import com.live.longmao.views.BaseActivity;

/**
 * Created by Administrator on 2017/2/16 0016.
 */
public class PersonTiXianActivity extends BaseActivity {
    private RelativeLayout tixianRv ;
    private EditText moneyEt ;
    private TextView tixianAllBtn ;
    private Button tixianBtn ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_person_tixian);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        setTitle("提现");
        setRigthImg(R.mipmap.tixian_wenhao);
        initMview();

    }

    @Override
    protected void setRigthClick(View v) {
        super.setRigthClick(v);
        // TODO: 2017/2/16 0016 点击跳转到提现记录
//        Intent intent = new Intent(PersonTiXianActivity.this,)

    }

    private void initMview(){
        tixianRv = (RelativeLayout) findViewById(R.id.zhifubao_rv);
        moneyEt = (EditText) findViewById(R.id.money_et);
        tixianAllBtn = (TextView) findViewById(R.id.tixian_btn);
        tixianBtn = (Button) findViewById(R.id.money_tixian_btn);
        tixianBtn.setOnClickListener(this);
        tixianAllBtn.setOnClickListener(this);
        moneyEt.setOnClickListener(this);
        tixianRv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.zhifubao_rv:
                // TODO: 2017/2/16 0016 跳转到更换绑定支付宝
                Intent intent = new Intent(PersonTiXianActivity.this,ChangeZhifubaoActivity.class);
                startActivity(intent);
                break;
            case R.id.money_et:
                break;
            //全部提现按钮
            case R.id.tixian_btn:
                break;
            //提现按钮
            case R.id.money_tixian_btn:
                break;
        }
    }
}
