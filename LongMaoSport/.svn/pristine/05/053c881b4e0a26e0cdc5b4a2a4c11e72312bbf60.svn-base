package com.live.longmao.dlg;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.live.longmao.R;

/**
 * Created by Administrator on 2016/9/23.
 */
public class PromptDialog extends DialogFragment {
    private Dialog dialog;
    private TextView tv_context;

    private void initView() {
        tv_context = (TextView) dialog.findViewById(R.id.tv_context);
        tv_context.postDelayed(new Runnable() {
            @Override
            public void run() {
                dismiss();
            }
        }, 2000);
    }

    public void setTextContext(String msg) {
        tv_context.setText(msg);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = new Dialog(getActivity(), R.style.dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // must be called before set content
        dialog.setContentView(R.layout.dialog_prompt);
        dialog.setCanceledOnTouchOutside(true);

        initView();
        return dialog;
    }
}
