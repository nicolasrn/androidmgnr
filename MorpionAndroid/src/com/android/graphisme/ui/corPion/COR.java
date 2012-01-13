package com.android.graphisme.ui.corPion;

import android.content.Context;

import com.android.graphisme.composant.pion.PionGraphique;

public abstract class COR {
	private COR suivant;
	
	public COR()
	{
		this(null);
	}
	
	public COR(COR suivant)
	{
		this.suivant = suivant;
	}
	
	public COR add(COR suivant)
	{
		this.suivant = suivant;
		return suivant;
	}
	
	public PionGraphique[] resoudre(String pb, Context context)
	{
		PionGraphique []solution = this._resoudre(pb, context); // récupération solution locale
		
		if (solution != null) //si solution locale
			return solution;
		else if (suivant != null) //pas de solution on regarde le suivant
			return suivant.resoudre(pb, context);
		return null;
	}
	
	protected abstract PionGraphique[] _resoudre(String pb, Context context);
	
}
