package com.live.longmao.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.live.longmao.R;
import com.live.longmao.adapter.CheckStoreAdapter;
import com.live.longmao.util.ChangeHeight;
import com.live.longmao.util.DlgTellUtil;
import com.live.longmao.view.ObservableScrollView;
import com.live.longmao.views.customviews.BaseActivity;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/15 0015.
 */
public class CheckStoreActivity extends BaseActivity implements View.OnClickListener {
    private ViewPager viewPager;
    private RecyclerView recyclerView;
    private TextView tv_select_num, phone;
    private ImageButton back;
    private int imageHeight;
    ArrayList<View> imageList;
    MyAdapter adapter;
    Context mContext;
    CheckStoreAdapter recyclerviewAdapter;
    RelativeLayout activity_base_title_rl;
    ObservableScrollView checkinfo_scrollview;
    private int photoCount = 12;
    private int[] image = {R.mipmap.wifi, R.mipmap.stopcar, R.mipmap.icrem, R.mipmap.card, R.mipmap.time};
    private String[] text = {"免费wifi", "免费停车", "免费冷饮", "刷卡预约", "预定预约"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        mContext = this;
        setContentView(R.layout.activity_check_store);
        phone = (TextView) findViewById(R.id.phone);
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DlgTellUtil.startDlgCall(CheckStoreActivity.this, "10086");
            }
        });
        init();
        initListeners();
        initScrollView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ChangeHeight.changeRH(this, activity_base_title_rl);
        }
    }

    private void initListeners() {
        // 获取顶部图片高度后，设置滚动监听
        ViewTreeObserver vto = viewPager.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                viewPager.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
                imageHeight = viewPager.getHeight() / 2;
            }
        });
    }
    private void initScrollView() {
        checkinfo_scrollview = (ObservableScrollView) findViewById(R.id.checkinfo_scrollview);
        checkinfo_scrollview.setScrollViewListener(new ObservableScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {Log.e("ObservableScrollView", "x:" + x + "y:" + y + "oldx:" + oldx + "oldy:" + oldy);
                if (y <= 0) {
                    activity_base_title_rl.setBackgroundColor(Color.argb((int) 0, 91, 78, 97));//AGB由相关工具获得，或者美工提供
                } else if (y > 0 && y <= imageHeight) {
                    float scale = (float) y / imageHeight;
                    float alpha = (255 * scale);
                    activity_base_title_rl.setBackgroundColor(Color.argb((int) alpha, 91, 78, 97));
                } else {
                    activity_base_title_rl.setBackgroundColor(Color.argb((int) 255, 91, 78, 97));
                }
            }
        });
        activity_base_title_rl = (RelativeLayout) findViewById(R.id.activity_base_title_rl);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        back = (ImageButton) findViewById(R.id.iv_back);
        back.setOnClickListener(this);
    }

    private void init() {
        tv_select_num = (TextView) findViewById(R.id.tv_select_num);
        tv_select_num.setText("1/" + photoCount);
        activity_base_title_rl = (RelativeLayout) findViewById(R.id.activity_base_title_rl);
        viewPager = (ViewPager) findViewById(R.id.check_store_viewpager);
        recyclerView = (RecyclerView) findViewById(R.id.check_store_recyclerview);
        back = (ImageButton) findViewById(R.id.iv_back);
        back.setOnClickListener(this);
        if (imageList == null) {
            imageList = new ArrayList<>();
            for (int i = 0; i < photoCount; i++) {
                View view = getLayoutInflater().inflate(R.layout.item_check_store, null);
                ImageView imagview = (ImageView) view.findViewById(R.id.image_check_header);
                imagview.setImageResource(R.mipmap.icon_star_bg);
                imageList.add(view);
            }
            adapter = new MyAdapter();
            viewPager.setAdapter(adapter);
        }

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager);
        recyclerviewAdapter = new CheckStoreAdapter(CheckStoreActivity.this, image, text);
        recyclerView.setAdapter(recyclerviewAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tv_select_num.setText((1 + position) + "/" + photoCount);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }

    class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imageList == null ? 0 : imageList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            container.addView(imageList.get(position));
            return imageList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("SplashScreen");
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        MobclickAgent.onPageEnd("SplashScreen");
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
