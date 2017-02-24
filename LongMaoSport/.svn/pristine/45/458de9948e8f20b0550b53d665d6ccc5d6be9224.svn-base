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
public class RoadsterFragment extends Fragment {
    private View view;
    ImageView img_roadster;
//    private AnimationDrawable RoadsterAniDraw;
    Handler handler = new Handler();
    SceneAnimation anim;
    private int[] meetPics = new int[]{
            R.mipmap.hanni_gift_car1, R.mipmap.hanni_gift_car2, R.mipmap.hanni_gift_car3,
            R.mipmap.hanni_gift_car4, R.mipmap.hanni_gift_car5, R.mipmap.hanni_gift_car6,
            R.mipmap.hanni_gift_car7, R.mipmap.hanni_gift_car8, R.mipmap.hanni_gift_car9,
            R.mipmap.hanni_gift_car10, R.mipmap.hanni_gift_car11, R.mipmap.hanni_gift_car12,
            R.mipmap.hanni_gift_car13, R.mipmap.hanni_gift_car14, R.mipmap.hanni_gift_car15,
            R.mipmap.hanni_gift_car16, R.mipmap.hanni_gift_car17, R.mipmap.hanni_gift_car18,
            R.mipmap.hanni_gift_car19, R.mipmap.hanni_gift_car20, R.mipmap.hanni_gift_car21,
            R.mipmap.hanni_gift_car22, R.mipmap.hanni_gift_car23, R.mipmap.hanni_gift_car24,
            R.mipmap.hanni_gift_car25, R.mipmap.hanni_gift_car26, R.mipmap.hanni_gift_car27,
            R.mipmap.hanni_gift_car28, R.mipmap.hanni_gift_car29, R.mipmap.hanni_gift_car30,
            R.mipmap.hanni_gift_car31, R.mipmap.hanni_gift_car32, R.mipmap.hanni_gift_car33,
            R.mipmap.hanni_gift_car34, R.mipmap.hanni_gift_car35, R.mipmap.hanni_gift_car36,
            R.mipmap.hanni_gift_car37, R.mipmap.hanni_gift_car38, R.mipmap.hanni_gift_car40,
            R.mipmap.hanni_gift_car41, R.mipmap.hanni_gift_car42, R.mipmap.hanni_gift_car43,
            R.mipmap.hanni_gift_car44, R.mipmap.hanni_gift_car45, R.mipmap.hanni_gift_car46,
            R.mipmap.hanni_gift_car47, R.mipmap.hanni_gift_car48, R.mipmap.hanni_gift_car49,
            R.mipmap.hanni_gift_car50, R.mipmap.hanni_gift_car51, R.mipmap.hanni_gift_car52,
            R.mipmap.hanni_gift_car52, R.mipmap.hanni_gift_car53, R.mipmap.hanni_gift_car54,
            R.mipmap.hanni_gift_car55, R.mipmap.hanni_gift_car56, R.mipmap.hanni_gift_car57,
            R.mipmap.hanni_gift_car58, R.mipmap.hanni_gift_car59, R.mipmap.hanni_gift_car60,
            R.mipmap.hanni_gift_car61, R.mipmap.hanni_gift_car62, R.mipmap.hanni_gift_car63,
            R.mipmap.hanni_gift_car64, R.mipmap.hanni_gift_car65, R.mipmap.hanni_gift_car66,
            R.mipmap.hanni_gift_car67, R.mipmap.hanni_gift_car68, R.mipmap.hanni_gift_car69,
            R.mipmap.hanni_gift_car70};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_roadster, container, false);
        initView();
        anim= new SceneAnimation(getActivity(),img_roadster, meetPics, 50);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                img_roadster.setVisibility(View.GONE);
                if(null!=mLoadComplete)
                {
                    anim = null;
                    mLoadComplete.loadComplete();
                }
            }
        },90*meetPics.length);
        return view;
    }

    private void initView() {
        img_roadster = (ImageView) view.findViewById(R.id.img_roadster);
        img_roadster.setVisibility(View.VISIBLE);
    }

//    private void gifAni() {
//        RoadsterAniDraw = (AnimationDrawable) img_roadster.getDrawable();
//        int duration = 0;
//        for(int i=0;i<RoadsterAniDraw.getNumberOfFrames();i++){
//            duration += RoadsterAniDraw.getDuration(i);
//        }
//        handler.postDelayed(new Runnable() {
//            public void run() {
//                RoadsterAniDraw.stop();
//                img_roadster.setVisibility(View.GONE);
//                if(null!=mLoadComplete)
//                {
//                    mLoadComplete.loadComplete();
//                }
//            }
//        }, duration+10);
//        img_roadster.setVisibility(View.VISIBLE);
//        RoadsterAniDraw.start();
//    }

    private LoadComplete mLoadComplete;

    public interface LoadComplete {
        void loadComplete();
    }

    public void setLoadComplete(LoadComplete loadComplete) {
        mLoadComplete = loadComplete;
    }
}