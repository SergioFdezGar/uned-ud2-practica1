package com.uned.ud2.practica2;

import java.util.List;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.LinearLayout;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class CustomItemizedOverlay extends MapActivity {

	LinearLayout llMapa;
	MapView mvMapa;
	List<Overlay> mapOverlays;
	Drawable drawable;
	MyMapActivity itemizedOverlay;
	MapController ctrlmapa;
	
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
		itemizedOverlay = new MyMapActivity(drawable, this);
		
		Bundle bundle=this.getIntent().getExtras();
		
		GeoPoint point = new GeoPoint(bundle.getInt("latitud"), bundle.getInt("longitud"));
		OverlayItem overlayitem = new OverlayItem(point, bundle.getString("mision").toString(), bundle.getString("direccion").toString());
		//ctrlmapa.setCenter(point);
		itemizedOverlay.addOverlay(overlayitem);
		mapOverlays.add(itemizedOverlay);
		
		
	}
	

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void onDestroy(){
		super.onDestroy();
		mapOverlays = null;
		drawable = null;
		itemizedOverlay = null;
		
		GeoPoint point = new GeoPoint(0, 0);
		
		
	}
}


/*
package com.uned.ud2.practica2;


import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class CustomItemizedOverlay extends ItemizedOverlay{

	private ArrayList<OverlayItem> oliItems= new ArrayList<OverlayItem>();
	private Context context;
	
	public CustomItemizedOverlay(Drawable defaultMarker, Context context) {
		super(boundCenterBottom(defaultMarker));//Siguiendo el ejemplo de la documentacion
		this.context=context;
		
	}

	@Override
	protected OverlayItem createItem(int i) {
		// TODO Auto-generated method stub
		return oliItems.get(i);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return oliItems.size();
	}

	public void addOverlay(OverlayItem overlay){
		oliItems.add(overlay);
		populate();
	}
	
	protected boolean onTap(int index){
		OverlayItem item = oliItems.get(index);
	    AlertDialog.Builder dialog = new AlertDialog.Builder(this.context);
	    dialog.setTitle(item.getTitle());
	    dialog.setMessage(item.getSnippet());
	    dialog.show();
	    return true;
	}
	
}
*/