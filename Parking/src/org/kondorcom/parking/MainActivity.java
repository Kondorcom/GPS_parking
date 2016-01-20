package org.kondorcom.parking;

import org.apache.commons.net.ftp.*;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyStore.PrivateKeyEntry;
import java.util.ArrayList;
//import com.softeq.android.prepopdb.R;
//import com.softeq.prepopdb.dbhelper.ExternalDbOpenHelper;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.sql.DataSource;

import org.apache.http.util.ByteArrayBuffer;
import org.kondorcom.parking.db.gradDBopenhelper;
import org.kondorcom.parking.db.gradDataSource;
import org.kondorcom.parking.db.korisnikDataSource;
import org.kondorcom.parking.model.Grad;
import org.kondorcom.parking.model.Korisnik;
import org.kondorcom.parking.model.Zona;
import org.w3c.dom.UserDataHandler;

import android.R.integer;
import android.R.string;
import android.app.Activity;
import android.app.ListActivity;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.NfcAdapter.CreateBeamUrisCallback;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

//import org.apache.commons.net.ftp.FTPClient;
public class MainActivity extends Activity {
	private static final String LOGTAG = "park";
	private static final String TAG = "down";

	Button platiParking;
	Button btn_update;
	/*
	 * private String server = "31.147.29.58"; private int portNumber = 20;
	 * private String user = "kondor"; private String password = "Powerup1";
	 * private String filename = "/home/kondor/ftp2/parking.db";
	 */
	private String server = "ftp.drivehq.com";
	private int portNumber = 21;
	private String user = "kondorcom";
	private String password = "Powerup1";
	private String filename = "/parking.db";
	gradDataSource datasource;
	korisnikDataSource datasource2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_main);

		/*
		 * File dbtest =new
		 * File("data/data/org.kondorcom.parking/databases/parking.db");
		 * 
		 * if(dbtest.exists()) {
		 * 
		 * korisnikSpinner(); gradSpinner(); } else { //samo ovako nece svaki
		 * put skidat bazu //Skini_bazu task = new Skini_bazu();
		 * //task.execute(); createData(); //sredi ovaj kod, napravi drugi samo
		 * za gradove kopirat
		 * 
		 * }
		 */

		// Skini_bazu task = new Skini_bazu();
		// task.execute();
		// proba download
		// radi download, provjeri zasto se prvi put srusi
		//
		// createData();

		korisnikSpinner();
		gradSpinner();

		onClick2();
		onClick3();

		platiParking = (Button) findViewById(R.id.plati);
		// zonaSpinner();

		platiParking.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String phoneNo = broj;
				String sms = rega;

				try {
					SmsManager smsManager = SmsManager.getDefault();
					smsManager.sendTextMessage(phoneNo, null, sms, null, null);
					Toast.makeText(getApplicationContext(), "SMS Sent!",
							Toast.LENGTH_LONG).show();
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(),
							"SMS failed, please try again later!",
							Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}
				Log.i(LOGTAG, broj + " " + rega);
			}
		});
		btn_update = (Button) findViewById(R.id.btn_update);
		btn_update.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Skini_bazu task = new Skini_bazu();
				task.execute();

			}

		});

	}

	private class Skini_bazu extends AsyncTask<Void, Void, Boolean> {

		@Override
		protected Boolean doInBackground(Void... params) {
			FTPClient ftp = null;
			File localFile = new File(
					"/data/data/org.kondorcom.parking/databases/parking.db");
			try {
				ftp = new FTPClient();

				ftp.connect(server, portNumber);

				Log.d(LOGTAG, "Connected. Reply: " + ftp.getReplyString());

				ftp.login(user, password);
				Log.d(LOGTAG, "Logged in");
				Log.d(LOGTAG, " Reply: " + ftp.getReplyString());

				ftp.setFileType(FTP.BINARY_FILE_TYPE);
				Log.d(LOGTAG, "Downloading");
				ftp.enterLocalPassiveMode();
				Log.d(LOGTAG, " Reply: " + ftp.getReplyString());
				OutputStream outputStream = null;
				boolean success = false;
				try {
					outputStream = new BufferedOutputStream(
							new FileOutputStream(localFile));
					Log.d(LOGTAG, " Reply: " + ftp.getReplyString());
					success = ftp.retrieveFile(filename, outputStream);
					Log.d(LOGTAG,
							" Reply skinuto: gotovo " + ftp.getReplyString());
				} finally {
					if (outputStream != null) {
						outputStream.close();
					}
				}

				return success;
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {

				if (ftp != null) {
					try {
						ftp.logout();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						ftp.disconnect();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			return true; /* or false */
		}

		@Override
		protected void onPostExecute(Boolean n) {
			Log.e(LOGTAG, "Logged in" + n);
			korisnikSpinner();
			gradSpinner();
		}
	}

	private String broj;

	public String getBroj() {
		return broj;
	}

	public void setBroj(String title) {
		this.broj = title;
	}

	private String rega;

	public String getRega() {
		return rega;
	}

	public void setRega(String title) {
		this.rega = title;
	}

	
	private void onClick() {
		final Spinner spinner = (Spinner) (findViewById(R.id.Spinner02));
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {

				int SelectedIndex = position + 1;
				Log.i(LOGTAG, "POZICIJA" + SelectedIndex);// tu dobijem poziciju
															// grada
				zonaSpinner(SelectedIndex);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
		;
	}

	private void onClick2() {
		final Spinner spinner2 = (Spinner) (findViewById(R.id.spinner03));
		spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {

				int Index = position;

				Log.i(LOGTAG,
						"Registracija" + " " + Index + "  "
								+ spinner2.getItemAtPosition(Index) + " "
								+ spinner2.getItemIdAtPosition(position));// tu
																			// dobijem
																			// poziciju
																			// broja
																			// telefona
				
				String izvuci_broj = spinner2.getItemAtPosition(Index)
						.toString();
				Log.i(LOGTAG, "!!!!!!!!!!!!" + izvuci_broj);
				String plus = "+";
				String broj = izvuci_broj.substring(izvuci_broj
						.lastIndexOf(" ") + 1);

				String broj2 = plus.concat(broj);
				setBroj(broj2);
				Log.i(LOGTAG, broj + " " + broj2);
				// long broj_za_slanje=long.parseLong(broj);

				// broju treba naprijed dodat +
				// Log.i(LOGTAG,broj+" "+broj_za_slanje);

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}

		});
		;
	}

	private void onClick3() {
		final Spinner spinner = (Spinner) (findViewById(R.id.Spinner01));
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {

				/*
				 * int SelectedIndex = position + 1; Log.i(LOGTAG, "POZICIJA" +
				 * SelectedIndex );// tu dobijem poziciju grada
				 * zonaSpinner(SelectedIndex);
				 */
				String izvuci_oznaku = spinner.getItemAtPosition(position)
						.toString();
				Log.i(LOGTAG, "KOJI AUTO" + izvuci_oznaku);
				String oznaka1 = izvuci_oznaku.substring(izvuci_oznaku
						.lastIndexOf(" ") + 1);
				setRega(oznaka1);
				Log.i(LOGTAG, oznaka1);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
		;
	}

	private void gradSpinner() {
		datasource = new gradDataSource(this);
		// createData(); //za probu, radi ali ne od prvog puta, ako to ostavim
		// onda prvo kopira bazu iz assets foldera
		// mozda da napravim praznu bazu pa da vidim
		datasource.open();
		List<Grad> gradovi = datasource.findAll();

		if (gradovi.size() == 0) {
			// createData();
			gradovi = datasource.findAll();
		}

		ArrayAdapter<Grad> adapter = new ArrayAdapter<Grad>(this,
				android.R.layout.simple_list_item_1, gradovi);
		// setListAdapter(adapter);
		Spinner lv = (Spinner) findViewById(R.id.Spinner02);
		onClick();
		lv.setAdapter(adapter);

	}

	private void zonaSpinner(int pozicija) {
		datasource = new gradDataSource(this);
		datasource.open();
		List<Zona> zone = datasource.findAll_zone(pozicija);
		if (zone.size() == 0) {
			// createData();
			zone = datasource.findAll_zone(pozicija);
		}
		ArrayAdapter<Zona> adapter2 = new ArrayAdapter<Zona>(this,
				android.R.layout.simple_list_item_1, zone);
		// setListAdapter(adapter);
		Spinner lv2 = (Spinner) findViewById(R.id.spinner03);

		lv2.setAdapter(adapter2);

	}

	private void korisnikSpinner() {
		datasource2 = new korisnikDataSource(this);
		datasource2.open();
		List<Korisnik> Korisnici = datasource2.findAll2();
		if (Korisnici.size() == 0) {
			// createData();
			Korisnici = datasource2.findAll2();
		}
		ArrayAdapter<Korisnik> adapter2 = new ArrayAdapter<Korisnik>(this,
				android.R.layout.simple_list_item_1, Korisnici);
		// setListAdapter(adapter);
		Spinner lv2 = (Spinner) findViewById(R.id.Spinner01);

		lv2.setAdapter(adapter2);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void gotoDodaj(View v) {
		Intent intent = new Intent(this, VoziloActivity.class);
		startActivity(intent);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		Log.i(LOGTAG, "ON RESUME");
		// datasource.open();
		korisnikSpinner();
		gradSpinner(); // ne znam dal treba

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		datasource.close();
	}

	private void createData() //ovo ne koristim jer bazu punim s neta
	{
		// String dbPath="data/data/org.kondorcom.parking/databases/parking.db";
		Log.i(LOGTAG, "provjeri dal postoji baza1");
		File dbtest = new File(
				"data/data/org.kondorcom.parking/databases/parking.db");

		if (dbtest.exists()) {
			// what to do if it does exist
			Log.i(LOGTAG, "BAZA POSTOJI");
			try {
				Log.i(LOGTAG, "kopiraj ako ne postoji");
				AssetManager am = getApplicationContext().getAssets();// ne
																		// smije
																		// kopirati
																		// bazu
																		// ako
																		// vec
																		// postoji
				Log.i(LOGTAG, "kopiraj ako ne postoji2");
				// trebalo bi prvo kreirat praznu bazu
				OutputStream os = new FileOutputStream(
						"/data/data/org.kondorcom.parking/databases/parking.db");

				Log.i(LOGTAG, "kopira bazu");
				byte[] b = new byte[100];

				Log.i(LOGTAG, "kopira bazu1");
				int r;

				Log.i(LOGTAG, "kopira bazu2");
				InputStream is = am.open("parking.db");

				Log.i(LOGTAG, "kopira bazu3");
				while ((r = is.read(b)) != -1) {
					os.write(b, 0, r);
				}

				Log.i(LOGTAG, "kopirao je bazu");

				is.close();
				os.close();

			} catch (Exception e) {

			}

		} else {
			// what to do if it doesn't exist
			try {
				Log.i(LOGTAG, "kopiraj ako ne postoji");
				AssetManager am = getApplicationContext().getAssets();// ne
																		// smije
																		// kopirati
																		// bazu
																		// ako
																		// vec
																		// postoji
				Log.i(LOGTAG, "kopiraj ako ne postoji2");
				AssetManager am2 = getApplicationContext().getAssets();
				// trebalo bi prvo kreirat praznu bazu
				// OutputStream os2 = new
				// FileOutputStream("/data/data/org.kondorcom.parking/databases/");
				// OutputStream os2 = new
				// FileOutputStream("/data/data/org.kondorcom.parking/databases/korisnici.db");
				// OutputStream os = new
				// FileOutputStream("/data/data/org.kondorcom.parking/databases/");
				OutputStream os = new FileOutputStream(
						"/data/data/org.kondorcom.parking/databases/parking.db");
				Log.i(LOGTAG, "kopiraj ako ne postoji22222");

				Log.i(LOGTAG, "kopira bazu");
				byte[] b = new byte[100];
				// byte[] c = new byte[100];
				Log.i(LOGTAG, "kopira bazu1");
				int r;
				int j;
				Log.i(LOGTAG, "kopira bazu2");
				InputStream is = am.open("parking.db");

				InputStream is2 = am2.open("korisnici.db");
				Log.i(LOGTAG, "kopira bazu3");
				while ((r = is.read(b)) != -1) {
					os.write(b, 0, r);
				}
				/*
				 * while ((j = is2.read(c)) != -1) { os2.write(c, 0, j); }
				 */
				Log.i(LOGTAG, "kopirao je bazu");

				is.close();
				os.close();
				is2.close();
				// os2.close();
			} catch (Exception e) {
				Log.i(LOGTAG, " " + e);

			}
		}
		

	}

}
