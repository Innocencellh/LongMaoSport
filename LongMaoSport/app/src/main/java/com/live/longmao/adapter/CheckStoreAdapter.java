package com.live.longmao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.live.longmao.R;

import java.util.List;

/**
 * Created by Administrator on 2016/7/15 0015.
 */
public class CheckStoreAdapter extends RecyclerView.Adapter<CheckStoreAdapter.MyViewHolder> {
    private Context context;
    private List<String> urlList;
    private LayoutInflater inflater;
    private int[] image;
    private String[] text;

    public CheckStoreAdapter(Context context, int[] image, String[] text) {
        this.context = context;
        this.image = image ;
        this.text = text;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.adapter_item_check_store, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.imageView.setImageResource(image[position]);
       // holder.textView.setText(text[position]);
    }


    @Override
    public int getItemCount() {
        return image.length;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
     //   private TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.adapter_check_store_image);
          //  textView = (TextView) itemView.findViewById(R.id.adapter_check_store_text);
        }
    }
}
