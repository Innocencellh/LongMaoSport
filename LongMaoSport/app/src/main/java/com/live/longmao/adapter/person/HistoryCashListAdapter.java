package com.live.longmao.adapter.person;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.live.longmao.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/16 0016.
 */
public class HistoryCashListAdapter extends BaseAdapter {
    private List<String> date;
    Context context;
    private LayoutInflater mInflater;

    public HistoryCashListAdapter(List<String> date, Context context) {
        this.date = date;
        this.context = context;
    }

    public void addItem(String itemData) {
        date.add(itemData);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return date.size();
    }

    @Override
    public Object getItem(int position) {
        return date.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.from(context).inflate(R.layout.item_listview_cash_history, null);
            holder.tianNameTv = (TextView) convertView.findViewById(R.id.tixian_name_tv);
            holder.cashTimeTv = (TextView) convertView.findViewById(R.id.tixian_time_tv);
            holder.cashShowTv = (TextView) convertView.findViewById(R.id.tixian_cash_all);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
       /* holder.tianNameTv.setText(date.get(position));
        holder.cashTimeTv.setText(date.get(position));
        holder.cashShowTv.setText(date.get(position));*/
        return convertView;
    }

    class ViewHolder {
        TextView tianNameTv, cashTimeTv, cashShowTv;
    }

}
