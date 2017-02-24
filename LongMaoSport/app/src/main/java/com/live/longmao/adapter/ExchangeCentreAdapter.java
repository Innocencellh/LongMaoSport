package com.live.longmao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.live.longmao.R;

/**
 * Created by HPDN on 2016/9/12.
 */
public class ExchangeCentreAdapter extends BaseAdapter {

    //上下文对象
    private Context context;
    private LayoutInflater inflater;
    //图片数组
    private Integer[] imgs = {
            R.mipmap.icon_exchange01, R.mipmap.icon_exchange02, R.mipmap.icon_exchange03,
            R.mipmap.icon_exchange04, R.mipmap.icon_exchange05, R.mipmap.icon_exchange06,
            R.mipmap.icon_exchange07, R.mipmap.icon_exchange08
    };

    public ExchangeCentreAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return imgs.length;
    }

    public Object getItem(int item) {
        return item;
    }

    public long getItemId(int id) {
        return id;
    }

    //创建View方法
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
           // imageView = new ImageView(context);
            convertView =inflater.inflate(R.layout.item_exchange_centre,parent,false);
            holder = new ViewHolder();
            holder.iv_exchange_centre = (ImageView) convertView.findViewById(R.id.iv_exchange_centre);
            holder.iv_exchange_centre.setImageResource(imgs[position]);//为ImageView设置图片资源
            convertView.setTag(holder);
        } else {
            holder =(ViewHolder)convertView.getTag();
        }

        return convertView;
    }
    class ViewHolder{
        ImageView iv_exchange_centre;

    }
}
