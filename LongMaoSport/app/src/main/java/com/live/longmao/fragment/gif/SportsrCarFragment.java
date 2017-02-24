package com.live.longmao.fragment.gif;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.live.longmao.R;
import com.live.longmao.util.ScreenUtil;
import com.live.longmao.util.UIUtils;

/**
 * Created by Administrator on 2016/8/25.
 */
public class SportsrCarFragment extends Fragment {
    private View view;
    RelativeLayout rl_che;
    ImageView iv_lunzi, iv_che;
    private AnimationDrawable CheAniDraw, LunZiAniDraw;
    Handler handler = new Handler();
    int width, height, allW, allH;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_gift_car, container, false);
        initView();
        new Thread(new Runnable() {
            @Override
            public void run() {
                gifAni();
            }
        }).start();
        return view;
    }

    private void initView() {
        rl_che = (RelativeLayout) view.findViewById(R.id.rl_che);
        iv_lunzi = (ImageView) view.findViewById(R.id.iv_lunzi);
        iv_che = (ImageView) view.findViewById(R.id.iv_che);
        width = UIUtils.getMeasuredWidth(rl_che);
        height = UIUtils.getMeasuredHeight(rl_che);
        allW = ScreenUtil.getScreenWidth(getActivity());
        allH = ScreenUtil.getScreenHeight(getActivity());
    }

    private void gifAni() {

        LunZiAniDraw = (AnimationDrawable) iv_lunzi.getDrawable();
        final ObjectAnimator objectAnimatorX = ObjectAnimator.ofFloat(rl_che, "translationX", -width, allW + width).setDuration(10000);
        objectAnimatorX.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                rl_che.setVisibility(View.GONE);
                LunZiAniDraw.stop();
                LunZiAniDraw = null;
                if (null != mLoadComplete) {
                    mLoadComplete.loadComplete();
                }
            }
        });
        final ObjectAnimator objectAnimatorY = ObjectAnimator.ofFloat(rl_che, "translationY", -height, allH + height).setDuration(10000);
        objectAnimatorY.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                rl_che.setVisibility(View.GONE);

            }

        });

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                LunZiAniDraw.start();
                objectAnimatorX.start();
                objectAnimatorY.start();
                rl_che.setVisibility(View.VISIBLE);
            }
        }, 50);
    }

    private LoadComplete mLoadComplete;

    public interface LoadComplete {
        void loadComplete();
    }

    public void setLoadComplete(LoadComplete loadComplete) {
        mLoadComplete = loadComplete;
    }
}
