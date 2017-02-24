package com.live.longmao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.live.longmao.R;
import com.live.longmao.util.DlgTellUtil;
import com.live.longmao.views.BaseActivity;

/**
 * Created by Administrator on 2016/7/9 0009.
 */
public class IncomeActivity extends BaseActivity {
    private TextView phone;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("收益");
        setView(R.layout.activity_income);
        phone = (TextView) findViewById(R.id.phone);
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DlgTellUtil.startDlgCall(IncomeActivity.this,"10086");
            }
        });
        setLineGone();
    }
}
