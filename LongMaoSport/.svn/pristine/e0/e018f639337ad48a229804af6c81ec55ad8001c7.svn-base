package com.live.longmao.activity.banner;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.live.longmao.R;
import com.live.longmao.util.DisplayUtil;
import com.live.longmao.views.BaseActivity;

import android.view.ViewGroup.LayoutParams;

/**
 * Created by HPDN on 2017/1/16.
 */
public class BannerTwoActivity extends BaseActivity {

    private ImageView iv_long_banner;
    private int height = 0;
    private int width = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_banner_two);
        setTitle("竞猜说明");
        initfvb();

    }

    private void initfvb() {

        iv_long_banner = (ImageView) findViewById(R.id.iv_long_banner);

        LayoutParams para;
        para = iv_long_banner.getLayoutParams();
//        iv_long_banner.setMaxWidth(com.live.longmao.util.DisplayUtil.getSysWidth(getApplication()));
//        iv_long_banner.setMaxHeight((int) (com.live.longmao.util.DisplayUtil.getSysWidth(getApplication())*3.9));
        para.width = DisplayUtil.getSysWidth(this);
        para.height = (int) (width * 7);
        Log.i("ssdfddd", "" + width);
        Log.i("ssdfddd", "" + height);
        iv_long_banner.setLayoutParams(para);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }
}
