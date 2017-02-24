package com.live.longmao.fragment.gif;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.live.longmao.R;
import com.live.longmao.view.GiftRainView;

/**
 * Created by Administrator on 2016/8/25.
 */
public class RedRainFragment extends Fragment{
    private View view;
    private GiftRainView giftRainView;
    Handler handler = new Handler();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_red_rain, container, false);
        initView();
        return view;
    }
    private void initView()
    {
        giftRainView = (GiftRainView) view.findViewById(R.id.grv);
        giftRainView.setImages(R.mipmap.icon_hb);
        startRain();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                stopRain();
                mLoadComplete.loadComplete();
            }
        },6000);
    }
    private void startRain() {
        giftRainView.startRain();
    }

    private void stopRain() {
        giftRainView.stopRainDely();
    }

    private LoadComplete mLoadComplete;

    public interface LoadComplete {
        void loadComplete();
    }

    public void setLoadComplete(LoadComplete loadComplete) {
        mLoadComplete = loadComplete;
    }
}
