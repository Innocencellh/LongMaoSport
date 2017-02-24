package com.live.longmao.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.live.longmao.R;
import com.live.longmao.model.LiveInfo;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/29.
 */
public class FindActiviyAdapter extends ArrayAdapter<LiveInfo>{
    private static String TAG = "FindActiviyAdapter";
    private int resourceId;
    private Activity mActivity;

    private class ViewHolder {

    }

    public FindActiviyAdapter(Activity activity, int resource, ArrayList<LiveInfo> objects) {
        super(activity, resource, objects);
        resourceId = resource;
        mActivity = activity;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_activity, null);
        holder = new ViewHolder();
        convertView.setTag(holder);

        return convertView;
    }

}