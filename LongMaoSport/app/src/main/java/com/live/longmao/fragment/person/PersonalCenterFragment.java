package com.live.longmao.fragment.person;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.live.longmao.R;
import com.live.longmao.activity.SettingActivity;
import com.live.longmao.activity.person.NewPersonAttentionActivity;
import com.live.longmao.activity.person.NewPersonFansActivity;
import com.live.longmao.activity.person.PersonAccountActivity;
import com.live.longmao.activity.person.PersonEditActivity;
import com.live.longmao.activity.person.PersonGradeActivity;
import com.live.longmao.activity.person.PersonProfileActivity;
import com.live.longmao.activity.person.PersonProgressActivity;
import com.live.longmao.activity.person.PersonRankActivity;
import com.live.longmao.model.CInfo;
import com.live.longmao.model.CoinInfo;
import com.live.longmao.model.GradeInfo;
import com.live.longmao.model.IncomeInfo;
import com.live.longmao.model.MySelfInfo;
import com.live.longmao.model.PersonInfo;
import com.live.longmao.presenters.CoinHelper;
import com.live.longmao.presenters.GradeHelper;
import com.live.longmao.presenters.IncomeHelper;
import com.live.longmao.presenters.PersonHelper;
import com.live.longmao.presenters.viewinface.CoinView;
import com.live.longmao.presenters.viewinface.GradeView;
import com.live.longmao.presenters.viewinface.IncomeView;
import com.live.longmao.presenters.viewinface.PersonView;
import com.live.longmao.util.ChangeHeight;
import com.live.longmao.util.GlideUtil;
import com.live.longmao.view.CircleImageView;
import com.live.longmao.view.ObservableScrollView;
import com.live.okhttp.OkHttpUtils;
import com.umeng.analytics.MobclickAgent;

import java.util.List;

/**
 * Created by HPDN on 2017/1/5.
 */
public class PersonalCenterFragment extends Fragment implements View.OnClickListener, PersonView, CoinView, GradeView, IncomeView {
    private ObservableScrollView scroll_view;
    private LinearLayout ll_02;
    private int imageHeight;
    private TextView tv_attention, tv_fans, tv_calolir, tv_chognzhi;
    private RelativeLayout rl_modification, tv_test5, rv_test4, tv_test1, tv_test2, tv_test7, activity_base_title_rl;
    private PersonHelper personHelper;
    private CoinHelper mCoinHelper;
    private GradeHelper mGradeHelper;
    private IncomeHelper incomeHelper ;


    private ImageView setImg;
    private CircleImageView headImg;
    private TextView nickTv, idTv, rankTv;
    private ImageView girlImg, boyImg;


    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (v == null) {
            v = inflater.inflate(R.layout.activity_personal, null);
            initfvb();
        }
        ViewGroup parent = (ViewGroup) v.getParent();
        if (parent != null) {
            parent.removeView(v);
        }

        return v;
    }

    private void initfvb() {
        personHelper = new PersonHelper(this);
        personHelper.getPerson();

        incomeHelper = new IncomeHelper(this);

        mCoinHelper = new CoinHelper(this);
        mCoinHelper.getCoin(MySelfInfo.getInstance().getId());

        mGradeHelper = new GradeHelper(this);
        mGradeHelper.givingGift();


        setImg = (ImageView) v.findViewById(R.id.iv_set);
        setImg.setOnClickListener(this);

        girlImg = (ImageView) v.findViewById(R.id.img_girl);

        headImg = (CircleImageView) v.findViewById(R.id.iv_user_head);
        nickTv = (TextView) v.findViewById(R.id.tv_name);
        idTv = (TextView) v.findViewById(R.id.person_id_tv);
        rankTv = (TextView) v.findViewById(R.id.tv_rank);
        tv_calolir = (TextView) v.findViewById(R.id.tv_cololir);
        tv_chognzhi = (TextView) v.findViewById(R.id.tv_calorie);


        ll_02 = (LinearLayout) v.findViewById(R.id.ll_02);
        rl_modification = (RelativeLayout) v.findViewById(R.id.rl_modification);
        tv_attention = (TextView) v.findViewById(R.id.tv_attention);
        tv_fans = (TextView) v.findViewById(R.id.tv_fans);
        tv_test5 = (RelativeLayout) v.findViewById(R.id.tv_test5);

        activity_base_title_rl = (RelativeLayout) v.findViewById(R.id.activity_base_title_rl);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ChangeHeight.changeRH(getActivity(), activity_base_title_rl);
        }

        //我的收益
        rv_test4 = (RelativeLayout) v.findViewById(R.id.tv_test4);
        tv_test2 = (RelativeLayout) v.findViewById(R.id.tv_test2);
        tv_test1 = (RelativeLayout) v.findViewById(R.id.tv_test1);
        tv_test7 = (RelativeLayout) v.findViewById(R.id.tv_test7);
        rv_test4.setOnClickListener(this);
        tv_test1.setOnClickListener(this);

        rl_modification.setOnClickListener(this);
        tv_attention.setOnClickListener(this);
        tv_fans.setOnClickListener(this);
        tv_test5.setOnClickListener(this);
        tv_test2.setOnClickListener(this);
        tv_test7.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //个人信息编辑
            case R.id.rl_modification:
                startActivity(new Intent(getActivity(), PersonEditActivity.class));
                break;
            //关注
            case R.id.tv_attention:
                startActivity(new Intent(getActivity(), NewPersonAttentionActivity.class));
                break;
            //粉丝
            case R.id.tv_fans:
                startActivity(new Intent(getActivity(), NewPersonFansActivity.class));
                break;
            //卡路里贡献榜
            case R.id.tv_test5:
                startActivity(new Intent(getActivity(), PersonRankActivity.class));
                break;
            //个人中心收益
            case R.id.tv_test4:
                startActivity(new Intent(getActivity(), PersonProfileActivity.class));
                break;
            //个人中心充值
            case R.id.tv_test1:
                startActivity(new Intent(getActivity(), PersonAccountActivity.class));
                break;
            //个人中心等级
            case R.id.tv_test2:
                startActivity(new Intent(getActivity(), PersonGradeActivity.class));
                break;
            //信誉
            case R.id.tv_test7:
                startActivity(new Intent(getActivity(), PersonProgressActivity.class));
                break;
            //跳转到设置界面
            case R.id.iv_set:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;

        }
    }


    @Override
    public void onPersonInfoSucc(PersonInfo result) {

//        Log.i("phoneiD","" + result.getBody().getPhoneId());
        if (result.getBody().getNickName() != null) {
            nickTv.setText(result.getBody().getNickName());
        }


        if (result.getBody().getId() != null) {
            idTv.setText("龙猫ID: " + result.getBody().getUserId());
        }

        if (result.getBody().getAttentionednum() == 0) {
            tv_attention.setText("0 关注");
        } else {
            tv_attention.setText(result.getBody().getAttentionednum() + " 关注");
        }

        if (result.getBody().getAttentionnum() == 0) {
            tv_fans.setText("0 粉丝");
        } else {
            tv_fans.setText(result.getBody().getAttentionednum() + " 粉丝");
        }

        if (result.getBody().getPhotoUrl() != null) {
            String Url = "http://101.37.28.112/photo/";
            GlideUtil.getInstance().load(getActivity(), headImg, OkHttpUtils.Photo_Url + result.getBody().getPhoneId());
            Log.i("phoneiD", String.valueOf(result.getBody().getPhoneId()));
        } else {
            Log.i("phoneiD", "没有获取到photo");
        }
        if (result.getBody().getSex() == 1) {
            girlImg.setImageResource(R.mipmap.icon_person_boy);
        } else {
            girlImg.setImageResource(R.mipmap.icon_person_girl);
        }

//        tv_calolir.setText(String.valueOf(result.getBody().getCalorie()) + " 卡");


    }

    @Override
    public void onPersonInfoError(String msg) {

    }

    //账户充值回调方法
    @Override
    public void onCoinSucc(CoinInfo result) {
        if (null != result && null != result.getBody()) {
            List<CInfo> body = result.getBody();
            CInfo cInfo = body.get(0);
            tv_chognzhi.setText(String.valueOf(cInfo.getLmCoinNum()) + " 币");
        }

    }

    @Override
    public void onError(String msg) {

    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("个人中心界面");
    }

    @Override
    public void onResume() {
        super.onResume();
        personHelper.getPerson();
        mCoinHelper.getCoin(MySelfInfo.getInstance().getId());
        mGradeHelper.givingGift();

        incomeHelper.getIncomeHelper();
        MobclickAgent.onPageStart("个人中心界面");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onGradeSucc(GradeInfo gradeInfo) {
        if (gradeInfo.getBody() != null) {
            rankTv.setText("Lv" + "1");
        } else {
            rankTv.setText("Lv0");
        }

    }

    @Override
    public void onGradeError(String msg) {

    }

    @Override
    public void IncomeViewSucc(IncomeInfo result) {
        if (result.getBody().getCalorie() == 0){
            tv_calolir.setText("0");
        }else {
            tv_calolir.setText(result.getBody().getCalorie() + "卡");
        }

    }

    @Override
    public void IncomeViewError(String msg) {

    }


}

