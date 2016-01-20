package org.kondorcom.parking.db;

import java.net.ContentHandler;
import java.util.ArrayList;
import java.util.List;

import org.kondorcom.parking.DatabaseHandler;
import org.kondorcom.parking.model.Grad;
import org.kondorcom.parking.model.Zona;

import android.R.integer;
import android.R.string;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.SyncStateContract.Helpers;
import android.util.Log;

public class gradDataSource {
	private static final String LOGTAG = "park";
	//DatabaseHandler dbHandler;
	SQLiteOpenHelper dbhelper;
	SQLiteDatabase database;
	 
	
	private static final String[] allColumns = {
		gradDBopenhelper.column_id,
		gradDBopenhelper.column_ime
	};
	
	private static final String[] allColumns_zona = {
		gradDBopenhelper.column_id3,
		gradDBopenhelper.column_zona,
		gradDBopenhelper.column_ID_grada,
		gradDBopenhelper.column_broj
	};
	private static final String[] Broj_telefona = {
		gradDBopenhelper.column_broj
	};
			
	public gradDataSource(Context context){
		dbhelper = new gradDBopenhelper(context,"parking.db"); 
	}
	
	
	
	public void open()
	{
		Log.e(LOGTAG, "Baza otvorena grad zasto ovo ide prvo");
		database = dbhelper.getWritableDatabase();
		
	}
	public void close()
	{
		Log.i(LOGTAG, "Database closed");
		dbhelper.close();
	}
	public Grad create (Grad grad){
		ContentValues values = new ContentValues();
		values.put(gradDBopenhelper.column_ime, grad.getImeGrada());
		long insertid = database.insert(gradDBopenhelper.table_grad, null, values);
		grad.setId(insertid);
		return grad;
	}
	public Zona create (Zona zona){
		ContentValues values = new ContentValues();
		values.put(gradDBopenhelper.column_zona, zona.getZona());
		values.put(gradDBopenhelper.column_ID_grada, zona.getZona());
		values.put(gradDBopenhelper.column_broj, zona.getZona());
		long insertid = database.insert(gradDBopenhelper.table_zona, null, values);
		zona.setId(insertid);
		return zona;
	}
	
	/*public void Broj_slanje(int poz_broja){ //mozda nece ni trebat 
		//List <Zona> zone = new ArrayList<Zona>();
		String whereClause = "_id = ? ";
		
		String poz = String.valueOf(poz_broja);					//pozicija grad
		
		String[] whereArgs = new String[] {
		    poz
		    		}; // odaberem iz zona onaj gdje je id poz
		Log.i(LOGTAG,poz + "	" + whereClause +"		" + whereArgs);
		
		
		Cursor cursor2 = database.query(gradDBopenhelper.table_zona, Broj_telefona, whereClause, whereArgs,
		        null, null, null);
		
		/*cursor2.moveToPosition(poz_broja);
		
		 int data = cursor2.getInt(cursor2.getColumnIndex("Zona"));
		 Log.i(LOGTAG,"BROJ ZA SLANJE" + "		"+data);*/
		 
		/*if (cursor2.moveToFirst()){
		   while(!cursor2.isAfterLast()){
		      String data = cursor2.getString(cursor2.getColumnIndex("broj"));
		      // do what ever you want here
		      cursor2.moveToNext();
		      Log.i(LOGTAG,"BROJ ZA SLANJE" + data);
		   }
		}*/
		//cursor2.close();
		
		
		
	//}
	public List<Zona> findAll_zone(int pozicija) {
		List <Zona> zone = new ArrayList<Zona>();
		String whereClause = "ID_grada = ? ";
		String poz = String.valueOf(pozicija);					//pozicija grad
		
		String[] whereArgs = new String[] {
		    poz
		    		}; // odaberem iz zona onaj gdje je id poz
		
		Cursor cursor = database.query(gradDBopenhelper.table_zona, allColumns_zona, whereClause, whereArgs,
		        null, null, null);
		Log.i(LOGTAG,"Returned" + cursor.getCount() + "rows" + " " + "zone");
								
		Log.i(LOGTAG, "pronadi sve");
		if (cursor.getCount() > 0) {
			 while (cursor.moveToNext()){
				 Zona zona = new Zona();
				 zona.setId(cursor.getLong(cursor.getColumnIndex(gradDBopenhelper.column_id)));
				 zona.setZona(cursor.getString(cursor.getColumnIndex(gradDBopenhelper.column_zona)));
				 zona.setIDgrada(cursor.getString(cursor.getColumnIndex(gradDBopenhelper.column_ID_grada)));
				 zona.setBroj(cursor.getString(cursor.getColumnIndex(gradDBopenhelper.column_broj)));
				 zone.add(zona);
				 				 			 }
					}
		return zone; 
	}
	
	public List<Grad> findAll() {
		List <Grad> gradovi = new ArrayList<Grad>();
		Cursor cursor = database.query(gradDBopenhelper.table_grad, allColumns, null, null, null, null, 
				null);
		Log.i(LOGTAG,"Returned" + cursor.getCount() + "rows" + " " + "gradovi");
		Log.i(LOGTAG, "pronadi sve");
		if (cursor.getCount() > 0) {
			 while (cursor.moveToNext()){
				 Grad grad = new Grad();
				 grad.setId(cursor.getLong(cursor.getColumnIndex(gradDBopenhelper.column_id)));
				 grad.setImeGrada(cursor.getString(cursor.getColumnIndex(gradDBopenhelper.column_ime)));
				 gradovi.add(grad);
				 
				 
			 }
			
		}
		return gradovi; 
	}
	

}
