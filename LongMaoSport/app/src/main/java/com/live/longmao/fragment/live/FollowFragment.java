package com.live.longmao.fragment.live;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.live.longmao.R;
import com.live.longmao.adapter.FollowAdapter;
import com.live.longmao.bean.LiveBean;
import com.live.longmao.fragment.base.BaseFragment;
import com.live.longmao.model.CurLiveInfo;
import com.live.longmao.model.ListLiveInfo;
import com.live.longmao.model.LiveInfo;
import com.live.longmao.model.MySelfInfo;
import com.live.longmao.model.NewsInfo;
import com.live.longmao.model.PersonAttentionInfo;
import com.live.longmao.model.PersonAttentionInfoTwoList;
import com.live.longmao.model.PersonAttentionInfoTwoListBean;
import com.live.longmao.presenters.AttentionSellLiveHelper;
import com.live.longmao.presenters.LiveListViewHelper;
import com.live.longmao.presenters.viewinface.AttentionSellLiveView;
import com.live.longmao.presenters.viewinface.LiveListView;
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
public class FollowFragment extends BaseFragment implements View.OnClickListener, LiveListView, FollowAdapter.OnItemClick, AttentionSellLiveView {
    private static final String TAG = "FollowFragment";

    private XRecyclerView mRecyclerView;
    //  private ArrayList<PersonAttentionInfoTwoListBean> liveBeanList = new ArrayList<PersonAttentionInfoTwoListBean>();
    private ArrayList<PersonAttentionInfoTwoList> liveList = new ArrayList<PersonAttentionInfoTwoList>();
    private FollowAdapter adapter;
    //private LiveListViewHelper mLiveListViewHelper;
    private AttentionSellLiveHelper attentionSellLiveHelper;
    View v;
    View headerView;
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
            v = inflater.inflate(R.layout.fragment_live_item, container, false);
            isPrepared = true;
            attentionSellLiveHelper = new AttentionSellLiveHelper(this);
            initView();
            lazyLoad();
        }
        ViewGroup parent = (ViewGroup) v.getParent();
        if (parent != null) {
            parent.removeView(v);
        }
        return v;
    }

    private void initData() {
        attentionSellLiveHelper.getAttention();
    }

    private void initView() {
        //    mLiveListViewHelper = new LiveListViewHelper(this);
        mRecyclerView = (XRecyclerView) v.findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallClipRotateMultiple);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotateMultiple);
        mRecyclerView.setArrowImageView(R.drawable.default_ptr_flip);

        headerView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_follow_tittle, (ViewGroup) v.findViewById(android.R.id.content), false);
        mRecyclerView.addHeaderView(headerView);

        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                refState = 0;

                initData();

            }

            @Override
            public void onLoadMore() {
                refState = 1;
                mRecyclerView.setNoMore(true);
            }
        });

        initData();
        adapter = new FollowAdapter(liveList, getContext());
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClick(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("关注列表");
        Log.e(TAG, TAG);
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible || mHasLoadedOnce) {
            return;
        }
        attentionSellLiveHelper.getAttention();
        //    mLiveListViewHelper.getPageData(1);
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("关注列表");
    }

    @Override
    public void onItemClick(int pos) {
        Log.e("pos", pos + "");
        PersonAttentionInfoTwoList item = liveList.get(pos);
        // 如果是自己
        if (item.getUserId().equals(MySelfInfo.getInstance().getId())) {
            Toast.makeText(getActivity(), "当前房间不存在", Toast.LENGTH_SHORT).show();
            return;
        }

            Intent intent = new Intent(getActivity(), LiveActivity.class);
            intent.putExtra(Constants.ID_STATUS, Constants.MEMBER);
            MySelfInfo.getInstance().setIdStatus(Constants.MEMBER);
            CurLiveInfo.setHostID(item.getUserId());
            CurLiveInfo.setHostName(item.getUserId());
            Log.i("IDIDIDID", CurLiveInfo.getHostID());
            Log.i("IDIDIDID", CurLiveInfo.getHostName());
            Log.i("IDIDIDID", MySelfInfo.getInstance().getId());
            CurLiveInfo.setHostAvator("");
            CurLiveInfo.setRoomNum(Integer.parseInt(item.getUserId()));
            startActivity(intent);

    }

    @Override
    public void showFirstPage(ListLiveInfo livelist) {
//        mHasLoadedOnce = true;
//        liveList.clear();
//        List<LiveInfo> body = livelist.getBody();
//        if (null != body) {
//            for (LiveInfo item : body) {
//                liveList.add(item);
//            }
//        }
//        adapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAttentionSucc(PersonAttentionInfo result) {
        mHasLoadedOnce = true;
        liveList.clear();
        List<PersonAttentionInfoTwoList> body = result.getBody().getObjList();
        if (null != body) {
            for (PersonAttentionInfoTwoList item : body) {
                liveList.add(item);
            }
        }
        adapter.notifyDataSetChanged();
        mRecyclerView.refreshComplete();
    }

    @Override
    public void onAttentionError(String msg) {

    }
}
