package com.live.longmao.adapter.see;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.live.longmao.R;
import com.live.longmao.model.CurLiveInfo;
import com.live.longmao.model.NewsInfo;
import com.live.longmao.util.GlideUtil;

import java.util.ArrayList;


/**
 * Created by Administrator on 2016/7/28.
 */
public class SeeHotAdapter extends RecyclerView.Adapter<SeeHotAdapter.ViewHolder> {
 //   public ArrayList<HotInfo> datas = null;
    public ArrayList<NewsInfo> datas = null;
    public Context mContext;

//    public HotAdapter(ArrayList<HotInfo> datas, Context mContext) {
//        this.datas = datas;
//        this.mContext = mContext;
//    }


    public SeeHotAdapter(ArrayList<NewsInfo> datas, Context mContext) {
        this.datas = datas;
        this.mContext = mContext;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_seeing_living, viewGroup, false);
        return new ViewHolder(view);
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        //热门列表得数据解析   姓名
        /*viewHolder.userName.setText(datas.get(position).getUserName());
        viewHolder.tv_tittle_hot.setText(datas.get(position).getLiveTitle());
        if(datas.get(position).getSex().equals("女"))
        {
            viewHolder.img_sex.setBackgroundResource(R.mipmap.global_female);
        }else
        {
            viewHolder.img_sex.setBackgroundResource(R.mipmap.global_male);
        }*/
        viewHolder.tv_num.setText(datas.get(position).getSeeNums()+"");
        viewHolder.live_lbs.setText(datas.get(position).getCity());
        viewHolder.tv_tittle_hot.setText(datas.get(position).getLiveTitle());
        viewHolder.userName.setText(datas.get(position).getUserId());
      //  viewHolder.userName.setText(datas.get(position).getUserId());
        if (datas.get(position).getSex()==0){
            viewHolder.img_sex.setBackgroundResource(R.mipmap.icon_person_girl);
        }else {
            viewHolder.img_sex.setBackgroundResource(R.mipmap.icon_person_boy);
        }
        if(position==0)
        {
            viewHolder.view_line.setBackgroundColor(mContext.getResources().getColor(R.color.white));
        }
//        if (!TextUtils.isEmpty(datas.get(position).getCover())) {
//            GlideUtil.getInstance().load(mContext, viewHolder.ivCover, datas.get(position).getCover());
//            GlideUtil.getInstance().load(mContext, viewHolder.avatar, datas.get(position).getCover());
//        } else {
//        GlideUtil.getInstance().load(mContext, viewHolder.ivCover, R.mipmap.icon_star_bg);
//        GlideUtil.getInstance().load(mContext, viewHolder.avatar, R.mipmap.icon_star_bg);

        String url = "http://121.40.65.153/photo/";
        GlideUtil.getInstance().load(mContext, viewHolder.ivCover, url + datas.get(position).getPicUrl());
        GlideUtil.getInstance().load(mContext, viewHolder.avatar, url + datas.get(position).getPicUrl());
        CurLiveInfo.setHandImg(url + datas.get(position).getPicUrl());

//        }
        viewHolder.ivCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onItemClick(position);
            }
        });
        viewHolder.avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onItemClick(position);
            }
        });
    }
    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivCover, avatar,img_sex;
        public TextView userName,tv_num,live_lbs;
        public TextView tv_tittle_hot;
        public View view_line;

        public ViewHolder(View view) {
            super(view);
            ivCover = (ImageView) view.findViewById(R.id.cover);
            avatar = (ImageView) view.findViewById(R.id.avatar);
            img_sex = (ImageView) view.findViewById(R.id.img_sex);
            userName = (TextView) view.findViewById(R.id.live_title);
            tv_num = (TextView) view.findViewById(R.id.tv_num);
            live_lbs = (TextView) view.findViewById(R.id.live_lbs);
            tv_tittle_hot = (TextView) view.findViewById(R.id.tv_tittle_hot);
            view_line = view.findViewById(R.id.view_line);
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
