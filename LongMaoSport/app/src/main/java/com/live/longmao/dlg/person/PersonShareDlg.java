package com.live.longmao.dlg.person;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.live.longmao.R;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

/**
 * Created by HPDN on 2017/1/11.
 */
public class PersonShareDlg extends DialogFragment implements View.OnClickListener {

    private Dialog dialog;
    LinearLayout ll_qengyouquan, ll_wx, ll_qq, ll_wb, ll_jb, ll_lh,ll_back;
    RelativeLayout rl_share_dig;
    private OnClickAllListener mOnClickGiveListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = new Dialog(getActivity(), R.style.dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // must be called before set content
        dialog.setContentView(R.layout.dialog_person_share);
        dialog.setCanceledOnTouchOutside(true);

        // 设置宽度为屏宽、靠近屏幕底部。
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);
        initView();
        return dialog;
    }

    private void initView() {
        ll_qengyouquan = (LinearLayout) dialog.findViewById(R.id.ll_qengyouquan);
        ll_wx = (LinearLayout) dialog.findViewById(R.id.ll_wx);
        ll_qq = (LinearLayout) dialog.findViewById(R.id.ll_qq);
        ll_wb = (LinearLayout) dialog.findViewById(R.id.ll_wb);
        ll_jb = (LinearLayout) dialog.findViewById(R.id.ll_jb);
        ll_lh = (LinearLayout) dialog.findViewById(R.id.ll_lh);
        rl_share_dig = (RelativeLayout) dialog.findViewById(R.id.rl_share_dig);
        ll_back = (LinearLayout) dialog.findViewById(R.id.ll_back);

        ll_qengyouquan.setOnClickListener(this);
        ll_wx.setOnClickListener(this);
        ll_qq.setOnClickListener(this);
        ll_wb.setOnClickListener(this);
        ll_jb.setOnClickListener(this);
        ll_lh.setOnClickListener(this);
        rl_share_dig.setOnClickListener(this);
        ll_back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_qengyouquan:
                WechatMoments.ShareParams wechatMoments_share = new WechatMoments.ShareParams();
                wechatMoments_share.setTitle("龙猫运动");
                wechatMoments_share.setText("龙猫直播，让你高潮的直播");
                wechatMoments_share.setImageUrl("http://99touxiang.com/public/upload/nvsheng/125/27-011820_433.jpg");
                wechatMoments_share.setShareType(Platform.SHARE_WEBPAGE);
                wechatMoments_share.setUrl("http://www.tvlongmao.com/share.html?uid=id");
                Platform platformPQ = ShareSDK.getPlatform(WechatMoments.NAME);
                platformPQ.share(wechatMoments_share);
                break;

            case R.id.ll_wx:
                Wechat.ShareParams wechat_share = new Wechat.ShareParams();
                wechat_share.setTitle("龙猫运动");
                wechat_share.setText("龙猫直播，让你高潮的直播");
                wechat_share.setImageUrl("http://99touxiang.com/public/upload/nvsheng/125/27-011820_433.jpg");
                wechat_share.setShareType(Platform.SHARE_WEBPAGE);
                wechat_share.setUrl("http://www.tvlongmao.com/share.html?uid=id");
                Platform platformWX = ShareSDK.getPlatform(Wechat.NAME);
                platformWX.share(wechat_share);
                break;

            case R.id.ll_qq:
                QQ.ShareParams qq_share = new QQ.ShareParams();
                qq_share.setTitle("龙猫运动");
                qq_share.setTitleUrl("http://www.tvlongmao.com/share.html?uid=id"); // 标题的超链接
                qq_share.setText("龙猫直播，让你高潮的直播");
                qq_share.setImageUrl("http://99touxiang.com/public/upload/nvsheng/125/27-011820_433.jpg");
                qq_share.setSite("龙猫运动");
                qq_share.setSiteUrl("http://www.tvlongmao.com/share.html?uid=id");
                Platform platformQQ = ShareSDK.getPlatform(QQ.NAME);
                platformQQ.share(qq_share);
                break;
            case R.id.ll_wb:
                SinaWeibo.ShareParams sina_share = new SinaWeibo.ShareParams();
                sina_share.setTitle("龙猫运动");
                sina_share.setText("龙猫直播，让你高潮的直播");
                sina_share.setImageUrl("http://99touxiang.com/public/upload/nvsheng/125/27-011820_433.jpg");
                sina_share.setSiteUrl("http://www.tvlongmao.com/share.html?uid=id");
                Platform platform = ShareSDK.getPlatform(SinaWeibo.NAME);
                platform.share(sina_share);
                break;
            case R.id.ll_jb:
                mOnClickGiveListener.onClickJvBao();
                Toast.makeText(getActivity(), "举报成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_lh:
                mOnClickGiveListener.onClickLaHei();
                Toast.makeText(getActivity(), "拉黑成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_share_dig:
                dismiss();
                break;
            case R.id.ll_back:
                dismiss();
                break;

        }
    }

    public PersonShareDlg setAllClick(OnClickAllListener onClickAllListener) {
        mOnClickGiveListener = onClickAllListener;
        return this;
    }

    public interface OnClickAllListener {
        void onClickJvBao();
        void onClickLaHei();
    }

}
