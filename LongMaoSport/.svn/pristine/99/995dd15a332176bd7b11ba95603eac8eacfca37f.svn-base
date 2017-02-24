package com.live.longmao.presenters;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.live.longmao.R;
import com.live.longmao.avcontrollers.QavsdkControl;
import com.live.longmao.model.CurLiveInfo;
import com.live.longmao.model.LiveInfoJson;
import com.live.longmao.model.MySelfInfo;
import com.live.longmao.presenters.viewinface.EnterQuiteRoomView;
import com.live.longmao.util.Constants;
import com.live.longmao.util.SxbLog;
import com.live.okhttp.utils.L;
import com.tencent.TIMCallBack;
import com.tencent.TIMConversationType;
import com.tencent.TIMGroupManager;
import com.tencent.TIMManager;
import com.tencent.TIMValueCallBack;
import com.tencent.av.sdk.AVContext;
import com.tencent.av.sdk.AVRoom;
import com.tencent.av.sdk.AVRoomMulti;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * 进出房间Presenter
 */
public class EnterLiveHelper extends Presenter {
    private EnterQuiteRoomView mStepInOutView;
    private Context mContext;
    private static final String TAG = EnterLiveHelper.class.getSimpleName();
    private static boolean isInChatRoom = false;
    private static boolean isInAVRoom = false;
    private LiveHelper mLiveHelper;
    private ArrayList<String> video_ids = new ArrayList<String>();

    private static final int TYPE_MEMBER_CHANGE_IN = 1;//进入房间事件。
    private static final int TYPE_MEMBER_CHANGE_OUT = 2;//退出房间事件。
    private static final int TYPE_MEMBER_CHANGE_HAS_CAMERA_VIDEO = 3;//有发摄像头视频事件。
    private static final int TYPE_MEMBER_CHANGE_NO_CAMERA_VIDEO = 4;//无发摄像头视频事件。
    private static final int TYPE_MEMBER_CHANGE_HAS_AUDIO = 5;//有发语音事件。
    private static final int TYPE_MEMBER_CHANGE_NO_AUDIO = 6;//无发语音事件。
    private static final int TYPE_MEMBER_CHANGE_HAS_SCREEN_VIDEO = 7;//有发屏幕视频事件。
    private static final int TYPE_MEMBER_CHANGE_NO_SCREEN_VIDEO = 8;//无发屏幕视频事件。


    public EnterLiveHelper(Context context, EnterQuiteRoomView view) {
        mContext = context;
        mStepInOutView = view;
    }


    /**
     * 进入一个直播房间流程
     */
    public void startEnterRoom() {
        if (MySelfInfo.getInstance().getIdStatus() == Constants.HOST) {
            createLive();
        } else {
            SxbLog.i(TAG, "joinLiveRoom startEnterRoom ");
            joinLive(CurLiveInfo.getRoomNum());
        }

    }


    /**
     * 房间回调
     */
    private AVRoomMulti.Delegate mRoomDelegate = new AVRoomMulti.Delegate() {
        // 创建房间成功回调
        public void onEnterRoomComplete(int result) {
            SxbLog.i(TAG, "onEnterRoomComplete  PerformanceTest    " + SxbLog.getTime());
            if (result == 0) {
                //只有进入房间后才能初始化AvView
                isInAVRoom = true;
                initAudioService();
                mStepInOutView.enterRoomComplete(MySelfInfo.getInstance().getIdStatus(), true);
            } else {
                mStepInOutView.enterRoomComplete(MySelfInfo.getInstance().getIdStatus(), false);
            }

        }

        // 离开房间成功回调
        public void onExitRoomComplete(int result) {
            isInAVRoom = false;
            quiteIMChatRoom();
            CurLiveInfo.setCurrentRequestCount(0);
            uninitAudioService();
            //通知结束
            notifyServerLiveEnd();
            if (mStepInOutView != null)
                mStepInOutView.quiteRoomComplete(MySelfInfo.getInstance().getIdStatus(), true, null);
            SxbLog.d(TAG, "WL_DEBUG mRoomDelegate.onExitRoomComplete result = " + result);

        }

        //房间成员变化回调
        public void onEndpointsUpdateInfo(int eventid, String[] updateList) {
            SxbLog.d(TAG, "WL_DEBUG onEndpointsUpdateInfo. eventid = " + eventid);

            switch (eventid) {
                case TYPE_MEMBER_CHANGE_IN:
                    SxbLog.i(TAG, "stepin id  " + updateList.length);
                    mStepInOutView.memberJoinLive(updateList);

                    break;
                case TYPE_MEMBER_CHANGE_HAS_CAMERA_VIDEO:
                    video_ids.clear();
                    for (String id : updateList) {
                        video_ids.add(id);
                        SxbLog.i(TAG, "camera id " + id);
                    }
                    Intent intent = new Intent(Constants.ACTION_CAMERA_OPEN_IN_LIVE);
                    intent.putStringArrayListExtra("ids", video_ids);
                    mContext.sendBroadcast(intent);
                    break;
//                case TYPE_MEMBER_CHANGE_NO_CAMERA_VIDEO:
//                    Log.e(TAG,"主播暂时离开房间");
//                    break;
                case TYPE_MEMBER_CHANGE_HAS_AUDIO:
                    break;

                case TYPE_MEMBER_CHANGE_OUT:
                    mStepInOutView.memberQuiteLive(updateList);
                    break;
                default:
                    break;
            }

        }

        public void OnPrivilegeDiffNotify(int privilege) {
            SxbLog.d(TAG, "OnPrivilegeDiffNotify. privilege = " + privilege);
        }

        @Override
        public void OnSemiAutoRecvCameraVideo(String[] strings) {

            mStepInOutView.alreadyInLive(strings);
        }
    };


    /**
     * 1_1 创建一个直播
     */
    private void createLive() {
        createIMChatRoom();

    }

    /**
     * 1_2创建一个IM聊天室
     */
    private void createIMChatRoom() {
        final ArrayList<String> list = new ArrayList<String>();
        final String roomName = "this is a  test";
        SxbLog.i(TAG, "createlive createIMChatRoom " + MySelfInfo.getInstance().getMyRoomNum());
        TIMGroupManager.getInstance().createGroup("AVChatRoom", list, roomName, "" + MySelfInfo.getInstance().getMyRoomNum(), new TIMValueCallBack<String>() {
            @Override
            public void onError(int i, String s) {
                SxbLog.i(TAG, "onError " + i + "   " + s);
                //已在房间中,重复进入房间
                if (i == 10025) {
                    isInChatRoom = true;
                    createAVRoom(MySelfInfo.getInstance().getMyRoomNum());
                    return;
                }
                // 创建IM房间失败，提示失败原因，并关闭等待对话框
//                Toast.makeText(mContext, " chatroom  error " + s + "i " + i, Toast.LENGTH_SHORT).show();
                quiteLive();
            }

            @Override
            public void onSuccess(String s) {
                isInChatRoom = true;
                //创建AV房间
                createAVRoom(MySelfInfo.getInstance().getMyRoomNum());

            }
        });

    }


    /**
     * 1_3创建一个AV房间
     */
    private void createAVRoom(int roomNum) {
        EnterAVRoom(roomNum);
    }

    /**
     * 初始化Usr
     */
    public void initAvUILayer(View avView) {
        //初始化AVSurfaceView
        if (QavsdkControl.getInstance().getAVContext() != null) {
            QavsdkControl.getInstance().initAvUILayer(mContext.getApplicationContext(), avView);
        }

    }


    /**
     * 1_5上传房间信息
     */
    public void notifyServerCreateRoom() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                JSONObject liveInfo = null;
                try {
                    liveInfo = new JSONObject();
                    if (TextUtils.isEmpty(CurLiveInfo.getTitle())) {
                        liveInfo.put("title", mContext.getString(R.string.text_live_default_title));
                    } else {
                        liveInfo.put("title", CurLiveInfo.getTitle());
                    }
                    liveInfo.put("cover", CurLiveInfo.getCoverurl());
                    liveInfo.put("chatRoomId", CurLiveInfo.getChatRoomId());
                    liveInfo.put("avRoomId", CurLiveInfo.getRoomNum());
                    JSONObject hostinfo = new JSONObject();
                    hostinfo.put("uid", MySelfInfo.getInstance().getId());
                    hostinfo.put("avatar", MySelfInfo.getInstance().getAvatar());
                    hostinfo.put("username", MySelfInfo.getInstance().getNickName());
                    liveInfo.put("host", hostinfo);
                    JSONObject lbs = new JSONObject();
                    lbs.put("longitude", CurLiveInfo.getLong1());
                    lbs.put("latitude", CurLiveInfo.getLat1());
                    lbs.put("address", CurLiveInfo.getAddress());
                    liveInfo.put("lbs", lbs);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (liveInfo != null)
                    OKhttpHelper.getInstance().notifyServerNewLiveInfo(liveInfo);
            }
        }).start();


    }


    /**
     * 2_1加入一个房间
     */
    private void joinLive(int roomNum) {
        joinIMChatRoom(roomNum);
    }

    /**
     * 2_2加入一个聊天室
     */
    private void joinIMChatRoom(int chatRoomId) {
        SxbLog.i(TAG, "joinLiveRoom joinIMChatRoom " + chatRoomId);
        TIMGroupManager.getInstance().applyJoinGroup("" + chatRoomId, Constants.APPLY_CHATROOM + chatRoomId, new TIMCallBack() {
            @Override
            public void onError(int i, String s) {
                //已经在是成员了
                if (i == Constants.IS_ALREADY_MEMBER) {
                    SxbLog.i(TAG, "joinLiveRoom joinIMChatRoom callback succ ");
                    joinAVRoom(CurLiveInfo.getRoomNum());
                    isInChatRoom = true;
                } else {
//                    Toast.makeText(mContext, "join IM room fail " + s + " " + i, Toast.LENGTH_SHORT).show();
                    Toast.makeText(mContext,"主播已退出",Toast.LENGTH_SHORT).show();
                    quiteLive();
                }
            }

            @Override
            public void onSuccess() {
                SxbLog.i(TAG, "joinLiveRoom joinIMChatRoom callback succ ");
                isInChatRoom = true;
                joinAVRoom(CurLiveInfo.getRoomNum());
            }
        });

    }

    /**
     * 2_2加入一个AV房间
     */
    private void joinAVRoom(int avRoomNum) {
//        if (!mQavsdkControl.getIsInEnterRoom()) {
//            initAudioService();
        EnterAVRoom(avRoomNum);
//        }
    }


    /**
     * 退出房间
     */
    public void quiteLive() {
        //退出IM房间

        //退出AV房间
        quiteAVRoom();

    }

    private NotifyServerLiveEnd liveEndTask;

    /**
     * 通知用户UserServer房间
     */
    private void notifyServerLiveEnd() {
        liveEndTask = new NotifyServerLiveEnd();
        liveEndTask.execute(MySelfInfo.getInstance().getId());
    }

    @Override
    public void onDestory() {
        mStepInOutView = null;
        mContext = null;
    }

    class NotifyServerLiveEnd extends AsyncTask<String, Integer, LiveInfoJson> {

        @Override
        protected LiveInfoJson doInBackground(String... strings) {
            return OKhttpHelper.getInstance().notifyServerLiveStop(strings[0]);
        }

        @Override
        protected void onPostExecute(LiveInfoJson result) {
        }
    }


    /**
     * 退出一个AV房间
     */
    private void quiteAVRoom() {
        SxbLog.d(TAG, "quiteAVRoom ");
        if (isInAVRoom == true) {
            AVContext avContext = QavsdkControl.getInstance().getAVContext();
            int result = avContext.exitRoom();
        } else {
            quiteIMChatRoom();
            CurLiveInfo.setCurrentRequestCount(0);
            uninitAudioService();
            //通知结束
//            notifyServerLiveEnd();

            mStepInOutView.quiteRoomComplete(MySelfInfo.getInstance().getIdStatus(), true, null);
        }
    }

    /**
     * 退出IM房间
     */
    private void quiteIMChatRoom() {
        if ((isInChatRoom == true)) {
            //主播解散群
            if (MySelfInfo.getInstance().getIdStatus() == Constants.HOST) {
                TIMGroupManager.getInstance().deleteGroup("" + CurLiveInfo.getRoomNum(), new TIMCallBack() {
                    @Override
                    public void onError(int i, String s) {
                    }

                    @Override
                    public void onSuccess() {
                        isInChatRoom = false;
                    }
                });
                TIMManager.getInstance().deleteConversation(TIMConversationType.Group, "" + MySelfInfo.getInstance().getMyRoomNum());
            } else {
                //成员退出群
                TIMGroupManager.getInstance().quitGroup("" + CurLiveInfo.getRoomNum(), new TIMCallBack() {
                    @Override
                    public void onError(int i, String s) {
                        SxbLog.e(TAG, "onError i: " + i + "  " + s);
                    }

                    @Override
                    public void onSuccess() {
                        SxbLog.i(TAG, "onSuccess ");
                        isInChatRoom = false;
                    }
                });
            }

            //
        }
    }


    /**
     * 进入AV房间
     *
     * @param roomNum
     */
    private void EnterAVRoom(int roomNum) {
        SxbLog.i(TAG, "createlive joinLiveRoom enterAVRoom " + roomNum);
        AVContext avContext = QavsdkControl.getInstance().getAVContext();
        byte[] authBuffer = null;//权限位加密串；TODO：请业务侧填上自己的加密串


        AVRoomMulti.EnterRoomParam enterRoomParam = new AVRoomMulti.EnterRoomParam();

        if (MySelfInfo.getInstance().getIdStatus() == Constants.HOST) {
            enterRoomParam.authBits =  Constants.HOST_AUTH;//；TODO：主播权限 所有权限
            enterRoomParam.avControlRole = Constants.HOST_ROLE;//；TODO：主播角色
            enterRoomParam.autoCreateRoom = true;//;TODO：主播自动创建房间
        } else {
            enterRoomParam.authBits = Constants.NORMAL_MEMBER_AUTH;//；TODO：普通成员权限
            enterRoomParam.avControlRole = Constants.NORMAL_MEMBER_ROLE;//；TODO：普通成员角色
            enterRoomParam.autoCreateRoom = false;//;TODO：
        }

        enterRoomParam.appRoomId = roomNum; //；TODO：房间号
        enterRoomParam.authBuffer = authBuffer;//；TODO：密钥
        enterRoomParam.audioCategory = Constants.AUDIO_VOICE_CHAT_MODE;//；TODO：音频场景策略
        enterRoomParam.videoRecvMode = AVRoom.VIDEO_RECV_MODE_SEMI_AUTO_RECV_CAMERA_VIDEO;//；TODO：半自动模式，加速成员进房间获取视频速度

        if (avContext != null) {
            // create room
            int ret = avContext.enterRoom(AVRoom.AV_ROOM_MULTI, mRoomDelegate, enterRoomParam);
            SxbLog.i(TAG, "EnterAVRoom " + ret);
        }

    }


    private void initAudioService() {
        if ((QavsdkControl.getInstance() != null) && (QavsdkControl.getInstance().getAVContext() != null) && (QavsdkControl.getInstance().getAVContext().getAudioCtrl() != null)) {
            QavsdkControl.getInstance().getAVContext().getAudioCtrl().startTRAEService();
        }
    }

    private void uninitAudioService() {
        if ((QavsdkControl.getInstance() != null) && (QavsdkControl.getInstance().getAVContext() != null) && (QavsdkControl.getInstance().getAVContext().getAudioCtrl() != null)) {
            QavsdkControl.getInstance().getAVContext().getAudioCtrl().startTRAEService();
        }
    }

}
