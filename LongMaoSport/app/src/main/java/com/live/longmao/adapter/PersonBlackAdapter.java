package com.live.longmao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.live.longmao.R;
import com.live.longmao.model.AttentionBean;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by HPDN on 2016/7/8.
 */
public class PersonBlackAdapter extends BaseAdapter {
    HashMap<Integer, Boolean> isSelect = new HashMap<>();


    @Override
    public int getCount() {
        return 20;
    }

    @Override
    public Object getItem(int position) {
        return 20;
    }

    @Override
    public long getItemId(int position) {
        return 20;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_person_attention, null);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.attention_iv);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.imageView.setTag(position);
        if (isSelect.size() > 0) {
            if (isSelect.containsKey(position) && isSelect.get(position)) {
                viewHolder.imageView.setSelected(true);
            } else {
                viewHolder.imageView.setSelected(false);
            }
        }
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!v.isSelected()) {
                    isSelect.put((Integer) v.getTag(), true);
                    notifyDataSetChanged();
                } else {
                    isSelect.put((Integer) v.getTag(), false);
                    notifyDataSetChanged();
                }
            }
        });
        return convertView;
    }

    private class ViewHolder {
        private ImageView imageView;
    }
}
