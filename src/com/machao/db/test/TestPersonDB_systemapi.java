package com.machao.db.test;

import java.util.List;

import com.machao.db.PersonSQLiteOpenHelper;
import com.machao.db.dao.PersonDao2;
import com.machao.db.domain.Person;

import android.test.AndroidTestCase;

public class TestPersonDB_systemapi extends AndroidTestCase {

	public void testCreateDB() throws Exception{
		PersonSQLiteOpenHelper helper = new PersonSQLiteOpenHelper(getContext());
		helper.getWritableDatabase();
	}
	public void add() {
		PersonDao2 pd = new PersonDao2(getContext());
		pd.add("lisi", "1234");
	}

	public void update()throws Exception {
		PersonDao2 pd = new PersonDao2(getContext());
		pd.update("lisi", "12345678");
	}
	public void delete()throws Exception {
		PersonDao2 pd = new PersonDao2(getContext());
		pd.delete("lisi");
	}
	public void find() throws Exception{
		PersonDao2 pd = new PersonDao2(getContext());
		boolean result = pd.find("lisi");
		assertEquals(true, result);
	}
	public void findAll() throws Exception{
		PersonDao2 pd = new PersonDao2(getContext());
		List<Person> persons = pd.findAll();
		for(Person p:persons){
			System.out.println(p.toString()+ "  "+ p.getId()+ p.getName() + p.getNumber());
		}
	}
}

