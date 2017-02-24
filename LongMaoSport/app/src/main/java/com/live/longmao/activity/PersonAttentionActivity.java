package com.live.longmao.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.live.longmao.R;
import com.live.longmao.adapter.LmbAdapter;
import com.live.longmao.adapter.PersonAttentionAdapter;
import com.live.longmao.model.AttentionBean;
import com.live.longmao.model.AttentionInfo;
import com.live.longmao.model.CurLiveInfo;
import com.live.longmao.model.LiveInfo;
import com.live.longmao.model.MySelfInfo;
import com.live.longmao.model.PersonAttentionInfo;
import com.live.longmao.model.PersonAttentionInfoOne;
import com.live.longmao.model.PersonAttentionInfoTwoList;
import com.live.longmao.model.PersonAttentionInfoTwoListBean;
import com.live.longmao.presenters.AttentionHelper;
import com.live.longmao.presenters.FollowHelper;
import com.live.longmao.presenters.viewinface.AttentionView;
import com.live.longmao.presenters.viewinface.FollowView;
import com.live.longmao.views.BaseActivity;
import com.live.longmao.xrecyclerview.ProgressStyle;
import com.live.longmao.xrecyclerview.XRecyclerView;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HPDN on 2016/7/8.
 */
public class PersonAttentionActivity extends BaseActivity implements AttentionView, FollowView {
    private static final String TAG = "PersonAttentionActivity";
    private XRecyclerView xListView;
    private PersonAttentionAdapter personAttentionAdapter;
    private AttentionHelper attentionHelper;
    private FollowHelper followHelper;
    private ArrayList<PersonAttentionInfoTwoList> list = new ArrayList<>();
    private Context context;
    private int refState = 0;//0代表下拉刷新，1代表上拉加载

    /**
     * 标志位，标志已经初始化完成
     */
    private boolean isPrepared;
    /**
     * 是否已被加载过一次，第二次就不再去请求数据了
     */
    private boolean mHasLoadedOnce;

    //关注列表缺少字段
    // private FollowHelper followHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        attentionHelper = new AttentionHelper(this);
        followHelper = new FollowHelper(this);
        setView(R.layout.activity_person_attention);
        setTitle("关注");
        xListView = (XRecyclerView) findViewById(R.id.bean_xListView);
        isPrepared = true;
        init();
        lazyLoad();
    }

    private void lazyLoad() {
        if (!isPrepared || mHasLoadedOnce) {
            return;
        }
        attentionHelper.getAttention();
    }

    private void init() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xListView.setLayoutManager(layoutManager);

        xListView.setRefreshProgressStyle(ProgressStyle.BallClipRotateMultiple);
        xListView.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotateMultiple);
        xListView.setArrowImageView(R.drawable.default_ptr_flip);

        xListView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                refState = 0;
                attentionHelper.getAttention();
                //  followHelper.getFollow(MySelfInfo.getInstance().getId(), String.valueOf(CurLiveInfo.getRoomNum()));
                // followHelper.cancelFollow(MySelfInfo.getInstance().getId(), String.valueOf(CurLiveInfo.getRoomNum()));
            }

            @Override
            public void onLoadMore() {
                refState = 1;
                attentionHelper.getAttention();
            }
        });

        personAttentionAdapter = new PersonAttentionAdapter(this, list, this);
        xListView.setAdapter(personAttentionAdapter);
    }

    @Override
    public void onAttentionSucc(PersonAttentionInfo result) {
        mHasLoadedOnce = true;
        if (refState == 0) {
            if (result == null) {
                xListView.refreshComplete();
                return;
            }
            list.clear();
            xListView.refreshComplete();
        } else if (refState == 1) {
            xListView.setNoMore(true);
            return;
            // mRecyclerView.loadMoreComplete();
        }

        if (null != result && null != result.getBody().getObjList()) {
            List<PersonAttentionInfoTwoList> body = result.getBody().getObjList();
            for (PersonAttentionInfoTwoList item : body) {
                item.setAttentionsState(1);
                list.add(item);
            }
            personAttentionAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(PersonAttentionActivity.this, "你还没有关注的主播，快去关注你的心仪主播", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onAttentionError(String msg) {
        Toast.makeText(PersonAttentionActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("个人中心关注界面");
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("个人中心关注界面");
        MobclickAgent.onPause(this);
    }

    @Override
    public void onFollowSucc(int pos) {
        Log.e(TAG, "onFollowSucc" + pos);
        list.get(pos).setAttentionsState(1);
        //在这里调用关注接口
        //      followHelper.getFollow(MySelfInfo.getInstance().getId(), list.get(pos).getPersonAttentionInfoTwoListBean().getUserId());
        personAttentionAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCancelFollowSucc(int pos) {
        Log.e(TAG, "onCancelFollowSucc" + pos);
        list.get(pos).setAttentionsState(2);
        //在这里调用取消关注接口
        //   followHelper.cancelFollow(MySelfInfo.getInstance().getId(), list.get(pos).getPersonAttentionInfoTwoListBean().getUserId());
        personAttentionAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFollowError(String msg) {

    }
}
