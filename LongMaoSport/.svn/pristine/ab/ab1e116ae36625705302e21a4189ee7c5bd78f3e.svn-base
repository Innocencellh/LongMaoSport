package com.live.longmao.views;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.igexin.sdk.PushManager;
import com.live.longmao.R;
import com.live.longmao.util.ChangeHeight;
import com.live.longmao.util.Constants;
import com.live.longmao.util.ScreenManager;

/**
 * activity父类
 */
public class BaseActivity extends Activity implements View.OnClickListener{

    private ImageButton iv_back, iv_add;
    private TextView tv_title, tv_add,line;
    private LayoutInflater mInflater;
    private FrameLayout fl;
    private RelativeLayout activity_base_title_rl;
    private BroadcastReceiver recv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        ScreenManager.getScreenManager().pushActivity(this);
        setContentView(R.layout.activity_base);
        PushManager.getInstance().initialize(this.getApplicationContext());

        recv = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(Constants.BD_EXIT_APP)){
                    finish();
                }
            }
        };

        IntentFilter filter = new IntentFilter();
        filter.addAction(Constants.BD_EXIT_APP);

        registerReceiver(recv, filter);
        mInflater = LayoutInflater.from(this);
        initView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ChangeHeight.changeRH(this, activity_base_title_rl);
        }
    }
    private void initView() {
        activity_base_title_rl = (RelativeLayout) findViewById(R.id.activity_base_title_rl);
        fl = (FrameLayout) findViewById(R.id.activity_base_fl);
        iv_back = (ImageButton) findViewById(R.id.iv_back);
        iv_add = (ImageButton) findViewById(R.id.iv_add);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_add = (TextView) findViewById(R.id.tv_add);
        line = (TextView) findViewById(R.id.line);
        iv_back.setOnClickListener(this);
        iv_add.setOnClickListener(this);
        tv_add.setOnClickListener(this);
    }

    protected void setTitleGone() {
        tv_title.setVisibility(View.GONE);
    }
    protected void setLeftGone() {
        iv_back.setVisibility(View.GONE);
    }
    protected void setLineGone() {
        line.setVisibility(View.GONE);
    }
    protected void setRigthClick(View v) {

    }


    protected void setTitle(String title) {
        if (title == null)
            return;
        tv_title.setText(title);
    }

    protected void setView(View view) {
        if (view == null)
            return;
        fl.addView(view);
    }

    protected void setRlBg(int color)
    {
        activity_base_title_rl.setBackgroundColor(color);
    }

    protected void setRigthImg(int img) {
        iv_add.setVisibility(View.VISIBLE);
        iv_add.setImageResource(img);
    }

    protected void setRigthText(String text) {
        tv_add.setVisibility(View.VISIBLE);
        tv_add.setText(text);
    }

    protected void setView(int id) {
        fl.addView(mInflater.inflate(id, null));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                this.finish();
            //    overridePendingTransition(R.anim.zoom , R.anim.zoomout);
                break;
            case R.id.tv_add:
                setRigthClick(v);
                break;
            case R.id.iv_add:
                setRigthClick(v);
                break;
            default:
                break;
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            this.finish();
          //  overridePendingTransition(R.anim.zoom , R.anim.zoomout);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        try {
            unregisterReceiver(recv);
        }catch (Exception e){
        }
        super.onDestroy();
    }
}
