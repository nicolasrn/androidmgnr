package com.android.graphisme.ui.corPion;

import android.content.Context;

import com.android.graphisme.composant.PionGraphique;
import com.android.graphisme.composant.PionJ1Deg;
import com.android.graphisme.composant.PionJ2Deg;

public class CorPionDeg extends COR
{
	public CorPionDeg() {
		super();
	}

	public CorPionDeg(COR suivant) {
		super(suivant);
	}

	@Override
	protected PionGraphique[] _resoudre(String pb, Context context) {
		PionGraphique tab[] = null;
		if (pb.trim().equals("Crade"))
		{
			tab = new PionGraphique[2];
			tab[0] = new PionJ1Deg(context);
			tab[1] = new PionJ2Deg(context);
		}
		return tab;
	}
}
