package org.kondorcom.parking;

import android.app.Activity;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import java.util.List;

import org.kondorcom.parking.db.korisnikDataSource;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.ActivityInfo;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.kondorcom.parking.model.Korisnik;

public class VoziloActivity extends Activity {
	Spinner spinner;
	private static final String LOGTAG = "park";
	Button btnAdd;
	EditText inputLabel, inputLabel1;
	DatabaseHandler dbHandler;
	korisnikDataSource datasource2;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_vozilo);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		dbHandler = new DatabaseHandler(getApplicationContext());

		inputLabel = (EditText) findViewById(R.id.input_label);
		inputLabel1 = (EditText) findViewById(R.id.input_label1);
		btnAdd = (Button) findViewById(R.id.tipka_dodaj);

		dbHandler = new DatabaseHandler(this);

		btnAdd.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String label = inputLabel.getText().toString();
				String data1 = inputLabel.getText().toString();
				String data2 = inputLabel1.getText().toString();

				Log.i(LOGTAG, "label11");
				if (data1.trim().length() > 0 & data2.trim().length() > 0) {
					// database handler
					DatabaseHandler db = new DatabaseHandler(
							getApplicationContext());
					Log.i(LOGTAG, "label22");
					// inserting new label into database
					db.insert(data1, data2);

					// making input filed text to blank
					inputLabel.setText("");
					inputLabel1.setText("");

					// Hiding the keyboard
					InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(inputLabel.getWindowToken(), 0);

					// loading spinner with newly added data
					loadSpinnerData();
				} else {
					Toast.makeText(getApplicationContext(),
							"Please enter label name", Toast.LENGTH_SHORT)
							.show();
				}
			}
		});

		Log.i(LOGTAG, "proba");

		datasource2 = new korisnikDataSource(this);
		Log.i(LOGTAG, "proba2");
		datasource2.open();
		List<Korisnik> Korisnici = datasource2.findAll2();
		/*
		 * if (Korisnici.size() == 0) { //createData(); Korisnici =
		 * datasource2.findAll2(); }
		 */
		ArrayAdapter<Korisnik> adapter2 = new ArrayAdapter<Korisnik>(this,
				android.R.layout.simple_list_item_1, Korisnici);
		// setListAdapter(adapter);
		Spinner vozila = (Spinner) findViewById(R.id.spinner_vozila);
		vozila.setAdapter(adapter2);

	}

	private void loadSpinnerData() {

		datasource2 = new korisnikDataSource(this);
		Log.i(LOGTAG, "proba2");
		datasource2.open();
		List<Korisnik> Korisnici = datasource2.findAll2();
		/*
		 * if (Korisnici.size() == 0) { //createData(); Korisnici =
		 * datasource2.findAll2(); }
		 */
		ArrayAdapter<Korisnik> adapter2 = new ArrayAdapter<Korisnik>(this,
				android.R.layout.simple_list_item_1, Korisnici);
		// setListAdapter(adapter);
		Spinner vozila = (Spinner) findViewById(R.id.spinner_vozila);
		vozila.setAdapter(adapter2);

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