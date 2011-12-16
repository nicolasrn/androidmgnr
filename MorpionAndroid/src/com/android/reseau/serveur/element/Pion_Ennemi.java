package com.android.reseau.serveur.element;

/**
 * Pion Ennemi correspond au joueur 2
 */
public class Pion_Ennemi extends Pion{

	public Pion_Ennemi() {
		super();
	}
	
	@Override
	public String toString() {
		return "O";
	}
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
