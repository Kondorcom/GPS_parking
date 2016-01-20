package org.kondorcom.parking.db;

import java.net.ContentHandler;
import java.util.ArrayList;
import java.util.List;

import org.kondorcom.parking.model.Korisnik;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class korisnikDataSource {
	private static final String LOGTAG = "park";
	
	SQLiteOpenHelper dbhelper;
	SQLiteDatabase database;
	
	private static final String[] allColumns = {
		korisnikDBopenhelper.column_id,
		korisnikDBopenhelper.column_vozilo,
		korisnikDBopenhelper.column_oznaka
	};
	
	
	
	public korisnikDataSource(Context context){
		dbhelper = new korisnikDBopenhelper(context,"korisnici.db"); 
	}
	
	/*
	public gradDataSource(Context context){
	dbhelper = new gradDBopenhelper(context); 
}*/
	
	public void open()
	{
		Log.e(LOGTAG, "Baza otvorena korisnik");
		database = dbhelper.getWritableDatabase();
		Log.i(LOGTAG, "DbHelper Opening Version: " +  this.database.getVersion());
		
		
	}
	public void close()
	{
		Log.i(LOGTAG, "Database closed");
		dbhelper.close();
	}
	public Korisnik create (Korisnik korisnik){
	
		ContentValues values = new ContentValues();
		values.put(korisnikDBopenhelper.column_vozilo, korisnik.getVozilo());
		
		values.put(korisnikDBopenhelper.column_oznaka, korisnik.getOznaka());
		
		long insertid = database.insert(korisnikDBopenhelper.table_grad, null, values);
		korisnik.setId(insertid);
		return korisnik;
	}
	public List<Korisnik> findAll2() {
		List <Korisnik> Korisnici = new ArrayList<Korisnik>();
		Cursor cursor = database.query(korisnikDBopenhelper.table_grad, allColumns, null, null, null, null, 
				null);
		Log.i(LOGTAG,"Returned" + cursor.getCount() + "korisnika");
		//Log.i(LOGTAG, "pronadi sve");
		if (cursor.getCount() > 0) {
			 while (cursor.moveToNext()){
				 Korisnik korisnik = new Korisnik();
				 korisnik.setId(cursor.getLong(cursor.getColumnIndex(korisnikDBopenhelper.column_id)));
				 korisnik.setVozilo(cursor.getString(cursor.getColumnIndex(korisnikDBopenhelper.column_vozilo)));
				 korisnik.setOznaka(cursor.getString(cursor.getColumnIndex(korisnikDBopenhelper.column_oznaka)));
				 
				 
				 Korisnici.add(korisnik);
			 }
			
		}
		return Korisnici; 
	}
	

}
