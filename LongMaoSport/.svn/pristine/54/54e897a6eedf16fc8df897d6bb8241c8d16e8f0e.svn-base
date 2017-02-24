package com.live.longmao.fragment.gif;

import android.app.Fragment;
import android.graphics.drawable.AnimationDrawable;
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

import com.live.longmao.R;

/**
 * Created by Administrator on 2016/8/25.
 */
public class XinDongFragment extends Fragment {
    private View view;
    private ImageView img_aixin;
    Animation anim;
    Handler handler = new Handler();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_xin_dong,container,false);
        initView();
        return view;
    }
    private void initView()
    {
        img_aixin = (ImageView) view.findViewById(R.id.img_aixin);
        anim = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_xindong);
        img_aixin.setAnimation(anim);
        img_aixin.setVisibility(View.VISIBLE);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                img_aixin.setVisibility(View.GONE);
                anim.cancel();
                anim=null;
                img_aixin.setAnimation(null);
                mLoadComplete.loadComplete();
            }
        },3000);
    }
    private LoadComplete mLoadComplete;
    public interface LoadComplete
    {
        void loadComplete();
    }
    public void setLoadComplete(LoadComplete loadComplete)
    {
        mLoadComplete = loadComplete;
    }
}
