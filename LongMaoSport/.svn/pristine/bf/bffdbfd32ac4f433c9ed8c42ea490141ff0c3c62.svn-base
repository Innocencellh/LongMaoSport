package com.live.longmao.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.live.longmao.R;
import com.live.longmao.adapter.ExchangeAccountAdapter;
import com.live.longmao.fragment.base.BaseFragment;
import com.live.longmao.xrecyclerview.ProgressStyle;
import com.live.longmao.xrecyclerview.XRecyclerView;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by Administrator on 2016/9/29 0029.
 */
public class ExchangeBeanFragment extends BaseFragment {
    private View v;
    private  XRecyclerView xListView;
    private ExchangeAccountAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (v == null) {
            v = inflater.inflate(R.layout.fragment_bean_exchange, container, false);
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
        xListView = (XRecyclerView) v.findViewById(R.id.bean_xListView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xListView.setLayoutManager(layoutManager);

        xListView.setRefreshProgressStyle(ProgressStyle.BallClipRotateMultiple);
        xListView.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotateMultiple);
        xListView.setArrowImageView(R.drawable.default_ptr_flip);

        adapter = new ExchangeAccountAdapter(getActivity());
        xListView.setAdapter(adapter);
    }

    @Override
    protected void lazyLoad() {

    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("ExchangeBeanFragment");
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("ExchangeBeanFragment");
    }

}
