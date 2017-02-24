package com.live.longmao.dlg.guessingdlg;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.live.longmao.R;
import com.live.longmao.util.TimeUtil;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by HPDN on 2016/12/4.
 */
public class GuessingReadingDialog extends DialogFragment implements View.OnClickListener {

    private TextView tv_user_guessing_tittle,
            tv_user_guessing_time, tv_a_tittle, tv_b_tittle, btn_num_bean;
    private OnClickAllListener mOnClickGiveListener;
    private RelativeLayout rl_back;
    Timer timer = new Timer();
    int timeCount;
    private Dialog dialog;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = new Dialog(getActivity(), R.style.dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // must be called before set content
        dialog.setContentView(R.layout.a_bzb_all_jing_cai);
        dialog.setCanceledOnTouchOutside(true);

        // 设置宽度为屏宽、靠近屏幕底部。
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);
        initview();
        return dialog;
    }

    private void initview() {
        tv_user_guessing_tittle = (TextView) dialog.findViewById(R.id.tv_user_guessing_tittle);
        tv_user_guessing_time = (TextView) dialog.findViewById(R.id.tv_user_guessing_time);
        tv_a_tittle = (TextView) dialog.findViewById(R.id.tv_a_tittle);
        tv_b_tittle = (TextView) dialog.findViewById(R.id.tv_b_tittle);
        rl_back = (RelativeLayout) dialog.findViewById(R.id.rl_back);
        rl_back.setOnClickListener(this);
        //  btn_num_bean = (TextView) dialog.findViewById(R.id.btn_num_bean);
    }

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            getActivity().runOnUiThread(new Runnable() {      // UI thread
                @Override
                public void run() {
                    timeCount--;
                    tv_user_guessing_time.setText(TimeUtil.getTimeFromInt(timeCount));
                    if (timeCount <= 0) {
                        timer.cancel();
                        dismiss();
                        mOnClickGiveListener.onClickDismiss();
                    }
                }
            });
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null != timer) {
            timer.cancel();
        }
    }

    public GuessingReadingDialog setAllClick(OnClickAllListener onClickAllListener) {
        mOnClickGiveListener = onClickAllListener;
        return this;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_back:
                dismiss();
                break;
        }
    }

    public interface OnClickAllListener {
        void onClickEntertained();//封盘

        void onClickDismiss();//退出dlg
    }

}
