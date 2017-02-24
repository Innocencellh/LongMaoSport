package com.live.longmao.dlg;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.Gravity;
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
import com.live.longmao.view.MagicProgressBar;

import cn.dreamtobe.percentsmoothhandler.ISmoothTarget;

/**
 * Created by Administrator on 2016/9/9.
 * 结算
 */
public class AnswerDlg extends DialogFragment implements View.OnClickListener{
    //private View view;
    private Dialog dialog;
    private TextView tv_money,tv_problem;
    private Button btn_settlement;
    private RelativeLayout dlg_whole,rl_money;
    private RadioGroup rg_select;
    RadioButton rb_can,rb_no_can;
    private OnClickAllListener mOnClickGiveListener;
    private String title,anA,anB;


    private void initView()
    {
        dlg_whole = (RelativeLayout) dialog.findViewById(R.id.dlg_whole);
        rl_money = (RelativeLayout) dialog.findViewById(R.id.rl_money);
        tv_money = (TextView) dialog.findViewById(R.id.tv_money);
        tv_problem = (TextView) dialog.findViewById(R.id.tv_problem);
        btn_settlement = (Button) dialog.findViewById(R.id.btn_settlement);
        rg_select = (RadioGroup)dialog.findViewById(R.id.rg_select);
        rb_can = (RadioButton)dialog.findViewById(R.id.rb_can);
        rb_no_can = (RadioButton)dialog.findViewById(R.id.rb_no_can);
        dlg_whole.setOnClickListener(this);
        btn_settlement.setOnClickListener(this);
        tv_problem.setText(getTitle());
        rb_can.setText(getAnA());
        rb_no_can.setText(getAnB());
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

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = new Dialog(getActivity(), R.style.dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // must be called before set content
        dialog.setContentView(R.layout.dialog_guessing_answer);
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
            case R.id.btn_settlement:
                if(rb_can.isChecked()||rb_no_can.isChecked())
                {
                    if(rb_can.isChecked()) {
                        mOnClickGiveListener.onClickSettlement("1");
                    }else
                    {
                        mOnClickGiveListener.onClickSettlement("2");
                    }
                    dismiss();
                }else
                {
                    Toast.makeText(getActivity(),"请选择答案",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public AnswerDlg setAllClick(OnClickAllListener onClickAllListener) {
        mOnClickGiveListener = onClickAllListener;
        return this;
    }

    public interface OnClickAllListener {
        void onClickSettlement(String answer);//结算
        void onClickDismiss();//退出dlg
    }
}
