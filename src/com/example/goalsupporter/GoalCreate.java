package com.example.goalsupporter;

import java.util.Calendar;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * 目標作成画面
 * @author Yoshiki
 *
 */
public class GoalCreate implements OnClickListener {
	static final String TAG = "GoalCreate";
	private Button cancelButton; /**キャンセルボタン*/
	private Button okButton; /**作成ボタン*/
	private Button deadlineButton; /**締め切り設定用ボタン*/
	private CreateGoalDatePickerDialog test; /**データピッカーダイアログ*/
	private int dayStudyTime; /**一日に必要な学習時間*/

	private EditText goalName; /**目標名*/
	private Calendar deadline; /**期限*/
	private int studyTime_hour;/**勉強時間*/
	private int studyTime_minutes; /**残り時間を分で表したもの*/

	private int dayCount; /**期間の日数*/

	private TextView deadlineDisplay; /**目標達成予定日*/

	private Activity activity; /**CreateGoalActivity*/
	private Intent intent;

	protected void init() {
		Log.d(TAG, "init()");
		activity.setContentView(R.layout.activity_create_goal); // レイアウト

		goalName = (EditText)activity.findViewById(R.id.goalname_editText);

		cancelButton = (Button)activity.findViewById(R.id.cancel_button);
		okButton = (Button)activity.findViewById(R.id.ok_button);
		deadlineButton = (Button)activity.findViewById(R.id.deadline_button);
		cancelButton.setOnClickListener(this);
		okButton.setOnClickListener(this);
		deadlineButton.setOnClickListener(this);
	}

	/**
	 * 目標達成に必要な一日の勉強時間を計算する
	 */
	protected void calcDayStudyTime() {
		// 期間を分に変換する
		//allStudyTime /
		EditText studytime = (EditText) activity.findViewById(R.id.allStudyTime_editText);

		studyTime_hour = Integer.parseInt(studytime.getText().toString());
		studyTime_minutes = studyTime_hour * 60;
		dayStudyTime = studyTime_minutes / dayCount;
		Log.v(TAG, "dayStudyTime:" + dayStudyTime);
	}

	protected void setViewStudyTime() {
		TextView studyTimeTextView = (TextView) activity.findViewById(R.id.today_studytime_textView);
		studyTimeTextView.setText(Integer.toString(dayStudyTime));
	}

	/**
	 * 期間の日数を求める
	 */
	protected void calcPeriod() {
		Calendar today = Calendar.getInstance();
		today = new GregorianCalendar(today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH));
		//year = calendar.get(Calendar.YEAR); // 年
		//monthOfYear = calendar.get(Calendar.MONTH); // 月
		//dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH); //日
		dayCount = (int) ((deadline.getTimeInMillis() - today.getTimeInMillis())/(24*3600*1000)) + 1;
		Log.v(TAG, "dayCount:" + Integer.toString(dayCount));
	}

	/**
	 * 入力された値を書き込む
	 * @param activity
	 */
	public void add() {
		GoalData goalData = new GoalData(); // 目標名,期限,残り時間
		goalData.setGoalName(goalName.getText().toString());
		goalData.setDeadline(deadline.get(Calendar.YEAR) + "/" + (deadline.get(Calendar.MONTH) +1) + "/" + deadline.get(Calendar.DATE));
		goalData.setRemainingTime(studyTime_hour +"");
		goalData.setDayStudyTime(Integer.toString(dayStudyTime));

		intent.putExtra("GOALDATA", goalData);
		//intent.putExtra("GOAL_NAME", goalName.getText().toString());
		//intent.putExtra("DEADLINE", deadline.get(Calendar.YEAR) + "/" + (deadline.get(Calendar.MONTH) +1) + "/" + deadline.get(Calendar.DATE));
		//intent.putExtra("REMAINING_TIME", Integer.toString(studyTime_hour));

		//intent.putExtra("GOAL_NAME", "test1");
		//intent.putExtra("DEADLINE", "test2");
		//intent.putExtra("REMAINING_TIME", "test3");
	}

	public void show(Activity activity) {
		Log.d(TAG, "show()");
		this.activity = activity;
		init();
	}

	/**
	 * 目標名の取得
	public String getGoalName() {
		return goalName;
	}
	*/

	/**
	 * 期限の取得
	public String getDeadline() {
		return deadline;
	}
	 */

	/**
	 * 作成ボタンもしくはキャンセルボタンのイベント
	 */
	@Override
	public void onClick(View arg0) {
		switch(arg0.getId()) {
		case R.id.cancel_button:
			Log.d(TAG, "CANCELButton is Click");
			activity.finish();
			break;
		case R.id.ok_button:
			Log.d(TAG, "OKButton is Click");
			intent = activity.getIntent(); //インテントを受け取る
			add(); // GoalDataインスタンスを入れる
			activity.setResult(Activity.RESULT_OK, intent);
			activity.finish();
			break;
		case R.id.deadline_button:
			Log.d(TAG, "DeadlineSettingButton is Click");
			test = new CreateGoalDatePickerDialog();
			test.show(activity, this);
			//new CreateGoalDatePickerDialog().show(activity);

		}
	}
	/**
	 * ダイアログから入力された終了日時をセットする
	 */
	public void setDeadline(Calendar deadline) {
		Log.d(TAG, "onDeadline()");
		this.deadline = deadline;
		setViewDeadline(); //ダイアログの値をテキストビューにセットする
		calcPeriod(); //期間の日数を求める
		//if (トータルの勉強時間が入力されている場合) {
		calcDayStudyTime(); //一日の勉強時間を求める
		//}
		setViewStudyTime();//一日の勉強時間を表示する
	}

	/**
	 * ダイアログの値をテキストビューにセットする
	 */
	private void setViewDeadline() {
		Log.d(TAG, "setViewDeadline()");
		deadlineDisplay = (TextView) activity.findViewById(R.id.deadline_textView);
		deadlineDisplay.setText(deadline.get(Calendar.YEAR) + "/" + (deadline.get(Calendar.MONTH) +1) + "/" + deadline.get(Calendar.DATE));
	}

}
