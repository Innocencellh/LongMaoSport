package com.live.longmao.activity.person;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.live.longmao.R;
import com.live.longmao.model.CoinInfo;
import com.live.longmao.model.ExchangeCoinInfo;
import com.live.longmao.model.IncomeInfo;
import com.live.longmao.model.MySelfInfo;
import com.live.longmao.presenters.CoinHelper;
import com.live.longmao.presenters.ExchangeCoinHelper;
import com.live.longmao.presenters.IncomeHelper;
import com.live.longmao.presenters.viewinface.CoinView;
import com.live.longmao.presenters.viewinface.ExchangeCoinView;
import com.live.longmao.presenters.viewinface.IncomeView;
import com.live.longmao.util.ToastUtil;
import com.live.longmao.views.BaseActivity;

/**
 * Created by Administrator on 2017/2/16 0016.
 */
public class ExchangeLongMaoBiActivity extends BaseActivity implements IncomeView, CoinView, ExchangeCoinView {
    private TextView allLongmaobiYuTv, kaluliYuTv, exchangeLongmaobiTv;
    private EditText kaluliExchangeEt;
    private Button exchangeBtn;
    private IncomeHelper mIncomeHelper ;
    private CoinHelper mCoinHelper ;
    private ExchangeCoinHelper exchangeCoinHelper ;
    private int coinNUm ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        setView(R.layout.activity_exchange_longmaobi);
        setTitle("兑换龙猫币");
        setRigthImg(R.mipmap.tixian_wenhao);

        initEcView();
    }

    @Override
    protected void setRigthClick(View v) {
        super.setRigthClick(v);
        // TODO: 2017/2/16 0016 点击问号要作甚???
    }

    private void initEcView() {
        mIncomeHelper = new IncomeHelper(this);
        mIncomeHelper.getIncomeHelper();




        mCoinHelper = new CoinHelper(this);
        mCoinHelper.getCoin(MySelfInfo.getInstance().getId());


        allLongmaobiYuTv = (TextView) findViewById(R.id.all_longmaobi_yu);
        kaluliYuTv = (TextView) findViewById(R.id.kaluli_yu_tv);
        exchangeLongmaobiTv = (TextView) findViewById(R.id.exchange_longmaobi_tv);

        kaluliExchangeEt = (EditText) findViewById(R.id.et_exchange);
        kaluliExchangeEt.setOnClickListener(this);
        kaluliExchangeEt.addTextChangedListener(KLLWatcher);

        exchangeBtn = (Button) findViewById(R.id.exchange_bi_btn);
        exchangeBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            //输入要兑换的龙猫币数量
            case R.id.et_exchange:
                break;
            //兑换龙猫币
            case R.id.exchange_bi_btn:
                exchangeCoinHelper = new ExchangeCoinHelper(this);
                exchangeCoinHelper.getExchangeCoinHelper(kaluliExchangeEt.getText().toString().trim());
//                exchangeCoinHelper.getExchangeCoinHelper("1000");
                break;
        }
    }


    private TextWatcher KLLWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {


        }

        @Override
        public void afterTextChanged(Editable s) {
            exchangeLongmaobiTv.setText(s);
        }
    };


    @Override
    public void IncomeViewSucc(IncomeInfo result) {
        if (result.getBody().getCalorie() == 0){
            kaluliYuTv.setText("0");
        }else{
            kaluliYuTv.setText(result.getBody().getCalorie() + "");
        }
    }

    @Override
    public void IncomeViewError(String msg) {

    }

    @Override
    public void onCoinSucc(CoinInfo result) {
        if (result.getBody() != null){
            allLongmaobiYuTv.setText(result.getBody().get(0).getLmCoinNum());
            Log.i("coin",result.getBody().get(0).getLmCoinNum() + "");
        }else {
            allLongmaobiYuTv.setText("0");
        }

    }

    @Override
    public void onError(String msg) {

    }

    @Override
    public void ExchangeCoinSucc(ExchangeCoinInfo result) {
//        allLongmaobiYuTv.setText(result.getBody().getCalorie());
//        allLongmaobiYuTv.setText(kaluliExchangeEt.getText().toString());
        kaluliYuTv.setText(result.getBody().getCalorie() + "");
        kaluliExchangeEt.setText("");
        mCoinHelper.getCoin(MySelfInfo.getInstance().getId());
        Log.i("coin","-------------->" + result.getBody().getCalorie());


        ToastUtil.showToast(this,"卡路里兑换成功");
    }

    @Override
    public void ExchangeCoinError(String msg) {
        if (msg.equals("请求参数错误null")){
            ToastUtil.showToast(this,"您没有可兑换的卡路里");
        }else {
            ToastUtil.showToast(this,msg);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        mIncomeHelper = new IncomeHelper(this);
//        mIncomeHelper.getIncomeHelper();
//        mCoinHelper = new CoinHelper(this);
//        mCoinHelper.getCoin(MySelfInfo.getInstance().getId());

    }
}