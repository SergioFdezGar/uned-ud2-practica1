package com.uned.ud2.practica1;

import java.util.ArrayList;
import java.util.Iterator;

import org.xmlpull.v1.XmlPullParser;

public class Entrada {

	private XmlPullParser parser;
	private ArrayList<String> misiones= new ArrayList<String>();
	private ArrayList<String> direcciones= new ArrayList<String>();

	
	
	public Entrada(){
		
	}
	
	public Entrada(XmlPullParser fichero){
		this.parser=fichero;
		separarMD();
	}
	
	
	public void getParser(XmlPullParser fichero){
		this.parser=fichero;
		separarMD();
	}
	
	private void separarMD(){
        
        try {
            while (this.parser.next() != XmlPullParser.END_DOCUMENT) {
                String name = this.parser.getName();
                
                String mision = null;
                String direccion = null;
                if ((name != null) && name.equals("entrada")) {
                    int size = this.parser.getAttributeCount();
                    int index=0;
                    for (int i = 0; i < size; i++) {
                        String attrName = this.parser.getAttributeName(i);
                        String attrValue = this.parser.getAttributeValue(i);
                        if ((attrName != null) && attrName.equals("mision")) {
                            mision = attrValue;
                        } else if ((attrName != null) && attrName.equals("direccion")) {
                            direccion = attrValue;
                        }
                    }
                    if ((mision != null) && (direccion != null)) {
                    	this.misiones.add(index, mision);
                    	this.direcciones.add(index,direccion);
                    	index++;
                    }
                }
            }
            
        } catch (Exception e) {
        
        }
        
	}
	
	
	public ArrayList<String> getMisiones(){
		return this.misiones;
	}
	
	
	public String getMisiones(int posicion){
		Iterator<String> iter= this.misiones.iterator();
		int i=0;
		while(iter.hasNext()){
			if(i==posicion){
				return iter.next().toString();
			}else{
				iter.next();
			}
			i++;
		}
		return null;
	}
	
	public ArrayList<String> getDirecciones(){
		return this.direcciones;
	}

	public String getDirecciones(int posicion){
		Iterator<String> iter= this.direcciones.iterator();
		int i=0;
		while(iter.hasNext()){
			if(i==posicion){
				return iter.next().toString();
			}else{
				iter.next();
			}
			i++;
		}
		return null;
	}

}
