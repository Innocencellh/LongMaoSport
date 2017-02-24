package com.live.longmao.service;

import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;

public class MyService extends Service {

	private MyButton mb;

	public IBinder onBind(Intent intent) {
		return null;
	}

	public int onStartCommand(Intent intent, int flags, int startId) {
		mb = new MyButton(60000, 1000);
		mb.start();
		
		return super.onStartCommand(intent, flags, startId);
	}
	
	public void onDestroy() {
		mb.cancel();
		super.onDestroy();
	}
	

	class MyButton extends CountDownTimer {
		public MyButton(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		public void onTick(long millisUntilFinished) {
			Intent intent = new Intent();
			intent.setAction("tick_time");
			intent.putExtra("flag", "on");
			intent.putExtra("time", millisUntilFinished / 1000 + "s");
			sendBroadcast(intent);
		}

		public void onFinish() {
			Intent intent = new Intent();
			intent.setAction("tick_time");
			intent.putExtra("flag", "close");
			sendBroadcast(intent);
		}

	}

}
