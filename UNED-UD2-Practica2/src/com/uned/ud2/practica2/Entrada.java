package com.uned.ud2.practica2;

public class Entrada {
	String mision;
	String direccion;
	
	public Entrada(String pMision, String pDireccion){
		this.mision = pMision;
		this.direccion = pDireccion;
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
}
