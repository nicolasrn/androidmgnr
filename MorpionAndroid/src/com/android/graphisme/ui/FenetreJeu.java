package com.android.graphisme.ui;

import com.android.graphisme.composant.Grille;
import com.android.graphisme.composant.PionJ1;
import com.android.graphisme.composant.PionJ2;
import com.android.graphisme.composant.SupportGraphique;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FenetreJeu extends LinearLayout {
	private Grille grille;
	
	public FenetreJeu(Context context, int nbLigne) {
		super(context);
		this.grille = new Grille(context, nbLigne, nbLigne);
		this.setOrientation(LinearLayout.VERTICAL);
		this.addView(new BandeauPresentation(context));
		this.addView(grille);
	}

}

class BandeauPresentation extends LinearLayout
{
	private TextView playerA, playerB, oppo;
	private SupportGraphique imgPlayerA, imgPlayerB;
	
	public BandeauPresentation(Context context) {
		super(context);
		this.setOrientation(LinearLayout.HORIZONTAL);
		
		playerA = new TextView(context);
		playerB = new TextView(context);
		oppo = new TextView(context);
		imgPlayerA = new SupportGraphique(context);
		imgPlayerB = new SupportGraphique(context);
		
		playerA.setText("Joueur1");
		playerB.setText("Joueur2");
		oppo.setText("versus");
		
		imgPlayerA.setPionGraphique(new PionJ1());
		imgPlayerB.setPionGraphique(new PionJ2());
		
		LayoutParams param = new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1);
		this.addView(playerA, param);
		this.addView(imgPlayerA, Grille.layoutParam);
		
		this.addView(oppo, param);

		this.addView(playerB, param);
		this.addView(imgPlayerB, Grille.layoutParam);
	}
}
