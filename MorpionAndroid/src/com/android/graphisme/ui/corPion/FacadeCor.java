package com.android.graphisme.ui.corPion;

public class FacadeCor {
	private static COR cor;
	
	public static COR getCor()
	{
		if (cor == null)
		{
			cor = new CorPionClassique();
			cor = new CorPionSimpson(cor);
		}
		
		return cor;
	}
}
