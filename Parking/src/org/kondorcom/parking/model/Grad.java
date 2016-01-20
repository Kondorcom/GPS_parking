package org.kondorcom.parking.model;



public class Grad {
	private long _id;
	private String imeGrada;

	
	public Grad() {
	}

	public Grad(String title) {
		this.imeGrada = title;
		
	}
	
	public long getId() {
		return _id; 
	}
	public void setId(long id) {
		this._id = id;
	}
	public String getImeGrada() {
		return imeGrada;
	}
	public void setImeGrada(String title) {
		this.imeGrada = title;
	}
	@Override
	public String toString() {
		
		return imeGrada + " " + _id;
	}
	
	
}

