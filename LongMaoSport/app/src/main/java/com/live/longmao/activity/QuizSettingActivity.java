package com.live.longmao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.live.longmao.R;
import com.live.longmao.dlg.ActionSheetDialog;
import com.live.longmao.model.GuessingInfo;
import com.live.longmao.model.ListGuessingInfo;
import com.live.longmao.model.MySelfInfo;
import com.live.longmao.presenters.GuessingHelper;
import com.live.longmao.presenters.viewinface.GuessingView;
import com.live.longmao.views.BaseActivity;

import java.util.List;

/**
 * Created by Administrator on 2016/9/8.
 */
public class QuizSettingActivity extends BaseActivity implements GuessingView {
    private LinearLayout ll_recharge;
    private EditText guess_tittle, money_limit, option_a, option_b;
    private TextView time_limit, txt_commit;
    GuessingHelper guessingHelper = null;
    int time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("竞猜设置");
        setView(R.layout.activity_quiz_setting);
        initView();
    }

    private void initView() {
        ll_recharge = (LinearLayout) findViewById(R.id.ll_recharge);
        guess_tittle = (EditText) findViewById(R.id.guess_tittle);
        money_limit = (EditText) findViewById(R.id.money_limit);
        option_a = (EditText) findViewById(R.id.option_a);
        option_b = (EditText) findViewById(R.id.option_b);
        txt_commit = (TextView) findViewById(R.id.txt_commit);
        txt_commit.setOnClickListener(this);
        time_limit = (TextView) findViewById(R.id.time_limit);
        time_limit.setOnClickListener(this);
        guessingHelper = new GuessingHelper(this);
    }

    private boolean isEmptyView() {
        if (!TextUtils.isEmpty(guess_tittle.getText().toString().trim())
                && !TextUtils.isEmpty(money_limit.getText().toString().trim())
                && !TextUtils.isEmpty(option_a.getText().toString().trim())
                && !TextUtils.isEmpty(option_b.getText().toString().trim())
                && !TextUtils.isEmpty(time_limit.getText().toString().trim())) {
            return true;
        }
        Toast.makeText(this,"请完善内容",Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.time_limit:
                new ActionSheetDialog(this)
                        .builder()
                        .setCancelable(false)
                        .setCanceledOnTouchOutside(false)
                        .addSheetItem("5分钟", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        time_limit.setText("5分钟");
                                        time=5;
                                    }
                                })
                        .addSheetItem("10分钟", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        time_limit.setText("10分钟");
                                        time=10;
                                    }
                                })
                        .addSheetItem("15分钟", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        time_limit.setText("15分钟");
                                        time=15;
                                    }
                                })
                        .addSheetItem("20分钟", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        time_limit.setText("20分钟");
                                        time=20;
                                    }
                                })
                        .addSheetItem("30分钟", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        time_limit.setText("30分钟");
                                        time=30;
                                    }
                                }).show();
                break;
            case R.id.txt_commit:
                if(isEmptyView()) {
                    txt_commit.setClickable(false);
                    String userId = MySelfInfo.getInstance().getId();
                    String guesstitle = guess_tittle.getText().toString().trim();
                    String beannum = money_limit.getText().toString().trim();
                    String answa = option_a.getText().toString().trim();
                    String answb = option_b.getText().toString().trim();
                    guessingHelper.addGuessing(userId, guesstitle, beannum, answa, answb,time+"");
                }
                break;
        }
    }

    @Override
    public void finish() {
        super.finish();
        //关闭窗体动画显示
        this.overridePendingTransition(0, R.anim.activity_close);
    }

    @Override
    public void onGuessingSucc() {
        setResult(2);
        finish();
    }

    @Override
    public void onGuessingRead(List<GuessingInfo> guessingInfos,boolean isSucc) {

    }

    @Override
    public void onGuessingEntertained(GuessingInfo guessingInfo) {

    }

    @Override
    public void onGuessingBet(GuessingInfo guessingInfos) {

    }

    @Override
    public void onGuessingJieSuanSucc() {

    }

    @Override
    public void onGuessingError(String msg) {
        txt_commit.setClickable(true);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
