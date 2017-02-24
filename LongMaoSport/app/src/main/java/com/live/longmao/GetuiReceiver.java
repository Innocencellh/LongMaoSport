package com.live.longmao;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;

import com.igexin.sdk.PushConsts;
import com.igexin.sdk.PushManager;
import com.live.longmao.views.HomeActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by HPDN on 2016/6/11.
 */
public class GetuiReceiver extends BroadcastReceiver {

    private static final String MASTERSECRET = "HjWJqw54Qy54GZW6zRHKr1";
    private Date curDate;
    private SimpleDateFormat formatter;

    private final static int NTF_CHAT_MSG = 10001;
    private static final String TAG = "GetuiReceiver";
    private static int tmpCount = 0;

   // private Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Log.d(TAG, "收到个推信息------------------------------");
        switch (bundle.getInt(PushConsts.CMD_ACTION)) {
            case PushConsts.GET_CLIENTID:
                final String cid = bundle.getString("clientid");
                BaseApp.getInstance().setClientid(cid);
                Log.i("AppAndroid", "WYC" + cid);
                break;
            case PushConsts.GET_MSG_DATA:
                // 获取透传（payload）数据
                byte[] payload = bundle.getByteArray("payload");
                Log.i("GETUI", "payload++++++" + payload);
                String taskid = bundle.getString("taskid");
                Log.i("GETUI", "taskid+++++" + taskid);
                String messageid = bundle.getString("messageid");
                Log.i("GETUI", "messageid+++++" + messageid);
                // smartPush第三方回执调用接口，actionid范围为90000-90999，可根据业务场景执行
                boolean result = PushManager.getInstance().sendFeedbackMessage(context, taskid, messageid, 90001);
                Log.d(TAG, "GetuiReceiver ----回执接口调用" + (result ? "成功" : "失败"));
                if (payload != null) {
                    String data = new String(payload);
                    BaseApp.getInstance().setData(data);
                    Log.d(TAG, "GetuiSdkDemo ----receiver payload : " + data);
                    dispatchMsg(context, data);

                    // 1 得到通知管理器
                    NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

                    // 2 设置通知的点击事件

                    Intent intent1 = new Intent(context, HomeActivity.class);
                    intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    PendingIntent contentIntent = PendingIntent.getActivity(context, 100,
                            intent1, 0);
                    // 3构建通知
                    Notification.Builder builder = new Notification.Builder(context)
                            // API 11添加的方法
                            .setContentIntent(contentIntent).setSmallIcon(R.mipmap.logo)
                                    // 设置状态栏的小标题
                            .setLargeIcon(
                                    BitmapFactory.decodeResource(context.getResources(),
                                            R.mipmap.logo))// 设置下拉列表里的图标
                            .setWhen(System.currentTimeMillis()).setTicker("龙猫运动")// 设置状态栏的显示的信息
                            .setAutoCancel(true)// 设置可以清除
                            .setContentTitle("龙猫运动") // 设置下拉列表里的标题
                                    // .setContentText("凤姐即将光临天拓游戏,各部门做好防雷准备"); // 设置可以清除
                            .setContentText(BaseApp.getInstance().getData()); // 设置可以清除
                    Notification notification = builder.build();// API 16添加创建notification的方法
                    //设置推送提示栏的震动和声音
                    notification.defaults = Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE;
                    // 通知
                    manager.notify(110, notification);
                }
                    break;
        }
    }
    private void dispatchMsg(Context ctx, String payload) {
        tmpCount++;
    }

}

