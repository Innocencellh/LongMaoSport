package com.live.longmao.fragment.gif;

import android.app.Fragment;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.live.longmao.R;
import com.live.longmao.util.SceneAnimation;

/**
 * Created by Administrator on 2016/8/25.
 */
public class FireworksFragment extends Fragment {
    private View view;
    ImageView img_fireworks;
//    private AnimationDrawable FireworkAniDraw;
    SceneAnimation anim;
    Handler handler = new Handler();
    private int[] meetPics = new int[]{
            R.mipmap.fireworks_1, R.mipmap.fireworks_2, R.mipmap.fireworks_3,
            R.mipmap.fireworks_4, R.mipmap.fireworks_5, R.mipmap.fireworks_6,
            R.mipmap.fireworks_7, R.mipmap.fireworks_8, R.mipmap.fireworks_9,
            R.mipmap.fireworks_10, R.mipmap.fireworks_flower1, R.mipmap.fireworks_flower2,
            R.mipmap.fireworks_flower3, R.mipmap.fireworks_flower4};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fireworks, container, false);
        initView();
        anim= new SceneAnimation(getActivity(),img_fireworks, meetPics, 100);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                img_fireworks.setVisibility(View.GONE);
                if (null != mLoadComplete) {
                    anim = null;
                    mLoadComplete.loadComplete();
                }
            }
        }, 120 * meetPics.length);
//        gifAni();
        return view;
    }

    private void initView() {
        img_fireworks = (ImageView) view.findViewById(R.id.img_fireworks);
        img_fireworks.setVisibility(View.VISIBLE);
    }

//    private void gifAni() {
//        FireworkAniDraw = (AnimationDrawable) img_fireworks.getDrawable();
//        int duration = 0;
//        for(int i=0;i<FireworkAniDraw.getNumberOfFrames();i++){
//            duration += FireworkAniDraw.getDuration(i);
//        }
//        handler.postDelayed(new Runnable() {
//            public void run() {
//                FireworkAniDraw.stop();
//                img_fireworks.setVisibility(View.GONE);
//                if(null!=mLoadComplete)
//                {
//
//                    mLoadComplete.loadComplete();
//                }
//            }
//        }, duration+10);
//        img_fireworks.setVisibility(View.VISIBLE);
//        FireworkAniDraw.start();
//    }

    private LoadComplete mLoadComplete;

    public interface LoadComplete {
        void loadComplete();
    }

    public void setLoadComplete(LoadComplete loadComplete) {
        mLoadComplete = loadComplete;
    }
}