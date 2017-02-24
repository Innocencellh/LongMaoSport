package com.live.longmao.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.live.longmao.R;
import com.live.longmao.model.ExchangeCoinInfo;
import com.live.longmao.model.IncomeInfo;
import com.live.longmao.presenters.ExchangeCoinHelper;
import com.live.longmao.presenters.IncomeHelper;
import com.live.longmao.presenters.viewinface.ExchangeCoinView;
import com.live.longmao.presenters.viewinface.IncomeView;
import com.live.longmao.views.BaseActivity;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by Administrator on 2016/9/27 0027.
 */
public class ExchangeCoinActivity extends BaseActivity implements ExchangeCoinView, IncomeView {
    private ExchangeCoinHelper exchangeCoinHelper;
    private IncomeHelper incomeHelper;
    private TextView tv_kll1, tv_kll2, tv_kll3, tv_kll4, tv_kll5, tv_kll6, tv_kll7, tv_kll8, tv_kll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_exchange_lmb);
        setLineGone();
        setTitle("兑换龙猫币");
        initview();
    }

    private void initview() {
        exchangeCoinHelper = new ExchangeCoinHelper(this);
        incomeHelper = new IncomeHelper(this);
        incomeHelper.getIncomeHelper();
        tv_kll1 = (TextView) findViewById(R.id.tv_kll1);
        tv_kll2 = (TextView) findViewById(R.id.tv_kll2);
        tv_kll3 = (TextView) findViewById(R.id.tv_kll3);
        tv_kll4 = (TextView) findViewById(R.id.tv_kll4);
        tv_kll5 = (TextView) findViewById(R.id.tv_kll5);
        tv_kll6 = (TextView) findViewById(R.id.tv_kll6);
        tv_kll7 = (TextView) findViewById(R.id.tv_kll7);
        tv_kll8 = (TextView) findViewById(R.id.tv_kll8);
        tv_kll = (TextView) findViewById(R.id.tv_kll);

        tv_kll1.setOnClickListener(this);
        tv_kll2.setOnClickListener(this);
        tv_kll3.setOnClickListener(this);
        tv_kll4.setOnClickListener(this);
        tv_kll5.setOnClickListener(this);
        tv_kll6.setOnClickListener(this);
        tv_kll7.setOnClickListener(this);
        tv_kll8.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_kll1:
                exchangeCoinHelper.getExchangeCoinHelper("100");
                break;
            case R.id.tv_kll2:
                exchangeCoinHelper.getExchangeCoinHelper("1000");
                break;
            case R.id.tv_kll3:
                exchangeCoinHelper.getExchangeCoinHelper("3000");
                break;
            case R.id.tv_kll4:
                exchangeCoinHelper.getExchangeCoinHelper("5000");
                break;
            case R.id.tv_kll5:
                exchangeCoinHelper.getExchangeCoinHelper("10000");
                break;
            case R.id.tv_kll6:
                exchangeCoinHelper.getExchangeCoinHelper("50000");
                break;
            case R.id.tv_kll7:
                exchangeCoinHelper.getExchangeCoinHelper("100000");
                break;
            case R.id.tv_kll8:
                exchangeCoinHelper.getExchangeCoinHelper("1000000");
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("卡路里换币界面");
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("卡路里换币界面");
        MobclickAgent.onPause(this);
    }

    @Override
    public void ExchangeCoinSucc(ExchangeCoinInfo result) {
        tv_kll.setText(result.getBody().getCalorie() + "");
    }

    @Override
    public void ExchangeCoinError(String msg) {

    }

    @Override
    public void IncomeViewSucc(IncomeInfo result) {
        tv_kll.setText(result.getBody().getCalorie() + "");
    }

    @Override
    public void IncomeViewError(String msg) {

    }
}
