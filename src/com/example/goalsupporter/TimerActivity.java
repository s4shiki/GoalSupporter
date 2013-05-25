package com.example.goalsupporter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/*
 * ここでタイマーの処理をします。
 */
public class TimerActivity extends Activity {
	private String TAG = "TimerActivity";//Log用string
	private GoalData item;  //期限、一日の勉強時間、目標の値、データを保持する。GoalListからの受け取り用。
	private long timeMsec;
	private long interval = 1000; //タイマーのインターバル　1秒
	private boolean offOn;//ボタンのON、OFF
	private String time; //stop押下時の時間保持用変数
	private String[] splitTime; //stop押下時の時間保持用変数
	private Intent intent;//GoalListとの値のやり取り用
	private TimerCountDownTimer tcdt;

	public TextView timer;
	public TextView deadline;
	public Button button;

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

		((TextView)findViewById(R.id.goalText)).setText( item.getGoalName() );

		long goalMsec	= Integer.parseInt( item.getRemainingTime() )*60*60;
		deadline.setText( String.format("%d:%02d:%02d",  goalMsec/60/60, goalMsec/60%60, goalMsec%60) );

		timeMsec = Integer.parseInt( item.getDayStudyTime() )*60*1000+999;
		timer.setText( String.format("%02d:%02d", timeMsec/1000/60, timeMsec/1000%60) );

		time = timer.getText().toString();
		splitTime = time.split(":", 0);

		intent = new Intent();
	}

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
