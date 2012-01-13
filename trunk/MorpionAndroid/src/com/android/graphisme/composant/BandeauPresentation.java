package com.android.graphisme.composant;

import com.android.graphisme.composant.pion.PionGraphique;

import android.content.Context;
import android.graphics.Canvas;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BandeauPresentation extends LinearLayout
{
	private TextView playerA, playerB, oppo;
	private SupportGraphique imgPlayerA, imgPlayerB;
	
	public BandeauPresentation(final Context context, final PionGraphique pionj1, final PionGraphique pionj2, String[] def) {
		super(context);
		this.setOrientation(LinearLayout.HORIZONTAL);
		
		playerA = new TextView(context);
		playerB = new TextView(context);
		oppo = new TextView(context);
		imgPlayerA = new SupportGraphique(context)
		{
			@Override
			public void draw(Canvas canvas) {
				pionj1.traceLogo(canvas, context.getResources());
			}
		};
		imgPlayerB = new SupportGraphique(context)
		{
			@Override
			public void draw(Canvas canvas) {
				pionj2.traceLogo(canvas, context.getResources());
			}
		};
		
		playerA.setText(def[0]);
		playerB.setText(def[2]);
		oppo.setText("versus");
		
		imgPlayerA.setPionGraphique(pionj1);
		imgPlayerB.setPionGraphique(pionj2);
		
		LayoutParams param = new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1);
		this.addView(playerA, param);
		this.addView(imgPlayerA, Util.getInstance().getLayoutParamsBandeau());
		
		this.addView(oppo, param);

		this.addView(playerB, param);
		this.addView(imgPlayerB, Util.getInstance().getLayoutParamsBandeau());
		
		this.setPadding(5, 5, 5, 5);
	}
}
