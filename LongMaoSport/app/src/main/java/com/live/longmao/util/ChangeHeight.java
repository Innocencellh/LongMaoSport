package com.live.longmao.util;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2016/7/7.
 */
public class ChangeHeight {
    public static void changeLH(Context c,View v) {
        LinearLayout.LayoutParams linearParams =
                (LinearLayout.LayoutParams) v.getLayoutParams(); //取控件textView当前的布局参数
        linearParams.height =DisplayUtil.dip2px(c, 50)+getStatusBarHeight(c);
        Log.e("linearParams.height",linearParams.height+"");
        v.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
        v.setPadding(0, DisplayUtil.dip2px(c, 25), 0, 0);
    }
    public static void changeRH(Context c,View v) {
        RelativeLayout.LayoutParams linearParams =
                (RelativeLayout.LayoutParams) v.getLayoutParams(); //取控件view当前的布局参数
        linearParams.height =DisplayUtil.dip2px(c,50)+getStatusBarHeight(c);
        Log.e("linearParams.height",linearParams.height+"");
        v.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
        v.setPadding(0, DisplayUtil.dip2px(c, 25), 0, 0);
    }
    public static void changeFH(Context c,View v) {
        FrameLayout.LayoutParams linearParams =
                (FrameLayout.LayoutParams) v.getLayoutParams(); //取控件view当前的布局参数
        linearParams.height =DisplayUtil.dip2px(c,50)+getStatusBarHeight(c);
        Log.e("linearParams.height",linearParams.height+"");
        v.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
        v.setPadding(0, DisplayUtil.dip2px(c, 25), 0, 0);
    }
    public static void setPadding(Context c,View v) {
        v.setPadding(0, DisplayUtil.dip2px(c, 25), 0, 0);
    }

    //获取手机状态栏高度

    public static int getStatusBarHeight(Context context){

        Class<?> c = null;

        Object obj = null;

        Field field = null;

        int x = 0, statusBarHeight = 38;//通常这个值会是38

        try {

            c = Class.forName("com.android.internal.R$dimen");

            obj = c.newInstance();

            field = c.getField("status_bar_height");

            x = Integer.parseInt(field.get(obj).toString());

            statusBarHeight = context.getResources().getDimensionPixelSize(x);

        } catch (Exception e1) {

            e1.printStackTrace();

        }

        return statusBarHeight;

    }
}
