package com.live.longmao.fragment.gif;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.live.longmao.R;

/**
 * Created by Administrator on 2016/9/22.
 */
public class AllInFragment extends Fragment{
    ImageView iv_all_in,iv_xin,iv_all_in_bg;
    AlphaAnimation alphaAnimation1;
    Animation allInAni,allInAlwaysAni,allInEndAni;
    Animation operatingAnim;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_all_in, container, false);
        initView();
        return view;
    }
    private void initView()
    {
        iv_xin = (ImageView) view.findViewById(R.id.iv_xin);
        iv_all_in_bg = (ImageView) view.findViewById(R.id.iv_all_in_bg);
        iv_all_in = (ImageView) view.findViewById(R.id.iv_all_in);
        operatingAnim= AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_bg_ani);
        LinearInterpolator lin = new LinearInterpolator();
        operatingAnim.setInterpolator(lin);
        //闪烁
        alphaAnimation1 = new AlphaAnimation(0.1f, 1.0f);
        alphaAnimation1.setDuration(300);
        alphaAnimation1.setRepeatCount(Animation.INFINITE);
        alphaAnimation1.setRepeatMode(Animation.REVERSE);
        iv_xin.setAnimation(alphaAnimation1);
        allInAlwaysAni= AnimationUtils.loadAnimation(getActivity(), R.anim.all_in_anin_always);
        allInEndAni= AnimationUtils.loadAnimation(getActivity(), R.anim.all_in_anin_end);
        allInAni = AnimationUtils.loadAnimation(getActivity(), R.anim.all_in_anin);

        Animation.AnimationListener allInAlwaysListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                iv_all_in_bg.setVisibility(View.VISIBLE);
                iv_all_in_bg.startAnimation(operatingAnim);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
        Animation.AnimationListener allInAniListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                iv_xin.setVisibility(View.VISIBLE);
                iv_all_in.startAnimation(allInAlwaysAni);
                alphaAnimation1.start();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
        Animation.AnimationListener endListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                iv_all_in_bg.clearAnimation();
                iv_all_in_bg.setImageBitmap(null);
                iv_xin.setImageBitmap(null);
                alphaAnimation1.cancel();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                iv_all_in.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
        allInEndAni.setAnimationListener(endListener);
        allInAni.setAnimationListener(allInAniListener);
        allInAlwaysAni.setAnimationListener(allInAlwaysListener);

        iv_all_in.startAnimation(allInAni);
        iv_all_in.postDelayed(new Runnable() {
            @Override
            public void run() {
                iv_all_in.clearAnimation();
                iv_all_in.startAnimation(allInEndAni);
            }
        },5000);

    }
}
