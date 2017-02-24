package com.live.longmao.adapter.person;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.live.longmao.R;
import com.live.longmao.model.PersonAttentionInfoTwoList;
import com.live.longmao.util.GlideUtil;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by HPDN on 2016/7/8.
 */
public class NewPersonFansAdapter extends RecyclerView.Adapter<NewPersonFansAdapter.ViewHolder> {
    public Context mContext;
    private ArrayList<PersonAttentionInfoTwoList> data = new ArrayList<>();

    public NewPersonFansAdapter(ArrayList<PersonAttentionInfoTwoList> data, Context mContext) {
        this.data = data;
        this.mContext = mContext;
    }

    @Override
    public NewPersonFansAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new_person_attention, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewPersonFansAdapter.ViewHolder holder, int position) {

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
        private CircleImageView iv_attention_head;

        public ViewHolder(View itemView) {
            super(itemView);
          //  tv_name = (TextView) itemView.findViewById(R.id.attention_tv);
            iv_attention_head = (CircleImageView) itemView.findViewById(R.id.iv_attention_head);
            // attention_iv_no = (ImageView) itemView.findViewById(R.id.attention_iv_no);

        }
    }
}