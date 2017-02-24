package com.live.longmao.find;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.live.longmao.R;
import com.live.longmao.util.ChangeHeight;
import com.live.longmao.view.ObservableScrollView;
import com.live.longmao.views.BaseActivity;

import java.util.ArrayList;

/**
 * Created by HPDN on 2016/7/8.
 */
public class FindSecondActivity extends AppCompatActivity implements View.OnClickListener {
    private WebView webView;
    private ViewPager viewPager;
    private int imageHeight;
    private int photoCount = 12;
    MyAdapter adapter;
    ArrayList<View> imageList;
    private TextView tv_select_num;
    private ImageButton iv_back;
    private RelativeLayout activity_base_title_rl;
    private ObservableScrollView scroll_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_exchange);
        viewPager = (ViewPager) findViewById(R.id.check_store_viewpager);
        scroll_view = (ObservableScrollView) findViewById(R.id.scroll_view);
        activity_base_title_rl = (RelativeLayout) findViewById(R.id.activity_base_title_rl);
        iv_back = (ImageButton) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ChangeHeight.changeRH(this, activity_base_title_rl);
        }
        init();
        initScrollView();
        initListeners();
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
        scroll_view = (ObservableScrollView) findViewById(R.id.scroll_view);
        scroll_view.setScrollViewListener(new ObservableScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
                Log.e("ObservableScrollView", "x:" + x + "y:" + y + "oldx:" + oldx + "oldy:" + oldy);
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
        iv_back = (ImageButton) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
    }

    private void init() {
        webView = (WebView) findViewById(R.id.webView);
        //WebView加载web资源
        webView.loadUrl("http://baidu.com");
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
        tv_select_num = (TextView) findViewById(R.id.tv_select_num);
        tv_select_num.setText("1/" + photoCount);
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
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                this.finish();
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

}
