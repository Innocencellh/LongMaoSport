package com.live.longmao.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.animation.AccelerateInterpolator;
import android.widget.TextView;

import com.live.longmao.R;
import com.live.longmao.model.GradeCInfo;
import com.live.longmao.model.GradeInfo;
import com.live.longmao.presenters.GradeHelper;
import com.live.longmao.presenters.viewinface.GradeView;
import com.live.longmao.view.MagicProgressBar;
import com.live.longmao.views.BaseActivity;
import com.umeng.analytics.MobclickAgent;

import java.util.Random;

/**
 * Created by HPDN on 2016/10/10.
 */
public class GradeActivity extends BaseActivity implements GradeView {

    private MagicProgressBar pb_magic;
    private boolean isAnimActive;
    private GradeCInfo gradeCInfo;
    private GradeHelper gradeHelper;
    private TextView tv_grade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_grade);
        setTitle("等级");
        initInfo();
//        anim();
    }

    private void initInfo() {
        gradeHelper = new GradeHelper(this);
        pb_magic = (MagicProgressBar) findViewById(R.id.pb_magic);
        tv_grade = (TextView) findViewById(R.id.tv_grade);
        gradeHelper.givingGift();
    }

    private void anim() {
        final double ceil;
        final double all;
        ceil = (double) gradeCInfo.getExpDist();
        all = (double) gradeCInfo.getEndExp();
        //用 现有经验/总经验  计算总经验和现有经验的百分比
        final double num = ceil / all * 100;

        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                //100f  表示总长度数值为100
                ObjectAnimator.ofFloat(pb_magic, "percent", 0, (int) num / 100f)
        );
        set.setDuration(600);
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                isAnimActive = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isAnimActive = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        set.setInterpolator(new AccelerateInterpolator());
        set.start();
    }

    @Override
    public void onGradeSucc(GradeInfo gradeInfo) {
        //  在这里进行数据接口实体类初始化  和  自定义方法初始化
        gradeCInfo = gradeInfo.getBody().getUserLevelDTO();
        anim();
        tv_grade.setText("LV " + gradeInfo.getBody().getUserLevelDTO().getLevel() + "");
    }

    @Override
    public void onGradeError(String msg) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("个人中心等级界面");
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("个人中心等级界面");
        MobclickAgent.onPause(this);
    }

}
