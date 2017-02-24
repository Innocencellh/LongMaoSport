package com.live.longmao.gifdlg;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.live.longmao.R;
import com.live.longmao.adapter.GiftListAdapter;
import com.live.longmao.bean.GiftBean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/23.
 */
public class GiftDlg extends DialogFragment implements View.OnClickListener, DialogInterface.OnKeyListener {
    private Dialog dialog;
    private RelativeLayout dlg_whole;
    private OnClickGiveListener mOnClickGiveListener;
    GiftBean giftLoc;
    private final long COUNT_DOWN_TOTAL = 5 * 1000;
    private final long COUNT_DOWN_INTERVAL = 250;
    private final int TOTALNUM = 20;
    private int countnum = 20;
    EmotionPanel gift_panel;
    Button giveBt;
    RelativeLayout fireRl, rl_money;
    TextView countDownTv;
    TextView tv_money;
    GiftListAdapter adapter;
    private List<GiftBean> beans;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = new Dialog(getActivity(), R.style.dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // must be called before set content
        dialog.setContentView(R.layout.dlg_live_gift);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setOnKeyListener(this);

        // 设置宽度为屏宽、靠近屏幕底部。
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);
        initView();
        // initData();
        return dialog;
    }

    private void initView() {
        giftLoc = null;
        gift_panel = (EmotionPanel) dialog.findViewById(R.id.gift_panel);
        giveBt = (Button) dialog.findViewById(R.id.giveBt);
        fireRl = (RelativeLayout) dialog.findViewById(R.id.fireRl);
        countDownTv = (TextView) dialog.findViewById(R.id.countDownTv);
        tv_money = (TextView) dialog.findViewById(R.id.tv_money);
        rl_money = (RelativeLayout) dialog.findViewById(R.id.rl_money);
        dlg_whole = (RelativeLayout) dialog.findViewById(R.id.dlg_whole);
        rl_money.setOnClickListener(this);
        dlg_whole.setOnClickListener(this);
        giveBt.setOnClickListener(this);
        dlg_whole.postDelayed(new Runnable() {
            @Override
            public void run() {
                initData();
            }
        }, 100);
    }


    public void setCoin(String bean) {
        tv_money.setText(bean);
    }


    private void initData() {
        beans = PngNameUtil.getInstance().getList();
        adapter = new GiftListAdapter(getActivity(), beans, new GiftListAdapter.PickListener() {

            @Override
            public void onPick(GiftBean bean, boolean isSelect) {
                if (isSelect) {
                    if (null != giftLoc) {
                        if (giftLoc.getId() != bean.getId()) {
                            hideFire();
                        }
                    }
                    giftLoc = bean;
                } else {
                    giftLoc = null;
                    hideFire();
                }
            }
        });
        gift_panel.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dlg_whole:
                dismiss();
                hideFire();
                mOnClickGiveListener.onClickDismiss();
                break;
            case R.id.rl_money:
                mOnClickGiveListener.onClickExchange();
                break;
            case R.id.giveBt:
                if (null == giftLoc) {
                    return;
                }
                mOnClickGiveListener.onClickGive(giftLoc);
                if (giftLoc.getIsSend() == 1) {
                    showFire();
                } else {
                    hideFire();
                }
                break;
        }
    }

    public GiftDlg setHandleGive(OnClickGiveListener clickGiveListener) {
        mOnClickGiveListener = clickGiveListener;
        return this;
    }

    public interface OnClickGiveListener {
        void onClickDismiss();

        void onClickExchange();

        void onClickGive(GiftBean giftBean);
    }

    @Override
    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            dismiss();
            mOnClickGiveListener.onClickDismiss();
            return true;
        } else {
            Log.e("---", "---");
            //这里注意当不是返回键时需将事件扩散，否则无法处理其他点击事件
            return false;
        }
    }

    private void showFire() {
        giveBt.setVisibility(View.GONE);
        if (fireRl.getVisibility() != View.VISIBLE) {
            fireRl.setVisibility(View.VISIBLE);
            AnimationDrawable drawable = (AnimationDrawable) fireRl.getBackground();
            drawable.start();
            startCount();
            fireRl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startCount();
                    mOnClickGiveListener.onClickGive(giftLoc);
                }
            });
        }
    }

    private void startCount() {
        countDownTimer.cancel();
        countnum = TOTALNUM;
        countDownTv.setText(String.valueOf(countnum));
        countDownTimer.start();
    }

    private void hideFire() {
        countDownTimer.cancel();
        giveBt.setVisibility(View.VISIBLE);
        fireRl.setVisibility(View.GONE);

        ObjectAnimator animator = tada(giveBt);
        animator.clone();
        animator.start();

    }

    //小礼物的连击按钮
    private final CountDownTimer countDownTimer = new CountDownTimer(COUNT_DOWN_TOTAL, COUNT_DOWN_INTERVAL) {
        @Override
        public void onTick(long millisUntilFinished) {
            countDownTv.setText(String.valueOf(countnum--));
        }

        @Override
        public void onFinish() {
            hideFire();
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    public static ObjectAnimator tada(View view) {
        return tada(view, 1f);
    }

    public static ObjectAnimator tada(View view, float shakeFactor) {

        PropertyValuesHolder pvhScaleX = PropertyValuesHolder.ofKeyframe(View.SCALE_X,
                Keyframe.ofFloat(0f, 1f),
                Keyframe.ofFloat(.1f, .9f),
                Keyframe.ofFloat(.2f, .9f),
                Keyframe.ofFloat(.3f, 1.1f),
                Keyframe.ofFloat(.4f, 1.1f),
                Keyframe.ofFloat(.5f, 1.1f),
                Keyframe.ofFloat(.6f, 1.1f),
                Keyframe.ofFloat(.7f, 1.1f),
                Keyframe.ofFloat(.8f, 1.1f),
                Keyframe.ofFloat(.9f, 1.1f),
                Keyframe.ofFloat(1f, 1f)
        );

        PropertyValuesHolder pvhScaleY = PropertyValuesHolder.ofKeyframe(View.SCALE_Y,
                Keyframe.ofFloat(0f, 1f),
                Keyframe.ofFloat(.1f, .9f),
                Keyframe.ofFloat(.2f, .9f),
                Keyframe.ofFloat(.3f, 1.1f),
                Keyframe.ofFloat(.4f, 1.1f),
                Keyframe.ofFloat(.5f, 1.1f),
                Keyframe.ofFloat(.6f, 1.1f),
                Keyframe.ofFloat(.7f, 1.1f),
                Keyframe.ofFloat(.8f, 1.1f),
                Keyframe.ofFloat(.9f, 1.1f),
                Keyframe.ofFloat(1f, 1f)
        );

        return ObjectAnimator.ofPropertyValuesHolder(view, pvhScaleX, pvhScaleY).
                setDuration(1500);
    }


}
