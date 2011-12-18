package com.android.graphisme.composant;

import java.util.Observable;
import java.util.Observer;

import com.android.graphisme.implementation.ActionClicCase;
import com.android.graphisme.implementation.Transmission;
import com.android.metier.Coordonnee;

import android.content.Context;
import android.widget.LinearLayout;

public class Grille extends LinearLayout implements Observer {
	private Case matriceCase[][];
	private int nbligne, nbcolonne;
	private Transmission trans;
	
	public static LinearLayout.LayoutParams layoutParam;
	public static Coordonnee coord;
	
	public Grille(Context context, int nbligne, int nbcolonne) {
		super(context);
		this.nbcolonne = nbcolonne;
		this.nbligne = nbligne;
		this.trans = null;
		
		matriceCase = new Case[nbligne][nbcolonne];
		layoutParam = new LinearLayout.LayoutParams(70, 70, 0);
		
		this.setOrientation(LinearLayout.VERTICAL);
		for(int i = 0; i < nbligne; i++)
		{
			LinearLayout ligne = new LinearLayout(context);
			for(int j = 0; j < nbcolonne; j++)
			{
				matriceCase[j][i] = new Case(context, j, i);
				ligne.addView(matriceCase[j][i], layoutParam);
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

	@Override
	public void update(Observable observable, Object data) {
		Case c = (Case)data;
		trans.transmettreMessage(c);
	}

	public void addEcouteurReseau(Transmission transmission) {
		trans = transmission;
	}
}
