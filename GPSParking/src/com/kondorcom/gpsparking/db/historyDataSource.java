package com.kondorcom.gpsparking.db;



import java.net.ContentHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.kondorcom.gpsparking.model.History;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class historyDataSource {
	private static final String LOGTAG = "park";
	
	SQLiteOpenHelper dbhelper;
	SQLiteDatabase database;
	
	private static final String[] allColumns = {
		historyDBopenhelper.column_id,
		historyDBopenhelper.column_vehicle,
		historyDBopenhelper.column_registration,
		historyDBopenhelper.column_DOP,
		historyDBopenhelper.column_TOP,
		historyDBopenhelper.column_city,
		historyDBopenhelper.column_zone
	};
	
	private static final String[] vozilo_user = {
		historyDBopenhelper.column_vehicle
	};
 	
	
	
	public historyDataSource(Context context){
		dbhelper = new historyDBopenhelper(context,"payments.db"); 
	}
	
	/*
	public gradDataSource(Context context){
	dbhelper = new gradDBopenhelper(context); 
}*/
	
	public void open()
	{
		Log.e(LOGTAG, "Baza otvorena hist0ry");
		database = dbhelper.getWritableDatabase();
		Log.i(LOGTAG, "history Opening Version: " +  this.database.getVersion());
		
		
	}
	public void close()
	{
		Log.i(LOGTAG, "Database closed");
		dbhelper.close();
	}
	public History create (History hist){
	
		ContentValues values = new ContentValues();
		values.put(historyDBopenhelper.column_vehicle, hist.getVehicle());
		values.put(historyDBopenhelper.column_registration, hist.getRegistration());
		values.put(historyDBopenhelper.column_DOP, hist.getDOP());
		values.put(historyDBopenhelper.column_TOP, hist.getTOP());
		values.put(historyDBopenhelper.column_city, hist.getCity());
		values.put(historyDBopenhelper.column_zone, hist.getZone());
		
		
		Log.e(LOGTAG, "create history");
		
		long insertid = database.insert(historyDBopenhelper.table_payments, null, values);
		hist.setId(insertid);
		return hist;
	}
	public List<History> findAll2() {
		List <History> hist = new ArrayList<History>();
		Cursor cursor = database.query(historyDBopenhelper.table_payments, allColumns, null, null, null, null, 
				null);
		Log.i(LOGTAG,"Returned" + cursor.getCount() + "history");
		
		Log.i(LOGTAG, "pronadi sve");
		if (cursor.getCount() > 0) {
			 while (cursor.moveToNext()){
				 History hist1 = new History();
				 hist1.setId(cursor.getLong(cursor.getColumnIndex(historyDBopenhelper.column_id)));
				 hist1.setVehicle(cursor.getString(cursor.getColumnIndex(historyDBopenhelper.column_vehicle)));
				 hist1.setRegistration(cursor.getString(cursor.getColumnIndex(historyDBopenhelper.column_registration)));
				 hist1.setDOP(cursor.getString(cursor.getColumnIndex(historyDBopenhelper.column_DOP)));
				 hist1.setTOP(cursor.getString(cursor.getColumnIndex(historyDBopenhelper.column_TOP)));
				 hist1.setCity(cursor.getString(cursor.getColumnIndex(historyDBopenhelper.column_city)));
				 hist1.setZone(cursor.getString(cursor.getColumnIndex(historyDBopenhelper.column_zone)));
				 
				 hist.add(hist1);
			 }
			
		}
		return hist; 
	}
	public List<History> findCars(String rega) {
		List <History> hist = new ArrayList<History>();
		String whereClause = "Registration = ?";
		String[] registracija = new String[]
				{
				rega
				};
		/*Cursor cursor = database.query(historyDBopenhelper.table_payments, allColumns, whereClause, registracija, null, null, 
				null);*/
		Cursor cursor = database.query(historyDBopenhelper.table_payments, allColumns, whereClause, registracija, null, null, 
				null);
		
		
		Log.i(LOGTAG,"Returned" + cursor.getCount() + "history");
		
		Log.i(LOGTAG, "pronadi sve");
		if (cursor.getCount() > 0) {
			 while (cursor.moveToNext()){
				 History hist1 = new History();
				
				 hist1.setVehicle(cursor.getString(cursor.getColumnIndex(historyDBopenhelper.column_vehicle)));
				 hist1.setRegistration(cursor.getString(cursor.getColumnIndex(historyDBopenhelper.column_registration)));
				 hist1.setDOP(cursor.getString(cursor.getColumnIndex(historyDBopenhelper.column_DOP)));
				 hist1.setTOP(cursor.getString(cursor.getColumnIndex(historyDBopenhelper.column_TOP)));
				 hist1.setCity(cursor.getString(cursor.getColumnIndex(historyDBopenhelper.column_city)));
				 hist1.setZone(cursor.getString(cursor.getColumnIndex(historyDBopenhelper.column_zone)));
				 
				  hist.add(hist1);
				  
			 }
			
		}
		Log.d("history_payments_source", hist.toString());
		return hist; 
	}
	public List<HashMap<String, String>> findCars2(String rega) {
		//List <History> hist = new ArrayList<History>();
		String whereClause = "Registration = ?";
		String[] registracija = new String[]
				{
				rega
				};
		/*Cursor cursor = database.query(historyDBopenhelper.table_payments, allColumns, whereClause, registracija, null, null, 
				null);*/
		Cursor cursor = database.query(historyDBopenhelper.table_payments, allColumns, whereClause, registracija, null, null, 
				null);
		
		
		Log.i(LOGTAG,"Returned" + cursor.getCount() + "history");
		
		Log.i(LOGTAG, "pronadi sve");
		List<HashMap<String, String>> fillMaps = new ArrayList<HashMap<String, String>>();
		if (cursor.getCount() > 0) {
			 while (cursor.moveToNext()){
				 HashMap<String, String> map = new HashMap<String, String>();
				 
				 map.put("DOP", "" + cursor.getString(cursor.getColumnIndex(historyDBopenhelper.column_DOP)));
				 map.put("TOP", "" + cursor.getString(cursor.getColumnIndex(historyDBopenhelper.column_TOP)));
				 map.put("City", "" + cursor.getString(cursor.getColumnIndex(historyDBopenhelper.column_city)));
				 map.put("Zone", "" + cursor.getString(cursor.getColumnIndex(historyDBopenhelper.column_zone)));
				
				 fillMaps.add(map);
				 
				 
				 
			 }
			
		}
		Log.d("history_payments_source", fillMaps.toString());
		return fillMaps; 
	}
	
	

}
