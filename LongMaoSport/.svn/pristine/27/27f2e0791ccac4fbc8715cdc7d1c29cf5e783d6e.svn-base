package com.live.longmao.activity.person;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.live.longmao.R;
import com.live.longmao.views.BaseActivity;

/**
 * Created by HPDN on 2017/1/13.
 */
public class TestActivity extends BaseActivity {

    private Button btn_person, btn_see_zb_person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_test);
        setTitle("测试界面");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        initInfo();

    }

    private void initInfo() {
        btn_see_zb_person = (Button) findViewById(R.id.btn_see_zb_person);
        btn_person = (Button) findViewById(R.id.btn_person);

        btn_person.setOnClickListener(this);
        btn_see_zb_person.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_see_zb_person:
                startActivity(new Intent(TestActivity.this, ZBPersonActivity.class));
                break;
            case R.id.btn_person:
                startActivity(new Intent(TestActivity.this, PersonalCenterActivity.class));
                break;


        }
    }
}
