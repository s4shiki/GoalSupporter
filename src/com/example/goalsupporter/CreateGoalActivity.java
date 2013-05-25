package com.example.goalsupporter;

import android.app.Activity;
import android.os.Bundle;

public class CreateGoalActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		new GoalCreate().show(this);
	}

}
