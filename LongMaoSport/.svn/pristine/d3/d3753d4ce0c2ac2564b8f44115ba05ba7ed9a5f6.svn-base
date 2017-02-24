package com.live.longmao.adapter;

import android.app.Service;
import android.content.Context;
import android.os.Vibrator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

import com.bumptech.glide.Glide;
import com.live.longmao.R;
import com.live.longmao.util.GlideUtil;

import java.util.List;

/**
 * Created by Jason on 16/5/4.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder>  {
    private boolean mNeedShake = false;
    private boolean mStartShake = false;

    private static final int ICON_WIDTH = 80;
    private static final int ICON_HEIGHT = 94;
    private static final float DEGREE_0 = 1.8f;
    private static final float DEGREE_1 = -2.0f;
    private static final float DEGREE_2 = 2.0f;
    private static final float DEGREE_3 = -1.5f;
    private static final float DEGREE_4 = 1.5f;
    private static final int ANIMATION_DURATION = 80;

    private int mCount = 0;

    float mDensity;

    private List<String> urlList;
    private Context mContext;
    private LayoutInflater mInflater;
    private OnItemClick itemClick;
    public interface OnItemClick
    {
        void onClick(int position);
        void onLongClick(int position,View v);
    }

    public void setOnItemClick(OnItemClick itemClick)
    {
        this.itemClick = itemClick;
    }

    public RecyclerViewAdapter(Context context, List<String> urlList) {
        this.mContext = context;
        this.urlList = urlList;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);//LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v =mInflater.inflate(R.layout.item_my_photo_list, parent, false);
        return new ViewHolder(v, R.id.img_photo);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,final int position) {
        if(position==0)
        {
            holder.img_list_item.setImageResource(R.mipmap.icon_photo_add);
        }else {
            GlideUtil.getInstance().load(mContext, holder.img_list_item, urlList.get(position));
        }
        holder.img_list_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNeedShake = false;
                mCount = 0;
                mStartShake = false;
                if (itemClick != null) {
                    itemClick.onClick(position);
                }
            }
        });
        holder.img_list_item.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (position!=0&&!mStartShake) {
                    mStartShake = true;
                    mNeedShake = true;
                    /**震动服务*/
                    Vibrator vib = (Vibrator) mContext.getSystemService(Service.VIBRATOR_SERVICE);
                    vib.vibrate(1000);//只震动一秒，一次
                    shakeAnimation(v);
                }
                if (itemClick != null) {
                    itemClick.onLongClick(position,v);
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return urlList.size();
    }

    private void shakeAnimation(final View v) {
        float rotate = 0;
        int c = mCount++ % 5;
        if (c == 0) {
            rotate = DEGREE_0;
        } else if (c == 1) {
            rotate = DEGREE_1;
        } else if (c == 2) {
            rotate = DEGREE_2;
        } else if (c == 3) {
            rotate = DEGREE_3;
        } else {
            rotate = DEGREE_4;
        }
        final RotateAnimation mra = new RotateAnimation(rotate, -rotate, ICON_WIDTH * mDensity / 2, ICON_HEIGHT * mDensity / 2);
        final RotateAnimation mrb = new RotateAnimation(-rotate, rotate, ICON_WIDTH * mDensity / 2, ICON_HEIGHT * mDensity / 2);

        mra.setDuration(ANIMATION_DURATION);
        mrb.setDuration(ANIMATION_DURATION);

        mra.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                if (mNeedShake) {
                    mra.reset();
                    v.startAnimation(mrb);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationStart(Animation animation) {

            }

        });

        mrb.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                if (mNeedShake) {
                    mrb.reset();
                    v.startAnimation(mra);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationStart(Animation animation) {

            }

        });
        v.startAnimation(mra);
    }

}