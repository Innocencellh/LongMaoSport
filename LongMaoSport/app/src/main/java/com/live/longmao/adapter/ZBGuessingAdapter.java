package com.live.longmao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.live.longmao.R;
import com.live.longmao.TimeTextView;
import com.live.longmao.model.GuessingInfo;

import java.util.List;
import java.util.Timer;

/**
 * Created by HPDN on 2016/12/4.
 */
public class ZBGuessingAdapter extends BaseAdapter {

    private List<GuessingInfo> data;
    public Context mContext;
    Timer timer = new Timer();
    int timeCount;
    private long sumTime;

    public ZBGuessingAdapter(List<GuessingInfo> data, Context mContext) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        GuessingViewHolder guessingViewHolder = null;
        if (convertView == null) {
            guessingViewHolder = new GuessingViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.a_item_list_view, null);
            guessingViewHolder.tv_item_guessing01 = (TimeTextView) convertView.findViewById(R.id.tv_item_guessing01);
            guessingViewHolder.iv = (ImageView) convertView.findViewById(R.id.iv_guess);
            guessingViewHolder.tv_item_guessing02 = (TextView) convertView.findViewById(R.id.tv_item_guessing02);
            guessingViewHolder.tv_item_tittle_guessing = (TextView) convertView.findViewById(R.id.tv_item_tittle_guessing);

            convertView.setTag(guessingViewHolder);
        } else {
            guessingViewHolder = (GuessingViewHolder) convertView.getTag();
        }
        if (position == data.size() - 1 && data.get(position).getAnchorUserId() == null) {
            guessingViewHolder.iv.setImageResource(R.mipmap.icon_guess_jiahao);
            guessingViewHolder.tv_item_guessing01.setVisibility(View.GONE);
            guessingViewHolder.tv_item_guessing02.setVisibility(View.GONE);
            guessingViewHolder.tv_item_tittle_guessing.setVisibility(View.GONE);
        } else {
            guessingViewHolder.iv.setImageResource(R.mipmap.icon_star_bg);
            guessingViewHolder.tv_item_tittle_guessing.setText(data.get(position).getGuessTitle());
            if (data.get(position).getStatus() == 1) {
                int zong = data.get(position).getSurplusTime();
                int fen = zong / 60;
                int miao = zong % 60;
                long[] longs = new long[2];
                longs[0] = new Long(fen);
                longs[1] = new Long(miao);
                guessingViewHolder.tv_item_guessing01.setVisibility(View.VISIBLE);
                guessingViewHolder.tv_item_guessing02.setVisibility(View.GONE);
               // guessingViewHolder.tv_item_guessing01.setText(data.get(position).getSurplusTime() + "");
                guessingViewHolder.tv_item_tittle_guessing.setText(data.get(position).getGuessTitle());
                guessingViewHolder.tv_item_guessing01.setTimes(longs);
                //已经在倒计时的时候不再开启计时
                if (!guessingViewHolder.tv_item_guessing01.isRun()) {
                    guessingViewHolder.tv_item_guessing01.run();
                }
            } else if (data.get(position).getStatus() == 2) {
                guessingViewHolder.tv_item_guessing01.setVisibility(View.GONE);
                guessingViewHolder.tv_item_guessing02.setVisibility(View.VISIBLE);
                guessingViewHolder.tv_item_guessing02.setText("已封盘");
                guessingViewHolder.tv_item_tittle_guessing.setText(data.get(position).getGuessTitle());
            }
        }

        return convertView;
    }

    public class GuessingViewHolder {
        private TextView tv_item_guessing02, tv_item_tittle_guessing;
        private ImageView iv;
        private TimeTextView tv_item_guessing01;
    }

}
