package com.live.longmao.adapter.person;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.live.longmao.R;
import com.live.longmao.model.PersonAttentionInfoTwoList;
import com.live.longmao.presenters.viewinface.FollowView;
import com.live.longmao.util.GlideUtil;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by HPDN on 2016/7/8.
 */
public class NewPersonAttentionAdapter extends RecyclerView.Adapter<NewPersonAttentionAdapter.ViewHolder> {
    public Context mContext;
    private ArrayList<PersonAttentionInfoTwoList> data = new ArrayList<>();
    private FollowView mFollowView;

    public NewPersonAttentionAdapter(Context mContext, ArrayList<PersonAttentionInfoTwoList> data, FollowView mFollowView) {
        this.mContext = mContext;
        this.data = data;
        this.mFollowView = mFollowView;
    }


    @Override
    public NewPersonAttentionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new_person_attention, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final NewPersonAttentionAdapter.ViewHolder holder, final int position) {

//        if (data.get(position).getAttentionsState() == 1) {
//            holder.imageView.setBackgroundDrawable(mContext.getResources().getDrawable(R.mipmap.icon_checked_address));
//            holder.imageView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mFollowView.onCancelFollowSucc(position);
//                }
//            });
//        } else {
//            holder.imageView.setBackgroundDrawable(mContext.getResources().getDrawable(R.mipmap.icon_attestion));
//            holder.imageView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mFollowView.onFollowSucc(position);
//                }
//            });
//        }
     //   holder.tv_name.setText(data.get(position).getNickName());
        String url = "http://121.40.65.153/photo/";
        GlideUtil.getInstance().load(mContext, holder.iv_attention_head, url + data.get(position).getFacePhoto());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_name;
        private ImageView imageView, attention_iv_no;
        private CircleImageView iv_attention_head;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.name);
            imageView = (ImageView) itemView.findViewById(R.id.attention_iv);
            iv_attention_head = (CircleImageView) itemView.findViewById(R.id.iv_attention_head);
            // attention_iv_no = (ImageView) itemView.findViewById(R.id.attention_iv_no);

        }
    }
}
