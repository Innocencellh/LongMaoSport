package com.live.longmao.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ListView;
import android.widget.Toast;

import com.live.longmao.R;
import com.live.longmao.adapter.PersonAttentionAdapter;
import com.live.longmao.adapter.PersonFansAdapter;
import com.live.longmao.model.AttentionBean;
import com.live.longmao.model.AttentionInfo;
import com.live.longmao.model.PersonAttentionInfo;
import com.live.longmao.model.PersonAttentionInfoTwoList;
import com.live.longmao.presenters.FansHelper;
import com.live.longmao.presenters.viewinface.FansView;
import com.live.longmao.views.BaseActivity;
import com.live.longmao.xrecyclerview.ProgressStyle;
import com.live.longmao.xrecyclerview.XRecyclerView;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HPDN on 2016/7/8.
 */
public class PersonFansActivity extends BaseActivity implements FansView {

    private XRecyclerView xListView;
    private PersonFansAdapter personFansAdapter;
    private ArrayList<PersonAttentionInfoTwoList> list = new ArrayList<>();
    private Context context;
    private FansHelper fansHelper;

    private int refState = 0;//0代表下拉刷新，1代表上拉加载

    /**
     * 标志位，标志已经初始化完成
     */
    private boolean isPrepared;
    /**
     * 是否已被加载过一次，第二次就不再去请求数据了
     */
    private boolean mHasLoadedOnce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fansHelper = new FansHelper(this);
        setView(R.layout.activity_person_attention);
        setTitle("粉丝");
        xListView = (XRecyclerView) findViewById(R.id.bean_xListView);
//        personFansAdapter = new PersonFansAdapter(list, context);
//        listView.setAdapter(personFansAdapter);
        isPrepared = true;
        init();
        lazyLoad();

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
                fansHelper.getFans();
            }

            @Override
            public void onLoadMore() {
                refState = 1;
                fansHelper.getFans();
            }
        });

        personFansAdapter = new PersonFansAdapter(list, this);
        xListView.setAdapter(personFansAdapter);
    }

    private void lazyLoad() {
        if (!isPrepared || mHasLoadedOnce) {
            return;
        }
        fansHelper.getFans();
    }

    @Override
    public void onFansSucc(PersonAttentionInfo result) {
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
                list.add(item);
            }
            personFansAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(PersonFansActivity.this, "你还没有粉丝，加油哦！！", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFansError(String msg) {

    }
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("个人中心发现界面");
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("个人中心发现界面");
        MobclickAgent.onPause(this);
    }
}
