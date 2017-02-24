package com.live.longmao.util;

import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;

/**
 * Created by Administrator on 2016/9/8.
 */
public class MyService extends Service {

    private VerificationTimer timer;

    public long getTiming() {
        return timing;
    }

    public void setTiming(long timing) {
        this.timing = timing;
    }

    private long timing;

    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        timer = new VerificationTimer(getTiming(), 1000);
        timer.start();

        return super.onStartCommand(intent, flags, startId);
    }

    public void onDestroy() {
        timer.cancel();
        super.onDestroy();
    }


    class VerificationTimer extends CountDownTimer {
        public VerificationTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        public void onTick(long millisUntilFinished) {
            Intent intent = new Intent();
            intent.setAction("tick_time");
            intent.putExtra("flag", "on");
            intent.putExtra("time", getTime(millisUntilFinished));
            sendBroadcast(intent);
        }

        public String getTime(long millisUntilFinished)
        {

            long minutes = millisUntilFinished/1000/60;
            long seconds = millisUntilFinished/1000%60;
            String time="";
            if(minutes<10)
            {
                time = "0"+minutes;
            }
            else
            {
                time = ""+minutes;
            }
            time += ":";
            if(seconds<10)
            {
                time+="0"+seconds;
            }
            else
            {
                time+=""+seconds;
            }
            return time;
        }

        public void onFinish() {
            Intent intent = new Intent();
            intent.setAction("tick_time");
            intent.putExtra("flag", "close");
            sendBroadcast(intent);
        }

    }
}
