package org.kondorcom.parking.db;

import java.io.File;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class korisnikDBopenhelper extends SQLiteOpenHelper {

	private static final String LOGTAG = "park";
	private static final String DATABASE_NAME = "korisnici.db";
	private static final int DATABASE_VERSION = 1;
	
	public static final String table_grad = "korisnik";
	public static final String column_id="_id";
	public static final String column_vozilo = "Vozilo"; //Vozilo
	public static final String column_oznaka = "Oznaka";
	private final static String DB_PATH = "/data/data/org.kondorcom.parking/databases/";
	//File dbPath = context.getDatabasePath(dbName);
				
	String dbName="korisnici.db";
	Context context;

	File dbFile;
	

	
	//dbPath="data/data/org.kondorcom.parking.db/databases/parking.db";
	
	

	private static final String TABLE_CREATE = 
			"CREATE TABLE " + table_grad + " (" +
			column_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + column_vozilo + " TEXT, " + column_oznaka + " TEXT " +
			")";
	
	
	public korisnikDBopenhelper(Context context, String dbName) {
	    super(context, dbName, null, DATABASE_VERSION);
	    this.context = context;
	    this.dbName = dbName;
	    
	    dbFile= new File(DB_PATH + dbName);
	}
	
	/*public gradDBopenhelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);

	}*/

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		db.execSQL(TABLE_CREATE); //komentiraj
		Log.i(LOGTAG, "tablica je kreirana1_korisnik");
		
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS" + table_grad);
			onCreate(db);						
	}

	
	}
	
	
	
	
	
	
	
	
	
	