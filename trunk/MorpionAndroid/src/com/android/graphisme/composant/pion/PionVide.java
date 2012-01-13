package com.android.graphisme.composant.pion;


import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class PionVide extends PionGraphique {

	public PionVide() {
		super(null);
	}

	@Override
	public void trace(Canvas canvas, Resources resource) {
		Paint p = new Paint();
		p.setColor(Color.RED);
		canvas.drawText("coucou", 20, 20, p);
	}

	@Override
	public void traceLogo(Canvas canvas, Resources resource) {
		trace(canvas, resource);
	}

}
