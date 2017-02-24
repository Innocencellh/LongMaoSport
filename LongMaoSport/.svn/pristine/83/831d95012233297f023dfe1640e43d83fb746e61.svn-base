package com.live.longmao.activity.person;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.live.longmao.R;
import com.live.longmao.util.ToastUtil;
import com.live.longmao.views.BaseActivity;

/**
 * Created by Administrator on 2017/2/15 0015.
 */
public class BindZhifubaoActivity extends BaseActivity {
    private EditText zhifu_et ,real_name_et ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_bind_zhifubao);
        setTitle("绑定支付宝");

        initView();
    }

    protected void initView(){
        zhifu_et = (EditText) findViewById(R.id.zhifubao_id);
        real_name_et = (EditText) findViewById(R.id.real_name);

        zhifu_et.setOnClickListener(this);
        real_name_et.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.zhifubao_id:
                ToastUtil.showToast(this,"请输入账户");
                break;
            case R.id.real_name:
                ToastUtil.showToast(this,"请输入真实姓名");
                break;
        }
    }
}
