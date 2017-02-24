package com.live.longmao.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Administrator on 2016/8/2.
 */
public class ScreenManager {
    private static ArrayList<Activity> activityStack;
    private static ScreenManager instance;
    private  ScreenManager(){
    }
    public static ScreenManager getScreenManager(){
        if(instance==null){
            instance=new ScreenManager();
        }
        return instance;
    }
    //退出栈顶Activity
    public void popActivity(Activity activity){
        if(activity!=null){
            activity.finish();
            activityStack.remove(activity);
            activity=null;
        }
    }

    //将当前Activity推入栈中
    public void pushActivity(Activity activity){
        if(activityStack==null){
            activityStack=new ArrayList<>();
        }
        activityStack.add(activity);
    }
    //退出栈中所有Activity
    public void popAllActivity(){
        for (Activity act:activityStack)
        {
            act.finish();
        }
    }
}