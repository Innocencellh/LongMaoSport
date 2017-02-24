package com.live.longmao.dlg.guessingdlg;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.live.longmao.R;
import com.live.longmao.util.TimeUtil;
import com.live.longmao.view.MagicProgressBar;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by HPDN on 2016/12/4.
 */
public class ZBFengPanDialog extends DialogFragment implements View.OnClickListener {

    private TextView tv_user_guessing_tittle,
            tv_user_guessing_time, tv_a_tittle, tv_b_tittle, tv_ukdialog_num;
    private Button btn_guess;
    private ImageView iv_back;
    Dialog dialog;
    private MagicProgressBar pb_magic;
    private OnClickAllListener mOnClickGiveListener;
    String answer = "-1";
    Timer timer = new Timer();
    int timeCount;
    private String title, anA, anB;
    private RelativeLayout rl_back;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = new Dialog(getActivity(), R.style.dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // must be called before set content
        dialog.setContentView(R.layout.a_bzb_all_jing_cai);
        dialog.setCanceledOnTouchOutside(true);

        // 设置宽度为屏宽。
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);

        initView();
        return dialog;
    }

    private void initView() {
        tv_user_guessing_tittle = (TextView) dialog.findViewById(R.id.tv_user_guessing_tittle);
        tv_user_guessing_time = (TextView) dialog.findViewById(R.id.tv_user_guessing_time);
        tv_a_tittle = (TextView) dialog.findViewById(R.id.tv_a_tittle);
        tv_b_tittle = (TextView) dialog.findViewById(R.id.tv_b_tittle);
        tv_ukdialog_num = (TextView) dialog.findViewById(R.id.tv_ukdialog_num);
        btn_guess = (Button) dialog.findViewById(R.id.btn_guess);
        pb_magic = (MagicProgressBar) dialog.findViewById(R.id.pb_magic);
        rl_back = (RelativeLayout) dialog.findViewById(R.id.rl_back);

        btn_guess.setOnClickListener(this);
        rl_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_guess:
                dismiss();
                mOnClickGiveListener.onClickEntertained();
                break;
            case R.id.rl_back:
                dismiss();
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
                    tv_user_guessing_time.setText(TimeUtil.getTimeFromInt(timeCount));
                    if (timeCount <= 0) {
                        timer.cancel();
                        mOnClickGiveListener.onClicFengPan();
                        dismiss();
                        mOnClickGiveListener.onClickDismiss();
                    }
                }
            });
        }
    };


public void setTextTittle(String tittle,String anA,String anB){
    tv_user_guessing_tittle.setText(tittle);
    tv_a_tittle.setText(anA);
    tv_b_tittle.setText(anB);
}


    public void setTextForBetNum(String betNum)
    {
        tv_ukdialog_num.setText(betNum);
    }

    public void setTextForBetNumChange(int betNum)
    {
        int index = tv_ukdialog_num.getText().toString().trim().indexOf("/");
      //  String strBeforeBet = tv_ukdialog_num.getText().toString().trim().substring(0, index);
        String strAfterBet = tv_ukdialog_num.getText().toString().trim().substring(index+1, tv_ukdialog_num.length());
        int beforeBet = betNum ;
        int afterBet = Integer.parseInt(strAfterBet);
        float f_afterBet = (float)beforeBet/(float)afterBet;
        tv_ukdialog_num.setText(beforeBet+"/"+afterBet);
        pb_magic.setSmoothPercent(f_afterBet, 2000);
    }
    public void setTextBetNumChange()
    {
        int index = tv_ukdialog_num.getText().toString().trim().indexOf("/");
        String strBeforeBet = tv_ukdialog_num.getText().toString().trim().substring(0, index);
        String strAfterBet = tv_ukdialog_num.getText().toString().trim().substring(index+1, tv_ukdialog_num.length());
        int beforeBet = Integer.parseInt(strBeforeBet);
        int afterBet = Integer.parseInt(strAfterBet);
        float f_afterBet = (float)beforeBet/(float)afterBet;
        tv_ukdialog_num.setText(beforeBet+"/"+afterBet);
        pb_magic.setSmoothPercent(f_afterBet, 2000);
    }

    public void setTextViewForCountdown(int time) {
        //时间倒计时方法的调用
        timeCount = time;
        tv_user_guessing_time.setText(TimeUtil.getTimeFromInt(time));
        timer.schedule(task, 0, 1000);
    }


    public ZBFengPanDialog setAllClick(OnClickAllListener onClickAllListener) {
        mOnClickGiveListener = onClickAllListener;
        return this;
    }

    public interface OnClickAllListener {
        void onClickEntertained();//封盘
        void onClicFengPan();//自动封盘
        void onClickDismiss();//退出dlg
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null != timer) {
            timer.cancel();
        }
    }

}
