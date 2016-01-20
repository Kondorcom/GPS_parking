package org.kondorcom.parking.db;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.DropBoxManager;
import android.util.Log;

public class gradDBopenhelper extends SQLiteOpenHelper {

	private static final String LOGTAG = "grad";
	private static final String DATABASE_NAME = "parking.db";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME2= "korisnici.db";
	
	public static final String table_grad = "grad";
	public static final String column_id="_id";
	public static final String column_ime = "Ime_grada";
	
	public static final String table_korisnik = "korisnik";
	public static final String column_id2="_id";
	public static final String column_vozilo = "Vozilo";
	public static final String column_oznaka = "Oznaka";
	
	public static final String table_zona = "zona";
	public static final String column_id3="_id";
	public static final String column_zona = "Zona";
	public static final String column_ID_grada="ID_grada";
	public static final String column_broj = "broj";
	
	private final static String DB_PATH = "/data/data/org.kondorcom.parking/databases/";
	//File dbPath = context.getDatabasePath(dbName);
				
	String dbName="parking.db";
	String dbName2="korisnici.db";
	Context context;

	File dbFile;
		
	//dbPath="data/data/org.kondorcom.parking.db/databases/parking.db";
		
	private static final String TABLE_CREATE = 
			"CREATE TABLE IF NOT EXISTS " + table_grad + " (" +
			column_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
			column_ime + " TEXT " +
			")"; // tu sam probao CREATE TABLE IF NOT EXISTS
	
	public gradDBopenhelper(Context context, String dbName) {
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
		
		db.execSQL(TABLE_CREATE);  //ovo zakomentiraj
		
		
		Log.i(LOGTAG, "tablica je kreirana1");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.i(LOGTAG, "Upgrading database from version " + oldVersion
	              + " to "
	              + newVersion + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS" + table_grad);
			onCreate(db);						
	}
  
}
