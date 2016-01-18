package com.kondorcom.gpsparking;



import javax.security.auth.PrivateCredentialPermission;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

  
public class DatabaseHandler extends SQLiteOpenHelper {  
    private static final int DATABASE_VERSION = 1;  
    private static final String DATABASE_NAME = "users_payments.db";  
    private static final String LOGTAG = "Databasehandler";
   /* private static final String TABLE_KORISNIK = "korisnik";  
    private static final String COLUMN_VOZILO = "Vozilo";  
	private static final String COLUMN_OZNAKA = "Oznaka";  */
	
    private static final String TABLE_USER = "user";
    private static final String COLUMN_ID_1 = "_id";  
    private static final String COLUMN_VEHICLE = "Vehicle";  
	private static final String COLUMN_REGISTRATION = "Registration";  
	
    private static final String TABLE_PAYMENTS = "payments";
    private static final String COLUMN_ID_2 = "_id";  
    private static final String COLUMN_VEHICLE_2 = "Vehicle";  
	private static final String COLUMN_REGISTRATION_2 = "Registration"; 
	private static final String COLUMN_DOP = "DOP"; 
	private static final String COLUMN_TOP = "TOP"; 
	private static final String COLUMN_CITY = "City"; 
	private static final String COLUMN_ZONE = "Zone"; 
	    
	private static  final String CREATE_ITEM_TABLE = "CREATE TABLE " + TABLE_USER + "("  
            + COLUMN_ID_1 + " INTEGER PRIMARY KEY," 
			+ COLUMN_VEHICLE + " TEXT," 
            + COLUMN_REGISTRATION + " TEXT)";
	   
	private static  final String CREATE_ITEM_TABLE_2 = "CREATE TABLE " + TABLE_PAYMENTS + "("  
			+ COLUMN_ID_2 + " INTEGER PRIMARY KEY," 
			+ COLUMN_VEHICLE_2 + " TEXT," 
			+ COLUMN_REGISTRATION_2 + " TEXT, " 
			+ COLUMN_DOP + "TEXT,"
			+ COLUMN_TOP + "TEXT, " 
			+ COLUMN_CITY + "TEXT," 
			+ COLUMN_ZONE + "TEXT)";
   
    public DatabaseHandler(Context context) {  
        super(context, DATABASE_NAME, null, DATABASE_VERSION);  
    }  
   
    // Creating Tables  
    @Override  
    public void onCreate(SQLiteDatabase db) {  
        // Category table create query  
    	
       /* String CREATE_ITEM_TABLE = "CREATE TABLE " + TABLE_KORISNIK + "("  
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_VOZILO + " TEXT," + COLUMN_OZNAKA + " TEXT)";  */
       
        
        
        
        Log.i(LOGTAG, "kreiranje tablica payments");
        Log.i(LOGTAG, "kreirane tablice ");
        try {
        	 db.execSQL(CREATE_ITEM_TABLE_2);  
             db.execSQL(CREATE_ITEM_TABLE); 
             
             Log.i(LOGTAG, "kreiranje tablica payments");
             Log.i(LOGTAG, "kreirane tablice ");
		} catch (SQLiteException e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
        
        
        
        
        
    }  
   
    // Upgrading database  
    @Override  
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {  
        // Drop older table if existed  
      
    	//db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
    	db.execSQL("DROP TABLE IF EXISTS TABLE_USER");
    	db.execSQL("DROP TABLE IF EXISTS TABLE_PAYMENTS");
       // db.execSQL("DROP TABLE IF EXISTS " + TABLE_PAYMENTS);
        // Create tables again  
        onCreate(db);  
    }  
    
    /*public DatabaseHandler openToWrite() throws android.database.SQLException {
    	  sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null, MYDATABASE_VERSION);
    	  sqLiteDatabase = sqLiteHelper.getWritableDatabase();
    	  return this; 
    	 }*/
    
    public void insert_user(String vehicle, String registration){
    	SQLiteDatabase db = this.getWritableDatabase();
    	
    	  ContentValues contentValues = new ContentValues();	// za probu 
    	  contentValues.put(COLUMN_VEHICLE, vehicle);
    	  contentValues.put(COLUMN_REGISTRATION, registration);
    	  //return db.insert(TABLE_KORISNIK, null, contentValues);
    	  Log.d(vehicle,vehicle.toString());
    	  db.insert(TABLE_USER, null, contentValues);//tableName, nullColumnHack, CotentValues 
    	  Log.i(LOGTAG, "insert user");
          db.close(); // Closing database connection  
          Log.i(LOGTAG, "zatvorena baza");
    	 }
    
    public void insert_payment(String vehicle, String registration, String date_of_payment, String time_of_payment, String city, String zone){
    	SQLiteDatabase db = this.getWritableDatabase();
    	
    	  ContentValues contentValues = new ContentValues();	// za probu 
    	  contentValues.put(COLUMN_VEHICLE_2, vehicle);
    	  contentValues.put(COLUMN_REGISTRATION_2, registration);
    	  contentValues.put(COLUMN_DOP, date_of_payment);
    	  contentValues.put(COLUMN_TOP, time_of_payment);
    	  contentValues.put(COLUMN_CITY, city);
    	  contentValues.put(COLUMN_ZONE, zone);
    	  //return db.insert(TABLE_KORISNIK, null, contentValues);
    	  Log.i(LOGTAG, "insert payment");
    	  db.insert(TABLE_PAYMENTS, null, contentValues);//tableName, nullColumnHack, CotentValues 
    	  
          db.close(); // Closing database connection  
    	 }
    
    /** 
     * Inserting new lable into lables table 
     * */  
    
    /*
     *	//ne koristim
     * public void insertLabel2(String label, String label1){  
        SQLiteDatabase db = this.getWritableDatabase();  
   
        ContentValues values = new ContentValues();  
        values.put(COLUMN_VOZILO, label);//column name, column value   // radilo nekad
        values.put(COLUMN_OZNAKA, label1);
   
        // Inserting Row  
        db.insert(TABLE_KORISNIK, null, values);//tableName, nullColumnHack, CotentValues  
        db.close(); // Closing database connection  
    }  */
   
    /** 
     * Getting all labels 
     * returns list of labels 
     * */  
   /* public List<String> getAllLabels(){  
        List<String> list = new ArrayList<String>();  
   
        // Select All Query  
        String selectQuery = "SELECT  * FROM " + TABLE_KORISNIK;  
   
        SQLiteDatabase db = this.getReadableDatabase();  
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments  
   
        // looping through all rows and adding to list  
        if (cursor.moveToFirst()) {  
            do {  
                list.add(cursor.getString(2));
                //adding 2nd column data  
                //biramo koji stupac dodati u spiner
            } while (cursor.moveToNext());  
        }  
        // closing connection  
        cursor.close();  
        db.close();  
   
        // returning lables  
        return list;  
    }  */

	
}  
