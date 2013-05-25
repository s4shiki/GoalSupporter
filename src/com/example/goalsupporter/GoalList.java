package com.example.goalsupporter;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * 目標のリスト表示を行うクラス
 * @author Yoshiki
 */
public class GoalList {
	static final String TAG = "GoalList";
	private ArrayList<Object>  list;
	private ListView listView;
	private ListAdapter adapter;
	private Activity activity;

	/**
	 * 初期設定メソッド
	 */
	protected void init(Activity activity){
		Log.d(TAG,"init()");
		activity.setContentView(R.layout.activity_goal);
		listView = (ListView)activity.findViewById(R.id.listView1);
		listView.setOnItemClickListener(new ClickEvent());

		list = new ArrayList<Object>(); // 目標表示用リスト
		list = Serializing.Load(activity);

		adapter = new ListAdapter(activity, R.layout.list, list);

		listView.setAdapter(adapter);
	}

	/**
	 * 目標リストの表示
	 */
	public void show(Activity activity) {
		Log.d(TAG,"show()");
		this.activity = activity;
		init(activity); // 初期設定メソッドの呼び出し
	}

	/**
	 * リストに表示する目標の追加
	 */
	public void add(GoalData goaldata) {
		list.add(0, goaldata);
		adapter.notifyDataSetChanged(); //リストの更新
		save(); //リストのファイル保存
	}

	public void set() {

	}

	/**
	 * 保存
	 */
	public void save() {
		Serializing.Save(activity, list);
	}

	/**
	 *  クリックイベント
	 *  タイマーの起動
	 */
	class ClickEvent implements OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
			//タイマー
			Log.d(TAG,"OnItemClickListener");
			GoalData item = (GoalData)listView.getItemAtPosition(position);
			Log.v(TAG,"GoalName:" + item.getGoalName() + "Deadline:" + item.getDeadline() + "RemainingTime：" + item.getRemainingTime());
			Intent intent = new Intent(activity, TimerActivity.class);
			intent.putExtra("GOALDATA", item);
			activity.startActivityForResult(intent, 1);
		}
	}

	/**
	 * 長押しされたときのイベント
	 * 設定画面を開く
	 */
	class LongTapEvent implements OnItemLongClickListener {
		@Override
		public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			Log.d(TAG, "OnItemLongClickListener");
			return false;
		}
	}
}
