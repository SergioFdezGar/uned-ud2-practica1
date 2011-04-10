package com.uned.ud2.practica2;

import java.util.List;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;



public class SettingsActivity extends PreferenceActivity implements OnSharedPreferenceChangeListener {
    
	SharedPreferences preferencias= getSharedPreferences("PrefMap", MODE_PRIVATE);
	SharedPreferences.Editor editor= preferencias.edit();
	
	private ListPreference mListPreference;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        
        mListPreference=(ListPreference)getPreferenceScreen().findPreference("view_map");
        
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }
	
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String Key ){
		editor.putString(Key, sharedPreferences.toString());
		editor.commit();
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		
		//mListPreference.setSummary(preferencias.getString("modo", ""));
	}
	
	@Override
	public void onDestroy(){
		//mListPreference.setSummary(sharedPreferences.getValue("modo", ""));
		
		
	}

}
