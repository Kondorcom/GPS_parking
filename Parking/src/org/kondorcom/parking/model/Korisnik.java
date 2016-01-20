package org.kondorcom.parking.model;

import android.util.Log;



public class Korisnik {
	private long _id;
	private String vozilo;
	private String oznaka;
	
	private String grad1;
	private String zona1;
	private String auto1;
	
	private static final String LOGTAG = "park";
	
	public Korisnik() {
	}

	public Korisnik(String title) {
		this.vozilo = title;
		
	}
	
	public long getId() {
		return _id; 
	}
	public void setId(long id) {
		this._id = id;
	}
	public String getVozilo() {
		return vozilo;
	}
	public void setVozilo(String title) {
		this.vozilo = title;
	}
	public String getOznaka() {
		return oznaka;
	}
	public void setOznaka(String title) {
		this.oznaka = title;
	}
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
	}
	@Override
	public String toString() {
		
		//Log.i(LOGTAG,"Returned" + " " +vozilo + " " + _id + " " + oznaka + " " + "korisnika");
		return vozilo + ": " + _id + " " + oznaka+":" + grad1 +" "+ zona1 +" ";
	}
	
	
}

