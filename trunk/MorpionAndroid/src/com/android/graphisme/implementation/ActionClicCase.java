package com.android.graphisme.implementation;

import java.util.Observable;

import com.android.graphisme.composant.Case;
import com.android.graphisme.composant.Grille;

import android.view.View;
import android.view.View.OnClickListener;

public class ActionClicCase extends Observable implements OnClickListener {
	@SuppressWarnings("unused")
	private Grille grille;
	private Case eltCase;
	
	public ActionClicCase(Grille grille, Case eltCase) {
		this.grille = grille;
		this.eltCase = eltCase;
		this.addObserver(grille);
	}
	
	@Override
	public void onClick(View v) {
		Grille.coord = eltCase.getCoordonee();
		this.setChanged();
		this.notifyObservers(eltCase);
	}

}
