package com.example.goalsupporter;

import com.example.goalsupporter.GoalList.LongTapEvent;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.ListView;

public class DeleteEditDialog implements OnClickListener {
	
	/**
	 * 長押し時の削除編集ダイアログの表示
	 */
	public void showDEDialog(Activity activity) {
		deDialog = (ListView)activity.findViewById(R.id.listView1);
		deDialog.setOnItemLongClickListener(new onClick());
		MyDialogListener listener = new MyDialogListener();
        Builder myDialogBuilder = new Builder(activity);

        myDialogBuilder.setTitle("アラート")
        .setItems(myList, listener)
        .setCancelable(false);

        activity.AlertDialog myAlertDialog = myDialogBuilder.create();
        myAlertDialog.show();
	}

	@Override
	public void onClick(DialogInterface arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}
}
