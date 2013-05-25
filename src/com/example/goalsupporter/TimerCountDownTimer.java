package com.example.goalsupporter;

import android.os.CountDownTimer;
import android.util.Log;

/*
 * TimerActivity.javaのタイマー
 */
public class TimerCountDownTimer extends CountDownTimer {
	private String TAG = "TimerCountDownTimer";
	private TimerActivity act;//外部クラスからtextViewとかをいじりたいので追加

	private String time; //stop押下時の時間保持用変数
	private String[] splitTime; //stop押下時の時間保持用変数[0]にtimer、[1]にdeadline
	private boolean first = true;

	public TimerCountDownTimer(long msec, long interval, TimerActivity act) {
		super(msec, interval);
		this.act = act;
	}

	@Override
	public void onTick(long msec) {
		Log.v(TAG, "onTick()");

		/* 1秒ごとにカウントダウン */
		act.timer.setText( String.format("%02d:%02d", msec/1000/60, msec/1000%60) );

		time	= act.deadline.getText().toString();
		splitTime  	= time.split(":", 0);
		long time	= ( Integer.parseInt(splitTime[0])*60*60*10 + Integer.parseInt(splitTime[1])*60*10 + Integer.parseInt(splitTime[2])*10 );

		if(first) {
			act.deadline.setText( String.format("%d:%02d:%02d",  (time)/10/60/60, (time)/10/60%60, msec/1000%60) );
			first = !first;
		}
		else {
			act.deadline.setText( String.format("%d:%02d:%02d",  (time-1)/10/60/60, (time-1)/10/60%60, msec/1000%60) );
		}
	}

	@Override
	public void onFinish() {
		/* Timerを0に初期化　
		 * 目標までの勉強時間のカウントダウンを停止
		 *  時間の横のクルクルも停止
		 */
		Log.v(TAG, "onFinish()");
		act.timer.setText( String.format("%02d:%02d", 0, 0) );

		time	= act.deadline.getText().toString();
		splitTime  	= time.split(":", 0);
		long time	= ( Integer.parseInt(splitTime[0])*60*60*10 + Integer.parseInt(splitTime[1])*60*10 + Integer.parseInt(splitTime[2])*10 );

		act.deadline.setText( String.format("%d:%02d:%02d",  (time)/10/60/60, (time)/10/60%60, 0) );

		act.button.setText("END");
		act.button.setEnabled(false);
	}
}