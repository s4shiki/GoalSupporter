package com.example.goalsupporter;

import android.util.Log;

/**
 * 時間の情報を保持するクラス
 * @author Hiromu Fujita
 */
public class TimeData {
	/**Log.v用タグ*/
	private static final String TAG = "TimeData";
	private String hour;
	private String minute;
	private String second;

	public TimeData(String hour, String minute, String second) {
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}

	public TimeData(int hour, int minute, int second) {
		this.hour = String.valueOf(hour);
		this.minute = String.valueOf(minute);
		this.second = String.valueOf(second);
	}

	/**
	 * 時間取得
	 * @return hour
	 */
	public String getHour() {
		Log.v(TAG, "getHour");
		return hour;
	}

	/**
	 * 分取得
	 * @return minute
	 */
	public String getMinute() {
		Log.v(TAG, "getMinute");
		return minute;
	}

	/**
	 * 秒取得
	 * @return second
	 */
	public String getSecond() {
		Log.v(TAG, "getSecond");
		return second;
	}

	/**
	 * 時間セット
	 * @param hour
	 */
	public void setHour(String hour) {
		Log.v(TAG, "setHour");
		this.hour = hour;
	}

	/**
	 * 分セット
	 * @param minute
	 */
	public void setMinute(String minute) {
		Log.v(TAG, "getHour");
		this.minute = minute;
	}

	/**
	 * 秒セット
	 * @param second
	 */
	public void setSecond(String second) {
		Log.v(TAG, "setSecond");
		this.second = second;
	}
	
	/**
	 * 時間セット
	 * @param hour
	 */
	public void setHour(int hour) {
		Log.v(TAG, "setHour");
		this.hour = String.valueOf(hour);
	}

	/**
	 * 分セット
	 * @param minute
	 */
	public void setMinute(int minute) {
		Log.v(TAG, "getHour");
		this.minute = String.valueOf(minute);
	}

	/**
	 * 秒セット
	 * @param second
	 */
	public void setSecond(int second) {
		Log.v(TAG, "setSecond");
		this.second = String.valueOf(second);
	}
}
