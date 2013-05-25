package com.example.goalsupporter;

import java.io.Serializable;
<<<<<<< HEAD
import java.util.Date;
=======

>>>>>>> origin/Fujita
import android.util.Log;

/**
 * 目標保持クラス
 * @author Yoshiki
 *
 */
public class GoalData implements Serializable {
	private static final long serialVersionUID = 3829383447959491778L;
	static final String TAG = "GoalData";
	private String goalName; /**目標名*/
	private String deadline; /**達成期限*/
	private TimeData remainingTime; /**目標学習時間*/
	private String dayStudyTime; /**一日の学習時間*/

	/**
	 * 目標名を得る
	 * @return 目標名
	 */
	public String getGoalName() {
		Log.d(TAG,"getGoalName()");
		return goalName;
	}
	/**
	 * 期限を得る
	 * @return 期限
	 */
	public String getDeadline() {
		Log.d(TAG,"getDeadline()");
        return deadline;
	}
	/**
	 * 目標学習時間を得る
	 * @return 目標学習時間
	 */
	public TimeData getRemainingTime() {
		Log.d(TAG,"getRemainingTime()");
        return remainingTime;
	}
	/**
	 * 一日の学習時間を得る
	 * @return 一日の学習時間
	 */
	public String getDayStudyTime() {
		Log.d(TAG,"getDayStudyTime()");
        return dayStudyTime;
	}

	/**
	 * 目標名を設定する
	 * @param goalName　目標名
	 */
	public void setGoalName(String goalName) {
		Log.d(TAG,"setGoalName()");
		this.goalName = goalName;
	}
	/**
	 * 期限を設定する
	 * @param goalName　期限
	 */
	public void setDeadline(String deadline) {
		Log.d(TAG,"setDeadline()");
        this.deadline = deadline;
	}
	/**
	 * 目標学習時間を設定する
	 * @param remainingTime 目標学習時間
	 */
	public void setRemainingTime(int remainingTime) {
		Log.d(TAG,"setRemainingTime()");
        this.remainingTime.setHour(remainingTime);
	}
	
	/**
	 * 一日の学習時間
	 * @param dayStudyTime
	 */
	public void setDayStudyTime(String dayStudyTime) {
		Log.d(TAG,"setRemainingTime()");
        this.dayStudyTime = dayStudyTime;
	}
}
