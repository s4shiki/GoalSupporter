package com.example.goalsupporter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.util.Log;

public class Serializing {
	static final String TAG = "Serializing";
	static int Save(Context context, List<Object> list){
		Log.d(TAG,"Save()");
		 try {
			 FileOutputStream outFile = context.openFileOutput("data.txt", 0);
			 ObjectOutputStream outObject = new ObjectOutputStream(outFile);
			 outObject.writeObject(list);
			 outObject.close();
			 outFile.close();
		 }
		 catch (Exception e) {
		 }

		return 0;
	}

	@SuppressWarnings("unchecked")
	static ArrayList<Object> Load(Context context){
		Log.d(TAG,"Load()");
		ArrayList<Object> list = new ArrayList<Object>();

		try {
			FileInputStream inFile = context.openFileInput("data.txt");
			ObjectInputStream inObject = new ObjectInputStream(inFile);
			list = (ArrayList<Object>)inObject.readObject();
			inObject.close();
			inFile.close();
		}
		catch (Exception e) {
		}

		return list;
	}
}
