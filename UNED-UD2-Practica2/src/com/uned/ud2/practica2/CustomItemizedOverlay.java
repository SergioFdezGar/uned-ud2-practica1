package com.uned.ud2.practica2;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

@SuppressWarnings("rawtypes")
public class CustomItemizedOverlay extends ItemizedOverlay{

	private OverlayItem oliItems;
	private Context context;
	
	public CustomItemizedOverlay(Drawable defaultMarker, Context context) {
		super(boundCenterBottom(defaultMarker));
		this.context=context;	
	}

	@Override
	protected OverlayItem createItem(int i) {
		// TODO Auto-generated method stub
		return oliItems;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 1;
	}

	public void addOverlay(OverlayItem overlay){
		oliItems=overlay;
		populate();
	}
	
	protected boolean onTap(int index){
	    AlertDialog.Builder dialog = new AlertDialog.Builder(this.context);
	    dialog.setTitle(oliItems.getTitle());
	    dialog.setMessage(oliItems.getSnippet());
	    dialog.show();
	    return true;
	}
}