package com.android.graphisme.composant;

import com.android.graphisme.composant.pion.PionGraphique;
import com.android.graphisme.composant.pion.PionVide;

import android.content.Context;
import android.graphics.Canvas;
import android.widget.ImageView;

public class SupportGraphique extends ImageView {
	private PionGraphique pion;
	
	public SupportGraphique(Context context) {
		super(context);
		pion = new PionVide();
	}
	
	public PionGraphique getPion()
	{
		return pion;
	}
	
	public void setPionGraphique(PionGraphique pion)
	{
		this.pion = pion;
	}
	
	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		pion.trace(canvas, this.getContext().getResources());
	}

}
