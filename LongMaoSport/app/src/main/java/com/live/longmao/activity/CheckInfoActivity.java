package com.live.longmao.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.live.longmao.R;
import com.live.longmao.info.CircularProgressBar;
import com.live.longmao.util.ChangeHeight;
import com.live.longmao.view.ObservableScrollView;
import com.live.longmao.views.customviews.BaseActivity;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/7/15 0015.
 */
public class CheckInfoActivity extends BaseActivity implements View.OnClickListener {
    RelativeLayout activity_base_title_rl, personSign, personName, personWork, personInter;
    CircularProgressBar cBar;
    private ImageButton iv_back;
    private ImageView iv;
    private ImageView person_image;
    private int imageHeight;
    ObservableScrollView scroll_view;
    HashMap<Integer, Boolean> isSelect = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_check_info);
        initView();
        initListeners();
        initScrollView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ChangeHeight.changeRH(this, activity_base_title_rl);
        }
    }
    private void initListeners() {
        // 获取顶部图片高度后，设置滚动监听
        ViewTreeObserver vto = person_image.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                person_image.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
                imageHeight = person_image.getHeight() / 2;
            }
        });
    }
    private void initScrollView() {
        scroll_view = (ObservableScrollView) findViewById(R.id.scroll_view);
        scroll_view.setScrollViewListener(new ObservableScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
                if (y <= 0) {
                    activity_base_title_rl.setBackgroundColor(Color.argb((int) 0, 91, 78, 97));//AGB由相关工具获得，或者美工提供
                } else if (y > 0 && y <= imageHeight) {
                    float scale = (float) y / imageHeight;
                    float alpha = (255 * scale);
                    activity_base_title_rl.setBackgroundColor(Color.argb((int) alpha, 91, 78, 97));
                } else {
                    activity_base_title_rl.setBackgroundColor(Color.argb((int) 255, 91, 78, 97));
                }
            }
        });
        activity_base_title_rl = (RelativeLayout) findViewById(R.id.activity_base_title_rl);
        iv_back = (ImageButton) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
    }
    private void initView() {
        activity_base_title_rl = (RelativeLayout) findViewById(R.id.activity_base_title_rl);
        iv_back = (ImageButton) findViewById(R.id.iv_back);
        personSign = (RelativeLayout) findViewById(R.id.check_rl_sign);
        personName = (RelativeLayout) findViewById(R.id.check_person_name);
        personWork = (RelativeLayout) findViewById(R.id.check_person_work);
        personInter = (RelativeLayout) findViewById(R.id.check_peson_inter);
        person_image = (ImageView) findViewById(R.id.person_image);
        iv_back.setOnClickListener(this);
        personSign.setOnClickListener(this);
        personName.setOnClickListener(this);
        personWork.setOnClickListener(this);
        personInter.setOnClickListener(this);
        cBar = (CircularProgressBar) findViewById(R.id.circularProgressbar);
        cBar.setProgress(20);//设置进度条的一个百分比用于测试用
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                this.finish();
                break;
            case  R.id.check_rl_sign:
                Intent intent = new Intent(new Intent(CheckInfoActivity.this, ProfressionHobbiesActivity.class));
                intent.putExtra("keys", "个人签名");
                startActivity(intent);
                break;
            case  R.id.check_person_name:
                Intent intentName = new Intent(new Intent(CheckInfoActivity.this, ProfressionHobbiesActivity.class));
                intentName.putExtra("keys", "昵称");
                startActivity(intentName);
                break;
            case  R.id.check_person_work:
                Intent intentPro = new Intent(new Intent(CheckInfoActivity.this, ProfressionHobbiesActivity.class));
                intentPro.putExtra("keys", "职业");
                startActivity(intentPro);
                break;
            case  R.id.check_peson_inter:
                Intent intentInter = new Intent(new Intent(CheckInfoActivity.this, ProfressionHobbiesActivity.class));
                intentInter.putExtra("keys", "兴趣爱好");
                startActivity(intentInter);
                break;
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("SplashScreen");
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        MobclickAgent.onPageEnd("SplashScreen");
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
