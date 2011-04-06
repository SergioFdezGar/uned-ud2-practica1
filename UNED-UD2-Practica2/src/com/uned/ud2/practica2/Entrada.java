package com.uned.ud2.practica2;

public class Entrada {
	private String mision;
	private String direccion;
	private int longitud;
	private int latitud;
	
	public Entrada(String pMision, String pDireccion, int pLongitud, int pLatitud){
		this.mision = pMision;
		this.direccion = pDireccion;
		//Cambiados aposta para por error en el XML
		this.longitud=pLatitud; 
		this.latitud=pLongitud;
	}
	
	public String getMision(){
		return this.mision;
	}
	
	public String getDireccion(){
		return this.direccion;
	}
	
	public String toString(){
		return this.mision + " " + this.direccion;
	}
	
	public int getLongitud(){
		return this.longitud;
	}
	
	public int getLatitud(){
		return this.latitud;
	}
}
