package com.android.graphisme.ui;

import com.android.graphisme.composant.BandeauPresentation;
import com.android.graphisme.composant.Grille;
import com.android.graphisme.composant.PionGraphique;
import com.android.graphisme.composant.Util;
import com.android.graphisme.implementation.Reception;
import com.android.graphisme.implementation.Transmission;
import com.android.graphisme.ui.corPion.FacadeCor;
import com.android.metier.DataConnexion;
import com.android.morpion.MorpionAndroidActivity;
import com.android.reseau.interpretation.Interpreteur;

import android.content.Context;
import android.util.Log;
import android.widget.LinearLayout;

public class FenetreJeu extends LinearLayout {
	private Grille grille;
	private BandeauPresentation presentation;
	
	private Interpreteur interpret;
	//private DataConnexion data;
	private String info;
	
	private String []def;
	
	private int tailleGrille;
	
	public static PionGraphique[] tabPion;
	
	public static int courant;
	
	public FenetreJeu(Context context) {
		super(context);
	}
	
	public FenetreJeu(Context context, DataConnexion data) {
		super(context);
		init(data);
	}
	
	public void init(DataConnexion data)
	{
		this.removeAllViews();
		
		this.info = data.getInfo();
		this.interpret = new Interpreteur(data.getClient());
		tabPion = FacadeCor.getCor().resoudre(data.getClient().getType(), this.getContext());
		
		for(PionGraphique p : tabPion)
			Log.v(MorpionAndroidActivity.tag, "" + p.getClass());
		
		//private String info = "joueur1;0;joueur2;1;tailleGrille";
		//nom joueur courant; numero courant; nom joueur suivant; numero joueur suivant; taille de la grille
		def = info.split(";");
		
		courant = Integer.parseInt(def[1]);
		tailleGrille = Integer.parseInt(def[4]);
		Util.getInstance(this.getContext(), tailleGrille);
		
		/*
		 * definition de la grille
		 */
		grille = new Grille(this.getContext(), tailleGrille, tailleGrille);
		
		/*
		 * definition de l'entete de la fenetre
		 */
		presentation = new BandeauPresentation(this.getContext(), tabPion[courant], tabPion[(courant+1)%2], def);
		
		//remplacer par un dp observer
		grille.addEcouteurReseau(new Transmission(this));
		Reception r = new Reception(this);
		r.start();
		
		this.setOrientation(LinearLayout.VERTICAL);
		this.addView(presentation);
		this.addView(grille);
	}

	public Interpreteur getIterpret() {
		return interpret;
	}

	public Grille getGrille() {
		return grille;
	}

}
