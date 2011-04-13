package com.uned.ud2.practica2;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

public class CustomItemizedOverlay extends MapActivity {

	private MapView mvMapa;
	private Drawable drawable;
	private MyMapActivity itemizedOverlay;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapa);
		
		mvMapa=(MapView)findViewById(R.id.mv_mapa);
		mvMapa.setBuiltInZoomControls(true);
		
		modoMapa();
		
		drawable = this.getResources().getDrawable(R.drawable.entrada);
		itemizedOverlay = new MyMapActivity(drawable, this);
		mvMapa.getOverlays().add(itemizedOverlay);
		
		Bundle bundle=this.getIntent().getExtras();
		
		GeoPoint point = new GeoPoint(bundle.getInt("latitud"), bundle.getInt("longitud"));
		OverlayItem overlayitem = new OverlayItem(point, bundle.getString("mision").toString(), bundle.getString("direccion").toString());
		itemizedOverlay.addOverlay(overlayitem);
		zoomLocalizacion(point);
		
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	
	
	private void modoMapa(){
		SharedPreferences preferencias = PreferenceManager.getDefaultSharedPreferences(this);
		
		switch(Integer.parseInt(preferencias.getString("view_map", ""))){
			case 0:
				mvMapa.setSatellite(false);
				mvMapa.setTraffic(true);
			break;
			
			case 1:
				mvMapa.setSatellite(true);
				mvMapa.setTraffic(false);
			break;
			
			default:
				mvMapa.setSatellite(false);
				mvMapa.setTraffic(true);

		}
	}
	
	private void zoomLocalizacion(GeoPoint point){
        if(point != null) {
        	mvMapa.getController().animateTo(point);
        	mvMapa.getController().setZoom(18);
        }
        else {
                Toast.makeText(this, "No se ha podido determinar la posición", Toast.LENGTH_SHORT).show();
        }

	}
}