package com.live.longmao.activity;

import android.os.Bundle;
import android.view.View;

import com.live.longmao.R;
import com.live.longmao.views.BaseActivity;

/**
 * Created by Administrator on 2016/9/28 0028.
 */
public class NoteAuthenticationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_note_authentication);
        setTitle("短信验证");
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }
}
