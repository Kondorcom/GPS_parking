package com.kondorcom.gpsparking;

import java.util.List;

import com.kondorcom.gpsparking.db.userDBopenhelper;
import com.kondorcom.gpsparking.db.userDataSource;
import com.kondorcom.gpsparking.model.User;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;

public class VoziloActivity extends Activity {
	Spinner spinner;
	private static final String LOGTAG = "park";
	Button btnAdd;
	EditText inputLabel, inputLabel1;
	Button btnDel;
	
	//DatabaseHandler dbHandler;
	String registracija;
	public String getRega() {
		return registracija;
	}

	public void setRega(String title) {
		this.registracija = title;
	}
	userDataSource datasource2;
	userDBopenhelper dbHelper;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_vozilo);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		//dbHandler = new DatabaseHandler(getApplicationContext());
		onClick2();
		dbHelper  = new userDBopenhelper(getApplicationContext(), "users");
		
		inputLabel = (EditText) findViewById(R.id.input_label);
		inputLabel1 = (EditText) findViewById(R.id.input_label1);
		btnAdd = (Button) findViewById(R.id.tipka_dodaj);
		
		btnDel =(Button)findViewById(R.id.button1);

		//dbHandler = new DatabaseHandler(this);
		
		Log.i(LOGTAG, "proba");

		datasource2 = new userDataSource(this);
		Log.i(LOGTAG, "proba2");
		datasource2.open();
		List<User> Users = datasource2.findAll2();
		/*
		 * if (Korisnici.size() == 0) { //createData(); Korisnici =
		 * datasource2.findAll2(); }
		 */
		ArrayAdapter<User> adapter2 = new ArrayAdapter<User>(this,
				android.R.layout.simple_list_item_1, Users);
		// setListAdapter(adapter);
		Spinner vozila = (Spinner) findViewById(R.id.spinner_vozila);
		vozila.setAdapter(adapter2);
		
		btnAdd.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				//String label = inputLabel.getText().toString();
				String vehicle_data = inputLabel.getText().toString();
				
				
				String reg_data = inputLabel1.getText().toString();
				

				Log.i(LOGTAG, "label11");
				if (vehicle_data.trim().length() > 0 & (reg_data.trim().length() == 7 || reg_data.trim().length() == 8)) {
					// database handler
					
					/*DatabaseHandler db = new DatabaseHandler(
							getApplicationContext());*/
					
					userDBopenhelper db  = new userDBopenhelper(
							getApplicationContext());
					
					Log.i(LOGTAG, "label22");
					Log.d("vehicle_data", vehicle_data.toString());
					// inserting new label into database
					
					//db.insert_user(vehicle_data, reg_data);
					
					db.insert_user(vehicle_data, reg_data);
					db.close();
					// making input filed text to blank
					inputLabel.setText("");
					inputLabel1.setText("");

					// Hiding the keyboard
					InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(inputLabel.getWindowToken(), 0);

					// loading spinner with newly added data
					loadSpinnerData();
				} else if (vehicle_data.trim().length() == 0) {
					inputLabel.setError( "First name is required!" );
				}
				
				else if (reg_data.trim().length() == 0){
					inputLabel1.setError( "Registration is required!" );
				}
				else if (reg_data.trim().length() != 8 || reg_data.trim().length() != 7){
					inputLabel1.setError( "Registration must have 7 or 8 digits" );
				}
				
			}
		});
		

					
		btnDel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Log.i(LOGTAG, "registracije="+ registracija);
				userDBopenhelper db  = new userDBopenhelper(
						getApplicationContext());
				db.delete_user(registracija);
				db.close();
				
				loadSpinnerData();
			}
			
			
		});

	}

	private void loadSpinnerData() {

		datasource2 = new userDataSource(this);
		Log.i(LOGTAG, "proba3");
		datasource2.open();
		List<User> Users = datasource2.findAll2();
		/*
		 * if (Korisnici.size() == 0) { //createData(); Korisnici =
		 * datasource2.findAll2(); }
		 */
		ArrayAdapter<User> adapter2 = new ArrayAdapter<User>(this,
				android.R.layout.simple_list_item_1, Users);
		Log.i(LOGTAG, "spiner");
		// setListAdapter(adapter);
		Log.d("Users", Users.toString());
		Spinner vozila = (Spinner) findViewById(R.id.spinner_vozila);
		Log.i(LOGTAG, "spiner");
		vozila.setAdapter(adapter2);

	}
	private void onClick2() {
		final Spinner spinner1 = (Spinner) (findViewById(R.id.spinner_vozila));
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
				String vozilo = izvuci_broj.substring(0, izvuci_broj.lastIndexOf(":"));

				//String broj2 = plus.concat(vozilo);
				//setVozilo(vozilo);
				Log.i(LOGTAG, vozilo);
				Log.i(LOGTAG, "ovo je ime vozila  " + vozilo + " broj2 vozilo " );
				// long broj_za_slanje=long.parseLong(broj);

				// broju treba naprijed dodat +
				// Log.i(LOGTAG,broj+" "+broj_za_slanje);
				
				String izvuci_oznaku = spinner1.getItemAtPosition(position)
						.toString();
				
				Log.i(LOGTAG, "KOJI AUTO" + izvuci_oznaku);
				String oznaka1 = izvuci_oznaku.substring(izvuci_oznaku
						.lastIndexOf(" ") + 1);
				
				setRega(oznaka1);
				
				Log.i(LOGTAG, "ovo je rega:    " + oznaka1);

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}

		});
		;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if (item.getItemId() == android.R.id.home) {
			finish();
		}
		return super.onOptionsItemSelected(item);
	}

}