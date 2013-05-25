package com.example.goalsupporter;

import android.app.Activity;
import android.os.Bundle;

/**
 * 目標作成画面のアクティビティ
 * @author Yoshiki
 *
 */
public class CreateGoalActivity extends Activity {

	/**
	 * GoalCreateクラスによる目標作成画面の生成
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		new GoalCreate().show(this);
	}

}
