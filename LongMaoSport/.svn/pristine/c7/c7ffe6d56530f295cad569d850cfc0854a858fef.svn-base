package com.live.longmao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.live.longmao.R;
import com.live.longmao.model.IncomeInfo;
import com.live.longmao.model.MySelfInfo;
import com.live.longmao.presenters.ExchangeCoinHelper;
import com.live.longmao.presenters.IncomeHelper;
import com.live.longmao.presenters.viewinface.IncomeView;
import com.live.longmao.views.BaseActivity;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by Administrator on 2016/9/28 0028.
 */
public class PersonalIncomeActivity extends BaseActivity implements View.OnClickListener, IncomeView {
    private Button btn_income, btn_exchange;
    private TextView tv_kll, tv_money;
    private IncomeHelper incomeHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_personal_income);
        setTitle("收益");
       // setRigthImg(R.mipmap.icon_record_btn);
        initViews();
    }

    private void initViews() {
        btn_income = (Button) findViewById(R.id.btn_income);
        btn_exchange = (Button) findViewById(R.id.btn_exchange);
        tv_kll = (TextView) findViewById(R.id.tv_kll);
        tv_money = (TextView) findViewById(R.id.tv_money);
        btn_income.setOnClickListener(this);
        btn_exchange.setOnClickListener(this);
        incomeHelper = new IncomeHelper(this);
        incomeHelper.getIncomeHelper();
    }

    @Override
    protected void setRigthClick(View v) {
        super.setRigthClick(v);
    //    startActivity(new Intent(this, PersonEarningsActivity.class));
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_income:
                startActivity(new Intent(this, ServerActivity.class));
                break;
            case R.id.btn_exchange:
                startActivity(new Intent(this, ExchangeCoinActivity.class));
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("个人中心收益界面");
        incomeHelper.getIncomeHelper();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("个人中心收益界面");
        MobclickAgent.onPause(this);
    }

    @Override
    public void IncomeViewSucc(IncomeInfo result) {
        tv_kll.setText(result.getBody().getCalorie() + "");
        tv_money.setText((result.getBody().getCalorie() * 0.85) + "");
    }

    @Override
    public void IncomeViewError(String msg) {

    }
}
