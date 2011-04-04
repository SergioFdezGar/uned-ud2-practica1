package com.uned.ud2.practica2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class TrivialActivity extends Activity
{

    @Override
	protected void onCreate(Bundle savedInstanceState)
    {       
        super.onCreate(savedInstanceState);
  
        // Establecemos Layout
        setContentView(R.layout.trivial);
        
        // Recogemos valores entre actividades
        Bundle extras = getIntent().getExtras();
        String sPregunta = extras.getString(HomeActivity.XML_PREGUNTA_ENCABEZADO);
        String sRespuesta1 = extras.getString(HomeActivity.XML_PREGUNTA_RESPUESTA1);
        String sRespuesta2 = extras.getString(HomeActivity.XML_PREGUNTA_RESPUESTA2);
        String sRespuesta3 = extras.getString(HomeActivity.XML_PREGUNTA_RESPUESTA3);
        String sRespuesta4 = extras.getString(HomeActivity.XML_PREGUNTA_RESPUESTA4);
                
        // Inicializamos texto pregunta
        TextView text = (TextView)findViewById(R.id.label_pregunta);
        text.setText(sPregunta);
        
        // Inicializamos texto botones y listener
        Button button = (Button)findViewById(R.id.button_resp1);
        button.setText(sRespuesta1);
        button.setOnClickListener(mRP1Listener);
        
        Button button2 = (Button)findViewById(R.id.button_resp2);
        button2.setText(sRespuesta2);
        button2.setOnClickListener(mRP2Listener);
        
        Button button3 = (Button)findViewById(R.id.button_resp3);
        button3.setText(sRespuesta3);
        button3.setOnClickListener(mRP3Listener);
        
        Button button4 = (Button)findViewById(R.id.button_resp4);
        button4.setText(sRespuesta4);
        button4.setOnClickListener(mRP4Listener);
    }

    private OnClickListener mRP1Listener = new OnClickListener()
    {
        public void onClick(View v)
        {
            // Enviamos resultado y finalizamos actividad
            setResult(RESULT_OK, (new Intent()).setAction("1"));
            finish();
        }
    };

    private OnClickListener mRP2Listener = new OnClickListener()
    {
        public void onClick(View v)
        {
        	// Enviamos resultado y finalizamos actividad
        	setResult(RESULT_OK, (new Intent()).setAction("2"));
            finish();
        }
    };
    
    private OnClickListener mRP3Listener = new OnClickListener()
    {
        public void onClick(View v)
        {
        	// Enviamos resultado y finalizamos actividad
        	setResult(RESULT_OK, (new Intent()).setAction("3"));
            finish();
        }
    };
    
    private OnClickListener mRP4Listener = new OnClickListener()
    {
        public void onClick(View v)
        {
        	// Enviamos resultado y finalizamos actividad
        	setResult(RESULT_OK, (new Intent()).setAction("4"));
            finish();
        }
    };
}