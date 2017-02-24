package com.live.longmao.dlg;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.live.longmao.R;
import com.live.longmao.util.TimeUtil;
import com.live.longmao.view.MagicProgressBar;

import java.util.Timer;
import java.util.TimerTask;

import cn.dreamtobe.percentsmoothhandler.ISmoothTarget;

/**
 * Created by Administrator on 2016/9/9.
 * 封盘
 */
public class EntertainedDlg extends DialogFragment implements View.OnClickListener{
    //private View view;
    private Dialog dialog;
    private TextView tv_money,tv_guessing_title,tv_countdown_time,tv_bet_num;
    private Button btn_entertained;
    private RelativeLayout dlg_whole,rl_money;
    private MagicProgressBar pb_magic;
    private OnClickAllListener mOnClickGiveListener;
    Timer timer = new Timer();
    int timeCount;

    private void initView()
    {
        dlg_whole = (RelativeLayout) dialog.findViewById(R.id.dlg_whole);
        rl_money = (RelativeLayout) dialog.findViewById(R.id.rl_money);
        tv_money = (TextView) dialog.findViewById(R.id.tv_money);
        tv_countdown_time = (TextView) dialog.findViewById(R.id.tv_countdown_time);
        tv_bet_num = (TextView) dialog.findViewById(R.id.tv_bet_num);
        tv_guessing_title = (TextView) dialog.findViewById(R.id.tv_guessing_title);
        btn_entertained = (Button) dialog.findViewById(R.id.btn_entertained);
        pb_magic = (MagicProgressBar) dialog.findViewById(R.id.pb_magic);
        dlg_whole.setOnClickListener(this);
        btn_entertained.setOnClickListener(this);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = new Dialog(getActivity(), R.style.dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // must be called before set content
        dialog.setContentView(R.layout.dialog_guessing_anchor);
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
                mOnClickGiveListener.onClickDismiss();
                break;
            case R.id.btn_entertained:
                dismiss();
                mOnClickGiveListener.onClickEntertained();
                break;
        }
    }


    TimerTask task = new TimerTask() {
        @Override
        public void run() {
                getActivity().runOnUiThread(new Runnable() {      // UI thread
                    @Override
                    public void run() {
                        timeCount--;
                        tv_countdown_time.setText(TimeUtil.getTimeFromInt(timeCount));
                        if (timeCount <= 0) {
                            timer.cancel();
                            dismiss();
                            mOnClickGiveListener.onClickDismiss();
                        }
                    }
                });
        }
    };

    private float getIncreasedPercent(ISmoothTarget target) {
        float increasedPercent = target.getPercent() + 0.1f;

        return Math.min(1, increasedPercent);
    }
    //设置标题
    public void setTextForTitle(String title)
    {
        tv_guessing_title.setText(title);
    }
    public void setTextForCountdown(int time)
    {
        timeCount = time;
        tv_countdown_time.setText(TimeUtil.getTimeFromInt(time));
        timer.schedule(task, 0, 1000);
    }
    public void setTextForBetNum(String betNum)
    {
        tv_bet_num.setText(betNum);
    }

    public void setTextForBetNumChange(int betNum)
    {
        int index = tv_bet_num.getText().toString().trim().indexOf("/");
        String strBeforeBet = tv_bet_num.getText().toString().trim().substring(0, index);
        String strAfterBet = tv_bet_num.getText().toString().trim().substring(index+1, tv_bet_num.length());
        int beforeBet = betNum +Integer.parseInt(strBeforeBet);
        int afterBet = Integer.parseInt(strAfterBet);
        float f_afterBet = (float)beforeBet/(float)afterBet;
        tv_bet_num.setText(beforeBet+"/"+afterBet);
        pb_magic.setSmoothPercent(f_afterBet, 2000);
    }

    public EntertainedDlg setAllClick(OnClickAllListener onClickAllListener) {
        mOnClickGiveListener = onClickAllListener;
        return this;
    }

    public interface OnClickAllListener {
        void onClickEntertained();//封盘
        void onClickDismiss();//退出dlg
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(null!=timer)
        {
            timer.cancel();
        }
    }
}
