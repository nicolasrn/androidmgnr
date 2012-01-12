package com.android.morpion;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import com.android.graphisme.ui.FenetreHistorique;
import com.android.graphisme.ui.FenetreJeu;
import com.android.graphisme.ui.FormulaireConnection;
import com.android.metier.DataConnexion;
import com.android.reseau.client.Client;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ScrollView;

public class MorpionAndroidActivity extends Activity //implements Observer
{
	public static final String tag = "MorpionAndroidActivity";
	private FormulaireConnection form;
	private FenetreJeu fen;
	private FenetreHistorique hist;
	private ScrollView view;
	
	private static final int MENU_QUITTER = 0;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		//Log.v(tag, "savedInstanceState : " + (savedInstanceState == null));
		super.onCreate(savedInstanceState);
		//this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE)
		
		setContentView(R.layout.main);
		Button b = (Button) this.findViewById(R.id.button1);
		b.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MorpionAndroidActivity.this, MorpionConnexionActivity.class);
				startActivity(intent);
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, MENU_QUITTER, 0, "Quitter");
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId())
		{
		case MENU_QUITTER: 
			System.exit(0);
			return true;
		}
		return false;
	}
	
}
