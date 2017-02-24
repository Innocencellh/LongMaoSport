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
import com.live.longmao.view.MagicProgressBar;

/**
 * Created by HPDN on 2016/12/19.
 */
public class UKReadingGuessDialog extends DialogFragment implements View.OnClickListener {
    private TextView tv_user_guessing_tittle,tv_ukdialog_num,rb_can,rb_no_can;
    private Button btn_guess;
    private ImageView iv_back;
    Dialog dialog;
    private MagicProgressBar pb_magic;
    private OnClickAllListener mOnClickGiveListener;
    private String title, anA, anB;
    private RelativeLayout rl_back;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = new Dialog(getActivity(), R.style.dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // must be called before set content
        dialog.setContentView(R.layout.a_yh_all_guess_reading);
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
        tv_user_guessing_tittle = (TextView) dialog.findViewById(R.id.tv_user_guessing_tittle);

        btn_guess = (Button) dialog.findViewById(R.id.btn_guess);
        pb_magic = (MagicProgressBar) dialog.findViewById(R.id.pb_magic);
        tv_ukdialog_num = (TextView) dialog.findViewById(R.id.tv_ukdialog_num);
        rb_can = (TextView) dialog.findViewById(R.id.rb_can);
        rb_no_can = (TextView) dialog.findViewById(R.id.rb_no_can);
        rl_back = (RelativeLayout) dialog.findViewById(R.id.rl_back);
        rb_can.setText("A. " + getAnA());
        rb_no_can.setText("B. " + getAnB());
        tv_user_guessing_tittle.setText(getTitle() + "?");
        btn_guess.setOnClickListener(this);
        rl_back.setOnClickListener(this);

    }

    public void setTextForBetNumChange(int betNum,int AllNum) {
        float f_afterBet = (float) betNum / (float) AllNum;
        tv_ukdialog_num.setText(betNum + "/" + AllNum);
        pb_magic.setSmoothPercent(f_afterBet, 2000);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_guess:
                dismiss();
                break;
            case R.id.rl_back:
                dismiss();
                break;
        }

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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




    public UKReadingGuessDialog setAllClick(OnClickAllListener onClickAllListener) {
        mOnClickGiveListener = onClickAllListener;
        return this;
    }

    public interface OnClickAllListener {
        void onClickSettlement();//结算
        void onClickDismiss();//退出dlg
    }

}
