package com.example.goalsupporter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListAdapter extends ArrayAdapter<Object> {
	static final String TAG = "ListAdapter";
	private ArrayList<Object> items;
	private LayoutInflater inflater;

	public ListAdapter(Context context, int textViewResourceId, ArrayList<Object> items) {
		super(context, textViewResourceId, items);
		Log.d(TAG,"ListAdapter()");

		this.items = items;
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		Log.d(TAG,"getView()");
		// ビューを受け取る
		View view = convertView;
		if (view == null) {		// 受け取ったビューがnullなら新しくビューを生成
			view = inflater.inflate(R.layout.list, null);		// 背景画像をセットする
		}

		GoalData item = (GoalData)items.get(position);		// 表示すべきデータの取得
		if (item != null) {
			TextView topScreenName = (TextView)view.findViewById(R.id.topText);
			TextView leftScreenName = (TextView)view.findViewById(R.id.leftText);
			TextView rightScreenName = (TextView)view.findViewById(R.id.rightText);
			TextView dayStudyTime = (TextView)view.findViewById(R.id.dayStudyTimeTextView);


			leftScreenName.setTypeface(Typeface.DEFAULT_BOLD);

			if (topScreenName != null) {
				topScreenName.setText(item.getGoalName());
			}
			if (leftScreenName != null) {
				leftScreenName.setText(item.getDeadline());
			}
			if (rightScreenName != null) {
				rightScreenName.setText( item.getRemainingTime().getHour()+":"+item.getRemainingTime().getMinute()+":"+item.getRemainingTime().getSecond());
			}
			if (dayStudyTime != null) {
				dayStudyTime.setText(item.getDayStudyTime().getMinute()+":"+item.getDayStudyTime().getSecond());
			}

		}
		return view;
	}
}