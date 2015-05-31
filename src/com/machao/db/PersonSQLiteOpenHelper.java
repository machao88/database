package com.machao.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PersonSQLiteOpenHelper extends SQLiteOpenHelper {

	private static final String TAG = "PersonSQLiteOpenHelper";

	public PersonSQLiteOpenHelper(Context context) {
		super(context, "person.db", null, 3);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table person (id integer primary key autoincrement, name varchar(20), number varchar(20), account varchar(20))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
		Log.i(TAG, "数据库发生变化了。。。。");
		//db.execSQL("alter table person add  account varchar(20);");

	}

}
