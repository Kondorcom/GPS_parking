package org.kondorcom.parking;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;  
import java.util.List;  

import android.content.ContentValues;  
import android.content.Context;  
import android.content.Intent;
import android.database.Cursor;  
import android.view.View;

  
public class DatabaseHandler extends SQLiteOpenHelper {  
    private static final int DATABASE_VERSION = 1;  
    private static final String DATABASE_NAME = "korisnici.db";  
    private static final String TABLE_KORISNIK = "korisnik";  
    private static final String COLUMN_ID = "_id";  
    private static final String COLUMN_VOZILO = "Vozilo";  
	    private static final String COLUMN_OZNAKA = "Oznaka";  
	    
	    
   
    public DatabaseHandler(Context context) {  
        super(context, DATABASE_NAME, null, DATABASE_VERSION);  
    }  
   
    // Creating Tables  
    @Override  
    public void onCreate(SQLiteDatabase db) {  
        // Category table create query  
        String CREATE_ITEM_TABLE = "CREATE TABLE " + TABLE_KORISNIK + "("  
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_VOZILO + " TEXT," + COLUMN_OZNAKA + " TEXT)";  
        db.execSQL(CREATE_ITEM_TABLE);  
    }  
   
    // Upgrading database  
    @Override  
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {  
        // Drop older table if existed  
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_KORISNIK);  
   
        // Create tables again  
        onCreate(db);  
    }  
    
    /*public DatabaseHandler openToWrite() throws android.database.SQLException {
    	  sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null, MYDATABASE_VERSION);
    	  sqLiteDatabase = sqLiteHelper.getWritableDatabase();
    	  return this; 
    	 }*/
    
    public void insert(String content1, String content2){
    	SQLiteDatabase db = this.getWritableDatabase();
    	  ContentValues contentValues = new ContentValues();	// za probu 
    	  contentValues.put(COLUMN_VOZILO, content1);
    	  contentValues.put(COLUMN_OZNAKA, content2);
    	  //return db.insert(TABLE_KORISNIK, null, contentValues);
    	  db.insert(TABLE_KORISNIK, null, contentValues);//tableName, nullColumnHack, CotentValues 
    	  
          db.close(); // Closing database connection  
    	 }
    /** 
     * Inserting new lable into lables table 
     * */  
    
    public void insertLabel2(String label, String label1){  
        SQLiteDatabase db = this.getWritableDatabase();  
   
        ContentValues values = new ContentValues();  
        values.put(COLUMN_VOZILO, label);//column name, column value   // radilo nekad
        values.put(COLUMN_OZNAKA, label1);
   
        // Inserting Row  
        db.insert(TABLE_KORISNIK, null, values);//tableName, nullColumnHack, CotentValues  
        db.close(); // Closing database connection  
    }  
   
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