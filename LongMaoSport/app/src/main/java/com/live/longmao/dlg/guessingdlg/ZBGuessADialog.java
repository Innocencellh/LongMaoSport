package com.live.longmao.dlg.guessingdlg;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.live.longmao.R;
import com.live.longmao.view.MagicProgressBar;

/**
 * Created by HPDN on 2016/12/4.
 */
public class ZBGuessADialog extends DialogFragment implements View.OnClickListener {

    private TextView tv_bean, tv_exp,tv_a_bean,tv_b_bean;
    Dialog dialog;
    private MagicProgressBar pb_yes_magic, pb_no_magic;
    private OnClickAllListener mOnClickGiveListener;
    private Button btn_zb_guess;
    private String all, anA, anB;
    private RelativeLayout rl_back;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = new Dialog(getActivity(), R.style.dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // must be called before set content
        dialog.setContentView(R.layout.a_zb_gurss_ayes);
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

        pb_yes_magic = (MagicProgressBar) dialog.findViewById(R.id.pb_yes_magic);
        tv_bean = (TextView) dialog.findViewById(R.id.tv_bean);
        tv_exp = (TextView) dialog.findViewById(R.id.tv_exp);
        pb_no_magic = (MagicProgressBar) dialog.findViewById(R.id.pb_no_magic);
        btn_zb_guess = (Button) dialog.findViewById(R.id.btn_zb_guess);
        tv_a_bean = (TextView) dialog.findViewById(R.id.tv_a_bean);
        tv_b_bean = (TextView) dialog.findViewById(R.id.tv_b_bean);
        rl_back = (RelativeLayout) dialog.findViewById(R.id.rl_back);
        btn_zb_guess.setOnClickListener(this);
        rl_back.setOnClickListener(this);

        tv_a_bean.setText("+ " + getAnA());
        tv_b_bean.setText("+ " + getAnB());
        tv_bean.setText("龙猫豆 + " +getAll());

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_zb_guess:
                dismiss();
                break;
            case R.id.rl_back:
                dismiss();
                break;

        }
    }


    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }

    public String getAnA() {
        return anA;
    }

    public void setAnA(String anA) {
        this.anA = anA;
    }

    public String getAnB() {
        return anB;
    }

    public void setAnB(String anB) {
        this.anB = anB;
    }


    public void setTextYesBetNumChange(int betNum,int AllNum) {
        float f_afterBet = (float) betNum / (float) AllNum;
        pb_yes_magic.setSmoothPercent(f_afterBet, 2000);
    }

    public void setTextNoBetNumChange(int betNum, int AllNum) {
        float f_afterBet = (float) betNum / (float) AllNum;
        pb_no_magic.setSmoothPercent(f_afterBet, 2000);
    }

    public ZBGuessADialog setAllClick(OnClickAllListener onClickAllListener) {
        mOnClickGiveListener = onClickAllListener;
        return this;
    }

    public interface OnClickAllListener {
        void onClickEntertained();//用户输赢结果

        void onClickDismiss();//退出dlg
    }


}
