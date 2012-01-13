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

public class PionJ2 extends PionGraphique {
	
	public PionJ2(Context context) {
		super(context);
		//if (imageType2 == null)
		//	imageType2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.imgrond);
		//if (imageType2 == null)
			imageType2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.imgrond);
	}

	@Override
	public void trace(Canvas canvas, Resources resource) {
		Paint p = new Paint();
		p.setColor(Color.RED);
		
		canvas.drawBitmap(imageType2, new Rect(0, 0, imageType2.getWidth(), imageType2.getHeight()), new Rect(0, 0, Util.getInstance().getLayoutParamsGrille().width, Util.getInstance().getLayoutParamsGrille().height), p);
		//Image(imageType1, 0, 0, CaseGrille.dimension.width, CaseGrille.dimension.height, null);
	}

	@Override
	public void traceLogo(Canvas canvas, Resources resource) {
		Paint p = new Paint();
		p.setColor(Color.RED);
		
		canvas.drawBitmap(imageType2, new Rect(0, 0, imageType2.getWidth(), imageType2.getHeight()), new Rect(0, 0, Util.getInstance().getLayoutParamsBandeau().width, Util.getInstance().getLayoutParamsBandeau().height), p);
		//Image(imageType1, 0, 0, CaseGrille.dimension.width, CaseGrille.dimension.height, null);
	}
}
