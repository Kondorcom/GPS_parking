package com.kondorcom.gpsparking.model;


import android.util.Log;



public class History {
	//private static final String LOGTAG = null;
	private long _id;
	private String vehicle;
	private String registration;
	
	private String DOP;
	private String TOP;
	private String City;
	private String Zone;
	
		
	public History() {
	}

	public History(String title) {
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
	
	public String getDOP() {
		return DOP;
	}
	public void setDOP(String title) {
		this.DOP = title;
	}
	public String getTOP() {
		return TOP;
	}
	public void setTOP(String title) {
		this.TOP = title;
	}
	
	public String getCity() {
		return City;
	}
	public void setCity(String title) {
		this.City = title;
	}
	public String getZone() {
		return Zone;
	}
	public void setZone(String title) {
		this.Zone = title;
	}
	
	
	
	@Override
	public String toString() {
		
		//Log.i(LOGTAG,"Returned" + " " + vehicle + " " + _id + " " + registration + " " + DOP + " " + TOP + "history");
		//return vehicle + ": " + _id + " " + registration+":" + grad1 +" "+ zona1 +" ";
		return vehicle + ": " + registration + " " + DOP + ": " + TOP + ": " + City + " " + Zone + "" ;
	}
	
	
}


