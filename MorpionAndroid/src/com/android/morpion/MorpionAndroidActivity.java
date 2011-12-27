package com.android.morpion;

import java.util.Observable;
import java.util.Observer;

import com.android.graphisme.ui.FenetreJeu;
import com.android.graphisme.ui.FormulaireConnection;
import com.android.metier.DataConnexion;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ScrollView;

public class MorpionAndroidActivity extends Activity implements Observer
{
	public static final String tag = "MorpionAndroidActivity";
	private FormulaireConnection form;
	private FenetreJeu fen;
	private ScrollView view;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.v(tag, "savedInstanceState : " + (savedInstanceState == null));
		super.onCreate(savedInstanceState);
		
		if (savedInstanceState == null)
		{
			view = new ScrollView(this);
			form = new FormulaireConnection(this);
			
			view.addView(form);
		}
		else
		{
			Log.v(tag, "onCreate avant view : " + (view == null));
			Log.v(tag, "onCreate apres view : " + (view == null));
		}
		setContentView(view);
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	@Override
	public void update(Observable observable, Object data) {
		//connexion au serveur puis lancer l'interface du morpion
		DataConnexion dataConnexion = (DataConnexion) data;
		Log.v(tag, "dans l'update");
		//la grille est construite par le serveur (parametres sont juste transmis)
		fen = new FenetreJeu(this, dataConnexion);
		view.removeAllViews();
		view.addView(fen);
		setContentView(view);
	}
}
