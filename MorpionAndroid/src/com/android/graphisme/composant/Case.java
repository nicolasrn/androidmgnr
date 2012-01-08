package com.android.graphisme.composant;

import com.android.metier.Coordonnee;

import android.content.Context;
import android.widget.Button;
import android.widget.LinearLayout;

public class Case extends LinearLayout {
	private Coordonnee coord;
	private Button bouton;
	private SupportGraphique pion;
	
	public Case(Context context, int cx, int cy) {
		this(context, new Coordonnee(cx, cy));
	}
	
	public Case(Context context, Coordonnee c)
	{
		super(context);
		coord = c;
		
		bouton = new Button(this.getContext());
		pion = new SupportGraphique(this.getContext());
		bouton.setText(coord.toString());
		
		activerBouton();
	}

	public void activerImage(PionGraphique p)
	{
		pion.setPionGraphique(p);
		
		while(this.getChildCount() != 0)
			this.removeAllViews();
		this.addView(pion, Util.getInstance().getLayoutParamsGrille());
		
		pion.invalidate();
	}
	
	public Button getButton()
	{
		return this.bouton;
	}
	
	public void activerBouton()
	{
		this.addView(bouton, Util.getInstance().getLayoutParamsGrille());
	}
	
	public Coordonnee getCoordonee()
	{
		return coord;
	}
	
	public int getX() {
		return coord.x;
	}

	public void setX(int x) {
		this.coord.x = x;
	}

	public int getY() {
		return coord.y;
	}

	public void setY(int y) {
		this.coord.y = y;
	}

}
