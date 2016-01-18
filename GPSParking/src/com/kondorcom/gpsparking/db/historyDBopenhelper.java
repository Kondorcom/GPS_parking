package com.kondorcom.gpsparking.db;



import java.io.File;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class historyDBopenhelper extends SQLiteOpenHelper {

	private static final String LOGTAG = "historyDBopenhelper";
	
	private static final String DATABASE_NAME = "payments.db";
	
	private static final int DATABASE_VERSION = 2; 
	
	public static final String table_payments = "payment";
	
	public static final String column_id="_id";
	
	public static final String column_vehicle = "Vehicle"; //Vozilo
	public static final String column_registration = "Registration";
	public static final String column_DOP = "DOP";
	
	public static final String column_TOP = "TOP";
	
	public static final String column_city = "City";
	public static final String column_zone = "Zone";
	
	private final static String DB_PATH = "/data/data/com.kondorcom.gpsparking/databases/";
	//File dbPath = context.getDatabasePath(dbName);
				
	String dbName="payments.db";
	Context context;

	File dbFile;
	
	
	
	
	//dbPath="data/data/org.kondorcom.parking.db/databases/parking.db";
	

	public  historyDBopenhelper(Context context) {  
        super(context, DATABASE_NAME, null, DATABASE_VERSION);  
    } 

	private static final String TABLE_CREATE = 
			"CREATE TABLE " + table_payments + " (" 
					+ column_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
					+ column_vehicle + " TEXT, " 
					+ column_registration + " TEXT, " 
					+ column_DOP + " TEXT, " 
					+ column_TOP + " TEXT, " 
					+ column_city + " TEXT, " 
					+ column_zone + " TEXT " + 
					")";
	 
	 
	
	
	public historyDBopenhelper(Context context, String dbName) {
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
		Log.i(LOGTAG, "kreiranje tablica payments");
		
		Log.i(LOGTAG, "tablica je history");
		
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS" + table_payments);
			Log.i(LOGTAG, "tablica je history_upgrade");
			onCreate(db);						
	}
	
	public void insert_payment(String vehicle, String registration, String date_of_payment, String time_of_payment, String city, String zone){
    	SQLiteDatabase db = this.getWritableDatabase();
    	
    	  ContentValues contentValues = new ContentValues();	// za probu 
    	  contentValues.put(column_vehicle, vehicle);
    	  contentValues.put(column_registration, registration);
    	  contentValues.put(column_DOP, date_of_payment);
    	  contentValues.put(column_TOP, time_of_payment);
    	  contentValues.put(column_city, city);
    	  contentValues.put(column_zone, zone);
    	  //return db.insert(TABLE_KORISNIK, null, contentValues);
    	  Log.i(LOGTAG, "insert payment");
    	  db.insert(table_payments, null, contentValues);//tableName, nullColumnHack, CotentValues ž
    	  
    	  Log.i(LOGTAG, contentValues.toString());
    	  
          db.close(); // Closing database connection  
    	 }

	
	}
	
	
	
	
	
	
	
	
	
	
