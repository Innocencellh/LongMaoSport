package com.live.longmao.avcontrollers;

import android.content.Context;
import android.content.Intent;

import com.live.longmao.util.Constants;
import com.live.longmao.util.SxbLog;
import com.tencent.av.sdk.AVContext;
import com.tencent.av.sdk.AVError;
import com.tencent.openqq.IMSdkInt;

/**
 * 音视频初始化接口类
 */
class AVContextControl {
    private static final String TAG = "AvContextControl";
    private boolean mIsInStartContext = false;
    private boolean mIsInStopContext = false;
    private Context mContext;
    private AVContext mAVContext = null;
    private String mSelfIdentifier = "";
    private String mPeerIdentifier = "";
    private AVContext.Config mConfig = null;
    private String mUserSig = "";
    private static boolean isStartContext = false;

    /**
     * 启动AVSDK系统的回调接口
     */
    private AVContext.StartCallback mStartContextCompleteCallback = new AVContext.StartCallback() {
        public void OnComplete(int result) {
            mIsInStartContext = false;
//            Toast.makeText(mContext, "SDKLogin complete : " + result, Toast.LENGTH_SHORT).show();
            SxbLog.i(TAG, "keypath AVSDK startContext  result " + result);
//            mContext.sendBroadcast(new Intent(
//                    AvConstants.ACTION_START_CONTEXT_COMPLETE).putExtra(
//                    AvConstants.EXTRA_AV_ERROR_RESULT, result));
            if (result == AVError.AV_OK)
                isStartContext = true;
            if (result != AVError.AV_OK) {
                mAVContext = null;
            }
        }
    };

    /**
     * 关闭AVSDK系统的回调接口
     */
    private AVContext.StopCallback mStopContextCompleteCallback = new AVContext.StopCallback() {
        public void OnComplete() {

            avDestory(true);
        }
    };

    AVContextControl(Context context) {
        mContext = context;
    }

    /**
     * 启动AVSDK系统
     *
     * @return 0 代表成功
     */
    int startContext() {
        int result = AVError.AV_OK;
        if (!hasAVContext()) {
            SxbLog.i(TAG, "AVSDKLogin startContext hasAVContext ");
            onAVSDKCreate(true, IMSdkInt.get().getTinyId(), 0);
        } else {
            return AVError.AV_ERR_FAILED;
        }
        return result;
    }

    /**
     *  设置AVSDK参数
     * @param appid
     * @param accountype
     * @param identifier
     * @param usersig
     */
    public void setAVConfig(int appid, String accountype, String identifier, String usersig) {
        mConfig = new AVContext.Config();
        mConfig.sdkAppId = appid;
        mConfig.accountType = accountype;
        mConfig.appIdAt3rd = Integer.toString(appid);
        mConfig.identifier = identifier;
        mUserSig = usersig;
    }

    /**
     * 关闭AVSDK系统
     */
    void stopContext() {
        if (hasAVContext()) {
            SxbLog.d(TAG, "WL_DEBUG stopContext");
            mAVContext.stop(mStopContextCompleteCallback);
            mIsInStopContext = true;
        }
    }

    boolean getIsInStartContext() {
        return mIsInStartContext;
    }

    boolean getIsInStopContext() {
        return mIsInStopContext;
    }

    boolean setIsInStopContext(boolean isInStopContext) {
        return this.mIsInStopContext = isInStopContext;
    }

    boolean hasAVContext() {
        return mAVContext != null;
    }

    AVContext getAVContext() {
        return mAVContext;
    }

    public String getSelfIdentifier() {
        return mSelfIdentifier;
    }


    /**
     * 实际初始化AVSDK
     * @param result
     * @param tinyId
     * @param errorCode
     */
    private void onAVSDKCreate(boolean result, long tinyId, int errorCode) {
        if (result) {
            mAVContext = AVContext.createInstance(mContext, mConfig);
            mSelfIdentifier = mConfig.identifier;
            int ret = mAVContext.start(mStartContextCompleteCallback);
            SxbLog.i(TAG, "onAVSDKCreate ret "+ret);
            mIsInStartContext = true;
        } else {
            mStartContextCompleteCallback.OnComplete(errorCode);
        }
    }


    /**
     * 销毁AVSDK
     * @param result
     */
    private void avDestory(boolean result) {
        mAVContext.destroy();
        mAVContext = null;
        mIsInStopContext = false;
        isStartContext = false;
        mContext.sendBroadcast(new Intent(
                Constants.ACTION_CLOSE_CONTEXT_COMPLETE));
    }


}