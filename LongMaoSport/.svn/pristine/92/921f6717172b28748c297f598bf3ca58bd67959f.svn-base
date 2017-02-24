package com.live.longmao.fragment.gif;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.app.Fragment;
import android.graphics.PointF;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.live.longmao.R;
import com.live.longmao.util.ScreenUtil;
import com.live.longmao.util.UIUtils;

/**
 * Created by Administrator on 2016/8/24.
 */
public class FlyingHouseFragment extends Fragment {
    private View view;
    private ValueAnimator valueAnimator;

    private int width, height;
    private int width_house, height_house;
    private ImageView img_house;
//    private MediaPlayer mediaPlayer;
    private ImageView img_fangzi, img_qiqiu;
    private LinearLayout ll_all;
    private ObjectAnimator objectAnimatorYF, objectAnimatorYQ;
    Handler handler = new Handler();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_flying_house, container, false);
        initView();
        new Thread(new Runnable() {
            @Override
            public void run() {
                initAni();
                initJAni();
            }
        }).start();
        return view;
    }

    private void initView() {
        img_house = (ImageView) view.findViewById(R.id.img_house);
        img_fangzi = (ImageView) view.findViewById(R.id.img_fangzi);
        img_qiqiu = (ImageView) view.findViewById(R.id.img_qiqiu);
        ll_all = (LinearLayout) view.findViewById(R.id.ll_all);
//        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.flyinghouse);
        width_house = UIUtils.getMeasuredWidth(img_house);
        height_house = UIUtils.getMeasuredHeight(img_house);
        width = ScreenUtil.getScreenWidth(getActivity()) / 2 - width_house / 2;
        height = ScreenUtil.getScreenHeight(getActivity()) / 2 - height_house / 2;

    }

    private void initAni() {
        valueAnimator = ValueAnimator.ofObject(new BezierEvaluator(), new PointF(-width_house, 0), new PointF(width, height));
        valueAnimator.setDuration(6000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pointF = (PointF) animation.getAnimatedValue();
                img_house.setX(pointF.x);
                img_house.setY(pointF.y);
            }

        });
        valueAnimator.setTarget(img_house);
        //valueAnimator.setRepeatCount(0);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        img_house.setVisibility(View.GONE);
                        ll_all.setVisibility(View.VISIBLE);
                        objectAnimatorYF.start();
                        objectAnimatorYQ.start();
                    }
                });
            }
        });
    }

    private void initJAni() {
        objectAnimatorYF = ObjectAnimator.ofFloat(img_fangzi, "translationY", 0, ScreenUtil.getScreenHeight(getActivity())).setDuration(2000);
        objectAnimatorYF.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                img_fangzi.setVisibility(View.GONE);
//                mediaPlayer.stop();
//                mediaPlayer = null;
                if (null != mLoadComplete) {
                    mLoadComplete.loadComplete();
                }
            }
        });
        objectAnimatorYQ = ObjectAnimator.ofFloat(img_qiqiu, "translationY", 0, -ScreenUtil.getScreenHeight(getActivity())).setDuration(2000);
        objectAnimatorYQ.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                img_qiqiu.setVisibility(View.GONE);
            }
        });
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                img_house.setVisibility(View.VISIBLE);
//                mediaPlayer.start();
                valueAnimator.start();
            }
        }, 50);
    }

    class BezierEvaluator implements TypeEvaluator<PointF> {

        @Override
        public PointF evaluate(float fraction, PointF startValue,
                               PointF endValue) {
            final float t = fraction;
            float oneMinusT = 1.0f - t;
            PointF point = new PointF();

            PointF point0 = (PointF) startValue;

            PointF point1 = new PointF();
            point1.set(width, 0);

            PointF point2 = new PointF();
            point2.set(0, height);

            PointF point3 = (PointF) endValue;

            point.x = oneMinusT * oneMinusT * oneMinusT * (point0.x)
                    + 3 * oneMinusT * oneMinusT * t * (point1.x)
                    + 3 * oneMinusT * t * t * (point2.x)
                    + t * t * t * (point3.x);

            point.y = oneMinusT * oneMinusT * oneMinusT * (point0.y)
                    + 3 * oneMinusT * oneMinusT * t * (point1.y)
                    + 3 * oneMinusT * t * t * (point2.y)
                    + t * t * t * (point3.y);
            return point;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private LoadComplete mLoadComplete;

    public interface LoadComplete {
        void loadComplete();
    }

    public void setLoadComplete(LoadComplete loadComplete) {
        mLoadComplete = loadComplete;
    }
}
