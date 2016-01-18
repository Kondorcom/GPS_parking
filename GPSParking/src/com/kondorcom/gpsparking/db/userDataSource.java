package com.kondorcom.gpsparking.db;



import java.net.ContentHandler;
import java.util.ArrayList;
import java.util.List;

import com.kondorcom.gpsparking.model.User;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class userDataSource {
	private static final String LOGTAG = "park_user";
	
	SQLiteOpenHelper dbhelper;
	SQLiteDatabase database;
	
	private static final String[] allColumns = {
		userDBopenhelper.column_id,
		userDBopenhelper.column_vehicle,
		userDBopenhelper.column_registration
		
	};
	
	
	
	public userDataSource(Context context){
		dbhelper = new userDBopenhelper(context,"users.db"); 
	}
	
	/*
	public gradDataSource(Context context){
	dbhelper = new gradDBopenhelper(context); 
}*/
	
	public void open()
	{
		Log.e(LOGTAG, "Baza otvorena user___");
		database = dbhelper.getWritableDatabase();
		Log.i(LOGTAG, "DbHelper Opening Version: " +  this.database.getVersion());
		
		
	}
	public void close()
	{
		Log.i(LOGTAG, "Database closed");
		dbhelper.close();
	}
	public User create (User user){
	
		ContentValues values = new ContentValues();
		values.put(userDBopenhelper.column_vehicle, user.getVehicle());
		
		values.put(userDBopenhelper.column_registration, user.getRegistration());
		
		Log.e(LOGTAG, "create user");
		
		long insertid = database.insert(userDBopenhelper.table_user, null, values);
		user.setId(insertid);
		return user;
	}
	public List<User> findAll2() {
		List <User> Users = new ArrayList<User>();
		Cursor cursor = database.query(userDBopenhelper.table_user, allColumns, null, null, null, null, 
				null);
		Log.i(LOGTAG,"Returned" + cursor.getCount() + "users");
		
		//Log.i(LOGTAG, "pronadi sve");
		if (cursor.getCount() > 0) {
			 while (cursor.moveToNext()){
				 User user = new User();
				 user.setId(cursor.getLong(cursor.getColumnIndex(userDBopenhelper.column_id)));
				 user.setVehicle(cursor.getString(cursor.getColumnIndex(userDBopenhelper.column_vehicle)));
				 user.setRegistration(cursor.getString(cursor.getColumnIndex(userDBopenhelper.column_registration)));
				 
				 
				 Users.add(user);
			 }
			
		}
		return Users; 
	}
	

}
