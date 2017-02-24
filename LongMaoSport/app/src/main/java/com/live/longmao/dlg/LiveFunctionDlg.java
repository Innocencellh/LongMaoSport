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
import android.widget.TextView;

import com.live.longmao.R;

/**
 * Created by Administrator on 2016/8/27.
 */
public class LiveFunctionDlg extends DialogFragment implements View.OnClickListener {
    private Dialog dialog;
    private RelativeLayout dlg_whole;
    private TextView tv_flashlight,tv_camera,tv_beauty;
    private ImageView img_flashlight, img_camera,img_beauty;
    private LinearLayout ll_flashlight, ll_camera,ll_beauty;
    private OnClickAllListener mOnClickGiveListener;
    private boolean is_flashlight,is_camera,is_beauty;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = new Dialog(getActivity(), R.style.dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // must be called before set content
        dialog.setContentView(R.layout.dlg_live_function);
        dialog.setCanceledOnTouchOutside(true);

        // 设置宽度为屏宽、靠近屏幕底部。
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);
        initView();
        initData();
        return dialog;
    }
    private void initData()
    {
        if(is_flashlight())
        {
            img_flashlight.setImageResource(R.mipmap.icon_live_flashlight_on);
            tv_flashlight.setText("闪光灯开");
            tv_flashlight.setTextColor(getResources().getColor(R.color.light_green_color));
        }
        if(is_camera())
        {
            img_camera.setImageResource(R.mipmap.icon_live_camera_on);
            tv_camera.setText("后置摄像头");
            tv_camera.setTextColor(getResources().getColor(R.color.light_green_color));
        }
        if(is_beauty())
        {
            img_beauty.setImageResource(R.mipmap.icon_live_beauty_on);
            tv_beauty.setText("美颜开");
            tv_beauty.setTextColor(getResources().getColor(R.color.light_green_color));
        }
    }


    public boolean is_flashlight() {
        return is_flashlight;
    }

    public void setIs_flashlight(boolean is_flashlight) {
        this.is_flashlight = is_flashlight;
    }

    public boolean is_camera() {
        return is_camera;
    }

    public void setIs_camera(boolean is_camera) {
        this.is_camera = is_camera;
    }

    public boolean is_beauty() {
        return is_beauty;
    }

    public void setIs_beauty(boolean is_beauty) {
        this.is_beauty = is_beauty;
    }

    private void initView() {
        dlg_whole = (RelativeLayout) dialog.findViewById(R.id.dlg_whole);
        tv_flashlight = (TextView) dialog.findViewById(R.id.tv_flashlight);
        tv_camera = (TextView) dialog.findViewById(R.id.tv_camera);
        tv_beauty = (TextView) dialog.findViewById(R.id.tv_beauty);

        img_flashlight = (ImageView) dialog.findViewById(R.id.img_flashlight);
        img_camera = (ImageView) dialog.findViewById(R.id.img_camera);
        img_beauty = (ImageView) dialog.findViewById(R.id.img_beauty);

        ll_flashlight = (LinearLayout) dialog.findViewById(R.id.ll_flashlight);
        ll_camera = (LinearLayout) dialog.findViewById(R.id.ll_camera);
        ll_beauty = (LinearLayout) dialog.findViewById(R.id.ll_beauty);
        dlg_whole.setOnClickListener(this);
        ll_flashlight.setOnClickListener(this);
        ll_camera.setOnClickListener(this);
        ll_beauty.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dlg_whole:
                dismiss();
                break;
            case R.id.ll_flashlight:
                mOnClickGiveListener.onClickFlashlight();
                dismiss();
                break;
            case R.id.ll_camera:
                mOnClickGiveListener.onClickCamera();
                dismiss();
                break;
            case R.id.ll_beauty:
                mOnClickGiveListener.onClickBeauty();
                dismiss();
                break;
        }
    }

    public LiveFunctionDlg setAllClick(OnClickAllListener onClickAllListener) {
        mOnClickGiveListener = onClickAllListener;
        return this;
    }

    public interface OnClickAllListener {
        void onClickFlashlight();//闪光灯

        void onClickCamera();//相机翻转

        void onClickBeauty();//美颜
    }
}