package com.android.graphisme.composant.pion;

import com.android.graphisme.composant.Util;
import com.android.morpion.R;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class PionJ1 extends PionGraphique {
	
	public PionJ1(Context context) {
		super(context);
		//if (imageType1 == null)
			imageType1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.imgcroix);
		//if (imageType1 == null)
			//imageType1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.imgcroix);
	}

	@Override
	public void trace(Canvas canvas, Resources resource) {
		Paint p = new Paint();
		p.setColor(Color.RED);
		
		canvas.drawBitmap(imageType1, new Rect(0, 0, imageType1.getWidth(), imageType1.getHeight()), new Rect(0, 0, Util.getInstance().getLayoutParamsGrille().width, Util.getInstance().getLayoutParamsGrille().height), p);
	}

	@Override
	public void traceLogo(Canvas canvas, Resources resource) {
		Paint p = new Paint();
		p.setColor(Color.RED);
		
		canvas.drawBitmap(imageType1, new Rect(0, 0, imageType1.getWidth(), imageType1.getHeight()), new Rect(0, 0, Util.getInstance().getLayoutParamsBandeau().width, Util.getInstance().getLayoutParamsBandeau().height), p);
	}

}
