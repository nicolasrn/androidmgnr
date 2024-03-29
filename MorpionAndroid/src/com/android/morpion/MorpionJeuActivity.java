package com.android.morpion;

import java.util.Observable;
import java.util.Observer;

import com.android.graphisme.ui.FenetreJeu;
import com.android.metier.DataConnexion;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MorpionJeuActivity extends ActivityGlobale implements Observer {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Log.v(MorpionAndroidActivity.tag, "" + getApplicationContext().getClass());
		MyApp app = ((MyApp)getApplicationContext());
	    DataConnexion data = app.getData();
	    setContentView(new FenetreJeu(this, data));
	}

	@Override
	public void update(Observable observable, Object data) {
		Log.v(MorpionAndroidActivity.tag, "dans la maj vers historique");
		if (data instanceof String)
		{
			Log.v(MorpionAndroidActivity.tag, "dans la condition");
			Intent intent = new Intent(this, MorpionFenetreHistoriqueActivity.class);
			intent.putExtra("historique", (String) data);
			startActivity(intent);
		}
	}

}
