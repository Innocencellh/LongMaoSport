package com.live.longmao.fragment.see;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.live.longmao.R;
import com.live.longmao.activity.banner.BannerOneActivity;
import com.live.longmao.activity.banner.BannerTwoActivity;
import com.live.longmao.adapter.see.HorizontalListView;
import com.live.longmao.adapter.see.HorizontalListViewAdapter;
import com.live.longmao.adapter.see.SeeHotAdapter;
import com.live.longmao.fragment.base.BaseFragment;
import com.live.longmao.model.CurLiveInfo;
import com.live.longmao.model.HotLiveInfo;
import com.live.longmao.model.MySelfInfo;
import com.live.longmao.model.NewsInfo;
import com.live.longmao.model.NewsListInfo;
import com.live.longmao.presenters.NewsListHelper;
import com.live.longmao.presenters.viewinface.HotListView;
import com.live.longmao.presenters.viewinface.NewsListView;
import com.live.longmao.util.Constants;
import com.live.longmao.util.GlideUtil;
import com.live.longmao.views.LiveActivity;
import com.live.longmao.xrecyclerview.ProgressStyle;
import com.live.longmao.xrecyclerview.XRecyclerView;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 直播列表页面
 */
public class SeeHotFragment extends BaseFragment implements View.OnClickListener, HotListView, NewsListView, SeeHotAdapter.OnItemClick {
    private static final String TAG = "SeeEntertainmentFragment";

    private XRecyclerView mRecyclerView;
    private SeeHotAdapter mAdapter;
    private ConvenientBanner banner;
    private TextView tv_title;
    View headerView;
    private List<String> urlList = null;

    HorizontalListView hListView;
    HorizontalListViewAdapter hListViewAdapter;
    View olderSelectView = null;



    //private ArrayList<HotInfo> liveList = new ArrayList<HotInfo>();
//    private HotListHelper hotListHelper;
    View v;
    private ArrayList<NewsInfo> liveList = new ArrayList<NewsInfo>();
    //private LiveListViewHelper mLiveListViewHelper;
    private NewsListHelper newsListHelper;
    /**
     * 标志位，标志已经初始化完成
     */
    private boolean isPrepared;
    /**
     * 是否已被加载过一次，第二次就不再去请求数据了
     */
    private boolean mHasLoadedOnce;

    private int refState = 0;//0代表下拉刷新，1代表上拉加载

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (v == null) {
            v = inflater.inflate(R.layout.fragment_see_hot, container, false);
            isPrepared = true;
            initView();
            initUI();
            initBanner();
            lazyLoad();
        }
        ViewGroup parent = (ViewGroup) v.getParent();
        if (parent != null) {
            parent.removeView(v);
        }
        return v;
    }

    public void initUI(){
        hListView = (HorizontalListView)headerView.findViewById(R.id.horizon_listview);
      //  previewImg = (ImageView)findViewById(R.id.image_preview);
//        String[] titles = {"怀师", "南怀瑾军校", "闭关", "南怀瑾", "南公庄严照", "怀师法相"};
//        final int[] ids = {R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};
        hListViewAdapter = new HorizontalListViewAdapter(getContext());
        hListView.setAdapter(hListViewAdapter);
        //      hListView.setOnItemSelectedListener(new OnItemSelectedListener() {
        //
        //          @Override
        //          public void onItemSelected(AdapterView<?> parent, View view,
        //                  int position, long id) {
        //              // TODO Auto-generated method stub
        //              if(olderSelected != null){
        //                  olderSelected.setSelected(false); //上一个选中的View恢复原背景
        //              }
        //              olderSelected = view;
        //              view.setSelected(true);
        //          }
        //
        //          @Override
        //          public void onNothingSelected(AdapterView<?> parent) {
        //              // TODO Auto-generated method stub
        //
        //          }
        //      });
        hListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
//              if(olderSelectView == null){
//                  olderSelectView = view;
//              }else{
//                  olderSelectView.setSelected(false);
//                  olderSelectView = null;
//              }
//              olderSelectView = view;
//              view.setSelected(true);
                //previewImg.setImageResource(ids[position]);
                hListViewAdapter.setSelectIndex(position);
                hListViewAdapter.notifyDataSetChanged();

            }
        });

    }




    private void initView() {
        newsListHelper = new NewsListHelper(this);
        mRecyclerView = (XRecyclerView) v.findViewById(R.id.recyclerview);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallClipRotateMultiple);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotateMultiple);
        mRecyclerView.setArrowImageView(R.drawable.default_ptr_flip);
        headerView = LayoutInflater.from(getContext()).inflate(R.layout.see_hot_banner, (ViewGroup) v.findViewById(android.R.id.content), false);
        mRecyclerView.addHeaderView(headerView);
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                refState = 0;
                newsListHelper.getNewsList();
            }

            @Override
            public void onLoadMore() {
                refState = 1;
                newsListHelper.getNewsList();
            }
        });

        mAdapter = new SeeHotAdapter(liveList, getActivity());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClick(this);
    }


    private void initBanner() {
        banner = (ConvenientBanner) headerView.findViewById(R.id.banner);
        tv_title = (TextView) headerView.findViewById(R.id.tv_title);
        tv_title.setText("热门话题");
        urlList = new ArrayList<>();
//        urlList.add("http://www.18183.com/uploads/140115/21-140115192239125.jpg");
//        urlList.add("http://upload.shunwang.com/2015/1224/1450927480194.jpg");
//        urlList.add("http://img5.duitang.com/uploads/blog/201405/12/20140512161725_J2zBw.jpeg");
//        urlList.add("http://pic.baike.soso.com/p/20131221/20131221161155-597537905.jpg");
//        urlList.add("http://g.hiphotos.baidu.com/zhidao/pic/item/b17eca8065380cd7d708b425a144ad34588281e7.jpg");
        urlList.add("file:///android_asset/icon_banner02.png");
        urlList.add("file:///android_asset/icon_banner03.png");
        urlList.add("file:///android_asset/icon_banner01.png");

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
        banner.startTurning(3000);

    }

    @Override
    public void onResume() {
        super.onResume();
        newsListHelper.getNewsList();
        MobclickAgent.onPageStart("热门界面");
        Log.e(TAG, TAG);
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible || mHasLoadedOnce) {
            return;
        }
        newsListHelper.getNewsList();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("热门界面");
    }

    @Override
    public void onItemClick(int pos) {
//        HotInfo item = liveList.get(pos);
//        // 如果是自己
//        if (item.getUserId().equals(MySelfInfo.getInstance().getId())) {
//            Toast.makeText(getActivity(), "当前房间不存在", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        Intent intent = new Intent(getActivity(), LiveActivity.class);
//        intent.putExtra(Constants.ID_STATUS, Constants.MEMBER);
//        MySelfInfo.getInstance().setIdStatus(Constants.MEMBER);
//        CurLiveInfo.setHostID(item.getUserId());
//        CurLiveInfo.setHostName(item.getRoomId());
//        CurLiveInfo.setHostAvator("");
//        CurLiveInfo.setRoomNum(Integer.parseInt(item.getRoomId()));
//        startActivity(intent);
/*        如果是自己(这个是腾讯的)
        if (item.getHost().getUid().equals(MySelfInfo.getInstance().getId())) {
            Toast.makeText(getActivity(), "this room don't exist", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(getActivity(), LiveActivity.class);
        intent.putExtra(Constants.ID_STATUS, Constants.MEMBER);
        MySelfInfo.getInstance().setIdStatus(Constants.MEMBER);
        CurLiveInfo.setHostID(item.getHost().getUid());
        CurLiveInfo.setHostName(item.getHost().getUsername());
        CurLiveInfo.setHostAvator(item.getHost().getAvatar());
        CurLiveInfo.setRoomNum(item.getAvRoomId());
        CurLiveInfo.setMembers(item.getWatchCount() + 1); // 添加自己
        CurLiveInfo.setAdmires(item.getAdmireCount());
        CurLiveInfo.setAddress(item.getLbs().getAddress());
        startActivity(intent);*/


        NewsInfo item = liveList.get(pos);
        // 如果是自己
        if (item.getUserId().equals(MySelfInfo.getInstance().getId())) {
            Toast.makeText(getActivity(), "当前房间不存在", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(getActivity(), LiveActivity.class);
        intent.putExtra(Constants.ID_STATUS, Constants.MEMBER);
        MySelfInfo.getInstance().setIdStatus(Constants.MEMBER);
        CurLiveInfo.setHostID(item.getUserId());
        CurLiveInfo.setHostName(item.getRoomId());
        Log.i("IDIDIDID", CurLiveInfo.getHostID());
        Log.i("IDIDIDID", CurLiveInfo.getHostName());
        Log.i("IDIDIDID", MySelfInfo.getInstance().getId());
        CurLiveInfo.setHostAvator("");
        CurLiveInfo.setRoomNum(Integer.parseInt(item.getRoomId()));
        startActivity(intent);


    }

    @Override
    public void hotListViewSucc(HotLiveInfo result) {
//        mHasLoadedOnce = true;
//        if (refState == 0) {
//            if (result == null) {
//                mRecyclerView.refreshComplete();
//                return;
//            }
//            liveList.clear();
//            mRecyclerView.refreshComplete();
//
//        } else if (refState == 1) {
//            mRecyclerView.setNoMore(true);
//            return;
//            // mRecyclerView.loadMoreComplete();
//        }
//        if (null != result && null != result.getBody()) {
//            List<HotInfo> body = result.getBody().getList();
//            for (HotInfo item : body) {
//                liveList.add(item);
//            }
//        }
//        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void hotListViewError(String msg) {
//        if (refState == 0) {
//            mRecyclerView.refreshComplete();
//        } else if (refState == 1) {
//            mRecyclerView.setNoMore(true);
//        }
//        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNewsListSucc(NewsListInfo result) {
        mHasLoadedOnce = true;
        if (refState == 0) {
            if (result == null) {
                mRecyclerView.refreshComplete();
                return;
            }
            liveList.clear();
            mRecyclerView.refreshComplete();
        } else if (refState == 1) {
            mRecyclerView.setNoMore(true);
            return;
            // mRecyclerView.loadMoreComplete();
        }
        if (null != result && null != result.getBody().getList()) {
            List<NewsInfo> body = result.getBody().getList();
            for (NewsInfo item : body) {
                liveList.add(item);
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onNewsListError(String msg) {
        if (refState == 0) {
            mRecyclerView.refreshComplete();
        } else if (refState == 1) {
            mRecyclerView.setNoMore(true);
        }
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    //点击Banner图片进行界面跳转的方法

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
                    if (position == 0) {
                        startActivity(new Intent(getActivity(), BannerOneActivity.class));
                    } else if (position == 1) {
                        startActivity(new Intent(getActivity(), BannerTwoActivity.class));
                    }
                }
            });
        }
    }

}
