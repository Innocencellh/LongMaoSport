package com.live.longmao.presenters;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.live.longmao.BaseApp;
import com.live.longmao.avcontrollers.QavsdkControl;
import com.live.longmao.model.ListUserInfo;
import com.live.longmao.model.MySelfInfo;
import com.live.longmao.model.UserInfo;
import com.live.longmao.presenters.viewinface.LoginView;
import com.live.longmao.presenters.viewinface.LogoutView;
import com.live.longmao.util.Constants;
import com.live.longmao.util.SxbLog;
import com.live.okhttp.JsonGenericsSerializator;
import com.live.okhttp.OkHttpUtils;
import com.live.okhttp.callback.GenericsCallback;
import com.tencent.TIMCallBack;
import com.tencent.TIMManager;
import com.tencent.TIMUser;

import java.util.List;

import okhttp3.Call;
import tencent.tls.platform.TLSErrInfo;
import tencent.tls.platform.TLSPwdLoginListener;
import tencent.tls.platform.TLSStrAccRegListener;
import tencent.tls.platform.TLSUserInfo;

/**
 * 登录的数据处理类
 */
public class LoginHelper extends Presenter {
    private Context mContext;
    private static final String TAG = LoginHelper.class.getSimpleName();
    private LoginView mLoginView;
    private LogoutView mLogoutView;
    private int RoomId = -1;

    public LoginHelper(Context context) {
        mContext = context;
    }

    public LoginHelper(Context context, LoginView loginView) {
        mContext = context;
        mLoginView = loginView;
    }

    public LoginHelper(Context context, LogoutView logoutView) {
        mContext = context;
        mLogoutView = logoutView;
    }

    public void getLogin(String phone,String pwd)
    {
        OkHttpUtils
                .post()
                .url(OkHttpUtils.LOGIN_URL)
                .addParams("userName", phone)
                .addParams("loginPwd", pwd)
                .addParams("cId", BaseApp.getInstance().getClientid())
                .build()//
                .execute(new GenericsCallback<ListUserInfo>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mLoginView.loginFail("服务器未响应");
                    }

                    @Override
                    public void onResponse(ListUserInfo response, int id) {
                        if(response.isSuccess()) {
                            List<UserInfo> userInfos = response.getBody();
                            MySelfInfo.getInstance().setId(userInfos.get(0).getUserId());
                            MySelfInfo.getInstance().setUserSig(userInfos.get(0).getSig());
                            MySelfInfo.getInstance().setMyRoomNum(Integer.parseInt(userInfos.get(0).getUserId()));
                            MySelfInfo.getInstance().writeToCache(mContext.getApplicationContext());
                            imLogin(MySelfInfo.getInstance().getId(), MySelfInfo.getInstance().getUserSig());
                        }else
                        {
                            mLoginView.loginFail(response.getErrMsg());
                        }
                    }

                });
    }


    /**
     * 登录imsdk
     *
     * @param identify 用户id
     * @param userSig  用户签名
     */
    public void imLogin(String identify, String userSig) {
        TIMUser user = new TIMUser();
        user.setAccountType(String.valueOf(Constants.ACCOUNT_TYPE));
        user.setAppIdAt3rd(String.valueOf(Constants.SDK_APPID));
        user.setIdentifier(identify);
        //发起登录请求
        TIMManager.getInstance().login(
                Constants.SDK_APPID,
                user,
                userSig,                    //用户帐号签名，由私钥加密获得，具体请参考文档
                new TIMCallBack() {
                    @Override
                    public void onError(int i, String s) {
                        SxbLog.e(TAG, "IMLogin fail ：" + i + " msg " + s);
                        mLoginView.loginFail("登陆失败");
                    }

                    @Override
                    public void onSuccess() {
                        SxbLog.i(TAG, "keypath IMLogin succ !");
//                        Toast.makeText(mContext, "IMLogin succ !", Toast.LENGTH_SHORT).show();
                        startAVSDK();
                    }
                });
    }


    /**
     * 退出imsdk
     * <p/>
     * 退出成功会调用退出AVSDK
     */
    public void imLogout() {
        TIMManager.getInstance().logout(new TIMCallBack() {
            @Override
            public void onError(int i, String s) {
                SxbLog.e(TAG, "IMLogout fail ：" + i + " msg " + s);
            }

            @Override
            public void onSuccess() {
                SxbLog.i(TAG, "IMLogout succ !");
                //清除本地缓存
                MySelfInfo.getInstance().clearCache(mContext);
                //反向初始化avsdk
                stopAVSDK();
            }
        });

    }


    /**
     * 初始化AVSDK
     */
    private void startAVSDK() {
        QavsdkControl.getInstance().setAvConfig(Constants.SDK_APPID, "" + Constants.ACCOUNT_TYPE, MySelfInfo.getInstance().getId(), MySelfInfo.getInstance().getUserSig());
        QavsdkControl.getInstance().startContext();
        if (mLoginView != null)
            mLoginView.loginSucc();
    }


    /**
     * 反初始化AVADK
     */
    public void stopAVSDK() {
        QavsdkControl.getInstance().stopContext();
        mLogoutView.logoutSucc();
    }


    @Override
    public void onDestory() {
        mLoginView = null;
        mLogoutView = null;
        mContext = null;
    }
}
