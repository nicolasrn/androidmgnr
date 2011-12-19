package com.android.graphisme.composant;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public abstract class PionGraphique {
	/**
	 * image representant d'un joueur
	 */
	protected static Bitmap imageType1;
	
	/**
	 * image representant l'autre joueur
	 */
	protected static Bitmap imageType2;
	
	public abstract void trace(Canvas canvas, Resources resource);
	
	public abstract void traceLogo(Canvas canvas, Resources resource);
}
