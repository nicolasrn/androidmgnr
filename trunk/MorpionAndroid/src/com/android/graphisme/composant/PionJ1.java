package com.android.graphisme.composant;

import com.android.morpion.R;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class PionJ1 extends PionGraphique {
	
	@Override
	public void trace(Canvas canvas, Resources resource) {
		Paint p = new Paint();
		p.setColor(Color.RED);
		
		if (imageType1 == null)
			imageType1 = BitmapFactory.decodeResource(resource, R.drawable.imgcroix);
		canvas.drawBitmap(imageType1, new Rect(0, 0, imageType1.getWidth(), imageType1.getHeight()), new Rect(0, 0, Util.getInstance().getLayoutParamsGrille().width, Util.getInstance().getLayoutParamsGrille().height), p);
	}

	@Override
	public void traceLogo(Canvas canvas, Resources resource) {
		Paint p = new Paint();
		p.setColor(Color.RED);
		
		if (imageType1 == null)
			imageType1 = BitmapFactory.decodeResource(resource, R.drawable.imgcroix);
		canvas.drawBitmap(imageType1, new Rect(0, 0, imageType1.getWidth(), imageType1.getHeight()), new Rect(0, 0, Util.getInstance().getLayoutParamsBandeau().width, Util.getInstance().getLayoutParamsBandeau().height), p);
	}

}
