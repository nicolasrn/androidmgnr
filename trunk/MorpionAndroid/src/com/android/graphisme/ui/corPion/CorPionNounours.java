package com.android.graphisme.ui.corPion;

import android.content.Context;

import com.android.graphisme.composant.pion.PionGraphique;
import com.android.graphisme.composant.pion.PionJ1Nounours;
import com.android.graphisme.composant.pion.PionJ2Nounours;

public class CorPionNounours extends COR
{
	public CorPionNounours() {
		super();
	}

	public CorPionNounours(COR suivant) {
		super(suivant);
	}

	@Override
	protected PionGraphique[] _resoudre(String pb, Context context) {
		PionGraphique tab[] = null;
		if (pb.trim().equals("Nounours"))
		{
			tab = new PionGraphique[2];
			tab[0] = new PionJ1Nounours(context);
			tab[1] = new PionJ2Nounours(context);
		}
		return tab;
	}
}
