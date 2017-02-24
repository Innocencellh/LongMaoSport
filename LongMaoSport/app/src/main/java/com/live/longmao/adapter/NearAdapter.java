package com.live.longmao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.live.longmao.BaseApp;
import com.live.longmao.R;
import com.live.longmao.model.CurLiveInfo;
import com.live.longmao.model.LiveInfo;
import com.live.longmao.model.NearInfo;
import com.live.longmao.util.DisplayUtil;
import com.live.longmao.util.GlideUtil;
import com.live.okhttp.OkHttpUtils;

import java.util.ArrayList;


/**
 * Created by Administrator on 2016/7/28.
 */
public class NearAdapter extends RecyclerView.Adapter<NearAdapter.ViewHolder> {
    public ArrayList<NearInfo> datas = null;
    public Context mContext;

    public NearAdapter(ArrayList<NearInfo> datas, Context mContext) {
        this.datas = datas;
        this.mContext = mContext;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_near, viewGroup, false);
        return new ViewHolder(view);
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.ivCover.getLayoutParams().height = DisplayUtil.getSysWidth(mContext) / 2 - DisplayUtil.dip2px(mContext, 2);
//        if (!TextUtils.isEmpty(datas.get(position).getCover())){
//            GlideUtil.getInstance().load(mContext, viewHolder.ivCover, datas.get(position).getCover());
//        } else {
        //    GlideUtil.getInstance().load(mContext, viewHolder.ivCover, R.mipmap.icon_star_bg);
        // }
        viewHolder.ivCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onItemClick(position);
            }
        });
        //viewHolder.tv_name.setText(datas.get(position).getUserName());
        viewHolder.tv_near_distance.setText(datas.get(position).getDistance() + "km");
        viewHolder.tv_follow_num.setText(datas.get(position).getSeeNums() + "人观看");
        viewHolder.tv_name.setText(datas.get(position).getUserId());
//        if (datas.get(position).getPicUrl() == null) {
//            viewHolder.ivCover.setImageResource(R.mipmap.icon_star_bg);
//        } else {
        String url = "http://121.40.65.153/photo/";
        GlideUtil.getInstance().load(mContext, viewHolder.ivCover, OkHttpUtils.Photo_Url + datas.get(position).getPicUrl());
        CurLiveInfo.setHandImg(url + datas.get(position).getPicUrl());
        //        viewHolder.ivCover.setImageResource(R.mipmap.icon_star_bg);
//        }
    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivCover;
        private TextView tv_name, tv_follow_num, tv_near_distance;

        public ViewHolder(View view) {
            super(view);
            ivCover = (ImageView) view.findViewById(R.id.cover);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_follow_num = (TextView) view.findViewById(R.id.tv_follow_num);
            tv_near_distance = (TextView) view.findViewById(R.id.tv_near_distance);

        }
    }

    private OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public interface OnItemClick {
        public void onItemClick(int pos);
    }
}
