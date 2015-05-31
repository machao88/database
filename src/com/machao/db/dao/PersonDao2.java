package com.machao.db.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.machao.db.PersonSQLiteOpenHelper;
import com.machao.db.domain.Person;

public class PersonDao2 {
	private PersonSQLiteOpenHelper helper;
	


	public  PersonDao2(Context context){
		helper = new PersonSQLiteOpenHelper(context);
	}

	public long add(String name, String number){
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("name", name);
		values.put("number", number);
		long id = db.insert("person", null, values);
		db.close();
		return id;
		
	}
	
	public int update(String name, String newnumber){
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("number", newnumber);
		int result = db.update("person", values, "name=?", new String[]{name});
		db.close();
		return result;
	}
	public int delete(String name){
		SQLiteDatabase db = helper.getWritableDatabase();
		int result = db.delete("person", "name=?", new String[]{name});
		db.close();
		return result;	
	}
	public boolean find(String name){
		SQLiteDatabase db = helper.getWritableDatabase();
		Cursor cursor = db.query("person", null, "name=?", new String[]{ name}, null, null, null);
		boolean b = cursor.moveToNext();
		cursor.close();
		db.close();
		return b;
	}
	public List<Person> findAll(){
		List<Person> persons = new ArrayList<Person>();
		SQLiteDatabase db = helper.getWritableDatabase();
		Cursor cursor = db.query("person", new String[]{"id","name","number"}, null, null, null, null, null);
		while(cursor.moveToNext()){
			int id = cursor.getInt(cursor.getColumnIndex("id"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String number = cursor.getString(cursor.getColumnIndex("number"));
			Person person = new Person(id, name, number);
			persons.add(person);
		}
		cursor.close();
		db.close();
		return persons;
	}
}
