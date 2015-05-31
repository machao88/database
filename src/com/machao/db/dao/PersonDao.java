package com.machao.db.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.machao.db.PersonSQLiteOpenHelper;
import com.machao.db.domain.Person;

public class PersonDao {
	private PersonSQLiteOpenHelper helper;
	


	public  PersonDao(Context context){
		helper = new PersonSQLiteOpenHelper(context);
	}

	public void add(String name, String number, int money){
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL("insert into person (name, number, account) values (?, ?, ?)", new Object[]{name, number, money});
		db.close();
	}
	public void update(String name, String newnumber){
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL("update person set number=? where name = ?;", new Object[]{newnumber, name});
		db.close();
	}
	public void delete(String name){
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL("delete from person where name = ?", new Object[]{name});
		db.close();
	}
	public boolean find(String name){
		SQLiteDatabase db = helper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from person where name =?;", new String[]{name});
		boolean b = cursor.moveToNext();
		cursor.close();
		db.close();
		return b;
	}
	public List<Person> findAll(){
		List<Person> persons = new ArrayList<Person>();
		SQLiteDatabase db = helper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from person;", null);
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
