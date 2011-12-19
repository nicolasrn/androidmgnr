package com.android.graphisme.composant;

import com.android.morpion.MorpionAndroidActivity;

import android.content.Context;
import android.view.Display;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class Util {
	private LinearLayout.LayoutParams layoutParamsBandeau;
	private LinearLayout.LayoutParams layoutParamsGrille;
	@SuppressWarnings("unused")
	private Context context;
	@SuppressWarnings("unused")
	private int nbligne;
	
	private static Util util = null;
	
	private Util(Context context, int nbligne) 
	{
		this.context = context;
		this.nbligne = nbligne;
		
		Display ecran = ((MorpionAndroidActivity)context).getWindowManager().getDefaultDisplay();
		int largeur= ecran.getWidth();
		int hauteur= ecran.getHeight();
		int taille = Math.min(largeur, hauteur)/nbligne;
		
		layoutParamsGrille = new LayoutParams(taille, taille, 0);
		layoutParamsBandeau = new LayoutParams(taille/3, taille/3, 0);
	}
	
	public LinearLayout.LayoutParams getLayoutParamsBandeau() {
		return layoutParamsBandeau;
	}

	public LinearLayout.LayoutParams getLayoutParamsGrille() {
		return layoutParamsGrille;
	}

	public static Util getInstance(Context context, int nbligne)
	{
		if (util == null)
			util = new Util(context, nbligne);
		return util;
	}
	
	public static void reset()
	{
		util = null;
	}
	
	public static Util getInstance()
	{
		return util;
	}
}
