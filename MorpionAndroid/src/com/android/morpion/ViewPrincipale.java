package com.android.morpion;

import android.content.Context;
import android.widget.ScrollView;

public class ViewPrincipale extends ScrollView {

	public ViewPrincipale(Context context) {
		super(context);
	}
	
	public Object getData()
	{
		return null;
		//((RecuperableData)this.getChildAt(0)).getData();
	}
}
