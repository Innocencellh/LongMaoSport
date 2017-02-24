package com.live.longmao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.live.longmao.R;
import com.live.longmao.model.ChangeEntayInfo;
import com.live.longmao.presenters.ModificationMessageHelper;
import com.live.longmao.presenters.viewinface.ModificationMessageView;
import com.live.longmao.views.BaseActivity;

/**
 * Created by jz on 2016/7/26 0026.
 */
public class ProfressionSigActivity extends BaseActivity implements ModificationMessageView {

    private EditText profression_et;
    private ImageButton iv_back;
    private ModificationMessageHelper modificationMessageHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_profression);
        setTitle("个性签名");
        setRigthText("保存");
        initInfo();
    }

    private void initInfo() {
        modificationMessageHelper = new ModificationMessageHelper(this);
        profression_et = (EditText) findViewById(R.id.profression_et);
        iv_back = (ImageButton) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        profression_et.setOnClickListener(this);
    }

    @Override
    protected void setRigthClick(View v) {
        super.setRigthClick(v);
        if (TextUtils.isEmpty(profression_et.getText().toString().trim())) {
            Toast.makeText(ProfressionSigActivity.this, "你还没有修改你的信息", Toast.LENGTH_SHORT).show();
        } else {
            modificationMessageHelper.getSig(profression_et.getText().toString().trim());
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_back:
                startActivity(new Intent(ProfressionSigActivity.this, InfoActivity.class));
                finish();
                break;
        }
    }

    @Override
    public void onModificationMessageSucc(ChangeEntayInfo result) {
        Toast.makeText(ProfressionSigActivity.this, "你的信息修改成功", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(ProfressionSigActivity.this, InfoActivity.class));
        this.finish();
    }

    @Override
    public void onModificationMessageError(String msg) {

    }
}
