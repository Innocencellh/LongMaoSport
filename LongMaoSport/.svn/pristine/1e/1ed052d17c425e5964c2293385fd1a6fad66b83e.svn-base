package com.live.longmao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.live.longmao.R;
import com.live.longmao.bean.LiveBean;
import com.live.longmao.model.CurLiveInfo;
import com.live.longmao.model.LiveInfoJson;
import com.live.longmao.model.PersonAttentionInfoTwoList;
import com.live.longmao.model.PersonAttentionInfoTwoListBean;
import com.live.longmao.util.GlideUtil;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2016/7/14.
 */
public class FollowAdapter extends RecyclerView.Adapter<FollowAdapter.ViewHolder> {
    public ArrayList<PersonAttentionInfoTwoList> liveBeanList = null;
    public Context mContext;

    public FollowAdapter(ArrayList<PersonAttentionInfoTwoList> liveBeanList, Context mContext) {
        this.liveBeanList = liveBeanList;
        this.mContext = mContext;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view;
        if (viewType == 0)
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_living_title, viewGroup, false);
        else if (viewType == 1)
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_living, viewGroup, false);
        else if (viewType == 2)
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_not_living_title, viewGroup, false);
        else
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_not_living, viewGroup, false);
        return new ViewHolder(view);
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        viewHolder.userName.setText(liveBeanList.get(position).getNickName());
        viewHolder.live_logo.setVisibility(View.GONE);

        viewHolder.father.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onItemClick(position);
            }
        });
        viewHolder.ivCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onItemClick(position);
            }
        });
        String url = "http://121.40.65.153/photo/";
        GlideUtil.getInstance().load(mContext, viewHolder.ivCover, url + liveBeanList.get(position).getFacePhoto());
        GlideUtil.getInstance().load(mContext, viewHolder.avatar, url + liveBeanList.get(position).getFacePhoto());
        CurLiveInfo.setHandImg(url + liveBeanList.get(position).getFacePhoto());

    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return liveBeanList.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivCover;
        public LinearLayout father;
        public TextView userName,live_logo;
        private CircleImageView avatar;

        public ViewHolder(View view) {
            super(view);
            ivCover = (ImageView) view.findViewById(R.id.cover);
            avatar = (CircleImageView) view.findViewById(R.id.avatar);
            father = (LinearLayout) view.findViewById(R.id.father);
            userName = (TextView) view.findViewById(R.id.live_title);
            live_logo = (TextView) view.findViewById(R.id.live_logo);
        }
    }

    @Override
    public int getItemViewType(int pos) {
//        if(pos>=0&&pos<liveBeanList.size()) {
//            if (liveBeanList.get(pos).getPersonAttentionInfoTwoListBean().getStats() == 1) {
//                return 0;
//            } else if (liveBeanList.get(pos).getPersonAttentionInfoTwoListBean().getStats() == 2) {
//                return 1;
//            } else if (liveBeanList.get(pos).getPersonAttentionInfoTwoListBean().getStats() == 1) {
//                return 2;
//            } else {
//                return 3;
//            }
//        }
        return 0;
    }

    private OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public interface OnItemClick {
        public void onItemClick(int pos);
    }
}
