package com.live.longmao.util;

import android.util.Log;

import java.util.Calendar;

/**
 * 日志输出
 */
public class SxbLog {
    public enum SxbLogLevel {
        OFF,
        ERROR,
        WARN,
        DEBUG,
        INFO
    }

    static private SxbLogLevel level = SxbLogLevel.INFO;

    static public String[] getStringValues() {
        SxbLogLevel[] levels = SxbLogLevel.values();
        String[] stringValuse = new String[levels.length];
        for (int i = 0; i < levels.length; i++) {
            stringValuse[i] = levels[i].toString();
        }
        return stringValuse;
    }

    static public void setLogLevel(SxbLogLevel newLevel) {
        level = newLevel;
        w("Log", "change log level: " + newLevel);
    }

    public static void v(String strTag, String strInfo) {
        Log.v(strTag, strInfo);
        if (level.ordinal() >= SxbLogLevel.INFO.ordinal()) {
            SxbLogImpl.writeLog("I", strTag, strInfo, null);
        }
    }

    public static void i(String strTag, String strInfo) {
        v(strTag, strInfo);
    }

    public static void d(String strTag, String strInfo) {
        Log.d(strTag, strInfo);
        if (level.ordinal() >= SxbLogLevel.DEBUG.ordinal()) {
            SxbLogImpl.writeLog("D", strTag, strInfo, null);
        }
    }


    public static void w(String strTag, String strInfo) {
        Log.w(strTag, strInfo);
        if (level.ordinal() >= SxbLogLevel.WARN.ordinal()) {
            SxbLogImpl.writeLog("W", strTag, strInfo, null);
        }
    }

    public static void e(String strTag, String strInfo) {
        Log.e(strTag, strInfo);
        if (level.ordinal() >= SxbLogLevel.ERROR.ordinal()) {
            SxbLogImpl.writeLog("E", strTag, strInfo, null);
        }
    }

    public static void writeException(String strTag, String strInfo, Exception tr) {
        SxbLogImpl.writeLog("C", strTag, strInfo, tr);
    }

    public static String getTime() {

        long currentTimeMillis = System.currentTimeMillis();

        Log.v("Test", String.valueOf(currentTimeMillis));


        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(currentTimeMillis);

        String time = calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND) + ":" + calendar.get(Calendar.MILLISECOND);
        return time;
    }
}
