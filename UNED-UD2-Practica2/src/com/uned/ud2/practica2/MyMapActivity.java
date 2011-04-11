package com.uned.ud2.practica2;

import java.util.List;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.LinearLayout;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class MyMapActivity extends MapActivity {

	LinearLayout llMapa;
	MapView mvMapa;
	List<Overlay> mapOverlays;
	Drawable drawable;
	CustomItemizedOverlay itemizedOverlay;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapa);
		mvMapa=(MapView)findViewById(R.id.mv_mapa);
		mvMapa.setBuiltInZoomControls(true);
		
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

		}
		
		mapOverlays = mvMapa.getOverlays();
		drawable = this.getResources().getDrawable(R.drawable.entrada);
		itemizedOverlay = new CustomItemizedOverlay(drawable, this);
		
		Bundle bundle=this.getIntent().getExtras();
		
		GeoPoint point = new GeoPoint(bundle.getInt("latitud"), bundle.getInt("longitud"));
		OverlayItem overlayitem = new OverlayItem(point, bundle.getString("mision").toString(), bundle.getString("direccion").toString());
		
		itemizedOverlay.addOverlay(overlayitem);
		mapOverlays.add(itemizedOverlay);
		
		
	}
	
	public MyMapActivity() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
