
package com.live.longmao.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;

import java.lang.reflect.Field;

public class ScreenUtil {

    public static int getStatusBarHeight(final Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
        }
        return statusBarHeight;
    }

    public static int getViewTopInWindow(final View v) {
        final int[] loc = new int[2];
        v.getLocationInWindow(loc);
        return loc[1];
    }

    public static int heightWithStatuBar(Context ctx, int height) {
        if (isWholeScreenH(height)) {
            return height;
        } else if (isWholeScreenH(height + getStatusBarHeight(ctx))) {
            return height + getStatusBarHeight(ctx);
        } else {
            return height;
        }
    }

    public static int heightWithoutStatuBar(Context ctx, int height) {
        if (isWholeScreenH(height)) {
            return height - getStatusBarHeight(ctx);
        }
        return height;
    }

    public static int getScreenWidth(final Context context) {
        return getDisplayMetrics(context).widthPixels;
    }

    public static int getScreenHeight(final Context context) {
        return getDisplayMetrics(context).heightPixels;
    }

    public static int getScreenDpi(final Context context) {
        return getDisplayMetrics(context).densityDpi;
    }

    public static float getScreenDensity(final Context context) {
        return getDisplayMetrics(context).density;
    }

    public static DisplayMetrics getDisplayMetrics(final Context context) {
        try {
            return context.getResources().getDisplayMetrics();
        } catch (Exception e) {
        }
        // got Exception, return default size
        final DisplayMetrics metrics = new DisplayMetrics();
        metrics.widthPixels = 720;
        metrics.heightPixels = 1280;
        return metrics;
    }

    /**
     * 判断是否是全部的屏幕高度[800,1280,1920,2560]
     */
    private static boolean isWholeScreenH(final int h) {
        return h == 800 || h % 320 == 0;
    }

}
