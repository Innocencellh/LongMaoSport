package com.live.longmao.fragment.live;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.live.longmao.R;
import com.live.longmao.adapter.NewAdapter;
import com.live.longmao.fragment.base.BaseFragment;
import com.live.longmao.model.CurLiveInfo;
import com.live.longmao.model.ListLiveInfo;
import com.live.longmao.model.LiveInfo;
import com.live.longmao.model.MySelfInfo;
import com.live.longmao.model.NewsInfo;
import com.live.longmao.model.NewsListInfo;
import com.live.longmao.presenters.LiveListViewHelper;
import com.live.longmao.presenters.NewsListHelper;
import com.live.longmao.presenters.viewinface.LiveListView;
import com.live.longmao.presenters.viewinface.NewsListView;
import com.live.longmao.util.Constants;
import com.live.longmao.views.LiveActivity;
import com.live.longmao.xrecyclerview.ProgressStyle;
import com.live.longmao.xrecyclerview.XRecyclerView;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/15.
 */
public class NewFragment extends BaseFragment implements View.OnClickListener, NewsListView,NewAdapter.OnItemClick {
    private static final String TAG = "NewFragment";
    private XRecyclerView mRecyclerView;
    private NewAdapter mAdapter;
    private TextView textView;
    View headerView;

    private ArrayList<NewsInfo> liveList = new ArrayList<NewsInfo>();
    //private LiveListViewHelper mLiveListViewHelper;
    private NewsListHelper newsListHelper;
    View v;

    /**
     * 标志位，标志已经初始化完成
     */
    private boolean isPrepared;
    /**
     * 是否已被加载过一次，第二次就不再去请求数据了
     */
    private boolean mHasLoadedOnce;

    private int refState=0;//0代表下拉刷新，1代表上拉加载

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (v == null) {
            v = inflater.inflate(R.layout.fragment_new_item, container, false);
            isPrepared = true;
            initView();
            lazyLoad();
        }
        ViewGroup parent = (ViewGroup) v.getParent();
        if (parent != null) {
            parent.removeView(v);
        }
        return v;
    }

    private void initView() {
        Log.e(TAG, TAG);
        newsListHelper = new NewsListHelper(this);
        mRecyclerView = (XRecyclerView)v.findViewById(R.id.recyclerview);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,
                StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallClipRotateMultiple);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotateMultiple);
        mRecyclerView.setArrowImageView(R.drawable.default_ptr_flip);

        headerView =   LayoutInflater.from(getContext()).inflate(R.layout.header_textview, (ViewGroup)v.findViewById(android.R.id.content),false);
        textView = (TextView) headerView.findViewById(R.id.tv_title_header);
        textView.setText("最新直播");
        mRecyclerView.addHeaderView(headerView);

        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                refState=0;
                newsListHelper.getNewsList();
            }

            @Override
            public void onLoadMore() {
                refState=1;
                newsListHelper.getNewsList();
            }
        });

        mAdapter = new NewAdapter(liveList,getActivity());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClick(this);
        //mRecyclerView.addItemDecoration(new SpaceItemDecoration(getContext(), 10, mAdapter));
    }

    @Override
    public void onResume() {
        super.onResume();
        newsListHelper.getNewsList();
        MobclickAgent.onPageStart("最新列表");
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
    public void onItemClick(int pos) {
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
//        if (item.getHost().getUid().equals(MySelfInfo.getInstance().getId())) {
//            Toast.makeText(getActivity(), "this room don't exist", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        Intent intent = new Intent(getActivity(), LiveActivity.class);
//        intent.putExtra(Constants.ID_STATUS, Constants.MEMBER);
//        MySelfInfo.getInstance().setIdStatus(Constants.MEMBER);
//        CurLiveInfo.setHostID(item.getHost().getUid());
//        CurLiveInfo.setHostName(item.getHost().getUsername());
//        CurLiveInfo.setHostAvator(item.getHost().getAvatar());
//        CurLiveInfo.setRoomNum(item.getAvRoomId());
//        CurLiveInfo.setMembers(item.getWatchCount() + 1); // 添加自己
//        CurLiveInfo.setAdmires(item.getAdmireCount());
//        CurLiveInfo.setAddress(item.getLbs().getAddress());
//        startActivity(intent);
    }
    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("最新列表");
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onNewsListSucc(NewsListInfo result) {
        mHasLoadedOnce = true;
        if(refState==0)
        {
            if(result==null)
            {
                mRecyclerView.refreshComplete();
                return;
            }
            liveList.clear();
            mRecyclerView.refreshComplete();
        }else if(refState==1)
        {
            mRecyclerView.setNoMore(true);
            return;
            // mRecyclerView.loadMoreComplete();
        }
        if (null != result&&null!=result.getBody().getList()) {
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
}
