package com.live.longmao.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.live.longmao.R;

import java.util.List;

/**
 * Created by Administrator on 2016/7/11.
 */
public class ShippingAddressAdapter extends BaseAdapter {
    private List<String> list;
    private Context context;
    public ShippingAddressAdapter(Context context,List<String> list){
        this.context = context;
        this.list=list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context,
                    R.layout.item_shipping_address, null);
            new ViewHolder(convertView);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.txt_name.setText(list.get(position));
        return convertView;
    }

    class ViewHolder {
        TextView txt_name;

        public ViewHolder(View view) {
            txt_name = (TextView) view
                    .findViewById(R.id.txt_name);
            view.setTag(this);
        }
    }
}
