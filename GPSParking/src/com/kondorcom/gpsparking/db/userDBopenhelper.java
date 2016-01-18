package com.kondorcom.gpsparking.db;



import java.io.File;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class userDBopenhelper extends SQLiteOpenHelper {

	private static final String LOGTAG = "userDBopenhelper";
	private static final String DATABASE_NAME = "users.db";
	private static final int DATABASE_VERSION = 1;
	
	public static final String table_user = "users";
	public static final String column_id="_id";
	public static final String column_vehicle = "Vehicle"; //Vozilo
	public static final String column_registration = "Registration";
	private final static String DB_PATH = "/data/data/com.kondorcom.gpsparking/databases/";
	//File dbPath = context.getDatabasePath(dbName);
				
	String dbName="users.db";
	Context context;

	File dbFile;
	

	
	//dbPath="data/data/org.kondorcom.parking.db/databases/parking.db";
	 public userDBopenhelper(Context context) {  
	        super(context, DATABASE_NAME, null, DATABASE_VERSION);  
	    } 
	

	private static final String TABLE_CREATE = 
			"CREATE TABLE " + table_user + " (" 
					+ column_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
					+ column_vehicle + " TEXT, " 
					+ column_registration + " TEXT " +
					")";
	
	
	public userDBopenhelper(Context context, String dbName) {
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
		Log.i(LOGTAG, "kreiranje tablica user");
		
		Log.i(LOGTAG, "tablica je User");
		
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS" + table_user);
			Log.i(LOGTAG, "tablica je user_upgrade");
			onCreate(db);						
	}
	 public void insert_user(String vehicle, String registration){
	    	SQLiteDatabase db = this.getWritableDatabase();
	    	
	    	  ContentValues contentValues = new ContentValues();	// za probu 
	    	  contentValues.put(column_vehicle, vehicle);
	    	  contentValues.put(column_registration, registration);
	    	  //return db.insert(TABLE_KORISNIK, null, contentValues);
	    	  Log.d(vehicle,vehicle.toString());
	    	  db.insert(table_user, null, contentValues);//tableName, nullColumnHack, CotentValues 
	    	  Log.i(LOGTAG, "insert user");
	          db.close(); // Closing database connection  
	          Log.i(LOGTAG, "zatvorena baza");
	    	 }
	 public void delete_user(String registration)
	 {
		 SQLiteDatabase db = this.getWritableDatabase();
		 String whereClause = "Registration = ?";
		 String[] registracija = new String[]
					{
					registration
					};
		 db.delete(table_user, whereClause, registracija);
		 db.close();
	 }

	
	}
	
	
	
	
	
	
	
	
	
	
