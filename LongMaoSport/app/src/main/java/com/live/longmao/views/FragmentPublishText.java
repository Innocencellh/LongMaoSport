package com.live.longmao.views;


import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.live.longmao.R;
import com.live.longmao.adapter.FindActiviyAdapter;
import com.live.longmao.adapter.FindAdapter;
import com.live.longmao.activity.ExchangeCentreActivity;
import com.live.longmao.find.FindSecondActivity;
import com.live.longmao.model.ListLiveInfo;
import com.live.longmao.model.LiveInfo;
import com.live.longmao.presenters.LiveListViewHelper;
import com.live.longmao.presenters.viewinface.LiveListView;
import com.live.longmao.util.ChangeHeight;
import com.live.longmao.util.GlideUtil;
import com.live.longmao.view.BottomScrollView;
import com.live.longmao.view.GridViewForScrollView;

import java.util.ArrayList;
import java.util.List;


/**
 * 活动页面
 */
public class FragmentPublishText extends Fragment implements View.OnClickListener, LiveListView, SwipeRefreshLayout.OnRefreshListener{
    private static final String TAG = "FragmentPublishText";

    private ConvenientBanner banner;
    View headerView;
    private List<String> urlList = null;
    private GridViewForScrollView mLiveGrid;
    private GridViewForScrollView mFiveGrid;
    private ArrayList<LiveInfo> liveList = new ArrayList<LiveInfo>();
    private ArrayList<LiveInfo> liveListT = new ArrayList<LiveInfo>();
    private FindAdapter adapter;
    private FindActiviyAdapter activityadapter;
    private LiveListViewHelper mLiveListViewHelper;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    BottomScrollView scroll_main;

    private TextView more, more_activity,activitytitle;
    RelativeLayout rl_near;

    View v;
//    FullDlg dlg;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (v == null) {
            v = inflater.inflate(R.layout.publishfragment_layout, container, false);
//            dlg = new FullDlg();
//            dlg.show(getChildFragmentManager(),"FullDlg");
            initBanner();
            initView();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                ChangeHeight.changeLH(getContext(), activitytitle);
            }
            setBannerTouch();
            mLiveListViewHelper.getPageData(1);
        }
        ViewGroup parent = (ViewGroup) v.getParent();
        if (parent != null) {
            parent.removeView(v);
        }
        return v;
    }

    private void initView() {
        Log.e(TAG, TAG);
        mLiveListViewHelper = new LiveListViewHelper(this);
        scroll_main = (BottomScrollView) v.findViewById(R.id.scroll_main);
        mLiveGrid = (GridViewForScrollView) v.findViewById(R.id.live_grid);
        mFiveGrid = (GridViewForScrollView) v.findViewById(R.id.find_grid);

        activitytitle = (TextView)v.findViewById(R.id.activitytitle);
        more = (TextView) v.findViewById(R.id.more);
        more.setOnClickListener(this);
        more_activity = (TextView) v.findViewById(R.id.more_activity);

        mSwipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipe_refresh_layout_near);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.title_color);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        adapter = new FindAdapter(getActivity(), R.layout.item_find, liveListT);
        activityadapter = new FindActiviyAdapter(getActivity(), R.layout.item_find, liveList);
        mLiveGrid.setAdapter(adapter);
        mFiveGrid.setAdapter(activityadapter);
//        scroll_main.setOnScrollToBottomLintener(new BottomScrollView.OnScrollToBottomListener() {
//            @Override
//            public void onScrollBottomListener(boolean isBottom) {
//                if (isBottom) {
//                    mLiveListViewHelper.getPageData();
//                }
//            }
//        });

        mLiveGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),FindSecondActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initBanner() {
        headerView = v.findViewById(R.id.include_banner);
        banner = (ConvenientBanner) headerView.findViewById(R.id.banner);
        rl_near= (RelativeLayout) headerView.findViewById(R.id.rl_near);
        rl_near.setVisibility(View.GONE);
        urlList = new ArrayList<>();
        urlList.add("http://www.18183.com/uploads/140115/21-140115192239125.jpg");
        urlList.add("http://upload.shunwang.com/2015/1224/1450927480194.jpg");
        urlList.add("http://img5.duitang.com/uploads/blog/201405/12/20140512161725_J2zBw.jpeg");
        urlList.add("http://pic.baike.soso.com/p/20131221/20131221161155-597537905.jpg");
        urlList.add("http://g.hiphotos.baidu.com/zhidao/pic/item/b17eca8065380cd7d708b425a144ad34588281e7.jpg");
        banner.setPages(new CBViewHolderCreator<LocalImageHolderView>() {
            @Override
            public LocalImageHolderView createHolder() {
                return new LocalImageHolderView();
            }
        }, urlList);
        //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
        banner.setPageIndicator(new int[]{R.mipmap.white_dot, R.mipmap.blue_dot})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
        banner.startTurning(4000);
    }

    private void setBannerTouch() {
        banner.getViewPager().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        mSwipeRefreshLayout.setEnabled(false);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        mSwipeRefreshLayout.setEnabled(true);
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, TAG);
//        mLiveListViewHelper.getPageData();
//        v.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                v.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//                mSwipeRefreshLayout.setRefreshing(true);
//                mLiveListViewHelper.getPageData();
//            }
//        });
    }


    public class LocalImageHolderView implements Holder<String> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, final int position, final String data) {
            GlideUtil.getInstance().load(context, imageView, data);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e(TAG, "OnClickListener" + position);
                }
            });
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void showFirstPage(ListLiveInfo result) {
        // dlg.dismiss();
        mSwipeRefreshLayout.setRefreshing(false);
        liveList.clear();
        liveListT.clear();
        if (null != result&&null!=result.getBody()) {
            List<LiveInfo> body = result.getBody();
            for (LiveInfo item : body) {
                if (liveList.size() < 4) {
                    liveList.add(item);
                    liveListT.add(item);
                }
            }
        }
        adapter.notifyDataSetChanged();
        activityadapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String msg) {
        mSwipeRefreshLayout.setRefreshing(false);
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        //dlg.show(getChildFragmentManager(),"FullDlg");
        mLiveListViewHelper.getPageData(1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.more:
                //startActivity(new Intent(getActivity(), ExchangeActivity.class));
                startActivity(new Intent(getActivity(), ExchangeCentreActivity.class));
                break;
        }

    }
}
