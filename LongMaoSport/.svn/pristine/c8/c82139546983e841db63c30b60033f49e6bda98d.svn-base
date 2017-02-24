package com.live.longmao.presenters.viewinface;


import com.tencent.av.TIMAvManager;

/**
 *  直播界面回调
 */
public interface LiveView extends MvpView {

    void showVideoView(boolean isHost, String id);

    void showInviteDialog();

    void refreshText(String text, String name);

    void refreshThumbUp();

    void refreshUI(String id);

    boolean showInviteView(String id);

    void cancelInviteView(String id);

    void cancelMemberView(String id);

    void memberJoin(String id, String name);

    void memberQuit(String id, String name);

    void readyToQuit();

    void hideInviteDialog();

    void pushStreamSucc(TIMAvManager.StreamRes streamRes);

    void stopStreamSucc();
}
