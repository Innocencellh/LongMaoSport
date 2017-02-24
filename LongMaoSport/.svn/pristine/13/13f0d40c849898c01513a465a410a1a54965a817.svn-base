package com.live.longmao.xrecyclerview;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.live.longmao.adapter.NearAdapter;
import com.live.longmao.adapter.NewAdapter;
import com.live.longmao.util.DisplayUtil;
import com.live.longmao.util.UIUtils;

/**
 * Created by Administrator on 2016/7/28.
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    int mSpace ;
    NewAdapter newAdapter;

    /**
     * @param space 传入的值，其单位视为dp
     */
    public SpaceItemDecoration(Context context,int space,NewAdapter newAdapter) {
        this.mSpace = DisplayUtil.px2dip(context,space);
        this.newAdapter =newAdapter;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
       // int itemCount = mAdapter.getItemCount();
        int pos = parent.getChildAdapterPosition(view);

        outRect.left = 0;
        outRect.top = 0;
        outRect.bottom = mSpace;

        if((pos-1)%3==0)
        {
            outRect.right=0;
        }else
        {
            outRect.right = mSpace;
        }
    }
}