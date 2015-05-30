package com.machao.db.test;

import com.machao.db.PersonSQLiteOpenHelper;

import android.test.AndroidTestCase;

public class TestPersonDB extends AndroidTestCase {

	public void testCreateDB() throws Exception{
		PersonSQLiteOpenHelper helper = new PersonSQLiteOpenHelper(getContext());
		helper.getWritableDatabase();
	}
}
