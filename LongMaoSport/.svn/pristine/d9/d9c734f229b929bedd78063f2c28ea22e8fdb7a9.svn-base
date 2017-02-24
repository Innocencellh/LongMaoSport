package com.live.longmao.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.WindowManager;

/**
 * Created by joncran001 on 2/19/16.
 */
public class NotCancelableDialog extends Dialog {

    private static final String TAG = "NotCancelableDialog";

    private Activity mContext;

    public NotCancelableDialog(Context context, int themeResId) {
        super(context, themeResId);
        mContext = (Activity) context;
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }

    @Override
    public void onBackPressed() {
        Log.i(TAG, "点击返回键");
        mContext.onBackPressed();
    }
}
