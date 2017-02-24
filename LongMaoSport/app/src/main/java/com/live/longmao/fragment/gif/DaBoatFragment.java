package com.live.longmao.fragment.gif;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.live.longmao.R;
import com.live.longmao.util.ScreenUtil;
import com.live.longmao.util.UIUtils;

/**
 * Created by Administrator on 2016/8/25.
 */
public class DaBoatFragment extends Fragment {
    private View view;
    private ImageView img_shui, img_chuan;
    private AnimationDrawable drawableG;
//    private MediaPlayer mediaPlayer;
    Handler handler = new Handler();
    Animation anim;
    int width_chuan, height_chuan, height_shui, allW, allH;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_da_boat, container, false);
        initView();
        new Thread(new Runnable() {
            @Override
            public void run() {
                initGift();
            }
        }).start();
        return view;
    }

    public int getIsSVIP() {
        return isSVIP;
    }

    public void setIsSVIP(int isSVIP) {
        this.isSVIP = isSVIP;
    }

    private int isSVIP; //1代表是svip

    private void initView() {
        img_chuan = (ImageView) view.findViewById(R.id.img_chuan);
        if (getIsSVIP() == 1) {
            img_chuan.setImageResource(R.mipmap.daboatchuansvip);
        }
        img_shui = (ImageView) view.findViewById(R.id.img_shui);
        drawableG = (AnimationDrawable) img_shui.getBackground();
//        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.daboat);
        width_chuan = UIUtils.getMeasuredWidth(img_chuan);
        height_chuan = UIUtils.getMeasuredHeight(img_chuan);
        height_shui = UIUtils.getMeasuredHeight(img_shui);
        allW = ScreenUtil.getScreenWidth(getActivity());
        allH = ScreenUtil.getScreenHeight(getActivity());
        RelativeLayout.LayoutParams layoutParamsC = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParamsC.setMargins(0, allH - height_shui - height_chuan + 160, 0, 0);
        img_chuan.setLayoutParams(layoutParamsC);
    }

    private void initGift() {
        anim = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_chuan);
        LinearInterpolator lir = new LinearInterpolator();
        anim.setInterpolator(lir);
        img_chuan.setAnimation(anim);
        final ObjectAnimator objectAnimatorX = ObjectAnimator.ofFloat(img_chuan, "translationX", allW, -width_chuan - 150).setDuration(7000);
        objectAnimatorX.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                img_chuan.setVisibility(View.GONE);
                anim.cancel();
                img_chuan.setAnimation(null);
                img_shui.setVisibility(View.GONE);
                drawableG.stop();
                drawableG = null;
                if (null != mLoadComplete) {
                    mLoadComplete.loadComplete();
                }
            }

        });
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                img_chuan.setVisibility(View.VISIBLE);
                drawableG.start();
                objectAnimatorX.start();
//                mediaPlayer.start();
            }
        }, 100);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        mediaPlayer.stop();
//        mediaPlayer = null;
    }

    private LoadComplete mLoadComplete;

    public interface LoadComplete {
        void loadComplete();
    }

    public void setLoadComplete(LoadComplete loadComplete) {
        mLoadComplete = loadComplete;
    }

}
