package com.android.graphisme.implementation;

import com.android.graphisme.composant.Case;
import com.android.graphisme.composant.Grille;
import com.android.graphisme.composant.PionJ2Deg;

import android.view.View;
import android.view.View.OnClickListener;

public class ActionClicCase implements OnClickListener {
	@SuppressWarnings("unused")
	private Grille grille;
	private Case eltCase;
	
	public ActionClicCase(Grille grille, Case eltCase) {
		this.grille = grille;
		this.eltCase = eltCase;
	}
	
	@Override
	public void onClick(View v) {
		eltCase.activerImage(new PionJ2Deg());
	}

}
