package com.example.goalsupporter;

import java.util.Calendar;
import java.util.GregorianCalendar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.util.Log;
import android.widget.DatePicker;

/**
 * 期限決定用日付入力ダイアログ
 * @author Yoshiki
 *
 */
public class CreateGoalDatePickerDialog implements OnDateSetListener {
	static final String TAG = "CreateGoalDatePickerDialog"; /**デバッグ用*/

	DatePickerDialog datePickerDialog; /**日付ピッカーダイアログ*/
	Activity activity; /**CreateGoalActivity*/
	GoalCreate goalCreate; /**GoalCreateに値をセットするため*/

	int year; /**年*/
	int monthOfYear; /**月*/
	int dayOfMonth; /**日*/

	/**
	 * データピッカーダイアログを表示する
	 * @param activity CreateGoalActivityインスタンス
	 * @param goalCreate GoalCreateインスタンス
	 */
	public void show(Activity activity,GoalCreate goalCreate) {
		this.activity = activity;
		this.goalCreate = goalCreate;
		Calendar calendar = Calendar.getInstance();
		year = calendar.get(Calendar.YEAR); // 年
		monthOfYear = calendar.get(Calendar.MONTH); // 月
		dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH); //日
		datePickerDialog = new DatePickerDialog(activity, this, year, monthOfYear, dayOfMonth);
		datePickerDialog.show();
	}

	/**
	 * 入力されたデータをセットする
	 */
	@Override
	public void onDateSet(DatePicker arg0, int year, int monthOfYear, int dayOfMonth) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onDateSet()");
		Calendar deadline = new GregorianCalendar(year, monthOfYear, dayOfMonth);
		Log.v(TAG,deadline.toString());
		goalCreate.setDeadline(deadline);
	}
}
