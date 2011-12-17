package com.android.graphisme.ui.cor;

import com.android.graphisme.composant.PionGraphique;

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
	
	public PionGraphique[] resoudre(String pb)
	{
		PionGraphique []solution = this._resoudre(pb); // récupération solution locale
		
		if (solution != null) //si solution locale
			return solution;
		else if (suivant != null) //pas de solution on regarde le suivant
			return suivant.resoudre(pb);
		return null;
	}
	
	protected abstract PionGraphique[] _resoudre(String pb);
	
}
