package com.android.morpion;

import java.util.Observable;
import java.util.Observer;

import com.android.graphisme.ui.FenetreHistorique;
import com.android.graphisme.ui.FenetreJeu;
import com.android.graphisme.ui.FormulaireConnection;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MorpionFenetreHistoriqueActivity extends Activity implements Observer {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent thisIntent = getIntent();
		FenetreHistorique f = new FenetreHistorique(this);
		f.init(thisIntent.getExtras().getString("historique"));
		setContentView(f);
	}

	@Override
	public void update(Observable observable, Object data) {
		Intent intent = new Intent(this, MorpionConnexionActivity.class);
		startActivity(intent);
	}

}
