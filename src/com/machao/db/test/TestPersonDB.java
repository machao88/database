package com.machao.db.test;

import java.util.List;

import com.machao.db.PersonSQLiteOpenHelper;
import com.machao.db.dao.PersonDao;
import com.machao.db.domain.Person;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

public class TestPersonDB extends AndroidTestCase {

	public void testCreateDB() throws Exception{
		PersonSQLiteOpenHelper helper = new PersonSQLiteOpenHelper(getContext());
		helper.getWritableDatabase();
	}
	public void add() {
		PersonDao pd = new PersonDao(getContext());
		pd.add("wangwu", "1234", 5000);
		pd.add("zhangsan", "1424", 5000);
	}

	public void update()throws Exception {
		PersonDao pd = new PersonDao(getContext());
		pd.update("lisi", "12345678");
	}
	public void delete()throws Exception {
		PersonDao pd = new PersonDao(getContext());
		pd.delete("lisi");
	}
	public void find() throws Exception{
		PersonDao pd = new PersonDao(getContext());
		boolean result = pd.find("lisi");
		assertEquals(true, result);
	}
	public void findAll() throws Exception{
		PersonDao pd = new PersonDao(getContext());
		List<Person> persons = pd.findAll();
		for(Person p:persons){
			System.out.println(p.toString()+ "  "+ p.getId()+ p.getName() + p.getNumber());
		}
	}
	
	public void testTransaction()throws Exception{
		
		PersonSQLiteOpenHelper helper = new PersonSQLiteOpenHelper(getContext());
		SQLiteDatabase db = helper.getWritableDatabase();
		String s = null;
		//开始数据库的事务。
		db.beginTransaction();
		try{
			db.execSQL("update  person set account = account - 1000 where name=?", new Object[]{"zhangsan"});
			//s.equals("asdf");
			db.execSQL("update  person set account = account + 1000 where name=?", new Object[]{"wangwu"});
			//标记数据事务执行成功，否则，会回滚。如果执行完上面第一条，断电了，那么下面的都会回滚。
			db.setTransactionSuccessful();
		}catch(Exception e){
			
		}
		finally{
			db.endTransaction();
			db.close();
		}
	}
}

