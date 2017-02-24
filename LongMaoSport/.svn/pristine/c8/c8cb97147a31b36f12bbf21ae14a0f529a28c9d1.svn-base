package com.live.longmao.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.live.longmao.R;
import com.live.longmao.adapter.RechargeAdapter;
import com.live.longmao.fragment.BeanFragment;
import com.live.longmao.fragment.CoinFragment;
import com.live.longmao.util.ChangeHeight;
import com.live.longmao.views.customviews.BaseFragmentActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HPDN on 2016/7/9.
 */
public class RechargeActivity extends BaseFragmentActivity implements View.OnClickListener{

    private ImageButton iv_back, iv_add;
    private TextView tv_title, tv_add;
    private RelativeLayout activity_base_title_rl;
    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
    private RechargeAdapter rechargeAdapter;

    private ViewPager mPageVp;
    /**
     * Tab显示内容TextView
     */
    private LinearLayout id_tab_chat_ll,id_tab_contacts_ll;
    private TextView id_zhifubao_tv, id_wechat_tv;
    /**
     * Fragment
     */
    private BeanFragment beanFragment;
    private CoinFragment coinFragment;
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
        setContentView(R.layout.activity_pay);
        findById();
        init();
        initView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ChangeHeight.changeLH(this, activity_base_title_rl);
        }
    }
    private void initView() {
        activity_base_title_rl = (RelativeLayout) findViewById(R.id.activity_base_title_rl);
        iv_back = (ImageButton) findViewById(R.id.iv_back);
     //   iv_add = (ImageButton) findViewById(R.id.iv_add);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_add = (TextView) findViewById(R.id.tv_add);
        iv_back.setOnClickListener(this);
    //    iv_add.setOnClickListener(this);
        tv_add.setOnClickListener(this);
    }


    private void findById() {
        id_tab_chat_ll = (LinearLayout) this.findViewById(R.id.id_tab_chat_ll);
        id_tab_contacts_ll = (LinearLayout) this.findViewById(R.id.id_tab_contacts_ll);
        id_zhifubao_tv = (TextView) this.findViewById(R.id.id_zhifubao_tv);
        id_wechat_tv = (TextView) this.findViewById(R.id.id_wechat_tv);
        id_tab_chat_ll.setOnClickListener(this);
        id_tab_contacts_ll.setOnClickListener(this);
        id_zhifubao_tv.setOnClickListener(this);
        id_wechat_tv.setOnClickListener(this);

        mPageVp = (ViewPager) this.findViewById(R.id.id_page_vp);
    }

    private void init() {
        coinFragment = new CoinFragment();
        beanFragment = new BeanFragment();
        mFragmentList.add(coinFragment);
        mFragmentList.add(beanFragment);

        rechargeAdapter = new RechargeAdapter(
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
            }

            @Override
            public void onPageSelected(int position) {
                resetTextView();
                switch (position) {
                    case 0:
                        id_tab_chat_ll.setBackgroundColor(getResources().getColor(R.color.white));
                        id_zhifubao_tv.setTextColor(getResources().getColor(R.color.lab_on));
                        id_tab_contacts_ll.setBackgroundColor(getResources().getColor(R.color.bg_un));
                        id_wechat_tv.setTextColor(getResources().getColor(R.color.lab_un));
                        break;
                    case 1:
                        id_tab_contacts_ll.setBackgroundColor(getResources().getColor(R.color.white));
                        id_wechat_tv.setTextColor(getResources().getColor(R.color.lab_on));
                        id_tab_chat_ll.setBackgroundColor(getResources().getColor(R.color.bg_un));
                        id_zhifubao_tv.setTextColor(getResources().getColor(R.color.lab_un));
                        break;
                }
                currentIndex = position;
            }
        });

    }


    /**
     * 重置颜色
     */
    private void resetTextView() {
        id_tab_chat_ll.setBackgroundColor(Color.WHITE);
        id_zhifubao_tv.setTextColor(Color.BLACK);
        id_tab_contacts_ll.setBackgroundColor(Color.WHITE);
        id_wechat_tv.setTextColor(Color.BLACK);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                this.finish();
                //    overridePendingTransition(R.anim.zoom , R.anim.zoomout);//动画
                break;
//            case R.id.iv_add:
//                startActivity(new Intent(RechargeActivity.this,PersonEarningsActivity.class));
//                break;
            case R.id.id_tab_chat_ll:
            case R.id.id_zhifubao_tv:
                mPageVp.setCurrentItem(0);
                break;
            case  R.id.id_wechat_tv:
            case R.id.id_tab_contacts_ll:
                mPageVp.setCurrentItem(1);
                break;
            default:
                break;
        }
    }
}
