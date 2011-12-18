package com.android.graphisme.ui;

import com.android.graphisme.composant.Grille;
import com.android.graphisme.composant.PionGraphique;
import com.android.graphisme.composant.SupportGraphique;
import com.android.graphisme.implementation.Reception;
import com.android.graphisme.implementation.Transmission;
import com.android.graphisme.ui.cor.FacadeCor;
import com.android.metier.DataConnexion;
import com.android.morpion.MorpionAndroidActivity;
import com.android.reseau.interpretation.Interpreteur;

import android.content.Context;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FenetreJeu extends LinearLayout {
	private Grille grille;
	private BandeauPresentation presentation;
	
	private Interpreteur interpret;
	//private DataConnexion data;
	private String info;
	
	private int tailleGrille;
	public static PionGraphique[] tabPion;

	public static int courant;
	
	public FenetreJeu(Context context, DataConnexion data) {
		super(context);
		//this.data = data;
		this.info = data.getInfo();
		this.interpret = new Interpreteur(data.getClient());
		tabPion = FacadeCor.getCor().resoudre(data.getClient().getType());
		
		for(PionGraphique p : tabPion)
			Log.v(MorpionAndroidActivity.tag, "" + p.getClass());
		
		//private String info = "joueur1;0;joueur2;1;tailleGrille";
		//nom joueur courant; numero courant; nom joueur suivant; numero joueur suivant; taille de la grille
		String def[] = info.split(";");
		
		courant = Integer.parseInt(def[1]);
		tailleGrille = Integer.parseInt(def[4]);

		/*
		 * definition de la grille
		 */
		grille = new Grille(context, tailleGrille, tailleGrille);
		
		/*
		 * definition de l'entete de la fenetre
		 */
		presentation = new BandeauPresentation(context, tabPion[courant], tabPion[(courant+1)%2], def);
		
		//remplacer par un dp observer
		grille.addEcouteurReseau(new Transmission(this));
		Reception r = new Reception(this);
		r.start();
		
		//this.grille = new Grille(context, nbLigne, nbLigne);
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

class BandeauPresentation extends LinearLayout
{
	private TextView playerA, playerB, oppo;
	private SupportGraphique imgPlayerA, imgPlayerB;
	
	public BandeauPresentation(Context context, PionGraphique pionj1, PionGraphique pionj2, String[] def) {
		super(context);
		this.setOrientation(LinearLayout.HORIZONTAL);
		
		playerA = new TextView(context);
		playerB = new TextView(context);
		oppo = new TextView(context);
		imgPlayerA = new SupportGraphique(context);
		imgPlayerB = new SupportGraphique(context);
		
		playerA.setText(def[0]);
		playerB.setText(def[2]);
		oppo.setText("versus");
		
		imgPlayerA.setPionGraphique(pionj1);
		imgPlayerB.setPionGraphique(pionj2);
		
		LayoutParams param = new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1);
		this.addView(playerA, param);
		this.addView(imgPlayerA, Grille.layoutParam);
		
		this.addView(oppo, param);

		this.addView(playerB, param);
		this.addView(imgPlayerB, Grille.layoutParam);
	}
}
