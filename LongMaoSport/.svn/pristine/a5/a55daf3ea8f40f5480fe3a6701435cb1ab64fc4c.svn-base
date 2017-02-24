package com.live.longmao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.live.longmao.R;

/**
 * Created by Administrator on 2016/9/29 0029.
 */
public class ExchangeAccountAdapter extends RecyclerView.Adapter<ExchangeAccountAdapter.ViewHolder> {
    public Context mContext;
    public  ExchangeAccountAdapter(Context context){
        mContext = context ;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coin_exchange, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 20;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);

        }
    }
}
