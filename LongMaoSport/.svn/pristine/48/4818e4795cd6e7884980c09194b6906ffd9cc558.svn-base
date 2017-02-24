package com.live.longmao.dlg;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.live.longmao.R;


/**
 * Created by Administrator on 2016/8/24.
 */
public class InfoDlg extends DialogFragment implements View.OnClickListener{
    //private View view;
    private Dialog dialog;
    private RelativeLayout dlg_whole;
    private ImageView img_photo,img_report;
    private LinearLayout ll_follow,ll_shielding;
    private OnClickAllListener mOnClickGiveListener;

    private void initView()
    {
        dlg_whole = (RelativeLayout) dialog.findViewById(R.id.dlg_whole);
        img_photo = (ImageView) dialog.findViewById(R.id.img_photo);
        img_report = (ImageView) dialog.findViewById(R.id.img_report);
        ll_follow = (LinearLayout) dialog.findViewById(R.id.ll_follow);
        ll_shielding = (LinearLayout) dialog.findViewById(R.id.ll_shielding);
        dlg_whole.setOnClickListener(this);
        img_photo.setOnClickListener(this);
        img_report.setOnClickListener(this);
        ll_follow.setOnClickListener(this);
        ll_shielding.setOnClickListener(this);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = new Dialog(getActivity(), R.style.dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // must be called before set content
        dialog.setContentView(R.layout.dlg_info);
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

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.dlg_whole:
                dismiss();
                break;
            case R.id.img_photo:
                mOnClickGiveListener.onClickInfo();
                break;
            case R.id.img_report:
                mOnClickGiveListener.onClickReport();
                break;
            case R.id.ll_follow:
                mOnClickGiveListener.onClickFollow();
                break;
            case R.id.ll_shielding:
                mOnClickGiveListener.onClickShielding();
                break;
        }
    }
    public InfoDlg setAllClick(OnClickAllListener onClickAllListener) {
        mOnClickGiveListener = onClickAllListener;
        return this;
    }

    public interface OnClickAllListener {
        void onClickReport();//举报
        void onClickInfo();//个人信息
        void onClickFollow();//关注
        void onClickShielding();//拉黑
    }
}
