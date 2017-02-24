package com.live.longmao.dlg.guessingdlg;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.live.longmao.R;
import com.live.longmao.util.TimeUtil;
import com.live.longmao.view.MagicProgressBar;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by HPDN on 2016/12/3.
 */
public class UKYaZhuDlg1 extends DialogFragment implements View.OnClickListener {

    private Dialog dialog;
    private ImageView iv_no100_bean, iv_no1000_bean, iv_no1w_bean,
            iv_no10w_bean, iv_yes100_bean, iv_yes1000_bean,
            iv_yes1w_bean, iv_yes10w_bean, iv_zb_jianhao, iv_zb_jiahao, iv_back;
    private TextView tv_zb_numbean, tv_user_guessing_tittle, tv_user_guessing_time, tv_uk_yazhudialog_num, tv_yh_guess_bean;
    private Button btn_zb_guess;
    private int bean;
    private MagicProgressBar pb_magic;
    private int maxBean = 100;
    private OnClickAllListener mOnClickGiveListener;
    private RadioGroup rg_select;
    private RadioButton aq, bq;
    private LinearLayout ll_05;
    private RelativeLayout rl_back;
    String answer = "-1";
    private String title, anA, anB;
    Timer timer = new Timer();
    int timeCount;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null != timer) {
            timer.cancel();
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = new Dialog(getActivity(), R.style.dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // must be called before set content
        dialog.setContentView(R.layout.a_all_uk_yazhu);
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
        tv_zb_numbean = (TextView) dialog.findViewById(R.id.tv_zb_numbean);
        tv_user_guessing_tittle = (TextView) dialog.findViewById(R.id.tv_user_guessing_tittle);
        tv_user_guessing_time = (TextView) dialog.findViewById(R.id.tv_user_guessing_time);
        btn_zb_guess = (Button) dialog.findViewById(R.id.btn_zb_guess);
        rg_select = (RadioGroup) dialog.findViewById(R.id.rg_select);
        aq = (RadioButton) dialog.findViewById(R.id.rb_can);
        bq = (RadioButton) dialog.findViewById(R.id.rb_no_can);
        rl_back = (RelativeLayout) dialog.findViewById(R.id.rl_back);
        tv_uk_yazhudialog_num = (TextView) dialog.findViewById(R.id.tv_uk_yazhudialog_num);
        pb_magic = (MagicProgressBar) dialog.findViewById(R.id.pb_magic);
        tv_yh_guess_bean = (TextView) dialog.findViewById(R.id.tv_yh_guess_bean);

        iv_no100_bean = (ImageView) dialog.findViewById(R.id.iv_no100_bean);
        iv_no1000_bean = (ImageView) dialog.findViewById(R.id.iv_no1000_bean);
        iv_no1w_bean = (ImageView) dialog.findViewById(R.id.iv_no1w_bean);
        iv_no10w_bean = (ImageView) dialog.findViewById(R.id.iv_no10w_bean);
        iv_yes100_bean = (ImageView) dialog.findViewById(R.id.iv_yes100_bean);
        iv_yes1000_bean = (ImageView) dialog.findViewById(R.id.iv_yes1000_bean);
        iv_yes1w_bean = (ImageView) dialog.findViewById(R.id.iv_yes1w_bean);
        iv_yes10w_bean = (ImageView) dialog.findViewById(R.id.iv_yes10w_bean);
        iv_zb_jianhao = (ImageView) dialog.findViewById(R.id.iv_zb_jianhao);
        iv_zb_jiahao = (ImageView) dialog.findViewById(R.id.iv_zb_jiahao);
        ll_05 = (LinearLayout) dialog.findViewById(R.id.ll_05);

        iv_no100_bean.setOnClickListener(this);
        iv_no1000_bean.setOnClickListener(this);
        iv_no1w_bean.setOnClickListener(this);
        iv_no10w_bean.setOnClickListener(this);
        iv_yes100_bean.setOnClickListener(this);
        iv_yes1000_bean.setOnClickListener(this);
        iv_yes1w_bean.setOnClickListener(this);
        iv_yes10w_bean.setOnClickListener(this);
        iv_zb_jianhao.setOnClickListener(this);
        iv_zb_jiahao.setOnClickListener(this);
        btn_zb_guess.setOnClickListener(this);
        rg_select.setOnClickListener(this);
        aq.setOnClickListener(this);
        bq.setOnClickListener(this);
        ll_05.setOnClickListener(this);
        rl_back.setOnClickListener(this);

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

    public void setTextTittle(String tittle, String anA, String anB) {
        tv_user_guessing_tittle.setText(tittle);
        aq.setText(anA);
        bq.setText(anB);
    }

    public void setBean(String bean) {
        tv_yh_guess_bean.setText(bean);
    }

    public void setTextForBetNum(String betNum) {
        tv_uk_yazhudialog_num.setText(betNum);
    }

    public void setTextForBetNumChange(int betNum) {
        int index = tv_uk_yazhudialog_num.getText().toString().trim().indexOf("/");
        String strBeforeBet = tv_uk_yazhudialog_num.getText().toString().trim().substring(0, index);
        String strAfterBet = tv_uk_yazhudialog_num.getText().toString().trim().substring(index + 1, tv_uk_yazhudialog_num.length());
        int beforeBet = betNum + Integer.parseInt(strBeforeBet);
        int afterBet = Integer.parseInt(strAfterBet);
        float f_afterBet = (float) beforeBet / (float) afterBet;
        tv_uk_yazhudialog_num.setText(beforeBet + "/" + afterBet);
        pb_magic.setSmoothPercent(f_afterBet, 2000);
    }

    public void setTextBetNumChange() {
        int index = tv_uk_yazhudialog_num.getText().toString().trim().indexOf("/");
        String strBeforeBet = tv_uk_yazhudialog_num.getText().toString().trim().substring(0, index);
        String strAfterBet = tv_uk_yazhudialog_num.getText().toString().trim().substring(index + 1, tv_uk_yazhudialog_num.length());
        int beforeBet = Integer.parseInt(strBeforeBet);
        int afterBet = Integer.parseInt(strAfterBet);
        float f_afterBet = (float) beforeBet / (float) afterBet;
        tv_uk_yazhudialog_num.setText(beforeBet + "/" + afterBet);
        pb_magic.setSmoothPercent(f_afterBet, 2000);
    }

    public void setTextViewForCountdown(int time) {
        //时间倒计时方法的调用
        timeCount = time;
        tv_user_guessing_time.setText(TimeUtil.getTimeFromInt(time));
        timer.schedule(task, 0, 1000);
    }

    public UKYaZhuDlg1 setAllClick(OnClickAllListener onClickAllListener) {
        mOnClickGiveListener = onClickAllListener;
        return this;
    }

    public interface OnClickAllListener {
        void onClickConfirm(String beanNum, String answer);//下注

        void onClickExchange();//兑换

        void onClickDismiss();//退出dlg
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_no100_bean:

                iv_no100_bean.setVisibility(View.GONE);
                iv_yes100_bean.setVisibility(View.VISIBLE);
                iv_yes1000_bean.setVisibility(View.GONE);
                iv_yes1w_bean.setVisibility(View.GONE);
                iv_yes10w_bean.setVisibility(View.GONE);
                iv_no1w_bean.setVisibility(View.VISIBLE);
                iv_no1000_bean.setVisibility(View.VISIBLE);
                iv_no10w_bean.setVisibility(View.VISIBLE);

                bean = 100;

                break;

            case R.id.iv_no1000_bean:

                iv_no1000_bean.setVisibility(View.GONE);
                iv_yes100_bean.setVisibility(View.GONE);
                iv_yes1000_bean.setVisibility(View.VISIBLE);
                iv_yes1w_bean.setVisibility(View.GONE);
                iv_yes10w_bean.setVisibility(View.GONE);
                iv_no1w_bean.setVisibility(View.VISIBLE);
                iv_no10w_bean.setVisibility(View.VISIBLE);
                iv_no100_bean.setVisibility(View.VISIBLE);

                bean = 1000;

                break;
            case R.id.iv_no1w_bean:

                iv_no1w_bean.setVisibility(View.GONE);
                iv_yes100_bean.setVisibility(View.GONE);
                iv_yes1000_bean.setVisibility(View.GONE);
                iv_yes1w_bean.setVisibility(View.VISIBLE);
                iv_yes10w_bean.setVisibility(View.GONE);
                iv_no10w_bean.setVisibility(View.VISIBLE);
                iv_no1000_bean.setVisibility(View.VISIBLE);
                iv_no100_bean.setVisibility(View.VISIBLE);

                bean = 10000;

                break;
            case R.id.iv_no10w_bean:

                iv_no10w_bean.setVisibility(View.GONE);
                iv_no1w_bean.setVisibility(View.VISIBLE);
                iv_no1000_bean.setVisibility(View.VISIBLE);
                iv_no100_bean.setVisibility(View.VISIBLE);
                iv_yes100_bean.setVisibility(View.GONE);
                iv_yes1000_bean.setVisibility(View.GONE);
                iv_yes1w_bean.setVisibility(View.GONE);
                iv_yes10w_bean.setVisibility(View.VISIBLE);

                bean = 100000;

                break;


            case R.id.iv_zb_jiahao:
                maxBean += bean;
                tv_zb_numbean.setText(maxBean + "");
                break;
            case R.id.iv_zb_jianhao:
                if (maxBean - bean >= 100) {
                    maxBean -= bean;
                    tv_zb_numbean.setText(maxBean + "");
                } else {
                    Toast.makeText(getActivity(), "押注金额不能小于100龙猫豆", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.btn_zb_guess:
                if (aq.isChecked() || bq.isChecked()) {
                    if (aq.isChecked()) {
                        answer = "1";
                        mOnClickGiveListener.onClickConfirm(tv_zb_numbean.getText().toString().trim(), answer);
                        bq.setChecked(false);
                    } else {
                        answer = "2";
                        mOnClickGiveListener.onClickConfirm(tv_zb_numbean.getText().toString().trim(), answer);
                        aq.setChecked(false);
                    }
                    dismiss();
                }
                break;

            case R.id.ll_05:
                mOnClickGiveListener.onClickExchange();
                break;
            case R.id.rl_back:
                dismiss();
                break;
        }
    }
}
