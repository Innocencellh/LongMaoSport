package com.live.longmao.util;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.live.longmao.R;

/**
 * Created by Administrator on 2016/8/29.
 */
public class GlideUtil {
    private static GlideUtil glideUtil;
    private GlideUtil(){}

    public static GlideUtil getInstance()
    {
        if(null==glideUtil)
        {
            glideUtil = new GlideUtil();
        }
        return glideUtil;
    }
    //加载头像
    public  void  load(Context mContext,ImageView view,int resources){
        Glide.with(mContext).load(resources).into(view);
    }
    //加载头像
    public  void  load(Activity mActivity,ImageView view,int resources){
        Glide.with(mActivity).load(resources).into(view);
    }
    //加载头像
    public void load(Activity mActivity,ImageView view,String url)
    {
        Glide.with(mActivity).load(url).dontAnimate().placeholder(R.mipmap.ic_default_head).into(view);
    }
    //加载头像
    public void load(Context mContext,ImageView view,String url)
    {
        Glide.with(mContext).load(url).dontAnimate().placeholder(R.mipmap.ic_default_head).into(view);
    }
    //加载头像
    public void load(Activity mActivity,ImageView view,String url,int resources)
    {
        Glide.with(mActivity).load(url).dontAnimate().placeholder(resources).into(view);
    }
    //加载头像
    public void load(Context mContext,ImageView view,String url,int resources)
    {
        Glide.with(mContext).load(url).dontAnimate().placeholder(resources).into(view);
    }
    //加载背景
    public void loadBg(Activity mActivity,ImageView view,String url)
    {
        Glide.with(mActivity).load(url).dontAnimate().placeholder(R.mipmap.ic_default_head).into(view);
    }
    //加载背景
    public void loadBg(Context mContext,ImageView view,String url)
    {
        Glide.with(mContext).load(url).dontAnimate().placeholder(R.mipmap.ic_default_head).into(view);
    }
    //加载背景
    public void loadBg(Activity mActivity,ImageView view,String url,int resources)
    {
        Glide.with(mActivity).load(url).dontAnimate().placeholder(resources).into(view);
    }
    //加载背景
    public void loadBg(Context mContext,ImageView view,String url,int resources)
    {
        Glide.with(mContext).load(url).dontAnimate().placeholder(resources).into(view);
    }
}
