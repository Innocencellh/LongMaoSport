package com.live.longmao.activity.person;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.live.longmao.R;
import com.live.longmao.views.BaseActivity;

/**
 * Created by Administrator on 2017/2/16 0016.
 */
public class ChangeZhifubaoActivity extends BaseActivity {
    private TextView yuanZhifubaoTv ;
    private EditText countZhifubaoEt ,countZhifubaoNameEt ;
    private Button bindBtn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setView(R.layout.activity_change_zhifubao);

        setTitle("更改支付宝");
        initBindView();
    }

    private void initBindView(){
        yuanZhifubaoTv = (TextView) findViewById(R.id.zhifubao_yuan_id);
        countZhifubaoEt = (EditText) findViewById(R.id.change_zhifubao_id);
        countZhifubaoEt.setOnClickListener(this);
        countZhifubaoNameEt = (EditText) findViewById(R.id.change_real_name);
        countZhifubaoNameEt.setOnClickListener(this);
        bindBtn = (Button) findViewById(R.id.change_btn_now_bind);
        bindBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.change_zhifubao_id:
                break;
            case R.id.change_real_name:
                break;
            case R.id.change_btn_now_bind:
                break;
        }
    }
}
