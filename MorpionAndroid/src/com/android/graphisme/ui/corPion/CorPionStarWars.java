package com.android.graphisme.ui.corPion;

import android.content.Context;

import com.android.graphisme.composant.pion.PionGraphique;
import com.android.graphisme.composant.pion.PionJ1StarWars;
import com.android.graphisme.composant.pion.PionJ2StarWars;

public class CorPionStarWars extends COR
{
	public CorPionStarWars() {
		super();
	}

	public CorPionStarWars(COR suivant) {
		super(suivant);
	}

	@Override
	protected PionGraphique[] _resoudre(String pb, Context context) {
		PionGraphique tab[] = null;
		if (pb.trim().equals("Star Wars"))
		{
			tab = new PionGraphique[2];
			tab[0] = new PionJ1StarWars(context);
			tab[1] = new PionJ2StarWars(context);
		}
		return tab;
	}
}
