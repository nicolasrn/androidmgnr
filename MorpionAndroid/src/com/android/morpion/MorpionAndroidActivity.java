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
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ScrollView;

public class MorpionAndroidActivity extends Activity implements Observer
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
		Log.v(tag, "savedInstanceState : " + (savedInstanceState == null));
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE)
		
		if (savedInstanceState == null)
		{
			view = new ScrollView(this);
			form = new FormulaireConnection(this);
			view.addView(form);
			fen = new FenetreJeu(this);
			hist = new FenetreHistorique(this);
		}
		else
		{
			Log.v(tag, "onCreate avant view : " + (view == null));
			Log.v(tag, "onCreate apres view : " + (view == null));
		}
		setContentView(view);
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
	
	@Override
	public void update(Observable observable, Object data) {
		Log.v(tag, "dans le update de MorpionAndroidActivity");
		if (data instanceof DataConnexion)
		{
			//connexion au serveur puis lancer l'interface du morpion
			DataConnexion dataConnexion = (DataConnexion) data;
			Log.v(tag, "dans l'update de la fenetre de jeu");
			//la grille est construite par le serveur (parametres sont juste transmis)
			fen = new FenetreJeu(this);
			fen.init(dataConnexion);
			view.removeAllViews();
			view.addView(fen);
		}
		else if (data instanceof String)
		{
			Log.v(tag, "dans le update de historique");
			String str = (String)data;
			hist.init(str);
			view.removeAllViews();
			view.addView(hist);
		}
		else
		{
			Log.v(tag, "dans le update du formulaire");
			view.removeAllViews();
			form.reset();
			view.addView(form);
		}
	}
}
