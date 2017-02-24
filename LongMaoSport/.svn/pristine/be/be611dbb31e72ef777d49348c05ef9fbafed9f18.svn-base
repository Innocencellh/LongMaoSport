package com.live.longmao.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.live.longmao.R;
import com.live.longmao.adapter.ReputationRecordAdapter;
import com.live.longmao.fragment.base.BaseFragment;

/**
 * Created by HPDN on 2016/7/10.
 */
public class ReputationRecordFragment extends BaseFragment {
    private ReputationRecordAdapter reputationRecordAdapter;
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reputation_record, null);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView = (ListView) getActivity().findViewById(R.id.record_lv);
        init();
    }

    private void init() {
        reputationRecordAdapter = new ReputationRecordAdapter();
        listView.setAdapter(reputationRecordAdapter);
    }

    @Override
    protected void lazyLoad() {

    }
}