package com.live.longmao.activity.person;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.live.longmao.R;
import com.live.longmao.model.IncomeInfo;
import com.live.longmao.presenters.IncomeHelper;
import com.live.longmao.presenters.viewinface.IncomeView;
import com.live.longmao.util.ToastUtil;
import com.live.longmao.views.BaseActivity;

/**
 * Created by Administrator on 2017/2/15 0015.
 */
public class PersonProfileActivity extends BaseActivity implements IncomeView {

    private Button tiXianBtn , earnCodeBiBtn ;
    private IncomeHelper mIncomeHelper ;
    private TextView caraliTv ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setView(R.layout.activity_person_profile);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setTitle("我的收益");
        setRigthImg(R.mipmap.btn_history_earning);
        initPView();
    }

    @Override
    protected void setRigthClick(View v) {
        super.setRigthClick(v);
        // TODO: 2017/2/15 0015
        ToastUtil.showToast(this,"你点击了收益历史记录按钮");
        Intent intent = new Intent(PersonProfileActivity.this,TiCashHistoryActivity.class);
        startActivity(intent);

    }

    private void initPView(){
        mIncomeHelper = new IncomeHelper(this);
        mIncomeHelper.getIncomeHelper();

        caraliTv = (TextView) findViewById(R.id.tv_kaluli);

        tiXianBtn = (Button) findViewById(R.id.person_tixian);
        earnCodeBiBtn = (Button) findViewById(R.id.person_Longmao_code);
        tiXianBtn.setOnClickListener(this);
        earnCodeBiBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.person_tixian:
                // TODO: 2017/2/15 0015 判断if 绑定了支付宝 就跳转到提现界面，if没有绑定支付宝则跳转到绑定支付宝界面再到提现界面
//                ToastUtil.showToast(this,"你点击了提现按钮");
                Intent intent = new Intent(PersonProfileActivity.this,PersonTiXianActivity.class);
                startActivity(intent);
                break;
            case R.id.person_Longmao_code:
                // TODO: 2017/2/15 0015
//                ToastUtil.showToast(this,"你点击了兑换龙猫币按钮");
                Intent intent1 = new Intent(PersonProfileActivity.this,ExchangeLongMaoBiActivity.class);
                startActivity(intent1);
                break;
        }

    }

    @Override
    public void IncomeViewSucc(IncomeInfo result) {
        if (result.getBody().getCalorie() == 0){
            caraliTv.setText("0");
        }else {
            caraliTv.setText(result.getBody().getCalorie() + "");
        }
    }

    @Override
    public void IncomeViewError(String msg) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mIncomeHelper = new IncomeHelper(this);
        mIncomeHelper.getIncomeHelper();
    }
}
