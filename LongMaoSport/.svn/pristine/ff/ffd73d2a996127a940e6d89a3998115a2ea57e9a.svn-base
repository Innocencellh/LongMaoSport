package com.live.longmao.dlg;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.live.longmao.R;
import com.live.longmao.util.TimeUtil;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2016/9/9.
 */
public class BetDlg extends DialogFragment implements View.OnClickListener{
    //private View view;
    private Dialog dialog;
    private TextView tv_money,tv_problem,tv_countdown,tv_already_bet;
    private Button btn_confirm;
    private RelativeLayout dlg_whole,rl_money;
    private RadioGroup rg_select;
    private RadioButton aq,bq;
    private EditText et_bet_num;
    private OnClickAllListener mOnClickGiveListener;
    String answer = "-1";
    Timer timer = new Timer();
    int timeCount;

    private void initView()
    {
        dlg_whole = (RelativeLayout) dialog.findViewById(R.id.dlg_whole);
        rl_money = (RelativeLayout) dialog.findViewById(R.id.rl_money);
        tv_money = (TextView) dialog.findViewById(R.id.tv_money);
        tv_problem = (TextView) dialog.findViewById(R.id.tv_problem);
        tv_already_bet = (TextView) dialog.findViewById(R.id.tv_already_bet);
        btn_confirm = (Button) dialog.findViewById(R.id.btn_confirm);
        rg_select = (RadioGroup)dialog.findViewById(R.id.rg_select);
        et_bet_num = (EditText) dialog.findViewById(R.id.et_bet_num);
        tv_countdown = (TextView) dialog.findViewById(R.id.tv_countdown);
        aq = (RadioButton) dialog.findViewById(R.id.rb_can);
        bq = (RadioButton) dialog.findViewById(R.id.rb_no_can);
        dlg_whole.setOnClickListener(this);
        btn_confirm.setOnClickListener(this);
        rg_select.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_can) {
                    answer = "0";
                } else if (checkedId == R.id.rb_no_can) {
                    answer = "1";
                }
            }
        });
    }

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            getActivity().runOnUiThread(new Runnable() {      // UI thread
                @Override
                public void run() {
                    timeCount--;
                    tv_countdown.setText(TimeUtil.getTimeFromInt(timeCount));
                    if (timeCount <= 0) {
                        timer.cancel();
                        dismiss();
                        mOnClickGiveListener.onClickDismiss();
                    }
                }
            });
        }
    };

    private boolean isEmptyView() {
        if (!TextUtils.isEmpty(et_bet_num.getText().toString().trim()) && !answer.equals("-1")) {
            return true;
        }
        Toast.makeText(getDialog().getContext(), "请完善内容", Toast.LENGTH_SHORT).show();
        return false;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = new Dialog(getActivity(), R.style.dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // must be called before set content
        dialog.setContentView(R.layout.dialog_guessing_bet);
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
            case R.id.btn_confirm:
                if(isEmptyView()) {
                    dismiss();
                    mOnClickGiveListener.onClickConfirm(et_bet_num.getText().toString().trim(), answer);
                }
                break;
        }
    }

    public void setTextViewForProblem(String problem)
    {
        tv_problem.setText(problem);
    }
    public void setTextViewForAQ(String aA)
    {
        aq.setText(aA);
    }
    public void setTextViewForBQ(String bA)
    {
        bq.setText(bA);
    }
    public void setTextViewForCountdown(int time)
    {
        //时间倒计时方法的调用
        timeCount = time;
        tv_countdown.setText(TimeUtil.getTimeFromInt(time));
        timer.schedule(task, 0, 1000);
    }
    public void setTextForBetNumChange(int countBetNum,int betNum)
    {
        tv_already_bet.setText(betNum+"/"+countBetNum);
    }

    public BetDlg setAllClick(OnClickAllListener onClickAllListener) {
        mOnClickGiveListener = onClickAllListener;
        return this;
    }

    public interface OnClickAllListener {
        void onClickConfirm(String beanNum,String answer);//下注
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
