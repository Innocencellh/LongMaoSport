package com.live.longmao.adapter.person;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.live.longmao.R;
import com.live.longmao.model.CurLiveInfo;
import com.live.longmao.model.NewsInfo;
import com.live.longmao.util.DisplayUtil;
import com.live.longmao.util.GlideUtil;

import java.util.ArrayList;


/**
 * Created by Administrator on 2016/7/28.
 */
public class PersonPhotoAdapter extends RecyclerView.Adapter<PersonPhotoAdapter.ViewHolder> {
    public ArrayList<String> datas = null;
    public Context mContext;

    public PersonPhotoAdapter(ArrayList<String> datas, Context mContext) {
        this.datas = datas;
        this.mContext = mContext;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_newshow, viewGroup, false);
        return new ViewHolder(view);
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.ivCover.getLayoutParams().height = DisplayUtil.getSysWidth(mContext) / 3 - DisplayUtil.dip2px(mContext, 2);

        viewHolder.ivCover.setImageResource(R.mipmap.icon_star_bg);

        viewHolder.ivCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onItemClick(position);
            }
        });
    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return 20;
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivCover, img_sex;

        public ViewHolder(View view) {
            super(view);
            ivCover = (ImageView) view.findViewById(R.id.cover);
            img_sex = (ImageView) view.findViewById(R.id.img_sex);
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
