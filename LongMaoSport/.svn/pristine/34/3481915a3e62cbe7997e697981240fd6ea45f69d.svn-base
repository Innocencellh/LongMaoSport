package com.live.longmao.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.live.longmao.R;
import com.live.longmao.adapter.ReputationExchangeAdapter;
import com.live.longmao.fragment.base.BaseFragment;

/**
 * Created by HPDN on 2016/7/10.
 */
public class ReputationExchangeFragment extends BaseFragment {
    private ReputationExchangeAdapter reputationExchangeAdapter;
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fargment_reputation_exchange, null);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView = (ListView) getView().findViewById(R.id.reputation_exchange_lv);
        init();
    }

    private void init() {
        reputationExchangeAdapter = new ReputationExchangeAdapter();
        listView.setAdapter(reputationExchangeAdapter);
    }

    @Override
    protected void lazyLoad() {


    }
}
