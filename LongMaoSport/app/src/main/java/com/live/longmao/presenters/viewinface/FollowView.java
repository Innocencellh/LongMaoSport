package com.live.longmao.presenters.viewinface;

/**
 * Created by Administrator on 2016/10/8.
 */
public interface FollowView {
    void onFollowSucc(int pos);
    void onCancelFollowSucc(int pos);
    void onFollowError(String msg);
}
