package com.uned.ud2.practica2;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class SettingsActivity extends PreferenceActivity{
    
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		//mListPreference.setSummary(sharedPreferences.getValue("modo", ""));
		
		
	}

}
