package com.live.longmao.dlg.guessingdlg;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.live.longmao.R;
import com.live.longmao.util.TimeUtil;
import com.live.longmao.view.MagicProgressBar;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by HPDN on 2016/12/3.
 */
public class UKGuessingDlg1 extends DialogFragment implements View.OnClickListener {
    private Dialog dialog;
    private TextView textView, tv_uk_dialog3, tv_answerA, tv_answerB, tv_time, tv_ukdialog_num;
    private RelativeLayout relativeLayout, rl_a_answer, rl_b_answer, rl_gone_a_answer, rl_back;
    private LinearLayout linearLayout, ll_answer;
    Timer timer = new Timer();
    int timeCount;
    private OnClickAllListener mOnClickGiveListener;
    private MagicProgressBar pb_magic;
    String answer = "-1";


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

        initView();

        return dialog;
    }


    private void initView() {
        tv_uk_dialog3 = (TextView) dialog.findViewById(R.id.tv_uk_dialog3);
        tv_uk_dialog3.setVisibility(View.VISIBLE);

        tv_time = (TextView) dialog.findViewById(R.id.tv_user_guessing_time);
        pb_magic = (MagicProgressBar) dialog.findViewById(R.id.pb_magic);
        tv_ukdialog_num = (TextView) dialog.findViewById(R.id.tv_ukdialog_num);
        rl_a_answer = (RelativeLayout) dialog.findViewById(R.id.rl_a_answer);
        rl_b_answer = (RelativeLayout) dialog.findViewById(R.id.rl_b_answer);
        rl_gone_a_answer = (RelativeLayout) dialog.findViewById(R.id.rl_gone_a_answer);
        rl_back = (RelativeLayout) dialog.findViewById(R.id.rl_back);

        rl_a_answer.setOnClickListener(this);
        rl_b_answer.setOnClickListener(this);
        rl_back.setOnClickListener(this);
    }

    public void setTextViewAnswer(String answerA, String answerB) {
        //问题的答案的确认
        tv_answerA.setText(answerA);
        tv_answerB.setText(answerB);

    }

    public void setTextViewUKNum(String UKNum) {
        //押注的进度条
        tv_ukdialog_num.setText(UKNum);
    }

    public void setTextViewNumChange(int UKNum) {
        //押注的进度条
        int index = tv_ukdialog_num.getText().toString().trim().indexOf("/");
        String strBeforeBet = tv_ukdialog_num.getText().toString().trim().substring(0, index);
        String strAfterBet = tv_ukdialog_num.getText().toString().trim().substring(index + 1, tv_ukdialog_num.length());
        int beforeBet = UKNum + Integer.parseInt(strBeforeBet);
        int afterBet = Integer.parseInt(strAfterBet);
        float f_afterBet = (float) beforeBet / (float) afterBet;
        tv_ukdialog_num.setText(beforeBet + "/" + afterBet);
        pb_magic.setSmoothPercent(f_afterBet, 2000);
    }

    public void setTextViewTime(int time) {
        //时间倒计时方法的调用
        timeCount = time;
        tv_time.setText(TimeUtil.getTimeFromInt(time));
        timer.schedule(task, 0, 1000);
    }

    //时间倒计时方法的调用
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            getActivity().runOnUiThread(new Runnable() {      // UI thread
                @Override
                public void run() {
                    timeCount--;
                    tv_time.setText(TimeUtil.getTimeFromInt(timeCount));
                    if (timeCount <= 0) {
                        timer.cancel();
                        dismiss();
                        mOnClickGiveListener.onClickDismiss();
                    }
                }
            });
        }
    };

    public UKGuessingDlg1 setAllClick(OnClickAllListener onClickAllListener) {
        mOnClickGiveListener = onClickAllListener;
        return this;
    }

    public interface OnClickAllListener {
        void onClickConfirm(String beanNum, String answer);//下注

        void onClickDismiss();//退出dlg
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //进行答案的选择
            case R.id.rl_a_answer:
                answer = "0";
                rl_gone_a_answer.setVisibility(View.VISIBLE);
                rl_a_answer.setVisibility(View.GONE);
                break;
            case R.id.rl_b_answer:
                answer = "1";
                break;
            case R.id.rl_back:
                dismiss();
                break;
        }
    }
}
