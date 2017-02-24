package com.live.longmao.dlg.guessingdlg;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.live.longmao.R;
import com.live.longmao.dlg.ActionSheetDialog;

/**
 * Created by HPDN on 2016/12/3.
 */
public class UKPKGuessingDlg1 extends DialogFragment implements View.OnClickListener {
    private Dialog dialog;
    private EditText tv_zb_guessing_tittle, et_zb_aAnswer, et_zb_bAnswer;
    private TextView tv_zb_time;
    private ImageView iv_no100_bean, iv_no1000_bean, iv_no1w_bean,
            iv_no10w_bean, iv_yes100_bean, iv_yes1000_bean,
            iv_yes1w_bean, iv_yes10w_bean, iv_zb_jianhao, iv_zb_jiahao, iv_back;
    private TextView tv_zb_numbean;
    private Button btn_zb_guess;
    private LinearLayout ll_05;
    private int time = 1;
    private int bean;
    private int maxBean = 10000;
    private OnClickAllListener mOnClickGiveListener;
    private RelativeLayout rl_back;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = new Dialog(getActivity(), R.style.dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // must be called before set content
        dialog.setContentView(R.layout.a_zb_all_jing_cai);
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
        tv_zb_guessing_tittle = (EditText) dialog.findViewById(R.id.tv_zb_guessing_tittle);
        et_zb_aAnswer = (EditText) dialog.findViewById(R.id.et_zb_aAnswer);
        et_zb_bAnswer = (EditText) dialog.findViewById(R.id.et_zb_bAnswer);
        tv_zb_time = (TextView) dialog.findViewById(R.id.tv_zb_time);
        tv_zb_numbean = (TextView) dialog.findViewById(R.id.tv_zb_numbean);
        btn_zb_guess = (Button) dialog.findViewById(R.id.btn_zb_guess);
        iv_back = (ImageView) dialog.findViewById(R.id.iv_back);

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
        rl_back = (RelativeLayout) dialog.findViewById(R.id.rl_back);

        tv_zb_time.setOnClickListener(this);
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
        ll_05.setOnClickListener(this);
        rl_back.setOnClickListener(this);

    }

    private boolean isEmptyView() {
        if (!TextUtils.isEmpty(tv_zb_guessing_tittle.getText().toString().trim())
                && !TextUtils.isEmpty(et_zb_aAnswer.getText().toString().trim())
                && !TextUtils.isEmpty(et_zb_bAnswer.getText().toString().trim())
                && !TextUtils.isEmpty(tv_zb_numbean.getText().toString().trim())) {
            return true;
        }
        Toast.makeText(getDialog().getContext(), "请完善内容", Toast.LENGTH_SHORT).show();
        return false;
    }

    public UKPKGuessingDlg1 setAllClick(OnClickAllListener onClickAllListener) {
        mOnClickGiveListener = onClickAllListener;
        return this;
    }

    public interface OnClickAllListener {
        void onClickConfirm(String tittle, String answerA, String answerB, String numBean, String time);//设置竞猜问题

        void onClickExchange();//兑换

        void onClickDismiss();//退出dlg
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_zb_time:
                new ActionSheetDialog(getActivity())
                        .builder()
                        .setCancelable(false)
                        .setCanceledOnTouchOutside(false)
                        .addSheetItem("5分钟", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        tv_zb_time.setText("5分钟");
                                        time = 5;
                                    }
                                })
                        .addSheetItem("10分钟", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        tv_zb_time.setText("10分钟");
                                        time = 10;
                                    }
                                })
                        .addSheetItem("15分钟", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        tv_zb_time.setText("15分钟");
                                        time = 15;
                                    }
                                })
                        .addSheetItem("20分钟", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        tv_zb_time.setText("20分钟");
                                        time = 20;
                                    }
                                })
                        .addSheetItem("30分钟", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        tv_zb_time.setText("30分钟");
                                        time = 30;
                                    }
                                }).show();

                break;

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
                if (maxBean - bean >= 10000) {
                    maxBean -= bean;
                    tv_zb_numbean.setText(maxBean + "");
                } else {
                    Toast.makeText(getActivity(), "押注金额不能小于10000龙猫豆", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.btn_zb_guess:
                if (isEmptyView()) {
                    dismiss();
                    mOnClickGiveListener.onClickConfirm(
                            tv_zb_guessing_tittle.getText().toString().trim(),
                            et_zb_aAnswer.getText().toString().trim(),
                            et_zb_bAnswer.getText().toString().trim(),
                            tv_zb_numbean.getText().toString().trim(),
                            time + "");
                }
/* private boolean isEmptyView() {
            if (!TextUtils.isEmpty(tv_zb_guessing_tittle.getText().toString().trim())
                    && !TextUtils.isEmpty(tv_zb_numbean.getText().toString().trim())
                    && !TextUtils.isEmpty(et_zb_aAnswer.getText().toString().trim())
                    && !TextUtils.isEmpty(et_zb_bAnswer.getText().toString().trim())
                    && !TextUtils.isEmpty(tv_zb_time.getText().toString().trim())) {
                return true;
            }D
            Toast.makeText(getActivity(),"请完善内容",Toast.LENGTH_SHORT).show();
            return false;
        }*/
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
