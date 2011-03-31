package com.uned.ud2.practica1;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import org.xmlpull.v1.XmlPullParser;


public class HomeActivity extends Activity{
	
	private Button btnRecuperar;
	private TextView txvEntrada;
	static final private int GET_CODE=0;
	private static int  correcta=0;
	private String direccion=null;
	private Entrada entrada_privada;	
    /** Called when the activity is first created. */
    @Override
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        
      //Creamos controladores para los elementos del layout
        this.btnRecuperar= (Button)findViewById(R.id.recuperar);
        this.txvEntrada= (TextView)findViewById(R.id.entrada);
        final XmlPullParser parser_entrada = getResources().getXml(R.xml.entradas);    
        
        //Agregamos los datos para el Spinner del formulario
        Entrada entrada= new Entrada(parser_entrada);
        this.entrada_privada=entrada;
        
        //Enlazamos el elemento Spinner con el elemento del XML
        final Spinner spinner = (Spinner)findViewById(R.id.sp_entradas);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, entrada.getMisiones());

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       	spinner.setAdapter(adapter);
       	
       	
        //Comportamiento para botones y otros elementos
       	
       	spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
       		
       		public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id){
       			direccion=entrada_privada.getDirecciones(position);
       			
       		}
       		

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				txvEntrada.setText(R.string.select_mision);
				
			}
		});
       	
        this.btnRecuperar.setOnClickListener(
        		new OnClickListener(){
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						txvEntrada.setVisibility(1);
						
						//Creamos un parser para saber el numero total de preguntas que hay en el archivo XML
						XmlPullParser parser_pregunta = getResources().getXml(R.xml.preguntas);
						Pregunta pregunta= new Pregunta(parser_pregunta);
						//Creamos un objeto para crear numeros aleatorios y así tomar una pregunta de forma aleatoria
				        Random aleatorio= new Random();
				        int iPregunta=(aleatorio.nextInt(pregunta.getTotal()));
						
						//Creamos un nuevo objeto para leer la pregunta que hemos elejido de forma aleatoria
				        XmlPullParser parser_tarjeta = getResources().getXml(R.xml.preguntas);
				        Pregunta tarjeta= new Pregunta(parser_tarjeta);
				        
				        tarjeta.generaPregunta(iPregunta);
						correcta=tarjeta.getCorrecta();
				        //Creamos un Intent para pasar el encabezado y las preguntas a TrivialActivity.java
				        Intent intent= new Intent(HomeActivity.this, TrivialActivity.class);
				        				        
				        Bundle bundle = new Bundle();
				        bundle.putString("encabezado", tarjeta.getEncabezado());
				        bundle.putString("respuesta1", tarjeta.getRespuesta(1));
				        bundle.putString("respuesta2", tarjeta.getRespuesta(2));
				        bundle.putString("respuesta3", tarjeta.getRespuesta(3));
				        bundle.putString("respuesta4", tarjeta.getRespuesta(4));
				        intent.putExtras(bundle);
				        
				        startActivityForResult(intent, GET_CODE);
					}    
        		});
	}
    
    public void onActivityResult(int requestCode, int resultCode, Intent data){
    	Bundle bundle= data.getExtras();
    	txvEntrada.setVisibility(0);
    	if((requestCode==GET_CODE)&&(resultCode==RESULT_OK)){
    		if(bundle.getInt("lol")==correcta){
    		txvEntrada.setText(direccion);
    		}
    		else{
    			txvEntrada.setText(R.string.denegado);
    		}
    	}else{
		txvEntrada.setText(R.string.error_respuesta);
    	}
	}
    
}