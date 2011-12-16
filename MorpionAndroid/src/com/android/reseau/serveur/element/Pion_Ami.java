package com.android.reseau.serveur.element;

/**
 * Pion Ami correspond au joueur 1
 */
public class Pion_Ami extends Pion{
	
	public Pion_Ami() {
		super();
	}
	
	@Override
	public String toString() {
		return "X";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		return true;
	}

	
	
	


}
