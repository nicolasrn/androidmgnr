package com.android.graphisme.ui.cor;

public class FacadeCor {
	private static COR cor;
	
	public static COR getCor()
	{
		if (cor == null)
		{
			cor = new CorPionClassique();
			cor = new CorPionDeg(cor);
		}
		
		return cor;
	}
}
