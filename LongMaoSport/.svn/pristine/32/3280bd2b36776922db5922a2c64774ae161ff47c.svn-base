package com.live.longmao.activity.person;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.live.longmao.R;
import com.live.longmao.adapter.RechargeAdapter;
import com.live.longmao.dlg.person.PersonShareDlg;
import com.live.longmao.fragment.person.ZBPersonFragment;
import com.live.longmao.fragment.person.ZBPersonPhotoFragment;
import com.live.longmao.util.ChangeHeight;
import com.live.longmao.views.customviews.BaseFragmentActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HPDN on 2017/1/9.
 */
public class ZBPersonActivity extends BaseFragmentActivity implements View.OnClickListener {

    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
    private RechargeAdapter rechargeAdapter;

    private ViewPager mPageVp;

    private TextView tv_person_one, tv_person_two, tv_line1, tv_line2;
    private RelativeLayout activity_base_title_rl, tv_test5;
    private ImageButton ib_share;

    private ZBPersonFragment zbPersonFragment;
    private ZBPersonPhotoFragment zbPersonPhotoFragment;

    private PersonShareDlg personShareDlg;


    /**
     * ViewPager的当前选中页
     */
    private int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        setContentView(R.layout.activity_zb_person);
        //  setTitle("正在直播......");

        activity_base_title_rl = (RelativeLayout) findViewById(R.id.activity_base_title_rl);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ChangeHeight.changeLH(this, activity_base_title_rl);
        }

        findById();
        init();

    }

    private void findById() {
        tv_person_one = (TextView) findViewById(R.id.tv_person_one);
        tv_person_two = (TextView) findViewById(R.id.tv_person_two);
        tv_line1 = (TextView) findViewById(R.id.tv_line1);
        tv_line2 = (TextView) findViewById(R.id.tv_line2);
        ib_share = (ImageButton) findViewById(R.id.ib_share);
        tv_test5 = (RelativeLayout) findViewById(R.id.tv_test5);
        tv_person_one.setOnClickListener(this);
        tv_person_two.setOnClickListener(this);
        ib_share.setOnClickListener(this);
        tv_test5.setOnClickListener(this);

        mPageVp = (ViewPager) findViewById(R.id.id_page_vp);
    }


    private void init() {
        zbPersonFragment = new ZBPersonFragment();
        zbPersonPhotoFragment = new ZBPersonPhotoFragment();
        mFragmentList.add(zbPersonFragment);
        mFragmentList.add(zbPersonPhotoFragment);

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
                        tv_person_one.setTextColor(getResources().getColor(R.color.lab_on));
                        tv_person_two.setTextColor(getResources().getColor(R.color.lab_un));
                        tv_line1.setVisibility(View.VISIBLE);
                        tv_line2.setVisibility(View.INVISIBLE);
                        break;
                    case 1:
                        tv_person_two.setTextColor(getResources().getColor(R.color.lab_on));
                        tv_person_one.setTextColor(getResources().getColor(R.color.lab_un));
                        tv_line1.setVisibility(View.INVISIBLE);
                        tv_line2.setVisibility(View.VISIBLE);
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
        tv_person_one.setTextColor(Color.BLACK);
        tv_person_two.setTextColor(Color.BLACK);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_person_one:
                mPageVp.setCurrentItem(0);
                break;
            case R.id.tv_person_two:
                mPageVp.setCurrentItem(1);
                break;
            case R.id.ib_share:
                initPersonShare();
                break;
            case R.id.tv_test5:
                startActivity(new Intent(ZBPersonActivity.this, PersonRankActivity.class));
                break;
        }

    }

    private void initPersonShare() {

        personShareDlg = new PersonShareDlg();
        personShareDlg.show(getFragmentManager(), "");
        personShareDlg.setAllClick(new PersonShareDlg.OnClickAllListener() {
            @Override
            public void onClickJvBao() {

            }

            @Override
            public void onClickLaHei() {

            }
        });

    }
}
