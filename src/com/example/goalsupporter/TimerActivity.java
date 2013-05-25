package com.example.goalsupporter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * タイマー画面です。タイマー関連の処理も行います。
 * @author Hiromu Fujita
 */
public class TimerActivity extends Activity {
	/**Log.v用タグ*/
	private String TAG = "TimerActivity";
	/**期限、一日の勉強時間、目標の値、データを保持する。GoalListからの受け取り用*/
	private GoalData item;
	/**一日の勉強時間のミリ秒*/
	private long timeMsec;
	/**カウントダウンタイマーのインターバル(１秒)*/
	private long interval = 1000;
	/**ボタンのONとOFF用*/
	private boolean offOn;
	/**STOPボタン押下時の時間保持用(:を含んだ状態)*/
	private String time;
	/**STOPボタン押下時の時間保持用(:で配列に分ける)*/
	private String[] splitTime;
	/**インテント(GoalListから来てGoalActivityのActivityResultへ)*/
	private Intent intent;
	/**カウントダウンタイマークラス*/
	private TimerCountDownTimer tcdt;
	/**１日の勉強時間。カウントダウンタイマークラスから編集するためにpublic*/
	public TextView timer;
	/**全体の勉強時間。カウントダウンタイマークラスから編集するためにpublic*/
	public TextView deadline;
	/**ON、OFF用のボタン。カウントダウンタイマークラスから編集するためにpublic
	 * @see TimerCountDownTimer.java
	 */
	public Button button;

	/**
	 * 初期化等を行う。この初期段階でResult用のIntentを用意しておく
	 *
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timer);
		Log.v(TAG, "onCreate()");

		timer = (TextView)findViewById(R.id.timer);
		button = (Button)findViewById(R.id.button1);
		deadline = (TextView)findViewById(R.id.deadline);

		offOn = true;

		intent = getIntent();
		item = ((GoalData) intent.getSerializableExtra("GOALDATA"));
		int position = intent.getIntExtra("POSITION", -1);

		((TextView)findViewById(R.id.goalText)).setText( item.getGoalName() );


		long goalMsec	= Integer.parseInt( item.getRemainingTime() )*60*60;
		splitTime = item.getRemainingTime().split(":", 0);
		String str = "";
		for(int i = 0; i < splitTime.length; i++) {
			str = str + splitTime[i];
			str = str+":";
		}

		deadline.setText( String.format("%d:%02d:%02d",  goalMsec/60/60, goalMsec/60%60, goalMsec%60) );

		timeMsec = Integer.parseInt( item.getDayStudyTime() )*60*1000+999;
		timer.setText( String.format("%02d:%02d", timeMsec/1000/60, timeMsec/1000%60) );

		time = timer.getText().toString();
		splitTime = time.split(":", 0);

		intent = new Intent();
		intent.putExtra("POSITION", position);
	}

	/**
	 * ボタン押下時の処理。XMLファイルの方にonCliickを指定
	 *
	 */
	public void buttonClick(View view) {
		Log.v(TAG, "buttonClick()");
		if( (Integer.parseInt(splitTime[0]) != 0) || (Integer.parseInt(splitTime[1]) != 0) ) {
			if( offOn ) {
				timeMsec	= (Integer.parseInt(splitTime[0])*60*1000 + Integer.parseInt(splitTime[1])*1000 + 500);
				tcdt 			= new TimerCountDownTimer(timeMsec, interval, this);
				tcdt.start();

				button.setText("STOP");
			}
			else {
				tcdt.cancel();

				//値を返せるようにセットしておく
				item.setRemainingTime(deadline.getText().toString());
				item.setDayStudyTime(timer.getText().toString());
				intent.putExtra("GOALDATA", item);
				setResult(RESULT_OK, intent);

				/*
				 * 再スタートの為にtextviewからそれぞれのタイマー時間を抜き出しておく
				 * textviewからtimeに抜き出し、split()で分割しsplitTimeにそれぞれ保持しておく
				 */
				time			= timer.getText().toString();
				splitTime	= time.split(":", 0);

				button.setText("START");
			}
		}
		offOn = !offOn;
	}
}
