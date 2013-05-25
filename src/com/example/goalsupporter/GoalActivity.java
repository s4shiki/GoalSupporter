package com.example.goalsupporter;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

/**
 * トップ画面用目標のアクティビティクラス
 * @author Yoshiki
 *
 */
@SuppressLint("NewApi")
public class GoalActivity extends Activity {

	static final String TAG = "GoalActivity";
	// インテントリクエストコード
	private static final int SHOSW_CALC = 0;
	private static final int SHOSW_CALC2 = 1;
	private GoalList goal_list; /**目標リスト表示用*/
	Intent goalCreateIntent; /**目標作成画面へ遷移用*/

	/**
	 * アクションバーの設定
	 * GoalListクラスの呼び出し
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(TAG,"onCreate");
		super.onCreate(savedInstanceState);

		goal_list = new GoalList(); // 目標のリストを表示
		goal_list.show(this);

		//ActionBarを取得
		final ActionBar mActionBar = getActionBar();
		//ActionBarのNavigationModeは標準に
		mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		//アイコンを表示しない
		mActionBar.setDisplayShowHomeEnabled(false);
		//タイトル表示を消す
		mActionBar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);
	}

	/**
	 * ActionBarへのメニューの追加
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		//メニューの要素を追加して取得(長押ししたときに表示されるTextをセット)
		MenuItem actionItem = menu.add("Action Button Help Icon");
		//アイコンを設定
		actionItem.setIcon(android.R.drawable.ic_input_add);
		actionItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		return true;
	}

	@Override
	/**
	 * 目標作成画面に遷移
	 */
	public boolean onOptionsItemSelected(MenuItem item) {
		goalCreateIntent = new Intent(this, CreateGoalActivity.class);
		startActivityForResult(goalCreateIntent, SHOSW_CALC);
		return true;
	}

	/**
	 * 作成画面から戻ってきた時の処理
	 * リストに作成した値を追加する
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == SHOSW_CALC) {
			if (resultCode == RESULT_OK) {
				Log.v(TAG,"onActivityResult()");
				GoalData goalData = (GoalData) data.getSerializableExtra("GOALDATA");
				goal_list.add(goalData); //リストへの表示追加
			}
		}
		if (requestCode == SHOSW_CALC2) {
			if (resultCode == RESULT_OK) {
				Log.v(TAG,"onActivityResult()");
				GoalData goalData = (GoalData) data.getSerializableExtra("GOALDATA");

				goal_list.set(goalData, data.getIntExtra("POSITION", -1)); //リストへの表示追加
			}
		}
	}
}
