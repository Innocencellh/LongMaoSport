package com.live.longmao.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.live.longmao.R;
import com.live.longmao.dlg.AlertDialog;
import com.live.longmao.model.MySelfInfo;

/**
 * Created by HPDN on 2016/9/13.
 */
public class DlgTellUtil {
    public static void startDlgCall(final Context mContext, final String phone)
    {
        AlertDialog backDlg = new AlertDialog(mContext).builder();
        backDlg.setTitle("拨打:"+phone+"?");
        backDlg.setPositiveButton("确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phone));
                mContext.startActivity(intent);
            }
        });
        backDlg.setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        backDlg.show();
    }
}
