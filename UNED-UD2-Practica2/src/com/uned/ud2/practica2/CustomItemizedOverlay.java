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
