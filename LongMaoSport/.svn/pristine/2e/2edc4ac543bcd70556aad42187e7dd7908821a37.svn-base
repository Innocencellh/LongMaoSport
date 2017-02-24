package com.live.longmao.views;


import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.live.longmao.R;
import com.live.longmao.model.MySelfInfo;
import com.live.longmao.presenters.LoginHelper;
import com.live.longmao.presenters.ProfileInfoHelper;
import com.live.longmao.presenters.viewinface.LogoutView;
import com.live.longmao.presenters.viewinface.ProfileView;
import com.live.longmao.util.GlideUtil;
import com.live.longmao.util.SxbLog;
import com.live.longmao.util.UIUtils;
import com.live.longmao.views.customviews.LineControllerView;
import com.tencent.TIMManager;
import com.tencent.TIMUserProfile;
import com.tencent.av.sdk.AVContext;
import com.tencent.qalsdk.QALSDKManager;

import java.util.List;


/**
 * 个人
 */
public class FragmentProfile extends Fragment implements View.OnClickListener, LogoutView, ProfileView {
    private static final String TAG = "FragmentLiveList";
    private ImageView mAvatar;
    private TextView mProfileName;
    private TextView mProfileId;
    private TextView mProfileInfo;
    private ImageView mEditProfile;
    private LoginHelper mLoginHeloper;
    private ProfileInfoHelper mProfileHelper;
    private LineControllerView mBtnLogout;
    private LineControllerView mBtnSet;
    private LineControllerView mVersion;
    private LineControllerView test;

    public FragmentProfile() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.profileframent_layout, container, false);
        mAvatar = (ImageView) view.findViewById(R.id.profile_avatar);
        mProfileName = (TextView) view.findViewById(R.id.profile_name);
        mProfileId = (TextView) view.findViewById(R.id.profile_id);
        mEditProfile = (ImageView) view.findViewById(R.id.edit_profile);
        mProfileInfo = (TextView) view.findViewById(R.id.profile_info);
        mBtnSet = (LineControllerView) view.findViewById(R.id.profile_set);
        mBtnLogout = (LineControllerView) view.findViewById(R.id.logout);
        mVersion = (LineControllerView) view.findViewById(R.id.version);
        test = (LineControllerView) view.findViewById(R.id.test);
        test.setOnClickListener(this);
        mBtnSet.setOnClickListener(this);
        mBtnLogout.setOnClickListener(this);
        mEditProfile.setOnClickListener(this);
        mVersion.setOnClickListener(this);

        mLoginHeloper = new LoginHelper(getActivity().getApplicationContext(), this);
        mProfileHelper = new ProfileInfoHelper(this);
        return view;
    }

    @Override
    public void onDestroy() {
        mLoginHeloper.onDestory();
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (null != mProfileInfo) {
            mProfileHelper.getMyProfile();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    private void enterSetProfile() {
        Intent intent = new Intent(getContext(), SetActivity.class);
        startActivity(intent);
    }

    private void enterEditProfile() {
        Intent intent = new Intent(getContext(), EditProfileActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.profile_set:
                enterSetProfile();
                break;
            case R.id.edit_profile:
                enterEditProfile();
                break;
            case R.id.logout:
                mLoginHeloper.imLogout();
                break;
            case R.id.version:
                showSDKVersion();
                break;
        }
    }


    private void showSDKVersion() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("APP : " + getAppVersion() + "\r\n" + "IM SDK: " + TIMManager.getInstance().getVersion() + "\r\n"
                + "QAL SDK: " + QALSDKManager.getInstance().getSdkVersion() + "\r\n"
                + "AV SDK: " + AVContext.getVersion());
        builder.show();
    }

    private String getAppVersion() {
        PackageManager packageManager = getActivity().getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        String version = "";
        try {
            packInfo = packageManager.getPackageInfo(getActivity().getPackageName(), 0);
            version = packInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        ;
        return version;
    }


    @Override
    public void logoutSucc() {
        Toast.makeText(getContext(), "Logout and quite", Toast.LENGTH_SHORT).show();
        getActivity().finish();
    }

    @Override
    public void logoutFail() {

    }

    @Override
    public void updateProfileInfo(TIMUserProfile profile) {
        if (TextUtils.isEmpty(profile.getNickName())) {
            MySelfInfo.getInstance().setNickName(profile.getIdentifier());
        } else {
            MySelfInfo.getInstance().setNickName(profile.getNickName());
        }
        mProfileName.setText(MySelfInfo.getInstance().getNickName());
        mProfileId.setText("ID:" + MySelfInfo.getInstance().getId());
        if (TextUtils.isEmpty(profile.getRemark())) {
            MySelfInfo.getInstance().setSign(profile.getSelfSignature());
            mProfileInfo.setText(profile.getSelfSignature());
        }
        if (TextUtils.isEmpty(profile.getFaceUrl())) {
            Bitmap bitmap = BitmapFactory.decodeResource(this.getContext().getResources(), R.mipmap.ic_default_head);
            Bitmap cirBitMap = UIUtils.createCircleImage(bitmap, 0);
            mAvatar.setImageBitmap(cirBitMap);
        } else {
            SxbLog.d(TAG, "profile avator: " + profile.getFaceUrl());
            MySelfInfo.getInstance().setAvatar(profile.getFaceUrl());
            GlideUtil.getInstance().load(getActivity(), mAvatar, profile.getFaceUrl());
        }
        MySelfInfo.getInstance().writeToCache(getContext());
    }

    @Override
    public void updateUserInfo(int reqid, List<TIMUserProfile> profiles) {

    }
}
