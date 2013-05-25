package com.example.goalsupporter;

import java.io.Serializable;
import android.util.Log;

public class GoalData implements Serializable {
	private static final long serialVersionUID = 3829383447959491778L;
	static final String TAG = "GoalData";
	private String goalName;
	private String deadline;
	private String remainingTime;
	private String dayStudyTime;

	public String getGoalName() {
		Log.d(TAG,"getGoalName()");
		return goalName;
	}
	public String getDeadline() {
		Log.d(TAG,"getDeadline()");
        return deadline;
	}
	public String getRemainingTime() {
		Log.d(TAG,"getRemainingTime()");
        return remainingTime;
	}
	public String getDayStudyTime() {
		Log.d(TAG,"getDayStudyTime()");
        return dayStudyTime;
	}


	public void setGoalName(String goalName) {
		Log.d(TAG,"setGoalName()");
		this.goalName = goalName;
	}
	public void setDeadline(String deadline) {
		Log.d(TAG,"setDeadline()");
        this.deadline = deadline;
	}
	public void setRemainingTime(String remainingTime) {
		Log.d(TAG,"setRemainingTime()");
        this.remainingTime = remainingTime;
	}
	public void setDayStudyTime(String dayStudyTime) {
		Log.d(TAG,"setRemainingTime()");
        this.dayStudyTime = dayStudyTime;
	}
}
