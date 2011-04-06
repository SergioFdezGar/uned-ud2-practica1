package com.uned.ud2.practica2;

import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
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
		
		mapOverlays = mvMapa.getOverlays();
		drawable = this.getResources().getDrawable(R.drawable.entrada);
		itemizedOverlay = new CustomItemizedOverlay(drawable, null);
		
		Bundle bundle=this.getIntent().getExtras();
		
		GeoPoint point = new GeoPoint(bundle.getInt("latitud"), bundle.getInt("longitud"));
		OverlayItem overlayitem = new OverlayItem(point, "Hello", "It's funny");
		
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
