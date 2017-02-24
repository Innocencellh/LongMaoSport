package com.live.longmao.util;

/**
 * Created by Administrator on 2016/10/17.
 * 封装的时间倒计时方法
 */
public class TimeUtil {
    /**
     * 时间的处理
     *
     * @param time
     * @return
     */
    public static String getTimeFromInt(int time) {

        if (time <= 0) {
            return "00:00";
        }
        int secondnd = time / 60;
        int million = time % 60;
        String f = secondnd >= 10 ? String.valueOf(secondnd) : "0"
                + String.valueOf(secondnd);
        String m = million >= 10 ? String.valueOf(million) : "0"
                + String.valueOf(million);
        return f + ":" + m;
    }
}
