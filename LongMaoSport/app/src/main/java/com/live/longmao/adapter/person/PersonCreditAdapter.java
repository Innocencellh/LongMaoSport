package com.live.longmao.adapter.person;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.live.longmao.R;

import java.util.List;

/**
 * Created by Administrator on 2017/2/17 0017.
 */
public class PersonCreditAdapter extends BaseAdapter {
    private Context context ;
    private LayoutInflater mInflate ;
    private List<String> date ;

    public PersonCreditAdapter(Context context, List<String> date) {
        this.context = context;
        this.date = date;
    }

    @Override
    public int getCount() {
        return date.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            convertView = mInflate.from(context).inflate(R.layout.item_credit_history,null);
            holder = new ViewHolder();
            holder.nameTv = (TextView) convertView.findViewById(R.id.xinyu_name_tv);
            holder.timeTv = (TextView) convertView.findViewById(R.id.xinyu_time_tv);
            holder.numTv = (TextView) convertView.findViewById(R.id.xinyu_cash_all);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    class ViewHolder{
        private TextView nameTv ,timeTv ,numTv ;
    }
}
