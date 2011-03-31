package com.uned.ud2.practica1;

import java.util.ArrayList;
import java.util.Iterator;


import org.xmlpull.v1.XmlPullParser;

public class Pregunta {

	private XmlPullParser parser;
	private String encabezado;
	private ArrayList<String> respuestas= new ArrayList<String>();
	private int correcta;
	
	public Pregunta(XmlPullParser fichero){
		this.parser=fichero;
		reiniciarValores();
	}
	
	public void generaPregunta(int n_pregunta){        
        try {
        	//Reiniciamos los posibles valores que tienen las variables.
        	reiniciarValores();
        	int voy=0;
            while (this.parser.next() != XmlPullParser.END_DOCUMENT) {
                
            	String nombre = this.parser.getName();
                
                if ((nombre != null) && nombre.equals("pregunta")) {
                    voy++;
                    if(voy>=n_pregunta){
	                	//Toma el numero de atributtos que existen
	                	int size = this.parser.getAttributeCount();
	                	
	                    for (int i = 0; i < size; i++) {
	                        String attrName = this.parser.getAttributeName(i);
	                        String attrValue = this.parser.getAttributeValue(i);
	                    
	                    	if ((attrName != null) && attrName.equals("encabezado")) {
	                    		this.encabezado = attrValue;
	                    	} else if ((attrName != null) && attrName.equals("respuesta1")) {
	                    		this.respuestas.add(attrValue);
	                    	} else if ((attrName!=null)&& attrName.equals("respuesta2")){
	                    		this.respuestas.add(attrValue);
	                    	} else if ((attrName!=null)&& attrName.equals("respuesta3")){
	                    		this.respuestas.add(attrValue);
	                    	} else if ((attrName!=null)&& attrName.equals("respuesta4")){
	                    		this.respuestas.add(attrValue);
	                    	} else if ((attrName!=null)&& attrName.equals("correcta")){
	                    		this.correcta= Integer.parseInt(attrValue);
	                    	}	
	                    
	                    	if ((this.encabezado != null) && (this.respuestas != null) && (this.correcta != 0)) {
	                    		//Salimos del método
	                    		return;
	                    	}
	                    }
                    }
                }
            }
            
        } catch (Exception e) {
        
        }
        
	}

/*	
	public Map getTarjeta(){
		Map tarjeta= new Map();
		tarjeta.putAll(this.encabezado, this.respuestas);
	
	
		
		return tarjeta;
	}
*/	
	public Integer getTotal(){
		int total=0;
		
		//Recorremos el documento en búsqueda del atributo encabezado
        try {
            while (this.parser.next() != XmlPullParser.END_DOCUMENT) {
                String name = this.parser.getName();
                if ((name != null) && name.equals("pregunta")) {
                    int size = this.parser.getAttributeCount();
                    for (int i = 0; i < size; i++) {
                        String attrName = this.parser.getAttributeName(i);
                        if ((attrName != null) && attrName.equals("encabezado")) {
                            total++;
                        }
                    }

                }
            }
        } catch (Exception e) {
        	return null;
        }		
		return total;
	}
	
	public String getEncabezado(){
		return this.encabezado;
	}
	
	public String getRespuesta(int orden){
		Iterator<String> iter= this.respuestas.iterator();
		int i=0;
		while(iter.hasNext()){
			i++;
			if(i==orden){
				return iter.next().toString();
			}else{
				iter.next();
			}
			
		}
		return null;
	}
	
	private void reiniciarValores(){
		this.encabezado=null;
		this.respuestas.clear();
		this.correcta=0;
	}
	
	public int getCorrecta(){
		return this.correcta;
	}
}