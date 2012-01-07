package com.android.graphisme.ui;

import java.util.Observable;
import java.util.Observer;

import com.android.morpion.MorpionAndroidActivity;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FenetreHistorique extends LinearLayout {
	
	public FenetreHistorique(Context context) {
		super(context);
		this.setOrientation(LinearLayout.VERTICAL);
	}
	
	private class ContinuerHistorique extends Observable implements  OnClickListener
	{

		public ContinuerHistorique() {
			super();
			this.addObserver((MorpionAndroidActivity)getContext());
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
		
		Button b = new Button(this.getContext());
		b.setText("Continuer");
		b.setOnClickListener(new ContinuerHistorique());
		
		this.addView(b);
	}
}
