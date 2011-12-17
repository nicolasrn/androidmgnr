package com.android.graphisme.ui.cor;

import com.android.graphisme.composant.PionGraphique;
import com.android.graphisme.composant.PionJ1;
import com.android.graphisme.composant.PionJ2;

public class CorPionClassique extends COR
{
	public CorPionClassique() {
		super();
	}

	public CorPionClassique(COR suivant) {
		super(suivant);
	}

	@Override
	protected PionGraphique[] _resoudre(String pb) {
		PionGraphique tab[] = null;
		if (pb.trim().equals("Classique"))
		{
			tab = new PionGraphique[2];
			tab[0] = new PionJ1();
			tab[1] = new PionJ2();
		}
		return tab;
	}
}
