package com.bliss.android.helloworld.main;

import java.util.ArrayList;

import android.content.Context;
import android.content.SharedPreferences;

public class SaveLoadTraining {
	private Context context;
	public static final String PREFS_NAME = "ListFile";
	private ArrayList<Boolean> list;   
	private SharedPreferences mSharedPrefs;

	public SaveLoadTraining(){
	   // this.context = getApplicationContext();
	    mSharedPrefs = context.getSharedPreferences(PREFS_NAME, 0);
	}

}
