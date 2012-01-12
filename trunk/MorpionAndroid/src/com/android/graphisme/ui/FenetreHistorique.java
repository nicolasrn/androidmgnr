package com.android.graphisme.ui;

import java.util.Observable;

import com.android.morpion.MorpionAndroidActivity;
import com.android.morpion.MorpionFenetreHistoriqueActivity;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FenetreHistorique extends LinearLayout {
	private Button b;
	
	public FenetreHistorique(Context context) {
		super(context);
		this.setOrientation(LinearLayout.VERTICAL);
		
		TextView tex = new TextView(context);
		tex.setText("Historique");
		tex.setTextSize(16);
		
		b = new Button(this.getContext());
		b.setText("Continuer");
		b.setOnClickListener(new ContinuerHistorique());
	}
	
	private class ContinuerHistorique extends Observable implements  OnClickListener
	{

		public ContinuerHistorique() {
			super();
			this.addObserver((MorpionFenetreHistoriqueActivity)getContext());
		}

		@Override
		public void onClick(View v) {
			this.setChanged();
			this.notifyObservers();
		}

	}
	
	public void init(String str)
	{
		this.removeAllViews();
		
		for(String s : str.split(";"))
		{
			TextView tmp = new TextView(this.getContext());
			tmp.setText(s);
			this.addView(tmp);
		}
		
		this.addView(b);
	}
}
