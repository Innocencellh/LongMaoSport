package com.live.longmao.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.live.longmao.R;

import java.util.HashMap;

/**
 * Created by HPDN on 2016/7/10.
 */
public class ReputationRecordAdapter extends BaseAdapter {
    HashMap<Integer, Boolean> isSelect = new HashMap<>();

    @Override
    public int getCount() {
        return 100;
    }

    @Override
    public Object getItem(int position) {
        return 100;
    }

    @Override
    public long getItemId(int position) {
        return 100;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reputation_record, null);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

    private class ViewHolder {

    }
}