package com.kondorcom.gpsparking;

import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import com.kondorcom.gpsparking.db.historyDBopenhelper;
import com.kondorcom.gpsparking.db.historyDataSource;
import com.kondorcom.gpsparking.db.userDataSource;
import com.kondorcom.gpsparking.model.History;
import com.kondorcom.gpsparking.model.User;

import android.R.anim;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

public class HistoryActivity extends Activity {
	private static final String LOGTAG = "history activity";
	ListView listView;
	
	Spinner spinner;
	historyDataSource datasource;
	userDataSource dataSource2;
	String registracija;
	
	
	public String getRega() {
		return registracija;
	}

	public void setRega(String title) {
		this.registracija = title;
	}
	
	@Override
	public void onCreate (Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_history);
		
		setContentView(R.layout.activity_history2);
		
		onClick2();
		//setContentView(R.layout.activity_vozilo);
		
		listView=(ListView)findViewById(R.id.list);
		loadSpinnerData();
		
		//ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1, values);
		
		//listView.setAdapter(adapter);	 
		
		
	}
	
	private void loadSpinnerData() {

		
		datasource = new historyDataSource(this);
		dataSource2 = new userDataSource(this);
		
		Log.i(LOGTAG, "historry spinner");
	
		datasource.open();
		dataSource2.open();
		
	List<User> user = dataSource2.findAll2();
		
		
		ArrayAdapter<User> adapter2 = new ArrayAdapter<User>(this,
				android.R.layout.simple_list_item_1, user);
		Log.i(LOGTAG, "spiner_main");
		// setListAdapter(adapter);
		
		Log.d("history_payments", user.toString());
		Spinner povijest = (Spinner) findViewById(R.id.spinner1);
		
		
		Log.i(LOGTAG, "spiner_main");
		povijest.setAdapter(adapter2);
		
		/*List<History> hist = datasource.findCars();
		
		
		ArrayAdapter<History> adapter2 = new ArrayAdapter<History>(this,
				android.R.layout.simple_list_item_1, hist);
		Log.i(LOGTAG, "spiner_main");
		// setListAdapter(adapter);
		
		Log.d("history_payments", hist.toString());
		Spinner povijest = (Spinner) findViewById(R.id.spinner1);
		
		
		Log.i(LOGTAG, "spiner_main");
		povijest.setAdapter(adapter2);*/
		
	}
	private void onClick2() {
		final Spinner spinner1 = (Spinner) (findViewById(R.id.spinner1));
		
		spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {

				int Index = position;

				Log.i(LOGTAG,
						"Registracija" + " " + Index + "  "
								+ spinner1.getItemAtPosition(Index) + " "
								+ spinner1.getItemIdAtPosition(position));
				
				String izvuci_broj = spinner1.getItemAtPosition(Index).toString();
				Log.i(LOGTAG, "!!!!!!!!!!!!" + izvuci_broj);
				
				//String plus = "+";
				
				/*String broj = izvuci_broj.substring(izvuci_broj
						.lastIndexOf(": ") + 1);*/
				//String vozilo = izvuci_broj.substring(0, izvuci_broj.lastIndexOf(":"));

				//String broj2 = plus.concat(vozilo);
				
				/*setVozilo(vozilo);
				Log.i(LOGTAG, vozilo);
				Log.i(LOGTAG, "ovo je ime vozila  " + vozilo + " broj2 vozilo " );*/
				// long broj_za_slanje=long.parseLong(broj);

				// broju treba naprijed dodat +
				// Log.i(LOGTAG,broj+" "+broj_za_slanje);
				
				String izvuci_oznaku = spinner1.getItemAtPosition(position)
						.toString();
				Log.i(LOGTAG, "KOJI AUTO" + izvuci_oznaku);
				
				String oznaka1 = izvuci_oznaku.substring(izvuci_oznaku
						.lastIndexOf(" ") + 1);
				setRega(oznaka1);
				Log.i(LOGTAG, "ovo je rega:_" + oznaka1 +"_");
				
				/*historyDBopenhelper db = new historyDBopenhelper(getApplicationContext());
				String selectQuery = "SELECT * FROM payments WHERE vehicle= oznaka1 ";
				Cursor c = db.rawQuery(selectQuery, new String[] { fileName });
				if (c.moveToFirst()) {
				    temp_address = c.getString(c.getColumnIndex("lastchapter"));
				}
				c.close();*/

				
				lista();
				
				/*Log.i(LOGTAG, "historry spinner");
			
				datasource.open();
				List<History> hist = datasource.findCars(oznaka1);
				
				Log.d("history_payments1111111", hist.toString());
				
				ArrayAdapter<History> adapter = new ArrayAdapter<History>(this,android.R.layout.simple_list_item_1,hist);
				
				ListView lv = (ListView)findViewById(R.id.list);*/
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}


		});
		;
	}
	private void lista()
	{
		Log.i(LOGTAG, "historry spinner");
		
		datasource.open();
		List<History> hist = datasource.findCars(registracija);
		
		 List<HashMap<String, String>> hist2 = datasource.findCars2(registracija);
				 
		Log.d("history_payments1111111", hist.toString());
		
		ArrayAdapter<History> adapter = new ArrayAdapter<History>(this,android.R.layout.simple_list_item_1,hist);
		
		String[] from = new String[] { "DOP", "TOP","City","Zone"};
        int[] to = new int[] {  R.id.item3, R.id.item4,R.id.item5,R.id.item6 };
		SimpleAdapter adapter3 = new SimpleAdapter(this, hist2, R.layout.grid_item, from, to);
		
		ListView lv = (ListView)findViewById(R.id.listview);
		lv.setAdapter(adapter3);
		
		//ListView lv = (ListView)findViewById(R.id.list);
		//lv.setAdapter(adapter);
	}

}
