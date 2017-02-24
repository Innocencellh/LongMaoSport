package com.live.okhttp;

import com.live.okhttp.utils.Platform;
import com.live.okhttp.builder.GetBuilder;
import com.live.okhttp.builder.HeadBuilder;
import com.live.okhttp.builder.OtherRequestBuilder;
import com.live.okhttp.builder.PostFileBuilder;
import com.live.okhttp.builder.PostFormBuilder;
import com.live.okhttp.builder.PostStringBuilder;
import com.live.okhttp.callback.Callback;
import com.live.okhttp.request.RequestCall;

import java.io.IOException;
import java.util.concurrent.Executor;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by zhy on 15/8/17.
 */
public class OkHttpUtils {
    //    public static final String HEAD_URL = "http://121.40.65.153:8080/gwcommon/";
    public static final String HEAD_URL = "http://101.37.28.112:8080/gwcommon/";
    //public static final String Ply_URL = "http://121.40.65.153:82/";

    public static final String Ply_URL = "http://101.37.28.112:82/";

   // public static final String Photo_Url ="http://121.40.65.153/photo/";
    public static final String Photo_Url ="http://101.37.28.112/photo/";

    /**
     * 1.
     * 注册接口
     * param String  userName    手机号
     * param String  loginPwd   密码
     * param String  verificationCode    短信验证码
     */
    public static final String REGISTER_URL = HEAD_URL + "uic/register.do";
    /**
     * 1.
     * 忘记密码
     * userId=10230001&loginPwd=123456&verificationCode=930492
     */
    public static final String ForgetPwd_URL = HEAD_URL + "uic/findBackPwd.do";

    /*
    * 忘记密码的验证码
    * userName=13354265289
    * */
    public static final String Forget_URL = HEAD_URL + "msg/sendBackPwdVerCode.do";
    /**
     * 2.
     * 登录接口
     * param String userName   用户名
     * param String loginPwd   密码
     * param String cId
     */
    public static final String LOGIN_URL = HEAD_URL + "uic/login.do";

    /**
     * 3,添加直播接口
     * param  String userId    用户id
     * param String liveTitle   直播标题
     * lng 经度
     * lat 纬度
     * city 城市
     * roomId 房间id
     */
    public static final String ADDLIVE_URL = HEAD_URL + "live/addLive.do";

    /**
     * 4.
     * 直播列表
     */
    public static final String READLIVE = HEAD_URL + "live/readlive.do";

    /**
     * 5.
     * 下播
     * param  String userId
     * param  String id
     */
    public static final String DELETELIVE_URL = HEAD_URL + "live/deleteLive.do";

    /**
     * 6.主播开盘接口
     * param  String  userId  用户id
     * param  String  guessTitle
     * param  String  beanNum
     * param  String  answa
     * param  String  answb
     * param  String  stopBetTime
     */
    public static final String ADDANCHOR_URL = HEAD_URL + "guess/addAnchorUserRecord.do";

    /**
     * 7.用户下注接口
     * param  String userId 用户id
     * param  String guessId 房间id
     * param String betResult
     * param String betBeanNum
     */
    public static final String ADDUSERRECORD_URL = HEAD_URL + "guess/addUserRecord.do";

    /**
     * 8.
     * 结算接口
     * param String userId 用户id
     * param String  guessId
     * param String finalResult
     */
    public static final String STATEMENT_URL = HEAD_URL + "guess/statement.do";

    /**
     * 9.
     * 封盘接口
     * param  String guessId
     */
    public static final String STOPBET_URL = HEAD_URL + "guess/stopBet.do";

    /**
     * 10. 读盘接口(读主播所有盘)
     * param String guessId
     */
    public static final String READGUESS_URL = HEAD_URL + "guess/readguess.do";

    /**
     * 11.
     * 读盘接口(读主播当前盘)
     * param String userId 用户Id
     */
    public static final String READGUESSNEW_URL = HEAD_URL + "guess/readGuessNew.do";

    /**
     * 12.
     * 礼物列表
     */
    public static final String GIFTLIST_URL = HEAD_URL + "virtualGift/queryPageByCondition.do";

    /**
     * 13.
     * 币换豆接口
     * param  String lmCoinNum
     * param String userId
     */
    public static final String COINTBEAN_URL = HEAD_URL + "account/coinToBean.do";

    /**
     * 14.
     * 用户币账户接口
     * param String userId 用户id
     */
    public static final String COIN_URL = HEAD_URL + "account/coin.do";

    /**
     * 15.
     * 用户豆账户接口
     * param  String userId 用户Id
     */
    public static final String BEAN_URL = HEAD_URL + "account/bean.do";

    /**
     * 16.
     * 送礼环节接口
     * param String  userId  用户id
     * param String  anchorUserId
     * param String   giftId
     * param String  liveId
     */
    // public static final String QUERYBYIDS_URL = HEAD_URL + "account/queryByIDS.do";
    public static final String QUERYBYIDS_URL = HEAD_URL + "virtualGift/sendGift.do";

    /**
     * 17.
     * 短信验证码接口
     * param String  userName  手机号
     */
    public static final String CODETIME_URL = HEAD_URL + "msg/sendVerificationCode.do";

    /**
     * 18.图片上传下载接口
     * photoType=? *相片类型：0：相册；1：头像；2：直播图片; 3 :热门banner; 4 : 附近banner
     * userId=? *用户id
     * userFace=? *上传的图片
     */
    public static final String IMAGEUPLOAD_URL = HEAD_URL + "photo/photo.do";

    /**
     * 19.主播申请接口
     * lmid  龙猫id
     * usercall  常用手机号
     * useraddr  目前住址
     * idcard    身份证照片
     * realname  真实姓名
     */
    public static final String ANCHORAPPLE_URL = HEAD_URL + "userapply";

    /**
     * 20,企业号申请
     * password  密码
     * Businesscall 企业电话
     * businessaddr 企业地址
     * businesslic 营业执照
     * verificationCode 验证码
     * Businessname 企业名称
     */
    public static final String ENTERPRISEAPPLY_URL = HEAD_URL + "businessapply";

    /**
     * 21,进入直播间
     * userId 用户id
     * roomId 房间id
     * countType 操作状态  进入房间为1
     */

    public static final String SEELIVE = HEAD_URL + "live/seeLive.do";

    /**
     * 22,退出直播间
     * userId 用户id
     * roomId 房间id
     * countType 操作状态  进入房间为1
     */

    public static final String OUTLIVE = HEAD_URL + "live/outLive.do";

    /**
     * 23，送礼统计  1.根据送礼人和主播id返回送礼人送礼记录排名和主播收礼记录排名
     * userId 用户id
     * anchorUserId 主播id
     */
    public static final String GIFTSTATISTICAL_URL = HEAD_URL + "gift/queryGiftpre.do";

    /**
     * 24,送礼环节  1,送礼与送礼记录，更新龙猫豆账户，更新主播卡路里，币变更记录
     * userId 用户id
     * anchorUserId 主播id
     * giftId 礼物id
     * liveId 直播id
     */

    public static final String GIFTLINK = HEAD_URL + "account/queryByIDS.do";

    /**
     * 25,附近主播列表 1,查找附近主播列表
     * userId 用户id
     * userLng 用户经度
     * userLat 用户纬度
     */

    //public static final String NEARBY_URL = HEAD_URL + "around/aroundQuery.do";
    public static final String NEARBY_URL = HEAD_URL + "live/queryLatestLive.do";

    /**
     * 26,热门接口
     * userId 用户id
     */

    public static final String HOT_URL = HEAD_URL + "live/queryHotLive.do";
    /**
     * 27,查找banner图片
     * potoType   int  相片类型：0：相册；1：头像；2：直播图片; 3 :热门banner; 4 : 附近banner
     */

    public static final String BANNER_URL = HEAD_URL + "photo/queryBanner.do";

    /**
     * 28,是上播还是重连的判断
     * userId
     */
    public static final String BrokenLine_URL = HEAD_URL + "live/isBrokenLine.do";

    /**
     * 29,心跳包
     * id 上播id
     * userId
     */
    public static final String LIVEHEART = HEAD_URL + "live/liveHeart.do";

    /**
     * 29，豆换币账户
     * userId 用户Id
     * lmBeanNum=(根据群里的兑换数量)
     */

    public static final String BeanExchangeMoney_URL = HEAD_URL + "account/beanToCoin.do";

    /*
    *30,用户退出直播间
    * userId 用户Id
    * roomId 房间Id
    */

    public static final String UserBackLive_URL = HEAD_URL + "account/outLive.do";

    /*
    *31，个人中心：我的关注
    *userId 用户Id
    */

    public static final String AttentionsUser_URL = HEAD_URL + "attention/queryMyAttentionsUser.do";
    /*
    *32,个人中心：我的粉丝
    * userId  用户Id
    */
    public static final String FunsUser_URL = HEAD_URL + "attention/queryMyFunsUser.do";
    /*
    *33,个人中心：修改用户信息
    * userId  用户Id
    * hobbies 业余爱好
    * nickName 昵称
    * sex 性别
    * signed 个性签名
    * profession 职业
    * phoneId 头像ID
    * sports 运动
    * id
    */
    public static final String ModificationUser_URL = HEAD_URL + "uic/updateUser.do";

    /*
    * 34，个人中心 我关注的直播
    * userId 用户Id
    */
    public static final String AttentionLive_URL = HEAD_URL + "live/queryMyAttentionLive.do";
    /*
    * 35，个人中心 最新直播
    */
    public static final String NEW_URL = HEAD_URL + "live/queryLiveOrderByTime.do";
    /*
    * 36，取消关注
    * userId
    * anchorUserId
    */
    public static final String CancelAttentions_URL = HEAD_URL + "attention/cancleAttentions.do";

    /*
    * 37，关注
    * userId
    * anchorUserId
    */
    public static final String Attentions_URL = HEAD_URL + "attention/addAttentions.do";
    /*
    * 38，删除图片
    * userId
    * anchorUserId
    */
    public static final String DELPHOTO_URL = HEAD_URL + "photo/delImage.do";
    /*
       * 39，结算
       * userId
       * guessId
       * finalResult
       */
    public static final String CLEARING_URL = HEAD_URL + "guess/statement.do";
    /*
     *40.查看照片
     *userId
     */
    public static final String UpPhoto_URL = HEAD_URL + "photo/userPhoto.do";

    /*
    *41，最新列表
     *  currentPage=1&pageSize=10
    */
    public static final String NewsList_URL = HEAD_URL + "live/queryNewLive.do";

    /*
    * 42.个人中心等级
    * userId=10216001
    */
    public static final String PersonGrade_URL = HEAD_URL + "uic/fetchUserLevelInfo.do";

    /*
    * 42.竞猜详细接口
    * guessId = 10216001
    */
    public static final String Guessing_URL = HEAD_URL + "guess/readByGuessId.do";

    /*
    * 43.个人中心
    * */
    public static final String Person_Url = HEAD_URL + "uic/userCenter.do";

    /*
    * 44，用户PK
    * roomId
    * userId
    * guessTitle
    * beanNum
    * answa
    * answb
    * */

    public static final String UKPK_Url = HEAD_URL + "guess/adduserPKRecord.do";

    /*1，PK  审核通过
    guessId=238002&status=1
    http://121.40.65.153:8080/gwcommon/checkUserPK.do  */


    /*竞猜输赢接口
    个人传   guessId   userId
    所有人列表传  guessId
    * /guess/querySettleInfo.do?
    * */

    public static final String GuessJieGuo_URL = HEAD_URL + "guess/querySettleInfo.do";

    /*
    * 个人中心:卡路里贡献榜
    * userId
    * */
    public static final String PersonRank_URL = HEAD_URL + "uic/countUserTopByUserId.do";
    /*
    * 主播卡路里贡献榜
    * anchorUserId
    * */
    public static final String ZBRank_URL = HEAD_URL + "uic/countAnchorTopByAnchorUserId.do";
    /*
    * 用户关注主播的直播列表
    * userId
    */

    public static final String ZBAttentions_URL = HEAD_URL + "attention/queryAttentionsInfo.do";

    /*
    *卡路里兑换龙猫币
    * userId
    * coinNum
    */
    public static final String KLL_URL = HEAD_URL + "uic/colorExchgCoin.do";
    /*
    *获取当前用户可兑换卡路里的信息
    * userId
    */
    public static final String ExchangerKLL_URL = HEAD_URL + "uic/getUserAvblCalorie.do";
    /*
    *龙猫币充值
    * userId
    */
    public static final String LongMao_URL = HEAD_URL + "account/recharge.do?";

    /*
    * 充值Ping++
    * */

    public static final String Ping_Url = Ply_URL + "charge/createCharge";

    public static final long DEFAULT_MILLISECONDS = 10_000L;
    private volatile static OkHttpUtils mInstance;
    private OkHttpClient mOkHttpClient;
    private Platform mPlatform;

    public OkHttpUtils(OkHttpClient okHttpClient) {
        if (okHttpClient == null) {
            mOkHttpClient = new OkHttpClient();
        } else {
            mOkHttpClient = okHttpClient;
        }

        mPlatform = Platform.get();
    }


    public static OkHttpUtils initClient(OkHttpClient okHttpClient) {
        if (mInstance == null) {
            synchronized (OkHttpUtils.class) {
                if (mInstance == null) {
                    mInstance = new OkHttpUtils(okHttpClient);
                }
            }
        }
        return mInstance;
    }

    public static OkHttpUtils getInstance() {
        return initClient(null);
    }


    public Executor getDelivery() {
        return mPlatform.defaultCallbackExecutor();
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    public static GetBuilder get() {
        return new GetBuilder();
    }

    public static PostStringBuilder postString() {
        return new PostStringBuilder();
    }

    public static PostFileBuilder postFile() {
        return new PostFileBuilder();
    }

    public static PostFormBuilder post() {
        return new PostFormBuilder();
    }

    public static OtherRequestBuilder put() {
        return new OtherRequestBuilder(METHOD.PUT);
    }

    public static HeadBuilder head() {
        return new HeadBuilder();
    }

    public static OtherRequestBuilder delete() {
        return new OtherRequestBuilder(METHOD.DELETE);
    }

    public static OtherRequestBuilder patch() {
        return new OtherRequestBuilder(METHOD.PATCH);
    }

    public void execute(final RequestCall requestCall, Callback callback) {
        if (callback == null)
            callback = Callback.CALLBACK_DEFAULT;
        final Callback finalCallback = callback;
        final int id = requestCall.getOkHttpRequest().getId();

        requestCall.getCall().enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                sendFailResultCallback(call, e, finalCallback, id);
            }

            @Override
            public void onResponse(final Call call, final Response response) {
                try {
                    if (call.isCanceled()) {
                        sendFailResultCallback(call, new IOException("Canceled!"), finalCallback, id);
                        return;
                    }

                    if (!finalCallback.validateReponse(response, id)) {
                        sendFailResultCallback(call, new IOException("request failed , reponse's code is : " + response.code()), finalCallback, id);
                        return;
                    }

                    Object o = finalCallback.parseNetworkResponse(response, id);
                    sendSuccessResultCallback(o, finalCallback, id);
                } catch (Exception e) {
                    sendFailResultCallback(call, e, finalCallback, id);
                } finally {
                    if (response.body() != null)
                        response.body().close();
                }

            }
        });
    }


    public void sendFailResultCallback(final Call call, final Exception e, final Callback callback, final int id) {
        if (callback == null) return;

        mPlatform.execute(new Runnable() {
            @Override
            public void run() {
                callback.onError(call, e, id);
                callback.onAfter(id);
            }
        });
    }

    public void sendSuccessResultCallback(final Object object, final Callback callback, final int id) {
        if (callback == null) return;
        mPlatform.execute(new Runnable() {
            @Override
            public void run() {
                callback.onResponse(object, id);
                callback.onAfter(id);
            }
        });
    }

    public void cancelTag(Object tag) {
        for (Call call : mOkHttpClient.dispatcher().queuedCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
        for (Call call : mOkHttpClient.dispatcher().runningCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
    }

    public static class METHOD {
        public static final String HEAD = "HEAD";
        public static final String DELETE = "DELETE";
        public static final String PUT = "PUT";
        public static final String PATCH = "PATCH";
    }
}

