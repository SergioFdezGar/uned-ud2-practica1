package com.uned.ud2.practica2;

public class Pregunta {
	String pregunta;
	String respuesta1;
	String respuesta2;
	String respuesta3;
	String respuesta4;
	int respCorrecta;
	
	public Pregunta(String pPregunta, String pRespuesta1, String pRespuesta2, String pRespuesta3, String pRespuesta4, int pRespCorrecta){
		this.pregunta = pPregunta;
		this.respuesta1 = pRespuesta1;
		this.respuesta2 = pRespuesta2;
		this.respuesta3 = pRespuesta3;
		this.respuesta4 = pRespuesta4;
		this.respCorrecta = pRespCorrecta;
	}
	
	public String getPregunta(){
		return this.pregunta;
	}
	
	public String getRespuesta1(){
		return this.respuesta1;
	}
	
	public String getRespuesta2(){
		return this.respuesta2;
	}
	
	public String getRespuesta3(){
		return this.respuesta3;
	}
	
	public String getRespuesta4(){
		return this.respuesta4;
	}
	
	public int getRespCorrecta(){
		return this.respCorrecta;
	}
	
	public String toString(){
		return this.pregunta + " " + this.respuesta1 + " " + this.respuesta2 + " " + this.respuesta3 + " " + this.respuesta4 + " " + this.respCorrecta;
	}
}
