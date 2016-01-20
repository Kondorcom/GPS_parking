package org.kondorcom.parking.model;

import android.util.Log;



public class Zona {
	private long _id;
	private String zona;
	private String id_grada;
	private String broj;
	private static final String LOGTAG = "zona";
	
	public Zona() {
	}

	public Zona(String title) {
		this.zona = title;
		
	}
	
	public long getId() {
		return _id; 
	}
	public void setId(long id) {
		this._id = id;
	}
	public String getZona() {
		return zona;
	}
	public void setZona(String title) {
		this.zona = title;
	}
	public String getIDgrada() {
		return id_grada;
	}
	public void setIDgrada(String title) {
		this.id_grada = title;
	}
	public String getBroj() {
		return broj;
	}
	public void setBroj(String title) {
		this.broj = title;
	}
	@Override
	public String toString() {
		
		//Log.i(LOGTAG,"Returned" + " " +zona + " " + id_grada + " " + broj + " " + "Zonaa");
		return zona + ":" +" " + broj;
	}
	
	
}

