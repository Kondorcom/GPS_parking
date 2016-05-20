package com.kondorcom.gpsparking.model;


import android.util.Log;



public class User {
	
	private long _id;
	private String vehicle;
	private String registration;
	
	public User() {
	}

	public User(String title) {
		this.vehicle = title;
		
	}
	
	public long getId() {
		return _id; 
	}
	public void setId(long id) {
		this._id = id;
	}
	public String getVehicle() {
		return vehicle;
	}
	public void setVehicle(String title) {
		this.vehicle = title;
	}
	public String getRegistration() {
		return registration;
	}
	public void setRegistration(String title) {
		this.registration = title;
	}

	
	@Override
	public String toString() {
		
		//Log.i(LOGTAG,"Returned" + " " +vehicle + " " + _id + " " + registration + " " + "korisnika");
		//return vehicle + ": " + _id + " " + registration+":" + grad1 +" "+ zona1 +" ";
		//return vehicle + ": " + _id + " " + registration +"";
		return vehicle + ": " + registration +"";
	}
	
	
}

