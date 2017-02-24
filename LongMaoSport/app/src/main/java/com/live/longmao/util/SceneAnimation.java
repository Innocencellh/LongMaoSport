package com.live.longmao.util;

import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by Administrator on 2016/8/26.
 */
public class SceneAnimation {
    private ImageView mImageView;
    private int[] mFrameRess;
    private int[] mDurations;
    private int mDuration;

    private int mLastFrameNo;
    private long mBreakDelay;
    private Activity mActivity;

    public SceneAnimation(ImageView pImageView, int[] pFrameRess,
                          int[] pDurations) {
        mImageView = pImageView;
        mFrameRess = pFrameRess;
        mDurations = pDurations;
        mLastFrameNo = pFrameRess.length - 1;

        mImageView.setBackgroundResource(mFrameRess[0]);
        play(1);
    }

    public SceneAnimation(Activity Activity,ImageView pImageView, int[] pFrameRess, int pDuration) {
        mActivity = Activity;
        mImageView = pImageView;
        mFrameRess = pFrameRess;
        mDuration = pDuration;
        mLastFrameNo = pFrameRess.length - 1;
        playRoadster(1);
    }

    public SceneAnimation(ImageView pImageView, int[] pFrameRess,
                          int pDuration, long pBreakDelay) {
        mImageView = pImageView;
        mFrameRess = pFrameRess;
        mDuration = pDuration;
        mLastFrameNo = pFrameRess.length - 1;
        mBreakDelay = pBreakDelay;

        mImageView.setImageResource(mFrameRess[0]);
        playConstant(0);
    }

    private void play(final int pFrameNo) {
        mImageView.postDelayed(new Runnable() {
            public void run() {
                mImageView.setImageResource(mFrameRess[pFrameNo]);
                if (pFrameNo == mLastFrameNo)
                    return;
                else
                    play(pFrameNo + 1);
            }
        }, mDurations[pFrameNo]);
    }

    private void playConstant(final int pFrameNo) {
        mImageView.postDelayed(new Runnable() {
            public void run() {
                mImageView.setImageResource(mFrameRess[pFrameNo]);

                if (pFrameNo == mLastFrameNo)
                    return;
                else
                    playConstant(pFrameNo + 1);
            }
        }, pFrameNo == mLastFrameNo && mBreakDelay > 0 ? mBreakDelay
                : mDuration);
    }
    private void playRoadster(final int pFrameNo) {
        mImageView.postDelayed(new Runnable() {
            public void run() {
                mImageView.setImageResource(mFrameRess[pFrameNo]);

                if (pFrameNo == mLastFrameNo) {
                    mImageView.setImageBitmap(null);
                    return;
                }
                else
                    playRoadster(pFrameNo + 1);
            }
        }, mDuration);
    }
}
