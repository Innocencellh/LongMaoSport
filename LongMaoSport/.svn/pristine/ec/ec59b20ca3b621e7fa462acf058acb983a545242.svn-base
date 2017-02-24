package com.live.longmao.activity.person;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.live.longmao.R;
import com.live.longmao.activity.AccountActivity;
import com.live.longmao.activity.PersonCoinActivity;
import com.live.longmao.util.ChangeHeight;
import com.live.longmao.view.ObservableScrollView;
import com.live.longmao.views.BaseActivity;

/**
 * Created by HPDN on 2017/1/5.
 */
public class PersonalCenterActivity extends BaseActivity implements View.OnClickListener {
    private ObservableScrollView scroll_view;
    private LinearLayout ll_02;
    private int imageHeight;
    private TextView tv_title, tv_attention, tv_fans;
    private RelativeLayout rl_modification, tv_test5, rv_test4, tv_test1, tv_test2, tv_test7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        setView(R.layout.activity_personal);

        setTitle("我的");
        setRigthImg(R.mipmap.icon_person_set);

        initfvb();
        initScrollView();
        initListeners();
    }


    @Override
    protected void setRigthClick(View v) {
        super.setRigthClick(v);

    }

    private void initfvb() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        ll_02 = (LinearLayout) findViewById(R.id.ll_02);
        rl_modification = (RelativeLayout) findViewById(R.id.rl_modification);
        tv_attention = (TextView) findViewById(R.id.tv_attention);
        tv_fans = (TextView) findViewById(R.id.tv_fans);
        tv_test5 = (RelativeLayout) findViewById(R.id.tv_test5);
        //我的收益
        rv_test4 = (RelativeLayout) findViewById(R.id.tv_test4);
        tv_test2 = (RelativeLayout) findViewById(R.id.tv_test2);
        tv_test1 = (RelativeLayout) findViewById(R.id.tv_test1);
        tv_test7 = (RelativeLayout) findViewById(R.id.tv_test7);
        rv_test4.setOnClickListener(this);
        tv_test1.setOnClickListener(this);

        rl_modification.setOnClickListener(this);
        tv_attention.setOnClickListener(this);
        tv_fans.setOnClickListener(this);
        tv_test5.setOnClickListener(this);
        tv_test2.setOnClickListener(this);
        tv_test7.setOnClickListener(this);
    }


    private void initListeners() {
        // 获取顶部图片高度后，设置滚动监听
        ViewTreeObserver vto = ll_02.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ll_02.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
                imageHeight = ll_02.getHeight() / 4;
            }
        });
    }

    private void initScrollView() {
        scroll_view = (ObservableScrollView) findViewById(R.id.scroll_view);
        scroll_view.setScrollViewListener(new ObservableScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
                if (y <= 0) {
                    tv_title.setText("个人中心");
                } else if (y >= imageHeight) {
                    tv_title.setText("大帅哥");
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            //个人信息编辑
            case R.id.rl_modification:
                startActivity(new Intent(PersonalCenterActivity.this, PersonEditActivity.class));
                break;
            //关注
            case R.id.tv_attention:
                startActivity(new Intent(PersonalCenterActivity.this, NewPersonAttentionActivity.class));
                break;
            //粉丝
            case R.id.tv_fans:
                startActivity(new Intent(PersonalCenterActivity.this, NewPersonFansActivity.class));
                break;
            //卡路里贡献榜
            case R.id.tv_test5:
                startActivity(new Intent(PersonalCenterActivity.this, PersonRankActivity.class));
                break;
            //个人中心收益
            case R.id.tv_test4:
                startActivity(new Intent(PersonalCenterActivity.this, PersonProfileActivity.class));
                break;
            //个人中心充值
            case R.id.tv_test1:
                startActivity(new Intent(PersonalCenterActivity.this, PersonAccountActivity.class));
                break;
            //个人中心等级
            case R.id.tv_test2:
                startActivity(new Intent(PersonalCenterActivity.this, PersonGradeActivity.class));
                break;
            //信誉
            case R.id.tv_test7:
                startActivity(new Intent(PersonalCenterActivity.this, PersonProgressActivity.class));
                break;

        }
    }
}
