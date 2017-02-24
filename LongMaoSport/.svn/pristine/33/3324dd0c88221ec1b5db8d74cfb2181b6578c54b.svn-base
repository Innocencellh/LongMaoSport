package com.live.longmao.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;

/**
 * Created by Administrator on 2016/8/24.
 */
public class EditTextPreimeView extends EditText {

    private OnClickBackListener mOnClickBackListener;
    public EditTextPreimeView(Context context) {
        super(context);
    }

    public EditTextPreimeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EditTextPreimeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchKeyEventPreIme(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            //when the softinput display
            //处理事件
            mOnClickBackListener.onClickBack();
        }
        return super.dispatchKeyEventPreIme(event);
    }
    public EditTextPreimeView setClickBack(OnClickBackListener onClickBackListener) {
        mOnClickBackListener = onClickBackListener;
        return this;
    }

    public interface OnClickBackListener {
        void onClickBack();
    }
}
