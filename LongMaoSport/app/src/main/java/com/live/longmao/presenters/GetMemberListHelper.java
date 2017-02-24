package com.live.longmao.presenters;

import android.content.Context;

import com.live.longmao.avcontrollers.QavsdkControl;
import com.live.longmao.model.MemberInfo;
import com.live.longmao.model.MySelfInfo;
import com.live.longmao.presenters.viewinface.MembersDialogView;
import com.live.longmao.presenters.viewinface.UserListView;
import com.live.longmao.util.SxbLog;
import com.tencent.TIMGroupManager;
import com.tencent.TIMGroupMemberInfo;
import com.tencent.TIMValueCallBack;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 16/5/24.
 */
public class GetMemberListHelper extends Presenter {
    private Context mContext;
    private MembersDialogView mMembersDialogView;
    private UserListView mUserListView;
    private static final String TAG = GetMemberListHelper.class.getSimpleName();
    private ArrayList<MemberInfo> mDialogMembers = new ArrayList<MemberInfo>();

    public GetMemberListHelper(Context context, MembersDialogView dialogView) {
        mContext = context;
        mMembersDialogView = dialogView;
    }
    public GetMemberListHelper(Context context, UserListView userListView) {
        mContext = context;
        mUserListView = userListView;
    }

    public void getMemberList(String roomNum) {
        TIMGroupManager.getInstance().getGroupMembers(roomNum, new TIMValueCallBack<List<TIMGroupMemberInfo>>() {
            @Override
            public void onError(int i, String s) {
                SxbLog.i(TAG, "get MemberList ");
            }

            @Override
            public void onSuccess(List<TIMGroupMemberInfo> timGroupMemberInfos) {
                SxbLog.i(TAG, "get MemberList ");
                getMemberListInfo(timGroupMemberInfos);

            }
        });
    }


    /**
     * 拉取成员列表信息
     *
     * @param timGroupMemberInfos
     */
    private void getMemberListInfo(List<TIMGroupMemberInfo> timGroupMemberInfos) {
        mDialogMembers.clear();
        for (TIMGroupMemberInfo item : timGroupMemberInfos) {
            if (item.getUser().equals(MySelfInfo.getInstance().getId())) {
                continue;
            }
            MemberInfo member = new MemberInfo();
            member.setUserId(item.getUser());
            if (QavsdkControl.getInstance().containIdView(item.getUser())) {
                member.setIsOnVideoChat(true);
            }
            mDialogMembers.add(member);

        }

        if(null!=mMembersDialogView)
        mMembersDialogView.showMembersList(mDialogMembers);
        else if(null!=mUserListView)
        mUserListView.showMembersList(mDialogMembers);

    }


    @Override
    public void onDestory() {
        mMembersDialogView =null;
        mUserListView = null;
        mContext = null;
    }
}
