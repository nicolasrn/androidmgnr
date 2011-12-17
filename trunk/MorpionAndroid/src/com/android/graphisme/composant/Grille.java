package com.android.graphisme.composant;

import com.android.graphisme.implementation.ActionClicCase;
import com.android.metier.Coordonnee;

import android.content.Context;
import android.widget.LinearLayout;

public class Grille extends LinearLayout {
	private Case matriceCase[][];
	private int nbligne, nbcolonne;
	public static LinearLayout.LayoutParams layoutParam;
	public static Coordonnee coord;
	
	public Grille(Context context, int nbligne, int nbcolonne) {
		super(context);
		this.nbcolonne = nbcolonne;
		this.nbligne = nbligne;
		
		matriceCase = new Case[nbcolonne][nbligne];
		layoutParam = new LinearLayout.LayoutParams(70, 70, 0);
		
		this.setOrientation(LinearLayout.VERTICAL);
		for(int i = 0; i < nbligne; i++)
		{
			LinearLayout ligne = new LinearLayout(context);
			for(int j = 0; j < nbcolonne; j++)
			{
				matriceCase[i][j] = new Case(context, i, j);
				ligne.addView(matriceCase[i][j], layoutParam);
			}
			this.addView(ligne);
		}
		activerGrille();
	}
	
	public void activerGrille()
	{
		for(int i = 0; i < nbligne; i++)
			for(int j = 0; j < nbcolonne; j++)
				matriceCase[i][j].getButton().setOnClickListener(new ActionClicCase(this, matriceCase[i][j]));
	}
	
	public void desactiverGrille()
	{
		for(int i = 0; i < nbligne; i++)
			for(int j = 0; j < nbcolonne; j++)
				matriceCase[i][j].getButton().setOnClickListener(null);
	}

	public Case[][] getMatriceCase() {
		return matriceCase;
	}

	public int getNbligne() {
		return nbligne;
	}

	public int getNbcolonne() {
		return nbcolonne;
	}

	public Case getObjetTableauAt(int x, int y) {
		return this.matriceCase[x][y];
	}
}
