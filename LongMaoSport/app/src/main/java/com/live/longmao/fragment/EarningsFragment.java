package com.live.longmao.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.live.longmao.R;
import com.live.longmao.adapter.EarningsAdapter;
import com.live.longmao.adapter.ExchangeAccountAdapter;
import com.live.longmao.fragment.base.BaseFragment;
import com.live.longmao.xrecyclerview.ProgressStyle;
import com.live.longmao.xrecyclerview.XRecyclerView;

/**
 * Created by HPDN on 2016/9/27.
 */
public class EarningsFragment extends BaseFragment {
    private EarningsAdapter earningsAdapter;
    private XRecyclerView xListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_exchange_earnings, null);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        xListView = (XRecyclerView) getView().findViewById(R.id.bean_xListView);
        init();
    }

    private void init() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xListView.setLayoutManager(layoutManager);

        xListView.setRefreshProgressStyle(ProgressStyle.BallClipRotateMultiple);
        xListView.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotateMultiple);
        xListView.setArrowImageView(R.drawable.default_ptr_flip);

        earningsAdapter = new EarningsAdapter(getActivity());
        xListView.setAdapter(earningsAdapter);
    }

    @Override
    protected void lazyLoad() {


    }
}
