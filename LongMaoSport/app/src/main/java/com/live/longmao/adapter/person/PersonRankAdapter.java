package com.live.longmao.adapter.person;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.live.longmao.R;
import com.live.longmao.model.PersonRankInfoBean;
import com.live.longmao.util.GlideUtil;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by HPDN on 2016/7/10.
 */
public class PersonRankAdapter extends BaseAdapter {
    private ArrayList<PersonRankInfoBean> data = new ArrayList<>();
    private Context mContext;
    String url = "http://121.40.65.153/photo/";

    public PersonRankAdapter(ArrayList<PersonRankInfoBean> data, Context mContext) {
        this.data = data;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder1 viewHolder1 = null;
        ViewHolder2 viewHolder2 = null;
//        ViewHolder3 viewHolder3 = null;
//        ViewHolder4 viewHolder4 = null;

        if (convertView == null) {
            switch (getItemViewType(position)) {
                case 0:
                    viewHolder1 = new ViewHolder1();
                    convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rank_one_new, null);
                    viewHolder1.tv_name = (TextView) convertView.findViewById(R.id.content_tv);
                    viewHolder1.iv_hand = (CircleImageView) convertView.findViewById(R.id.iv_hand);
                    viewHolder1.tv_kll = (TextView) convertView.findViewById(R.id.tv_kll);
                    convertView.setTag(viewHolder1);
                    break;
                case 1:
                    viewHolder2 = new ViewHolder2();
                    convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rank_all_new, null);
                    viewHolder2.tv_name = (TextView) convertView.findViewById(R.id.content_tv);
                    viewHolder2.tv_kll = (TextView) convertView.findViewById(R.id.tv_kll);
                    viewHolder2.iv_hand = (CircleImageView) convertView.findViewById(R.id.iv_hand);
                    viewHolder2.rank_tv = (TextView) convertView.findViewById(R.id.rank_tv);
                    viewHolder2.icon_crown_new_two = (ImageView) convertView.findViewById(R.id.icon_crown_new_two);
                    viewHolder2.icon_crown_new_three = (ImageView) convertView.findViewById(R.id.icon_crown_new_three);
                    viewHolder2.iv_rank_two = (ImageView) convertView.findViewById(R.id.iv_rank_two);
                    viewHolder2.iv_rank_three = (ImageView) convertView.findViewById(R.id.iv_rank_three);
                    if (position == 1) {
                        viewHolder2.rank_tv.setVisibility(View.GONE);
                        viewHolder2.icon_crown_new_three.setVisibility(View.GONE);
                        viewHolder2.iv_rank_three.setVisibility(View.GONE);
                        viewHolder2.icon_crown_new_two.setVisibility(View.VISIBLE);
                        viewHolder2.iv_rank_two.setVisibility(View.VISIBLE);
                    } else if (position == 2) {
                        viewHolder2.rank_tv.setVisibility(View.GONE);
                        viewHolder2.icon_crown_new_two.setVisibility(View.GONE);
                        viewHolder2.iv_rank_two.setVisibility(View.INVISIBLE);
                        viewHolder2.icon_crown_new_three.setVisibility(View.VISIBLE);
                        viewHolder2.iv_rank_three.setVisibility(View.VISIBLE);
                    } else {
                        viewHolder2.icon_crown_new_three.setVisibility(View.GONE);
                        viewHolder2.iv_rank_three.setVisibility(View.GONE);
                        viewHolder2.icon_crown_new_two.setVisibility(View.GONE);
                        viewHolder2.iv_rank_two.setVisibility(View.INVISIBLE);
                    }

                    convertView.setTag(viewHolder2);
                    break;
//                case 2:
//                    viewHolder3 = new ViewHolder3();
//                    convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rank_all_new, null);
//                    viewHolder3.tv_name = (TextView) convertView.findViewById(R.id.content_tv);
//                    viewHolder3.tv_kll = (TextView) convertView.findViewById(R.id.tv_kll);
//                    viewHolder3.iv_hand = (CircleImageView) convertView.findViewById(R.id.iv_hand);
//                    convertView.setTag(viewHolder3);
//                    break;
//                case 3:
//                    viewHolder4 = new ViewHolder4();
//                    convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rank_all_new, null);
//                    viewHolder4.rank_tv = (TextView) convertView.findViewById(R.id.rank_tv);
//                    viewHolder4.tv_name = (TextView) convertView.findViewById(R.id.content_tv);
//                    viewHolder4.tv_kll = (TextView) convertView.findViewById(R.id.tv_kll);
//                    viewHolder4.iv_hand = (CircleImageView) convertView.findViewById(R.id.iv_hand);
//                    convertView.setTag(viewHolder4);
//                    break;
            }
        } else {
            switch (getItemViewType(position)) {
                case 0:
                    viewHolder1 = (ViewHolder1) convertView.getTag();
                    break;
                case 1:
                    viewHolder2 = (ViewHolder2) convertView.getTag();
                    break;
//                case 2:
//                    viewHolder3 = (ViewHolder3) convertView.getTag();
//                    break;
//                case 3:
//                    viewHolder4 = (ViewHolder4) convertView.getTag();
//                    break;
            }
        }
        switch (getItemViewType(position)) {
            case 0:
                viewHolder1.tv_name.setText(data.get(position).getUserName());
                viewHolder1.tv_kll.setText("贡献" + data.get(position).getSumClr() + "卡路里");
                GlideUtil.getInstance().load(mContext, viewHolder1.iv_hand, url + data.get(0).getPhotoUrl());
                break;
            case 1:
                viewHolder2.tv_name.setText(data.get(position).getUserName());
                viewHolder2.tv_kll.setText("贡献:" + data.get(position).getSumClr() + "卡");
                GlideUtil.getInstance().load(mContext, viewHolder2.iv_hand, url + data.get(1).getPhotoUrl());
                if (position == 3) {
                    viewHolder2.rank_tv.setText((position + 1) + "");
                }
                break;
//            case 2:
//                viewHolder3.tv_name.setText(data.get(2).getUserName());
//                viewHolder3.tv_kll.setText("贡献"+data.get(2).getSumClr()+"卡路里");
//                GlideUtil.getInstance().load(mContext, viewHolder3.iv_hand, url + data.get(2).getPhotoUrl());
//                break;
//            case 3:
//                viewHolder4.rank_tv.setText("NO." + (position + 1));
//                viewHolder4.tv_kll.setText("贡献"+data.get(position).getSumClr()+"卡路里");
//                viewHolder4.tv_name.setText(data.get(position).getUserName());
//                GlideUtil.getInstance().load(mContext, viewHolder4.iv_hand, url + data.get(position).getPhotoUrl());
//                break;
        }
        return convertView;
    }

    public class ViewHolder1 {
        TextView tv_name, tv_kll, rank_tv;
        CircleImageView iv_hand;
        ImageView icon_crown_new_two, icon_crown_new_three, iv_rank_two, iv_rank_three;

    }

    static class ViewHolder2 {
        TextView tv_name, tv_kll, rank_tv;
        CircleImageView iv_hand;
        ImageView icon_crown_new_two, icon_crown_new_three, iv_rank_two, iv_rank_three;
    }

//    static class ViewHolder3 {
//        TextView tv_name,tv_kll,rank_tv;
//        CircleImageView iv_hand;
//    }
//
//    static class ViewHolder4 {
//        TextView rank_tv;
//        TextView tv_name,tv_kll;
//        CircleImageView iv_hand;
//    }
}
