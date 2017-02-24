package com.live.longmao.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.live.longmao.R;
import com.live.longmao.model.ChatEntity;
import com.live.longmao.model.LiveInfoJson;
import com.live.longmao.util.Constants;
import com.live.longmao.util.GlideUtil;
import com.live.longmao.util.LevelUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lady on 2016/9/7.
 */
public class LiveForMsgAdapter extends RecyclerView.Adapter<LiveForMsgAdapter.ViewHolder> {
    private List<ChatEntity> listMessage = null;
    public Context context;

    public LiveForMsgAdapter(ArrayList<ChatEntity> listMessage, Context mContext) {
        this.listMessage = listMessage;
        this.context = mContext;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_live_msg, viewGroup, false);
        return new ViewHolder(view);
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        SpannableString spanString;
        ChatEntity item = listMessage.get(position);
        if (item.getType() != Constants.TEXT_TYPE) {
            // 设置名称为粗体
            spanString = new SpannableString(item.getSenderName() + " : " + item.getContext());
            holder.tv_level.setVisibility(View.GONE);
            StyleSpan boldStyle = new StyleSpan(Typeface.BOLD_ITALIC);
            spanString.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.item_name_color)), 0, item.getSenderName().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            if (item.getContext().equals("进入了房间")||item.getContext().equals("退出了房间")){
                holder.rl_got_live.setVisibility(View.VISIBLE);
                holder.rl_live.setVisibility(View.GONE);
                holder.tv_context1.setTextColor(context.getResources().getColor(R.color.colorTextWhite));
            }else {
                holder.rl_got_live.setVisibility(View.GONE);
                holder.rl_live.setVisibility(View.VISIBLE);
                holder.tv_context.setTextColor(context.getResources().getColor(R.color.colorTextWhite));
            }
            holder.tv_context.setText(spanString);
            holder.tv_context1.setText(spanString);
        } else {
            // 根据名称计算颜色
            String strt = "\t\t\t\t\t";
            spanString = new SpannableString(strt+item.getSenderName()+"：" + item.getContext());
            holder.tv_level.setVisibility(View.VISIBLE);
            spanString.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.item_name_color)),
                    0, item.getSenderName().length() + strt.length() + 1, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
           // holder.tv_context.setTextColor(context.getResources().getColor(R.color.colorTextWhite));
            holder.tv_context.setTextColor(context.getResources().getColor(R.color.item_gift_color));
            holder.tv_context.setText(spanString);
        }
        if(item.getLevel()>0)
            holder.tv_level.setBackgroundResource(LevelUtil.getInstance().getLevel(item.getLevel()));
    }
    //获取数据的数量
    @Override
    public int getItemCount() {
        return listMessage.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_context,tv_level,tv_context1;
        public RelativeLayout rl_got_live,rl_live;

        public ViewHolder(View view) {
            super(view);
            tv_context = (TextView) view.findViewById(R.id.tv_context);
            tv_level = (TextView) view.findViewById(R.id.tv_level);
            tv_context1 = (TextView) view.findViewById(R.id.tv_context1);
            rl_got_live = (RelativeLayout) view.findViewById(R.id.rl_got_live);
            rl_live = (RelativeLayout) view.findViewById(R.id.rl_live);
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