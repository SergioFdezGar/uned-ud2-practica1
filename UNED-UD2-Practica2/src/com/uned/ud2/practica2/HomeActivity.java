package com.uned.ud2.practica2;


import java.util.ArrayList;
import java.util.Random;

import org.xmlpull.v1.XmlPullParser;

import com.uned.ud2.practica2.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class HomeActivity extends Activity implements AdapterView.OnItemSelectedListener {
	
	private static final String LOGTAG = "UD2-Practica2";
    
	private static final int GET_CODE = 0;
	
	//Valores del menu
	private static final int CONFIGURACION= 1;
	private static final int ACERCADE= 2;
	
	
    
	// KEYS de etiquetas para facilitar la lectura de los ficheros xml
	private static final String XML_ENTRADA = "entrada";
	private static final String XML_PREGUNTA = "pregunta";
	private static final String XML_ENTRADA_MISION = "mision";
	private static final String XML_ENTRADA_DIRECCION = "direccion";
	private static final String XML_ENTRADA_LONGITUD = "longitud";
	private static final String XML_ENTRADA_LATITUD = "latitud";
	    	
	// KEYS utilizados para el paso de parametros entre actividades
	public static final String XML_PREGUNTA_ENCABEZADO = "encabezado";
    public static final String XML_PREGUNTA_RESPUESTA1 = "respuesta1";
    public static final String XML_PREGUNTA_RESPUESTA2 = "respuesta2";
    public static final String XML_PREGUNTA_RESPUESTA3 = "respuesta3";
    public static final String XML_PREGUNTA_RESPUESTA4 = "respuesta4";
    
    private static final String XML_PREGUNTA_RESPCORRECTA = "correcta";
    
	private ArrayList<Entrada> aEntradas;
	private ArrayList<Pregunta> aPreguntas;
	private int iEntradaSeleccionada = 1; // Por defecto
	private int iPregunta = 0;
		
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Establecemos Layout y vistas
        setContentView(R.layout.home);
        
        Spinner mSpinner=(Spinner)findViewById(R.id.spinner);
        mSpinner.setOnItemSelectedListener(this);
        
        Button button = (Button)findViewById(R.id.button_direccion);        
        button.setOnClickListener(new OnClickListener()
		        {
		            public void onClick(View v)
		            {
		           
		            	llamarTrivialActivity();
		            }
		        });
        
        // Inicializamos ArrayList de Entradas y Respuestas
        aEntradas = new ArrayList<Entrada>();
        aPreguntas = new ArrayList<Pregunta>();
        
        if (cargaDatosEntradas() && cargaDatosPreguntas()){    
        	
        	// Asignamos valores al Spinner
        	ArrayAdapter<String> aAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, obtenerListaTitulos());
        	aAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        	mSpinner.setAdapter(aAdapter);
        	        	
        }        
    }    
    
    //Cuando la Actividad vuelve a estar Activa	
    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	
    	TextView mResultado = (TextView)findViewById(R.id.text_resultado);
    	if (requestCode==RESULT_OK){
    		switch (resultCode){
    			case GET_CODE:
    				
    				Button btnDireccion=(Button)findViewById(R.id.btn_direccion);
    				
                	int iDevuelto = Integer.parseInt(data.getAction());
                	if (iDevuelto == aPreguntas.get(iPregunta).getRespCorrecta()){ //Comprobamos si es correcto el valor con la respuesta
                		mResultado.setText(getString(R.string.label_resultado));
                		btnDireccion.setVisibility(1);
                		btnDireccion.setText(aEntradas.get(iEntradaSeleccionada).getDireccion());
                	}else{
                		mResultado.setText(getString(R.string.label_resultado_error));
                	}
    				
    				//Listener 
    				btnDireccion.setOnClickListener(new OnClickListener()
    				{
    					public void onClick(View v)
    					{
    						llamarMyMapActivity();
    					}
    				});
            	break;
            	
            	
    			case CONFIGURACION:
    				
    				break;
    		}
    		
    	}else{
    		mResultado.setText("");
    	}

    }


    private void llamarTrivialActivity(){
    	// Creamos un Intent y añadimos los parametros de una pregunta de forma aleatoria
    	Intent i = new Intent(this, TrivialActivity.class);    	    	
    	Random mRnd = new Random();
    	iPregunta = mRnd.nextInt(aPreguntas.size());
    	Pregunta aP = aPreguntas.get(iPregunta);
    	i.putExtra(XML_PREGUNTA_ENCABEZADO, aP.getPregunta());
    	i.putExtra(XML_PREGUNTA_RESPUESTA1, aP.getRespuesta1());
    	i.putExtra(XML_PREGUNTA_RESPUESTA2, aP.getRespuesta2());
    	i.putExtra(XML_PREGUNTA_RESPUESTA3, aP.getRespuesta3());
    	i.putExtra(XML_PREGUNTA_RESPUESTA4, aP.getRespuesta4());    	
    	startActivityForResult(i, GET_CODE);
    }

    
    private void llamarMyMapActivity(){
    	// Creamos un Intent y añadimos los parametros de una pregunta de forma aleatoria    	    	    	   	
    	Intent i = new Intent(this, MyMapActivity.class);
    	i.putExtra(XML_ENTRADA_MISION, aEntradas.get(iEntradaSeleccionada).getMision().toString());
    	i.putExtra(XML_ENTRADA_DIRECCION, aEntradas.get(iEntradaSeleccionada).getDireccion().toString());
    	i.putExtra(XML_ENTRADA_LONGITUD, aEntradas.get(iEntradaSeleccionada).getLongitud());
    	i.putExtra(XML_ENTRADA_LATITUD, aEntradas.get(iEntradaSeleccionada).getLatitud());
    	
    	startActivity(i);
    	
    }    


	@Override
	public void onItemSelected(AdapterView<?>  parent, View v, int position, long id) {
		iEntradaSeleccionada = position; //Almacenamos la posicion de la lista seleccionada en spinner		
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
	}
	
	// Funcion que recupera y almacena los datos desde el xml entradas
    private boolean cargaDatosEntradas(){
    	
        boolean bReturn = true; // Inicializamos valor de retorno
    	XmlPullParser parser = getResources().getXml(R.xml.entradas); // Obtenemos recurso xml
       
        try { // Parseamos XML
            while (parser.next() != XmlPullParser.END_DOCUMENT) {
                String name = parser.getName();                
                String mision = null;
                String direccion = null;
                int longitud= 0;
                int latitud=0;
                if ((name != null) && name.equals(XML_ENTRADA)) {
                    int size = parser.getAttributeCount();
                    for (int i = 0; i < size; i++) {
                        String attrName = parser.getAttributeName(i);
                        String attrValue = parser.getAttributeValue(i);
                        if ((attrName != null) && attrName.equals(XML_ENTRADA_MISION)) {
                            mision = attrValue;
                        } else if ((attrName != null) && attrName.equals(XML_ENTRADA_DIRECCION)) {
                            direccion = attrValue;
                        } else if ((attrName != null) && attrName.equals(XML_ENTRADA_LONGITUD)) {
                            longitud = Integer.parseInt(attrValue);
                        } else if ((attrName != null) && attrName.equals(XML_ENTRADA_LATITUD)) {
                            latitud = Integer.parseInt(attrValue);
                        }
                        
                    }
                    if ((mision != null) && (direccion != null)) {
                        aEntradas.add(new Entrada(mision,direccion, longitud, latitud));
                    }
                }                
            }            
        } catch (Exception e) { // Error
            Log.e(HomeActivity.LOGTAG, e.getMessage(), e);
            bReturn = false; 
        }  
        return bReturn;
    }
    
	// Funcion que recupera y almacena los datos desde el xml preguntas
    private boolean cargaDatosPreguntas(){
    	
        boolean bReturn = true; // Inicializamos valor de retorno
    	XmlPullParser parser = getResources().getXml(R.xml.preguntas); // Obtenemos recurso xml
        
        try { // Parseamos XML
            while (parser.next() != XmlPullParser.END_DOCUMENT) {
                String name = parser.getName();                
                String pregunta = null;
                String respuesta1 = null;
                String respuesta2 = null;
                String respuesta3 = null;
                String respuesta4 = null;
                int respCorrecta = 0;
                if ((name != null) && name.equals(XML_PREGUNTA)) {
                    int size = parser.getAttributeCount();
                    for (int i = 0; i < size; i++) {
                        String attrName = parser.getAttributeName(i);
                        String attrValue = parser.getAttributeValue(i);
                        if ((attrName != null) && attrName.equals(XML_PREGUNTA_ENCABEZADO)) {
                            pregunta = attrValue;
                        } else if ((attrName != null) && attrName.equals(XML_PREGUNTA_RESPUESTA1)) {
                            respuesta1 = attrValue;
                        } else if ((attrName != null) && attrName.equals(XML_PREGUNTA_RESPUESTA2)) {
                        	respuesta2 = attrValue;
                        } else if ((attrName != null) && attrName.equals(XML_PREGUNTA_RESPUESTA3)) {
                        	respuesta3 = attrValue;
                        } else if ((attrName != null) && attrName.equals(XML_PREGUNTA_RESPUESTA4)) {
                        	respuesta4 = attrValue;
                        } else if ((attrName != null) && attrName.equals(XML_PREGUNTA_RESPCORRECTA)) {
                        	respCorrecta = Integer.parseInt(attrValue);
                        }
                    }
                    if ((pregunta != null) && (respuesta1 != null) && (respuesta2 != null) && (respuesta3 != null) && (respuesta4 != null) && (respCorrecta != 0)) {
                        aPreguntas.add(new Pregunta(pregunta,respuesta1, respuesta2, respuesta3, respuesta4, respCorrecta));                        
                    }
                }                
            }            
        } catch (Exception e) { // Error
            Log.e(HomeActivity.LOGTAG, e.getMessage(), e);
            bReturn = false; 
        }  
        return bReturn;
    }
    
    // Funcion que recupera los titulos del ArrayList tipo Entrada
    private ArrayList<String> obtenerListaTitulos(){
    	ArrayList<String> aListaTitulos = new ArrayList<String>();
    	for (int i=0; i<aEntradas.size(); i++){
    		aListaTitulos.add(aEntradas.get(i).getMision());
    	}
    	return aListaTitulos;
    }
    
    
	   @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        MenuInflater inflater = getMenuInflater();
	        inflater.inflate(R.menu.menu, menu);
	        return true;
	    }
	    
	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        // Handle item selection
	        switch (item.getItemId()) {
	        case CONFIGURACION:
	            //Llamamos a la actividad para la configuración
	        	Intent config = new Intent(this, SettingsActivity.class);
	        	startActivityForResult(config, CONFIGURACION);
	        	
	            return true;
	        case ACERCADE:
	            //Función para ver la actividad de Acerca de;
	        	//Intent about = new Intent(this, AboutActivity.class);
	        	//startActivity(about, ACERCADE);

	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	        }
	    	
	    }
}