package com.uned.ud2.practica1;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class TrivialActivity extends Activity {
	private Button btnRespuesta1;
	private Button btnRespuesta2;
	private Button btnRespuesta3;
	private Button btnRespuesta4;
	
	@Override
	public void onCreate(Bundle icicle){
		super.onCreate(icicle);
		setContentView(R.layout.trivial_activity);
		
		TextView txvEncabezado= (TextView)findViewById(R.id.encabezado);
		this.btnRespuesta1= (Button)findViewById(R.id.respuesta1);
		this.btnRespuesta1.setOnClickListener(btnRespuesta1Listener);
		
		this.btnRespuesta2= (Button)findViewById(R.id.respuesta2);
		this.btnRespuesta2.setOnClickListener(btnRespuesta2Listener);
		
		this.btnRespuesta3= (Button)findViewById(R.id.respuesta3);
		this.btnRespuesta3.setOnClickListener(btnRespuesta3Listener);
		
		this.btnRespuesta4= (Button)findViewById(R.id.respuesta4);
		this.btnRespuesta4.setOnClickListener(btnRespuesta4Listener);
		
		Bundle bundle= this.getIntent().getExtras();
		
		//Comprobamos que llega con datos
		if (bundle!=null){
			//Creación de los botones y sus valores para ser mostrados en pantalla
			txvEncabezado.setText(bundle.getString("encabezado"));
			btnRespuesta1.setText(bundle.getString("respuesta1"));
			btnRespuesta2.setText(bundle.getString("respuesta2"));
			btnRespuesta3.setText(bundle.getString("respuesta3"));
			btnRespuesta4.setText(bundle.getString("respuesta4"));
		}
		
		//A continuación tendremos que devolver a HomeActivity la respuesta del usuario
	}
	
	private OnClickListener btnRespuesta1Listener=new OnClickListener(){
			public void onClick(View v){
				Intent intent = new Intent();
				Bundle bundle= new Bundle();
				//bundle.putString("friday", "friday");
				bundle.putInt("lol", 1);
				intent.putExtras(bundle);
				setResult(RESULT_OK, intent);
				finish();
			}
	
	};
	
	private OnClickListener btnRespuesta2Listener=new OnClickListener(){
		public void onClick(View v){
			Intent intent = new Intent();
			Bundle bundle= new Bundle();
			//bundle.putString("friday", "friday");
			bundle.putInt("lol", 2);
			intent.putExtras(bundle);
			setResult(RESULT_OK, intent);
			finish();
		}

	};

	private OnClickListener btnRespuesta3Listener=new OnClickListener(){
		public void onClick(View v){
			Intent intent = new Intent();
			Bundle bundle= new Bundle();
			//bundle.putString("friday", "friday");
			bundle.putInt("lol", 3);
			intent.putExtras(bundle);
			setResult(RESULT_OK, intent);
			finish();
		}

	};

	private OnClickListener btnRespuesta4Listener=new OnClickListener(){
		public void onClick(View v){
			Intent intent = new Intent();
			Bundle bundle= new Bundle();
			//bundle.putString("friday", "friday");
			bundle.putInt("lol", 4);
			intent.putExtras(bundle);
			setResult(RESULT_OK, intent);
			finish();
		}

	};
	
}
