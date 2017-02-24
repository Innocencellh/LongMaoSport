package com.live.longmao.views;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.live.longmao.R;
import com.live.longmao.fragment.live.FollowFragment;
import com.live.longmao.fragment.live.HotFragment;
import com.live.longmao.fragment.live.NearFragment;
import com.live.longmao.fragment.live.NewFragment;
import com.live.longmao.util.ChangeHeight;
import com.live.longmao.view.InsideViewPager;
import com.live.longmao.view.PagerSlidingTabStrip;


/**
 * 直播列表页面
 */
public class FragmentLiveList extends Fragment {
    private View v;
    private InsideViewPager mPager;// 页卡内容
    private RelativeLayout rl_title;
    private ImageView iv_search;

    public FragmentLiveList() {

    }

    PagerSlidingTabStrip tabStrip;
    private final static String[] pageTitle = {
            "关注", "热门", "最新", "附近"
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (v == null) {
            Log.e("MessageFragment", "MessageFragment");
            v = inflater.inflate(R.layout.liveframent_layout, null);
            initView();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                ChangeHeight.changeRH(getContext(), tabStrip);
                ChangeHeight.changeRH(getContext(), rl_title);
                ChangeHeight.setPadding(getContext(), iv_search);
            }
        }
        ViewGroup parent = (ViewGroup) v.getParent();
        if (parent != null) {
            parent.removeView(v);
        }
        return v;
    }

    private void initView() {
        iv_search = (ImageView) v.findViewById(R.id.iv_search);
        iv_search.setVisibility(View.GONE);
        rl_title = (RelativeLayout) v.findViewById(R.id.rl_title);
        mPager = (InsideViewPager) v.findViewById(R.id.vPager);
        tabStrip = (PagerSlidingTabStrip) v.findViewById(R.id.tabStrip);
        mPager.setAdapter(new SchemePagerAdapter(getChildFragmentManager()));
        mPager.setOffscreenPageLimit(1);
        tabStrip.setViewPager(mPager);
        mPager.setCurrentItem(1);
    }

    private class SchemePagerAdapter extends FragmentStatePagerAdapter {

        private Fragment[] frags = new Fragment[pageTitle.length];

        public SchemePagerAdapter(FragmentManager fm) {
            super(fm);
            frags[0] = new FollowFragment();
            frags[1] = new HotFragment();
            frags[2] = new NewFragment();
            frags[3] = new NearFragment();
        }

        @Override
        public Fragment getItem(int i) {
            return frags[i];
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // super.destroyItem(container, position, object);
        }

        @Override
        public int getCount() {
            return pageTitle.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return pageTitle[position];
        }
    }

}
