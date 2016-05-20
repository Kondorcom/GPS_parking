package com.kondorcom.gpsparking;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.kondorcom.gpsparking.db.historyDBopenhelper;
import com.kondorcom.gpsparking.db.historyDataSource;
import com.kondorcom.gpsparking.db.userDataSource;
import com.kondorcom.gpsparking.model.User;

import android.R.string;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.provider.Settings;

@SuppressLint("SimpleDateFormat")
public class MainActivity extends Activity implements LocationListener {

	private static final String LOGTAG = "main_activity";
	
	private LocationManager locationManager;
	
	
	
	userDataSource user_data_source;
	
	historyDataSource datasource3;
	Button proba2;
	Button platiParking;
	
	private String vozilo;
	
	public String getVozilo() {
		return vozilo;
	}
	public void setVozilo(String title) {
		
		this.vozilo = title;
	}
	
	
	private String broj;
	
	
	
	private String registracija;
	public String getRega() {
		return registracija;
	}

	public void setRega(String title) {
		this.registracija = title;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_main);
		
		
		
		loadSpinnerData();
	//	onClick3();
		onClick2();
		datasource3= new historyDataSource(this);
		datasource3.open();

	   
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		 
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000, 20, this);
		
		
		//final Button update = (Button) findViewById(R.id.button4);
		
		/*update.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//Get the data
				
				
				DoPOST mDoPOST = new DoPOST(MainActivity.this, location.getLatitude(),location.getLongitude());
				mDoPOST.execute("");
				update.setEnabled(false);
			}
		});*/
		
		proba2 = (Button) findViewById(R.id.button4);  //ovo simulira uspješno placanje parkinga, sprema podatke u history/payments
		proba2.setOnClickListener(new View.OnClickListener() {		//tipka update location ili simuliraj placanje
			@Override
			public void onClick(View arg0) {
				String grad;
				 String zona;
				 
				 
				
				TextView broj1 = (TextView)findViewById(R.id.textView7);//uncomment
				broj = broj1.getText().toString();
				
				TextView grad1 = (TextView)findViewById(R.id.textView4);
				grad = grad1.getText().toString();
				TextView zona1 = (TextView)findViewById(R.id.textView5);
				zona = zona1.getText().toString();
			
				Calendar c = Calendar.getInstance();

				SimpleDateFormat df2 = new SimpleDateFormat("dd-MM-yy");
				String datum = df2.format(c.getTime());
				 Log.i("datum " ,datum );
				 
				 SimpleDateFormat df3 = new SimpleDateFormat("HH:mm:ss");
					String vrijeme = df3.format(c.getTime());
					 Log.i("vrijeme " ,vrijeme );
					 
					 registracija=getRega();
					 
			
				Log.i(LOGTAG,  registracija);
				historyDBopenhelper db = new historyDBopenhelper(getApplicationContext());
				 try {
					 db.insert_payment(vozilo, registracija, datum, vrijeme, grad, zona);
					 Log.i(LOGTAG,  vozilo + " " + registracija + " " + datum + " " + vrijeme + " " 
								+ grad + " " + zona  );
				} catch (Exception e) {
					// TODO: handle exception
					Log.e(LOGTAG, e.toString());
				}
				
				
				
			
			}
		});
		
		
		platiParking = (Button) findViewById(R.id.button3);
		
		// zonaSpinner();
		
		
		platiParking.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String grad;
				 String zona;
				 
				 
				
				TextView broj1 = (TextView)findViewById(R.id.textView7);//uncomment
				broj = broj1.getText().toString();
				
				TextView grad1 = (TextView)findViewById(R.id.textView4);
				grad = grad1.getText().toString();
				TextView zona1 = (TextView)findViewById(R.id.textView5);
				zona = zona1.getText().toString();
			
				Calendar c = Calendar.getInstance();

				SimpleDateFormat df2 = new SimpleDateFormat("dd-MM-yyyy");
				String datum = df2.format(c.getTime());
				 Log.i("datum " ,datum );
				 
				 SimpleDateFormat df3 = new SimpleDateFormat("HH:mm:ss");
					String vrijeme = df3.format(c.getTime());
					 Log.i("vrijeme " ,vrijeme );
					 registracija=getRega();
					 // ovo ide ako je poruka uspjesno poslana 
					
						
						
						
				//String phoneNo = broj; //trenutno isključeno da bzvz ne šaljem poruke
						
				String phoneNo = null;
				
				registracija=getRega();
				String sms = registracija;
				Log.i(LOGTAG,  registracija);
											
				
				Log.i(LOGTAG,  vozilo + " " + registracija + " " + datum + " " + vrijeme + " " 
				+ grad + " " + zona  );
				
				
				Log.d("sms",sms.toString());
				Log.d("phoneNo",phoneNo.toString());
				try {
					SmsManager smsManager = SmsManager.getDefault();
					smsManager.sendTextMessage(phoneNo, null, sms, null, null);
					Toast.makeText(getApplicationContext(), "SMS Sent!",
							Toast.LENGTH_LONG).show();
					
					
					Log.i(LOGTAG,  registracija);
					historyDBopenhelper db = new historyDBopenhelper(getApplicationContext());
					 try {
						 db.insert_payment(vozilo, registracija, datum, vrijeme, grad, zona);
						 Log.i(LOGTAG,  vozilo + " " + registracija + " " + datum + " " + vrijeme + " " 
									+ grad + " " + zona  );
					} catch (Exception e) {
						// TODO: handle exception
						Log.e(LOGTAG, e.toString());
					}
					
					
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(),
							"SMS failed, please try again later!",
							Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}
				Log.i(LOGTAG, broj + " " + registracija);
			}
		});
		
		
		
		
	}
	private void onClick2() //u spinneru biram vozilo za koje placam parking
	{
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
				
				String izvuci_broj = spinner1.getItemAtPosition(Index).toString();//ovo više nije izvuci broj nego treba biti izvuci regu
				
				Log.i(LOGTAG, "!!!!!!!!!!!!" + izvuci_broj);
				
				//String plus = "+";
				
				/*String broj = izvuci_broj.substring(izvuci_broj
						.lastIndexOf(": ") + 1);*/
				
				String vozilo = izvuci_broj.substring(0, izvuci_broj.lastIndexOf(":"));// možda ne treba jer tražim samo regu, a ne vozilo

				//String broj2 = plus.concat(vozilo);
				
				setVozilo(vozilo);
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
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000, 20, this);
		Log.i(LOGTAG, "ON RESUME");
		datasource3.open();
		loadSpinnerData();

	}
	
	@Override
	protected void onPause()
	{
		super.onPause();
		Log.i(LOGTAG, "ON Pause GPS disable");
		//locationManager.removeUpdates(this);
	}
	@Override
	protected void onStop()
	{
		super.onStop();
		Log.i(LOGTAG, "ON Stop GPS disable");
		locationManager.removeUpdates(this);
	}
	
	
	
	public void gotoDodaj(View v) {
		Intent intent = new Intent(this, VoziloActivity.class);
		startActivity(intent);
	}
	
	public void gotoHistory(View v) {
		Intent intent = new Intent(this, HistoryActivity.class);
		startActivity(intent);
	}
	
	
	private void loadSpinnerData() {

		
		user_data_source = new userDataSource(this);
		
		Log.i(LOGTAG, "loading spinner");
	
		user_data_source.open();
		
		List<User> Users = user_data_source.findAll2();
		
		ArrayAdapter<User> adapter2 = new ArrayAdapter<User>(this,
				android.R.layout.simple_list_item_1, Users);
		Log.i(LOGTAG, "spiner_main");
		// setListAdapter(adapter);
		Log.d("Users", Users.toString());
		Spinner vozila = (Spinner) findViewById(R.id.spinner1);
		
		
		Log.i(LOGTAG, "spiner_main");
		vozila.setAdapter(adapter2);
		
	}
	

	@Override
	public void onLocationChanged( Location location) {
 
		/*String msg = "New Latitude: " + location.getLatitude()
				+ "New Longitude: " + location.getLongitude();*/
	
		
		
	DoGET mDoGET = new DoGET(MainActivity.this, location.getLatitude(),location.getLongitude());
		mDoGET.execute("");
		
		Log.i("Message: ","Location changed, " + location.getAccuracy() + " , " + location.getLatitude()+ "," + location.getLongitude());
		//Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
	}
	
	@Override
	public void onProviderDisabled(String provider) {
 
		Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
		startActivity(intent);
		Toast.makeText(getBaseContext(), "Gps is turned off!! ",
				Toast.LENGTH_SHORT).show();
	}
	@Override
	public void onProviderEnabled(String provider) {
 
		Toast.makeText(getBaseContext(), "Gps is turned on!! ",
				Toast.LENGTH_SHORT).show();
	}
 
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
 
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
	
	
	
	public class DoGET extends AsyncTask<String, Void, Boolean>{

		Context mContext = null;
		String strNameToSearch = "";
		double LatitudeToSearch ;
		double LongitudeToSearch ;
		
		//Result data
		String tag = "marko";
		String strFirstName;
		String strLastName;
		
		int intAge;
		int intPoints;
		
		String lat2;
		String long2;
		String grad1;
		String zona1;
		String broj1;
		
		Exception exception = null;
		
		DoGET(Context context, double searchLatitude, double searchLongitude){
			mContext = context;
			
			LatitudeToSearch = searchLatitude;
			LongitudeToSearch = searchLongitude;
			
			 lat2 = Double.toString(LatitudeToSearch);
			 long2 = Double.toString(LongitudeToSearch);
			 
			String tag = "marko";
			Log.i(LOGTAG, "DOGET");
			Log.i(tag, lat2);
			Log.i(tag, long2);
		}

		@Override
		protected Boolean doInBackground(String... arg0) {

			try{

				//Setup the parameters
				
				
				ArrayList<NameValuePair> lat_long_value = new ArrayList<NameValuePair>(); //GiveLat_long
				lat_long_value.add(new BasicNameValuePair("GiveLat", lat2));
				lat_long_value.add(new BasicNameValuePair("GiveLong", long2));
			
				ArrayList<NameValuePair> lat_value = new ArrayList<NameValuePair>(); //GiveLat
				lat_long_value.add(new BasicNameValuePair("GiveLat", lat2));
				
				ArrayList<NameValuePair> long_value = new ArrayList<NameValuePair>(); //GiveLong
				
				lat_long_value.add(new BasicNameValuePair("GiveLong", long2));
			
			

				//Create the HTTP request
				HttpParams httpParameters = new BasicHttpParams();

				//Setup timeouts
				HttpConnectionParams.setConnectionTimeout(httpParameters, 15000);
				HttpConnectionParams.setSoTimeout(httpParameters, 15000);			

				HttpClient httpclient = new DefaultHttpClient(httpParameters);
				
				//HttpPost httppost = new HttpPost("http://192.168.5.75:8080/GPS_parking/login3.php");//ovaj dio treba srediti
				//HttpPost httppost = new HttpPost("http://192.168.5.75:8080/GPS_parking/login.php");//lokalna baza 
				//HttpPost httppost = new HttpPost("http://kondorcom.site88.net/login4.php");
				
				//HttpPost httppost = new HttpPost("http://kondorcom.ddns.net:8080/GPS_parking/login.php");
				//lokalna baza pristup izvana
				
		//	HttpPost httppost = new HttpPost("http://kondorcom.ddns.net:8080/GPS_parking/server_login_16.5/login.php");
				
				String url = "http://kondorcom.ddns.net:8080/GPS_parking/server_login_16.5/login.php";
				
				//String url = "http://www.kondor88.podserver.info/index.php";
				//HttpGet httpget = new HttpGet(url + "?" +lat_value + "?" + long_value);
				
				 String paramString = URLEncodedUtils.format(lat_long_value, "utf-8");
				 
				HttpGet httpget = new HttpGet(url + "?" + paramString);
				//HttpGet httpget = new HttpGet("http://kondorcom.ddns.net:8080/GPS_parking/server_login_16.5/login.php?GiveLat=3&GiveLong=5");
				
			
		/*	HttpGet httpget = new HttpGet("http://kondorcom.ddns.net:8080/GPS_parking/server_login_16.5/login.php");
			HttpParams p = new BasicHttpParams();
			
			Log.i(LOGTAG, lat2);
			Log.i(LOGTAG, long2);
			
			p.setParameter("GiveLat", lat2);
			p.setParameter("GiveLong", long2);
			httpget.setParams(p);*/
			
			//httpget.setParams("GiveLat", lat2); 
			
			HttpResponse response = httpclient.execute(httpget);
				
				
				
								
				//httppost.setEntity(new UrlEncodedFormEntity(lat_long_value)); 
				
				//HttpResponse response = httpclient.execute(httppost);
				
				HttpEntity entity = response.getEntity();
				

				
				String result = EntityUtils.toString(entity);
				Log.d(tag, result);

				// Create a JSON object from the request response
				JSONObject jsonObject = new JSONObject(result);

				//Retrieve the data from the JSON object
				
				grad1=jsonObject.getString("Grad");
				
				Log.d(tag, jsonObject.getString("Grad"));	
				
				zona1=jsonObject.getString("Zona");
				broj1=jsonObject.getString("Broj");
				
			}catch (Exception e){
				Log.e("GPSparking", "Error:", e);
				exception = e;
			}

			return true;
		}

		@Override
		protected void onPostExecute(Boolean valid){
			//Update the UI
									
			TextView grad_txt = (TextView) findViewById(R.id.textView4);
			TextView zona_txt = (TextView) findViewById(R.id.textView5);
			TextView broj_txt = (TextView) findViewById(R.id.textView7);
		
			
			grad_txt.setText(grad1);
			zona_txt.setText(zona1);
			broj_txt.setText(broj1);
			//buttonGetData.setEnabled(true);
			
			if(exception != null){
				Toast.makeText(mContext, exception.getMessage(), Toast.LENGTH_LONG).show();
			}
		}

	}
}
