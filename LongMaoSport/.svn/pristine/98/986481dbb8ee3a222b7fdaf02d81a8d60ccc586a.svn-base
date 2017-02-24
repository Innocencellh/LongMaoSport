package com.live.longmao.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/2/15 0015.
 */
public class ToastUtil {
    private static Toast toast = null;
    public static void showToast(Context context ,String content){
        if (toast!=null){
            toast.cancel();
        }
        toast = Toast.makeText(context,content,Toast.LENGTH_SHORT);
        toast.show();
    }

}
