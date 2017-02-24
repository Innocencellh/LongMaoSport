package com.live.longmao.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.live.longmao.R;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/6/30.
 */
public class AddressAdapter extends BaseAdapter {
    private List<String> lists;
    private Context context;
    private HashMap<Integer, Boolean> selectMap;

    public AddressAdapter(List<String> lists,Context context,HashMap<Integer, Boolean> selectMap)
    {
        this.lists = lists;
        this.context = context;
        this.selectMap=selectMap;
    }
    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_live_classification, null);
            holder = new ViewHolder();
            holder.tv_btn = (TextView)convertView.findViewById(R.id.tv_btn);
            holder.tv_city_name = (TextView)convertView.findViewById(R.id.tv_city_name);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)convertView.getTag();
        }
        initDate(holder,position);
        return convertView;
    }
    private void initDate(ViewHolder holder,int position)
    {
        if(selectMap.size()>0) {
            if (selectMap.containsKey(position) && selectMap.get(position)){
                holder.tv_btn.setSelected(true);
            } else {
                holder.tv_btn.setSelected(false);
            }
        }
        holder.tv_city_name.setText(lists.get(position));
    }

    private class ViewHolder
    {
        TextView tv_btn,tv_city_name;
    }
}
