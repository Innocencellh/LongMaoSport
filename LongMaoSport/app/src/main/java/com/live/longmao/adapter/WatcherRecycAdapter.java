package com.live.longmao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.live.longmao.R;
import com.live.longmao.model.MemberInfo;
import com.live.longmao.util.GlideUtil;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by joncran001 on 1/13/16.
 */
public class WatcherRecycAdapter extends RecyclerView.Adapter<WatcherRecycAdapter.ViewHolder> {

    private WatcherItemClickListener itemClickListener;
    private List<MemberInfo> mList;
    private Context mContext;

    public WatcherRecycAdapter(Context context,List<MemberInfo> list, WatcherItemClickListener watcherItemClickListener) {
        mContext = context;
        mList = list;
        itemClickListener = watcherItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(
                parent.getContext()).inflate(R.layout.cell_recyclerview_live_watcher, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        MemberInfo info = mList.get(position);
        if(null!=info.getAvatar()&&info.getAvatar().equals(""))
        {
            holder.faceIv.setImageResource(R.mipmap.ic_default_head);
        }else {
            GlideUtil.getInstance().load(mContext, holder.faceIv, info.getAvatar());
        }
        holder.faceIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView faceIv;

        public ViewHolder(View view) {
            super(view);
            faceIv = (CircleImageView) view.findViewById(R.id.faceIv);
        }
    }

    public interface WatcherItemClickListener {
        void onItemClick(int position);
    }
}
