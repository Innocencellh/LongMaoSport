package com.live.longmao;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.support.multidex.MultiDex;

import com.live.longmao.presenters.InitBusinessHelper;
import com.live.longmao.util.FileUtil;
import com.live.longmao.util.SxbLogImpl;

import cn.sharesdk.framework.ShareSDK;

/**
 * Created by Administrator on 2016/6/24.
 */
public class BaseApp extends Application {

    private static BaseApp app;
    private static Context context;
    private static String clientid;
    private static String data;
    public static String LONGMAOIMG = Environment.getExternalStorageDirectory().getAbsolutePath()
            + "/LongMao/Compression/";
    private static double longitude;
    private static double latitude;
    private static String LId;//上播ID

    private static String LMId;

    public static String getLMId() {
        return LMId;
    }

    public static void setLMId(String LMId) {
        BaseApp.LMId = LMId;
    }

    public static String getLId() {
        return LId;
    }

    public static void setLId(String LId) {
        BaseApp.LId = LId;
    }

    public static String getCity() {
        return city;
    }

    public static void setCity(String city) {
        BaseApp.city = city;
    }

    private static String city;

    public static double getLongitude() {
        return longitude;
    }

    public static void setLongitude(double longitude) {
        BaseApp.longitude = longitude;
    }

    public static double getLatitude() {
        return latitude;
    }

    public static void setLatitude(double latitude) {
        BaseApp.latitude = latitude;
    }

    public static String getClientid() {
        return clientid;
    }

    public static void setClientid(String clientid) {
        BaseApp.clientid = clientid;
    }

    public static String getData() {
        return data;
    }

    public static void setData(String data) {
        BaseApp.data = data;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        context = getApplicationContext();

        SxbLogImpl.init(getApplicationContext());

        //初始化APP
        InitBusinessHelper.initApp(context);
        FileUtil.CREATEFILE(LONGMAOIMG);
        ShareSDK.initSDK(this);

//        LeakCanary.install(this);

        //创建AVSDK 控制器类
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


    public static Context getContext() {
        return context;
    }

    public static BaseApp getInstance() {
        return app;
    }
}
