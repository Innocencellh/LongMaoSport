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
public class SpaceItemNear extends RecyclerView.ItemDecoration {
    int mSpace ;
    NearAdapter nearAdapter;

    /**
     * @param space 传入的值，其单位视为dp
     */

    public SpaceItemNear(Context context,int space,NearAdapter nearAdapter) {
        this.mSpace = DisplayUtil.px2dip(context,space);
        this.nearAdapter =nearAdapter;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        // int itemCount = mAdapter.getItemCount();
        int pos = parent.getChildAdapterPosition(view);

        outRect.left = 0;
        outRect.top = 0;
        outRect.bottom = mSpace;

        if((pos-1)%2==0)
        {
            outRect.right=0;
        }else
        {
            outRect.right = mSpace;
        }
    }
}