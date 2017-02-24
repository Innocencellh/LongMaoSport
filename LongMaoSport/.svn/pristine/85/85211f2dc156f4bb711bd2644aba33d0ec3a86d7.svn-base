package com.live.longmao.presenters;


import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.live.longmao.avcontrollers.QavsdkControl;
import com.live.longmao.bean.GiftData;
import com.live.longmao.model.CurLiveInfo;
import com.live.longmao.model.MySelfInfo;
import com.live.longmao.presenters.viewinface.LiveView;
import com.live.longmao.presenters.viewinface.MembersDialogView;
import com.live.longmao.util.Constants;
import com.live.longmao.util.SxbLog;
import com.tencent.TIMCallBack;
import com.tencent.TIMConversation;
import com.tencent.TIMConversationType;
import com.tencent.TIMCustomElem;
import com.tencent.TIMElem;
import com.tencent.TIMElemType;
import com.tencent.TIMGroupSystemElem;
import com.tencent.TIMGroupSystemElemType;
import com.tencent.TIMManager;
import com.tencent.TIMMessage;
import com.tencent.TIMMessageListener;
import com.tencent.TIMTextElem;
import com.tencent.TIMUserProfile;
import com.tencent.TIMValueCallBack;
import com.tencent.av.TIMAvManager;
import com.tencent.av.sdk.AVAudioCtrl;
import com.tencent.av.sdk.AVContext;
import com.tencent.av.sdk.AVEndpoint;
import com.tencent.av.sdk.AVError;
import com.tencent.av.sdk.AVRoomMulti;
import com.tencent.av.sdk.AVVideoCtrl;
import com.tencent.av.sdk.AVView;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * 直播的控制类presenter
 */
public class LiveHelper extends Presenter {
    private LiveView mLiveView;
    private MembersDialogView mMembersDialogView;
    public Context mContext;
    private static final String TAG = LiveHelper.class.getSimpleName();
    private static final int CAMERA_NONE = -1;
    private static final int FRONT_CAMERA = 0;
    private static final int BACK_CAMERA = 1;
    private static final int MAX_REQUEST_VIEW_COUNT = 4; //当前最大支持请求画面个数
    private static final boolean LOCAL = true;
    private static final boolean REMOTE = false;
    private TIMConversation mGroupConversation;
    private TIMConversation mC2CConversation;
    private boolean isMicOpen = true;
    private static final String UNREAD = "0";
    private AVView mRequestViewList[] = new AVView[MAX_REQUEST_VIEW_COUNT];
    private String mRequestIdentifierList[] = new String[MAX_REQUEST_VIEW_COUNT];
    private Boolean isOpenCamera = false;


    public LiveHelper(Context context, LiveView liveview) {
        mContext = context;
        mLiveView = liveview;
    }


    private AVVideoCtrl.CameraPreviewChangeCallback mCameraPreviewChangeCallback = new AVVideoCtrl.CameraPreviewChangeCallback() {
        @Override
        public void onCameraPreviewChangeCallback(int cameraId) {
            SxbLog.d(TAG, "WL_DEBUG mCameraPreviewChangeCallback.onCameraPreviewChangeCallback cameraId = " + cameraId);

            QavsdkControl.getInstance().setMirror(FRONT_CAMERA == cameraId);
        }
    };

    public void setCameraPreviewChangeCallback() {
        AVVideoCtrl avVideoCtrl = QavsdkControl.getInstance().getAVContext().getVideoCtrl();
        avVideoCtrl.setCameraPreviewChangeCallback(mCameraPreviewChangeCallback);
    }

    /**
     * 开启摄像头和MIC
     */
    public void openCameraAndMic() {
        openCamera();
        AVAudioCtrl avAudioCtrl = QavsdkControl.getInstance().getAVContext().getAudioCtrl();//开启Mic
        avAudioCtrl.enableMic(true);
        isMicOpen = true;

    }


    /**
     * 打开摄像头
     */
    private void openCamera() {
        if (mIsFrontCamera) {
            enableCamera(FRONT_CAMERA, true);
        } else {
            enableCamera(BACK_CAMERA, true);
        }
    }


    public void closeCameraAndMic() {
        closeCamera();
        closeMic();
    }

    /**
     * 开关摄像头
     */
    public void toggleCamera() {
        if (isOpenCamera) {
            closeCamera();
        } else {
            openCamera();
        }
    }


    /**
     * 开关Mic
     */
    public void toggleMic() {
        if (isMicOpen) {
            openMic();
        } else {
            muteMic();
        }
    }


    public void closeCamera() {
        if (mIsFrontCamera) {
            enableCamera(FRONT_CAMERA, false);
        } else {
            enableCamera(BACK_CAMERA, false);
        }
    }

    public void closeMic() {
        AVAudioCtrl avAudioCtrl = QavsdkControl.getInstance().getAVContext().getAudioCtrl();//开启Mic
        avAudioCtrl.enableMic(false);
        isMicOpen = false;
    }


    /**
     * 开启摄像头
     *
     * @param camera
     * @param isEnable
     */
    private void enableCamera(final int camera, boolean isEnable) {
        if (isEnable) {
            isOpenCamera = true;
        } else {
            isOpenCamera = false;
        }
        SxbLog.i(TAG, "createlive enableCamera camera " + camera + "  isEnable " + isEnable);
        AVVideoCtrl avVideoCtrl = QavsdkControl.getInstance().getAVContext().getVideoCtrl();
        //打开摄像头
        int ret = avVideoCtrl.enableCamera(camera, isEnable, new AVVideoCtrl.EnableCameraCompleteCallback() {
            protected void onComplete(boolean enable, int result) {//开启摄像头回调
                super.onComplete(enable, result);
                SxbLog.i(TAG, "createlive enableCamera result " + result);
                if (result == AVError.AV_OK) {//开启成功

                    if (camera == FRONT_CAMERA) {
                        mIsFrontCamera = true;
                    } else {
                        mIsFrontCamera = false;
                    }

                    //如果是主播直接本地渲染
//                    if (MySelfInfo.getInstance().getIdStatus() == Constants.HOST)
//                        mLiveView.showVideoView(LOCAL, CurLiveInfo.getHostID());

                }
            }
        });

        SxbLog.i(TAG, "enableCamera " + ret);

    }

    /**
     * AVSDK 请求主播数据
     *
     * @param identifiers 主播ID
     */
    public void requestViewList(ArrayList<String> identifiers) {
        SxbLog.i(TAG, "requestViewList " + identifiers);
        if (identifiers.size() == 0) return;
        AVEndpoint endpoint = ((AVRoomMulti) QavsdkControl.getInstance().getAVContext().getRoom()).getEndpointById(identifiers.get(0));
        SxbLog.d(TAG, "requestViewList hostIdentifier " + identifiers + " endpoint " + endpoint);
        if (endpoint != null) {
            ArrayList<String> alreadyIds = QavsdkControl.getInstance().getRemoteVideoIds();//已经存在的IDs

            SxbLog.i(TAG, "requestViewList identifiers : " + identifiers.size());
            SxbLog.i(TAG, "requestViewList alreadyIds : " + alreadyIds.size());
            for (String id : identifiers) {//把新加入的添加到后面
                alreadyIds.add(id);
            }
            int viewindex = 0;
            for (String id : alreadyIds) {//一并请求
                if (viewindex >= 4) break;
                AVView view = new AVView();
                view.videoSrcType = AVView.VIDEO_SRC_TYPE_CAMERA;
                view.viewSizeType = AVView.VIEW_SIZE_TYPE_BIG;
                //界面数
                mRequestViewList[viewindex] = view;
                mRequestIdentifierList[viewindex] = id;
                viewindex++;
            }
            int ret = AVEndpoint.requestViewList(mRequestIdentifierList, mRequestViewList, alreadyIds.size(), mRequestViewListCompleteCallback);


        } else {
            Toast.makeText(mContext, "Wrong Room!!!! Live maybe close already!", Toast.LENGTH_SHORT).show();
        }


    }


    private AVEndpoint.RequestViewListCompleteCallback mRequestViewListCompleteCallback = new AVEndpoint.RequestViewListCompleteCallback() {
        protected void OnComplete(String identifierList[], AVView viewList[], int count, int result) {
            for (String id : identifierList) {
                mLiveView.showVideoView(REMOTE, id);
            }
            // TODO
            SxbLog.d(TAG, "RequestViewListCompleteCallback.OnComplete");
        }
    };

    public void sendGroupText(TIMMessage Nmsg) {
        if (mGroupConversation != null)
            mGroupConversation.sendMessage(Nmsg, new TIMValueCallBack<TIMMessage>() {
                @Override
                public void onError(int i, String s) {
                    if (i == 85) { //消息体太长
                        Toast.makeText(mContext, "Text too long ", Toast.LENGTH_SHORT).show();
                    } else if (i == 6011) {//群主不存在
                        Toast.makeText(mContext, "Host don't exit ", Toast.LENGTH_SHORT).show();
                    }
                    SxbLog.e(TAG, "send message failed. code: " + i + " errmsg: " + s);
                }

                @Override
                public void onSuccess(TIMMessage timMessage) {
                    //发送成回显示消息内容
                    for (int j = 0; j < timMessage.getElementCount(); j++) {
                        TIMElem elem = (TIMElem) timMessage.getElement(0);
                        if (timMessage.isSelf()) {
                            handleTextMessage(elem, MySelfInfo.getInstance().getNickName());
                        } else {
                            TIMUserProfile sendUser = timMessage.getSenderProfile();
                            String name;
                            if (sendUser != null) {
                                name = sendUser.getNickName();
                            } else {
                                name = timMessage.getSender();
                            }
                            //String sendId = timMessage.getSender();
                            handleTextMessage(elem, name);
                        }
                    }
                    SxbLog.i(TAG, "Send text Msg ok");

                }
            });
    }

    public void sendGroupMessage(int cmd, String param, TIMValueCallBack<TIMMessage> callback) {
        JSONObject inviteCmd = new JSONObject();
        try {
            inviteCmd.put(Constants.CMD_KEY, cmd);
            inviteCmd.put(Constants.CMD_PARAM, param);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String cmds = inviteCmd.toString();
        SxbLog.i(TAG, "send cmd : " + cmd + "|" + cmds);
        TIMMessage Gmsg = new TIMMessage();
        TIMCustomElem elem = new TIMCustomElem();
        elem.setData(cmds.getBytes());
        elem.setDesc("");
        Gmsg.addElement(elem);

        if (mGroupConversation != null)
            mGroupConversation.sendMessage(Gmsg, callback);
    }

    public void sendGroupMessage(int cmd, String param) {
        sendGroupMessage(cmd, param, new TIMValueCallBack<TIMMessage>() {
            @Override
            public void onError(int i, String s) {
                if (i == 85) { //消息体太长
                    Toast.makeText(mContext, "Text too long ", Toast.LENGTH_SHORT).show();
                } else if (i == 6011) {//群主不存在
                    Toast.makeText(mContext, "Host don't exit ", Toast.LENGTH_SHORT).show();
                }
                SxbLog.e(TAG, "send message failed. code: " + i + " errmsg: " + s);
            }

            @Override
            public void onSuccess(TIMMessage timMessage) {
                SxbLog.i(TAG, "onSuccess ");
            }
        });
    }

    /**
     * 初始化聊天室  设置监听器
     */
    public void initTIMListener(String chatRoomId) {
        SxbLog.v(TAG, "initTIMListener->current room id: " + chatRoomId);
        mGroupConversation = TIMManager.getInstance().getConversation(TIMConversationType.Group, chatRoomId);
        TIMManager.getInstance().addMessageListener(msgListener);
        mC2CConversation = TIMManager.getInstance().getConversation(TIMConversationType.C2C, chatRoomId);
    }

    private void notifyQuitReady() {
        TIMManager.getInstance().removeMessageListener(msgListener);
        if (mLiveView != null)
            mLiveView.readyToQuit();
    }

    public void perpareQuitRoom(boolean bPurpose) {
        if (bPurpose) {
            sendGroupMessage(Constants.AVIMCMD_ExitLive, "", new TIMValueCallBack<TIMMessage>() {
                @Override
                public void onError(int i, String s) {
                    notifyQuitReady();
                }

                @Override
                public void onSuccess(TIMMessage timMessage) {
                    notifyQuitReady();
                }
            });
        } else {
            notifyQuitReady();
        }
    }


    /**
     * 群消息回调
     */
    private TIMMessageListener msgListener = new TIMMessageListener() {
        @Override
        public boolean onNewMessages(List<TIMMessage> list) {
            //SxbLog.d(TAG, "onNewMessages readMessage " + list.size());
            //解析TIM推送消息
            parseIMMessage(list);
            return false;
        }
    };

    /**
     * 解析消息回调
     *
     * @param list 消息列表
     */
    private void parseIMMessage(List<TIMMessage> list) {
        List<TIMMessage> tlist = list;


        if (tlist.size() > 0) {
            if (mGroupConversation != null)
                mGroupConversation.setReadMessage(tlist.get(0));
            SxbLog.d(TAG, "parseIMMessage readMessage " + tlist.get(0).timestamp());
        }
//        if (!bNeverLoadMore && (tlist.size() < mLoadMsgNum))
//            bMore = false;

        for (int i = tlist.size() - 1; i >= 0; i--) {
            TIMMessage currMsg = tlist.get(i);
//


            for (int j = 0; j < currMsg.getElementCount(); j++) {
                if (currMsg.getElement(j) == null)
                    continue;
                TIMElem elem = currMsg.getElement(j);
                TIMElemType type = elem.getType();
                String sendId = currMsg.getSender();

                //系统消息
                if (type == TIMElemType.GroupSystem) {
                    if (TIMGroupSystemElemType.TIM_GROUP_SYSTEM_DELETE_GROUP_TYPE == ((TIMGroupSystemElem) elem).getSubtype()) {
                        mContext.sendBroadcast(new Intent(
                                Constants.ACTION_HOST_LEAVE));
                    }

                }
                //定制消息
                if (type == TIMElemType.Custom) {
                    String id, nickname;
                    if (currMsg.getSenderProfile() != null) {
                        id = currMsg.getSenderProfile().getIdentifier();
                        nickname = currMsg.getSenderProfile().getNickName();
                    } else {
                        id = sendId;
                        nickname = sendId;
                    }
                    handleCustomMsg(elem, id, nickname);
                    continue;
                }

                //其他群消息过滤

                if (currMsg.getConversation() != null && currMsg.getConversation().getPeer() != null)
                    if (!CurLiveInfo.getChatRoomId().equals(currMsg.getConversation().getPeer())) {
                        continue;
                    }

                //最后处理文本消息
                if (type == TIMElemType.Text) {
                    if (currMsg.isSelf()) {
                        handleTextMessage(elem, MySelfInfo.getInstance().getNickName());
                    } else {
                        String nickname;
                        if (currMsg.getSenderProfile() != null && (!currMsg.getSenderProfile().getNickName().equals(""))) {
                            nickname = currMsg.getSenderProfile().getNickName();
                        } else {
                            nickname = sendId;
                        }
                        handleTextMessage(elem, nickname);
                    }
                }
            }
        }
    }

    /**
     * 处理文本消息解析
     *
     * @param elem
     * @param name
     */
    private void handleTextMessage(TIMElem elem, String name) {
        TIMTextElem textElem = (TIMTextElem) elem;
//        Toast.makeText(mContext, "" + textElem.getText(), Toast.LENGTH_SHORT).show();
        mLiveView.refreshText(textElem.getText(), name);
//        sendToUIThread(REFRESH_TEXT, textElem.getText(), sendId);
    }


    /**
     * 处理定制消息 赞 关注 取消关注
     *
     * @param elem
     */
    private void handleCustomMsg(TIMElem elem, String identifier, String nickname) {
        try {
            String customText = new String(((TIMCustomElem) elem).getData(), "UTF-8");
            SxbLog.i(TAG, "cumstom msg  " + customText);

            JSONTokener jsonParser = new JSONTokener(customText);
            // 此时还未读取任何json文本，直接读取就是一个JSONObject对象。
            // 如果此时的读取位置在"name" : 了，那么nextValue就是"yuanzhifei89"（String）
            JSONObject json = (JSONObject) jsonParser.nextValue();
            int action = json.getInt(Constants.CMD_KEY);
            switch (action) {
                case Constants.AVIMCMD_MUlTI_HOST_INVITE:
                    mLiveView.showInviteDialog();
                    break;
                case Constants.AVIMCMD_MUlTI_JOIN:
                    Log.i(TAG, "handleCustomMsg " + identifier);
                    mLiveView.cancelInviteView(identifier);
                    break;
                case Constants.AVIMCMD_MUlTI_REFUSE:
                    mLiveView.cancelInviteView(identifier);
                    Toast.makeText(mContext, identifier + " refuse !", Toast.LENGTH_SHORT).show();
                    break;
                case Constants.AVIMCMD_Praise:
                    mLiveView.refreshThumbUp();
                    break;
                case Constants.AVIMCMD_EnterLive:
                    //mLiveView.refreshText("Step in live", sendId);
                    if (mLiveView != null)
                        mLiveView.memberJoin(identifier, nickname);
                    break;
                case Constants.AVIMCMD_ExitLive:
                    //mLiveView.refreshText("quite live", sendId);
                    if (mLiveView != null)
                        mLiveView.memberQuit(identifier, nickname);
                    break;
                case Constants.AVIMCMD_MULTI_CANCEL_INTERACT://主播关闭摄像头命令
                    //如果是自己关闭Camera和Mic
                    String closeId = json.getString(Constants.CMD_PARAM);
                    if (closeId.equals(MySelfInfo.getInstance().getId())) {//是自己
                        //TODO 被动下麦 下麦 下麦
                        changeAuthandRole(false, Constants.NORMAL_MEMBER_AUTH, Constants.NORMAL_MEMBER_ROLE);

                    }
                    //其他人关闭小窗口
                    QavsdkControl.getInstance().closeMemberView(closeId);
                    mLiveView.hideInviteDialog();
                    mLiveView.refreshUI(closeId);
                    break;
                case Constants.AVIMCMD_MULTI_HOST_CANCELINVITE:
                    mLiveView.hideInviteDialog();
                    break;
                case Constants.AVIMCMD_MULTI_HOST_CONTROLL_CAMERA:
                    toggleCamera();
                    break;
                case Constants.AVIMCMD_MULTI_HOST_CONTROLL_MIC:
                    toggleMic();
                    break;
                default:
                    break;
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JSONException ex) {
            // 异常处理代码
        }
    }


    public boolean isFrontCamera() {
        return mIsFrontCamera;
    }

    private boolean mIsFrontCamera = true;

    /**
     * 转换前后摄像头
     *
     * @return
     */
    public int switchCamera() {
        AVVideoCtrl avVideoCtrl = QavsdkControl.getInstance().getAVContext().getVideoCtrl();
        int result = avVideoCtrl.switchCamera(mIsFrontCamera ? BACK_CAMERA : FRONT_CAMERA, mSwitchCameraCompleteCallback);
        return result;
    }


    /**
     * 装换摄像头回调
     */
    private AVVideoCtrl.SwitchCameraCompleteCallback mSwitchCameraCompleteCallback = new AVVideoCtrl.SwitchCameraCompleteCallback() {
        protected void onComplete(int cameraId, int result) {
            super.onComplete(cameraId, result);

            if (result == AVError.AV_OK) {
                mIsFrontCamera = !mIsFrontCamera;
            }
        }
    };

    public boolean isMicOpen() {
        return isMicOpen;
    }

    /**
     * 开启Mic
     */
    public void openMic() {
        AVAudioCtrl avAudioCtrl = QavsdkControl.getInstance().getAVContext().getAudioCtrl();//开启Mic
        avAudioCtrl.enableMic(true);
        isMicOpen = true;
    }

    /**
     * 关闭Mic
     */
    public void muteMic() {
        AVAudioCtrl avAudioCtrl = QavsdkControl.getInstance().getAVContext().getAudioCtrl();//关闭Mic
        avAudioCtrl.enableMic(false);
        isMicOpen = false;
    }


    /**
     * 开关闪光灯
     */
    private boolean flashLgihtStatus = false;

    public void toggleFlashLight() {
        AVVideoCtrl videoCtrl = QavsdkControl.getInstance().getAVContext().getVideoCtrl();
        if (null == videoCtrl) {
            return;
        }

        final Object cam = videoCtrl.getCamera();
        if ((cam == null) || (!(cam instanceof Camera))) {
            return;
        }
        final Camera.Parameters camParam = ((Camera) cam).getParameters();
        if (null == camParam) {
            return;
        }

        Object camHandler = videoCtrl.getCameraHandler();
        if ((camHandler == null) || (!(camHandler instanceof Handler))) {
            return;
        }

        //对摄像头的操作放在摄像头线程
        if (flashLgihtStatus == false) {
            ((Handler) camHandler).post(new Runnable() {
                public void run() {
                    try {
                        camParam.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                        ((Camera) cam).setParameters(camParam);
                        flashLgihtStatus = true;
                    } catch (RuntimeException e) {
                        SxbLog.d("setParameters", "RuntimeException");
                    }
                }
            });
        } else {
            ((Handler) camHandler).post(new Runnable() {
                public void run() {
                    try {
                        camParam.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                        ((Camera) cam).setParameters(camParam);
                        flashLgihtStatus = false;
                    } catch (RuntimeException e) {
                        SxbLog.d("setParameters", "RuntimeException");
                    }

                }
            });
        }
    }


    public void sendC2CMessage(final int cmd, String Param, final String sendId) {
        JSONObject inviteCmd = new JSONObject();
        try {
            inviteCmd.put(Constants.CMD_KEY, cmd);
            inviteCmd.put(Constants.CMD_PARAM, Param);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String cmds = inviteCmd.toString();
        SxbLog.i(TAG, "send cmd : " + cmd + "|" + cmds);
        TIMMessage msg = new TIMMessage();
        TIMCustomElem elem = new TIMCustomElem();
        elem.setData(cmds.getBytes());
        elem.setDesc("");
        msg.addElement(elem);
        mC2CConversation = TIMManager.getInstance().getConversation(TIMConversationType.C2C, sendId);
        mC2CConversation.sendMessage(msg, new TIMValueCallBack<TIMMessage>() {
            @Override
            public void onError(int i, String s) {
                SxbLog.e(TAG, "enter error" + i + ": " + s);
            }

            @Override
            public void onSuccess(TIMMessage timMessage) {
                SxbLog.i(TAG, "send praise succ !");
            }
        });
    }


    private TIMAvManager.RoomInfo roomInfo;
    private long streamChannelID;

    public void pushAction(TIMAvManager.StreamParam mStreamParam) {
        int roomid = (int) QavsdkControl.getInstance().getAVContext().getRoom().getRoomId();
        SxbLog.i(TAG, "Push roomid: " + roomid);
        SxbLog.d(TAG, "Push groupid: " + CurLiveInfo.getRoomNum());
        roomInfo = TIMAvManager.getInstance().new RoomInfo();
        roomInfo.setRoomId(roomid);
        roomInfo.setRelationId(CurLiveInfo.getRoomNum());
        //推流的接口
        if (TIMAvManager.getInstance() != null) {
            TIMAvManager.getInstance().requestMultiVideoStreamerStart(roomInfo, mStreamParam, new TIMValueCallBack<TIMAvManager.StreamRes>() {
                @Override
                public void onError(int i, String s) {
                    SxbLog.e(TAG, "url error " + i + " : " + s);
                    Toast.makeText(mContext, "start stream error,try again " + i + " : " + s, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onSuccess(TIMAvManager.StreamRes streamRes) {
                    List<TIMAvManager.LiveUrl> liveUrls = streamRes.getUrls();
                    streamChannelID = streamRes.getChnlId();
                    mLiveView.pushStreamSucc(streamRes);

//                ClipToBoard(url, url2);

                }
            });
        }
    }

    public void stopPushAction() {
        SxbLog.d(TAG, "Push stop Id " + streamChannelID);
        List<Long> myList = new ArrayList<Long>();
        myList.add(streamChannelID);
        TIMAvManager.getInstance().requestMultiVideoStreamerStop(roomInfo, myList, new TIMCallBack() {
            @Override
            public void onError(int i, String s) {
                SxbLog.e(TAG, "url stop error " + i + " : " + s);
                Toast.makeText(mContext, "stop stream error,try again " + i + " : " + s, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onSuccess() {
                mLiveView.stopStreamSucc();

            }
        });
    }


    @Override
    public void onDestory() {
        mLiveView = null;
        mContext = null;
    }


    /**
     * 改变角色和权限 最终会控制自己Camera和MiC
     *
     * @param leverChange true代表上麦 false 代表下麦
     * @param auth_bits   权限字段
     * @param role        角色字段
     */
    public void changeAuthandRole(final boolean leverChange, long auth_bits, final String role) {
        changeAuthority(auth_bits, null, new AVRoomMulti.ChangeAuthorityCallback() {
            protected void onChangeAuthority(int retCode) {
                SxbLog.i(TAG, "changeAuthority code " + retCode);
                changeRole(role, leverChange);
            }
        });
    }


    /**
     * 改变权限
     *
     * @param auth_bits   权限
     * @param auth_buffer 密钥
     * @param callback
     * @return
     */
    private boolean changeAuthority(long auth_bits, byte[] auth_buffer, AVRoomMulti.ChangeAuthorityCallback callback) {
        SxbLog.d(TAG, " changeAuthority");
        QavsdkControl qavsdk = QavsdkControl.getInstance();
        AVContext avContext = qavsdk.getAVContext();
        AVRoomMulti room = (AVRoomMulti) avContext.getRoom();
        if (auth_buffer != null) {
            return room.changeAuthority(auth_bits, auth_buffer, auth_buffer.length, callback);
        } else {
            return room.changeAuthority(auth_bits, null, 0, callback);
        }
    }


    /**
     * 改变角色
     *
     * @param role 角色名
     */
    public void changeRole(String role, final boolean leverupper) {
        ((AVRoomMulti) (QavsdkControl.getInstance().getRoom())).changeAVControlRole(role, new AVRoomMulti.ChangeAVControlRoleCompleteCallback() {
                    @Override
                    public void OnComplete(int arg0) {
                        SxbLog.i(TAG, "changeRole code " + arg0);
                        if (arg0 == AVError.AV_OK) {
                            if (leverupper == true) {
                                openCameraAndMic();//打开摄像头
                                sendC2CMessage(Constants.AVIMCMD_MUlTI_JOIN, "", CurLiveInfo.getHostID());//发送回应消息
                            } else {
                                closeCameraAndMic();
                            }

                            Toast.makeText(mContext, "change to VideoMember succ !", Toast.LENGTH_SHORT);
                        } else {
                            Toast.makeText(mContext, "change to VideoMember failed", Toast.LENGTH_SHORT);
                        }
                    }
                }

        );
    }


}
