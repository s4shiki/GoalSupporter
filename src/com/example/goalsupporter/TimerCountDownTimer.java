package com.example.goalsupporter;

import android.os.CountDownTimer;
import android.util.Log;

/**
 * TimerActivity.javaのカウントダウンタイマー
 * @author Fujita
 */
public class TimerCountDownTimer extends CountDownTimer {
	/** Log.v用のタグ */
	private String TAG = "TimerCountDownTimer";
	/**外部クラスからコンポーネントを編集するため*/
	private TimerActivity act;
	/** stop押下時の時間保持用変数 */
	private String time;
	/**stop押下時の時間保持用変数*/
	private String[] splitTime;
	/**タイマースタート時の時刻合わせ用*/
	private boolean first = true;

	public TimerCountDownTimer(long msec, long interval, TimerActivity act) {
		super(msec, interval);
		this.act = act;
	}

	/**
	 * 引数に指定したミリ秒ごとに呼び出され、カウントダウンを行う
	 * @param msec
	 */
	@Override
	public void onTick(long msec) {
		Log.v(TAG, "onTick()");
		/* 1秒ごとにカウントダウン */
		act.timer.setText( String.format("%02d:%02d", msec/1000/60, msec/1000%60) );

		time	= act.deadline.getText().toString();
		splitTime  	= time.split(":", 0);
		long time	= ( Integer.parseInt(splitTime[0])*60*60 + Integer.parseInt(splitTime[1])*60 + Integer.parseInt(splitTime[2]) );

		if(first) {
			act.deadline.setText( String.format("%d:%02d:%02d",  (time)/60/60, (time)/60%60, msec/1000%60) );
			first = !first;
		}
		else {
			act.deadline.setText( String.format("%d:%02d:%02d",  (time-1)/60/60, (time-1)/60%60, msec/1000%60) );
		}
	}

	/**
	 * カウントダウン終了時に呼び出される。
	 * カウントダウンを停止させ、timerを0にする
	 */
	@Override
	public void onFinish() {
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