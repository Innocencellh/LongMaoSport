package com.live.longmao.dlg.logindig;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.live.longmao.R;

/**
 * Created by HPDN on 2017/1/3.
 */
public class RegisterDialog extends DialogFragment implements View.OnClickListener {

    private Dialog dialog;
    private RelativeLayout login_dialog;
    private TextView tv_tittle;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        dialog = new Dialog(getActivity(), R.style.dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // must be called before set content
        dialog.setContentView(R.layout.login_dialog);
        dialog.setCanceledOnTouchOutside(true);

        // 设置宽度为屏宽、靠近屏幕底部。
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);

        init();

        return dialog;
    }

    public void setTextTittle(String tittle) {
        tv_tittle.setText(tittle);
    }

    private void init() {
        login_dialog = (RelativeLayout) dialog.findViewById(R.id.login_dialog);
        tv_tittle = (TextView) dialog.findViewById(R.id.tv_tittle);
        login_dialog.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_dialog:
                dismiss();
                break;
        }
    }
}
