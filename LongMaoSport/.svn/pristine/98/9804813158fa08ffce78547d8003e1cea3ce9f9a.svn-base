package com.live.longmao.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.live.longmao.R;
import com.live.longmao.adapter.ReputationAdapter;
import com.live.longmao.fragment.ReputationExchangeFragment;
import com.live.longmao.fragment.ReputationRecordFragment;
import com.live.longmao.util.ChangeHeight;
import com.live.longmao.views.customviews.BaseFragmentActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HPDN on 2016/7/10.
 */
public class PersonReputationActivity extends BaseFragmentActivity implements View.OnClickListener{

    private ImageButton iv_back, iv_add;
    private TextView tv_title, tv_add;
    private RelativeLayout activity_base_title_rl;
    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
    private ReputationAdapter rechargeAdapter;

    private ViewPager mPageVp;
    /**
     * Tab显示内容TextView
     */
    private TextView id_zhifubao_tv, id_wechat_tv;
    /**
     * Tab的那个引导线
     */
    private ImageView mTabLineIv;
    /**
     * Fragment
     */
    private ReputationRecordFragment reputationRecordFragment;
    private ReputationExchangeFragment reputationExchangeFragment;
    /**
     * ViewPager的当前选中页
     */
    private int currentIndex;
    /**
     * 屏幕的宽度
     */
    private int screenWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_reputation);
        findById();
        init();
        initTabLineWidth();
        initView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ChangeHeight.changeRH(this, activity_base_title_rl);
        }
    }
    private void initView() {
        activity_base_title_rl = (RelativeLayout) findViewById(R.id.activity_base_title_rl);
        iv_back = (ImageButton) findViewById(R.id.iv_back);
        iv_add = (ImageButton) findViewById(R.id.iv_add);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_add = (TextView) findViewById(R.id.tv_add);
        iv_back.setOnClickListener(this);
        iv_add.setOnClickListener(this);
        tv_add.setOnClickListener(this);
    }


    private void findById() {
        id_zhifubao_tv = (TextView) this.findViewById(R.id.id_zhifubao_tv);
        id_wechat_tv = (TextView) this.findViewById(R.id.id_wechat_tv);
        id_zhifubao_tv.setOnClickListener(this);
        id_wechat_tv.setOnClickListener(this);

        mTabLineIv = (ImageView) this.findViewById(R.id.id_tab_line_iv);

        mPageVp = (ViewPager) this.findViewById(R.id.id_page_vp);
    }

    private void init() {
        reputationRecordFragment = new ReputationRecordFragment();
        reputationExchangeFragment = new ReputationExchangeFragment();
        mFragmentList.add(reputationRecordFragment);
        mFragmentList.add(reputationExchangeFragment);

        rechargeAdapter = new ReputationAdapter(
                this.getSupportFragmentManager(), mFragmentList);
        mPageVp.setAdapter(rechargeAdapter);
        mPageVp.setCurrentItem(0);

        mPageVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            /**
             * state滑动中的状态 有三种状态（0，1，2） 1：正在滑动 2：滑动完毕 0：什么都没做。
             */
            @Override
            public void onPageScrollStateChanged(int state) {

            }

            /**
             * position :当前页面，及你点击滑动的页面 offset:当前页面偏移的百分比
             * offsetPixels:当前页面偏移的像素位置
             */
            @Override
            public void onPageScrolled(int position, float offset,
                                       int offsetPixels) {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv
                        .getLayoutParams();

                Log.e("offset:", offset + "");
                /**
                 * 利用currentIndex(当前所在页面)和position(下一个页面)以及offset来
                 * 设置mTabLineIv的左边距 滑动场景：
                 * 记3个页面,
                 * 从左到右分别为0,1,2
                 * 0->1; 1->2; 2->1; 1->0
                 */

                if (currentIndex == 0 && position == 0)// 0->1
                {
                    lp.leftMargin = (int) (offset * (screenWidth * 1.0 / 2) + currentIndex
                            * (screenWidth / 2));

                } else if (currentIndex == 1 && position == 0) // 1->0
                {
                    lp.leftMargin = (int) (-(1 - offset)
                            * (screenWidth * 1.0 / 2) + currentIndex
                            * (screenWidth / 2));

                } else if (currentIndex == 1 && position == 1) // 1->2
                {
                    lp.leftMargin = (int) (offset * (screenWidth * 1.0 / 2) + currentIndex
                            * (screenWidth /2));
                } else if (currentIndex == 2 && position == 1) // 2->1
                {
                    lp.leftMargin = (int) (-(1 - offset)
                            * (screenWidth * 1.0 / 2) + currentIndex
                            * (screenWidth / 2));
                }
                mTabLineIv.setLayoutParams(lp);
            }

            @Override
            public void onPageSelected(int position) {
                resetTextView();
                switch (position) {
                    case 0:
                        id_zhifubao_tv.setTextColor(getResources().getColor(R.color.title_color));
                        break;
                    case 1:
                        id_wechat_tv.setTextColor(getResources().getColor(R.color.title_color));
                        break;
                }
                currentIndex = position;
            }
        });

    }

    /**
     * 设置滑动条的宽度为屏幕的1/3(根据Tab的个数而定)
     */
    private void initTabLineWidth() {
        DisplayMetrics dpMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay()
                .getMetrics(dpMetrics);
        screenWidth = dpMetrics.widthPixels;
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv
                .getLayoutParams();
        lp.width = screenWidth / 2;
        mTabLineIv.setLayoutParams(lp);
    }

    /**
     * 重置颜色
     */
    private void resetTextView() {
        id_zhifubao_tv.setTextColor(Color.BLACK);
        id_wechat_tv.setTextColor(Color.BLACK);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                this.finish();
                //    overridePendingTransition(R.anim.zoom , R.anim.zoomout);
                break;
            case R.id.id_zhifubao_tv:
                mPageVp.setCurrentItem(0);
                break;
            case R.id.id_wechat_tv:
                mPageVp.setCurrentItem(1);
                break;
            default:
                break;
        }
    }
}
