package com.live.longmao.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.live.longmao.R;
import com.live.longmao.model.BInfo;
import com.live.longmao.model.BeanInfo;
import com.live.longmao.model.CointoBeanInfo;
import com.live.longmao.model.MySelfInfo;
import com.live.longmao.presenters.BeanHelper;
import com.live.longmao.presenters.CoinToBeanHelper;
import com.live.longmao.presenters.viewinface.BeanView;
import com.live.longmao.presenters.viewinface.CoinToBeanView;
import com.live.longmao.util.DlgTellUtil;
import com.live.longmao.views.BaseActivity;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/9/11 0011.
 */
public class BeanActivity extends BaseActivity implements View.OnClickListener, BeanView, CoinToBeanView {
    private BeanHelper beanHelper;
    private CoinToBeanHelper coinToBeanHelper;
    private TextView beanAccount;
    private LinearLayout lTen, lFirty, lHundred, lTwoHundred, lFirtyHundred, lFInal;
    private TextView tenTv, firtyTv, hundredTv, twoHundredTv, firtyHundredTv, finaltv,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("龙猫豆");
        setView(R.layout.activity_bean);
        setLineGone();
        initView();
        beanHelper.getBean(MySelfInfo.getInstance().getId());
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("SplashScreen");
        MobclickAgent.onResume(this);
    }

    private void initView() {
        beanHelper = new BeanHelper(this);
        coinToBeanHelper = new CoinToBeanHelper(this);
        beanAccount = (TextView) findViewById(R.id.money_money);
        lTen = (LinearLayout) findViewById(R.id.bean_Ten);
        lFirty = (LinearLayout) findViewById(R.id.bean_fifty);
        lHundred = (LinearLayout) findViewById(R.id.bean_hundred);
        lTwoHundred = (LinearLayout) findViewById(R.id.bean_two_hundred);
        lFirtyHundred = (LinearLayout) findViewById(R.id.bean_five_hundred);
        lFInal = (LinearLayout) findViewById(R.id.bean_ten_hundred);
        tenTv = (TextView) findViewById(R.id.bean_Ten_tv);
        firtyTv = (TextView) findViewById(R.id.bean_fifty_tv);
        hundredTv = (TextView) findViewById(R.id.bean_hundred_tv);
        twoHundredTv = (TextView) findViewById(R.id.bean_two_hundred_tv);
        firtyHundredTv = (TextView) findViewById(R.id.bean_five_hundred_tv);
        finaltv = (TextView) findViewById(R.id.bean_ten_hundred_tv);
        phone = (TextView) findViewById(R.id.phone);
        phone.setOnClickListener(this);
        lTen.setOnClickListener(this);
        lFirty.setOnClickListener(this);
        lHundred.setOnClickListener(this);
        lTwoHundred.setOnClickListener(this);
        lFirtyHundred.setOnClickListener(this);
        lFInal.setOnClickListener(this);
    }


    @Override
    public void onBeanSucc(BeanInfo result) {
        if(null!=result&&null!=result.getBody()) {
            ArrayList<BInfo> body = result.getBody();
            BInfo bInfo = body.get(0);//取第一个
            Log.i("BeanActivity", bInfo.getLmBeanNum() + "");
            beanAccount.setText(String.valueOf(bInfo.getLmBeanNum()));
        }
    }

    @Override
    public void onBeanError(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCoinToBeanSucc(CointoBeanInfo result) {
        Toast.makeText(this, "兑换成功", Toast.LENGTH_SHORT).show();//请求之后获取龙猫豆
        beanHelper.getBean(MySelfInfo.getInstance().getId());
    }

    @Override
    public void onCtoBError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.bean_Ten:
                coinToBeanHelper.getCoinToBean(tenTv.getText().toString(), MySelfInfo.getInstance().getId());
                break;
            case R.id.bean_fifty:
                coinToBeanHelper.getCoinToBean(firtyTv.getText().toString(), MySelfInfo.getInstance().getId());
                break;
            case R.id.bean_hundred:
                coinToBeanHelper.getCoinToBean(hundredTv.getText().toString(), MySelfInfo.getInstance().getId());
                break;
            case R.id.bean_two_hundred:
                coinToBeanHelper.getCoinToBean(twoHundredTv.getText().toString(), MySelfInfo.getInstance().getId());
                break;
            case R.id.bean_five_hundred:
                coinToBeanHelper.getCoinToBean(firtyHundredTv.getText().toString(), MySelfInfo.getInstance().getId());
                break;
            case R.id.bean_ten_hundred:
                coinToBeanHelper.getCoinToBean(finaltv.getText().toString(), MySelfInfo.getInstance().getId());
                break;
            case R.id.phone:
                DlgTellUtil.startDlgCall(BeanActivity.this, "18164017508");
                break;
        }

    }



    @Override
    protected void onPause() {
        MobclickAgent.onPageEnd("SplashScreen");
        super.onPause();
        MobclickAgent.onPause(this);
    }

}
