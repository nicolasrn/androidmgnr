package com.android.graphisme.composant;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class PionVide extends PionGraphique {

	@Override
	public void trace(Canvas canvas, Resources resource) {
		Paint p = new Paint();
		p.setColor(Color.RED);
		canvas.drawText("coucou", 20, 20, p);
	}

}
