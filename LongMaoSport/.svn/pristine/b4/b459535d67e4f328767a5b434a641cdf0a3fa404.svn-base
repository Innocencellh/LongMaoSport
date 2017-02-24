package com.live.longmao.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.live.longmao.R;

/**
 * Created by HPDN on 2016/7/6.
 */
public class ZBGuessSetActivity extends Activity implements View.OnClickListener {

    private EditText guess_tittle, money_limit, option_a, option_b;
    private TextView txt_commit;
    private String guess, money, option_A, option_B;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_setting);
        guess_tittle = (EditText) findViewById(R.id.guess_tittle);
        money_limit = (EditText) findViewById(R.id.money_limit);
        option_a = (EditText) findViewById(R.id.option_a);
        option_b = (EditText) findViewById(R.id.option_b);
        txt_commit = (TextView) findViewById(R.id.txt_commit);
        //findViewById(R.id.btn_return).setOnClickListener(this);
        txt_commit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.succeed:
                guess = guess_tittle.getText().toString().trim();
                money = money_limit.getText().toString().trim();
                option_A = option_a.getText().toString().trim();
                option_B = option_b.getText().toString().trim();
                if (TextUtils.isEmpty(guess)) {
                    Toast.makeText(ZBGuessSetActivity.this, "竞猜标题不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(money)) {
                    if (Integer.parseInt(money) < 1000) {
                        Toast.makeText(ZBGuessSetActivity.this, "您的龙猫豆设置金额不能低于1000", Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(ZBGuessSetActivity.this, "龙猫豆金额不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(option_A)) {
                    Toast.makeText(ZBGuessSetActivity.this, "选项A不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(option_B)) {
                    Toast.makeText(ZBGuessSetActivity.this, "选项B不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Toast.makeText(ZBGuessSetActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }
}
