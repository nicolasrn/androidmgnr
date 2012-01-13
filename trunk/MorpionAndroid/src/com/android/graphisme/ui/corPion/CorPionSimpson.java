package com.android.graphisme.ui.corPion;

import android.content.Context;

import com.android.graphisme.composant.pion.PionGraphique;
import com.android.graphisme.composant.pion.PionJ1Simpson;
import com.android.graphisme.composant.pion.PionJ2Simpson;

public class CorPionSimpson extends COR
{
	public CorPionSimpson() {
		super();
	}

	public CorPionSimpson(COR suivant) {
		super(suivant);
	}

	@Override
	protected PionGraphique[] _resoudre(String pb, Context context) {
		PionGraphique tab[] = null;
		if (pb.trim().equals("Simpson"))
		{
			tab = new PionGraphique[2];
			tab[0] = new PionJ1Simpson(context);
			tab[1] = new PionJ2Simpson(context);
		}
		return tab;
	}
}
