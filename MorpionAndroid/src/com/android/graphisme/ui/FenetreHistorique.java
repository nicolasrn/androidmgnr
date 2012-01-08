package com.android.graphisme.ui;

import java.util.Observable;
import java.util.Observer;

import com.android.graphisme.composant.Util;
import com.android.morpion.MorpionAndroidActivity;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FenetreHistorique extends LinearLayout {
	private LinearLayout layout;
	private Button b;
	
	public FenetreHistorique(Context context) {
		super(context);
		this.setOrientation(LinearLayout.VERTICAL);
		
		layout = new LinearLayout(context);
		layout.setOrientation(LinearLayout.VERTICAL);
		
		b = new Button(this.getContext());
		b.setText("Continuer");
		b.setOnClickListener(new ContinuerHistorique());
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
		layout.removeAllViews();
		
		for(String s : str.split(";"))
		{
			TextView tmp = new TextView(this.getContext());
			tmp.setText(s);
			layout.addView(tmp);
			//this.addView(tmp);
		}
		
		layout.addView(b);
		this.addView(layout);
	}
}
