package com.live.longmao.fragment.person;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.live.longmao.R;
import com.live.longmao.model.BInfo;
import com.live.longmao.model.BeanInfo;
import com.live.longmao.model.CoinInfo;
import com.live.longmao.model.MySelfInfo;
import com.live.longmao.presenters.BeanHelper;
import com.live.longmao.presenters.BeanToCoinHelper;
import com.live.longmao.presenters.CoinHelper;
import com.live.longmao.presenters.viewinface.BeanToCoinView;
import com.live.longmao.presenters.viewinface.BeanView;
import com.live.longmao.presenters.viewinface.CoinView;
import com.live.longmao.util.ToastUtil;

import java.util.ArrayList;

/**
 * Created by HPDN on 2017/1/9.
 */
public class LMTwoFragment extends Fragment implements BeanView, BeanToCoinView, CoinView {
    private View view;
    private TextView lmDouTv ;
    private BeanHelper mBeanHelper ;
    private Button exchangeBiBtn ;
    private BeanToCoinHelper beanToCoinHelper ;
    private EditText beanEt ;
    private TextView lmBiTv ;
    private CoinHelper coinHelper ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_lm_two, null);
            initView();
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        return view;

    }



    private void initView() {
        mBeanHelper = new BeanHelper(this);
        mBeanHelper.getBean(MySelfInfo.getInstance().getId());

        coinHelper = new CoinHelper(this);
        lmDouTv = (TextView) view.findViewById(R.id.lm_dou_yu_tv);
        beanToCoinHelper = new BeanToCoinHelper(this);
        exchangeBiBtn = (Button) view.findViewById(R.id.exchange_bi_btn);
        beanEt = (EditText) view.findViewById(R.id.et_exchange);
        beanEt.setClickable(true);
        beanEt.addTextChangedListener(textWatcher);
        lmBiTv = (TextView) view.findViewById(R.id.exchange_longmaobi_tv);

        exchangeBiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beanToCoinHelper.getData(MySelfInfo.getInstance().getId(), beanEt.getText().toString().trim() + "");
            }
        });
    }




    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
//            if (s.length() == 0 && s.toString() == null){
//                lmBiTv.setText("0");
//            }else {
//                lmBiTv.setText(s);
//            }

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.toString().length() == 0) {
                lmBiTv.setText("0");
            } else {
                int a = Integer.valueOf((Integer.valueOf(s.toString())) * 10);
                lmBiTv.setText(String.valueOf(a));
            }

        }
    };

    @Override
    public void onBeanSucc(BeanInfo result) {
        if (null != result && null != result.getBody()) {
            ArrayList<BInfo> body = result.getBody();
            BInfo bInfo = body.get(0);//取第一个
            lmDouTv.setText(String.valueOf(bInfo.getLmBeanNum()));
        }else {
            lmDouTv.setText("0");
        }

    }



    @Override
    public void onBeanError(String msg) {

    }

    @Override
    public void onBeanToCoinSucc(BeanInfo result) {
        if (result != null && result.getBody() != null){
            ArrayList<BInfo> body = result.getBody();
            BInfo bInfo = body.get(0);
            lmDouTv.setText(String.valueOf(bInfo.getLmBeanNum()));
            beanEt.setText("");
        }

    }

    @Override
    public void onBeanToCoinError(String msg) {
        ToastUtil.showToast(getActivity(),msg);

    }

    @Override
    public void onResume() {
        super.onResume();
        mBeanHelper.getBean(MySelfInfo.getInstance().getId());

    }

    @Override
    public void onCoinSucc(CoinInfo result) {

    }

    @Override
    public void onError(String msg) {

    }
}
