package com.kondorcom.gpsparking.model;


import android.util.Log;



public class User {
	//private static final String LOGTAG = null;
	private long _id;
	private String vehicle;
	private String registration;
	
	private String grad1;
	private String zona1;
	private String auto1;
	
	private static final String LOGTAG = "park";
	
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
	/*
	public String getGrad1() {
		return grad1;
	}
	public void setGrad1(String title) {
		this.grad1 = title;
	}
	public String getZona1() {
		return zona1;
	}
	public void setZona1(String title) {
		this.zona1 = title;
	}
	public String getAuto1() {
		return auto1;
	}
	public void setAuto1(String title) {
		this.auto1 = title;
	}*/
	
	@Override
	public String toString() {
		
		//Log.i(LOGTAG,"Returned" + " " +vehicle + " " + _id + " " + registration + " " + "korisnika");
		//return vehicle + ": " + _id + " " + registration+":" + grad1 +" "+ zona1 +" ";
		//return vehicle + ": " + _id + " " + registration +"";
		return vehicle + ": " + registration +"";
	}
	
	
}

