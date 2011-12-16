package com.android.reseau.serveur.element;

/**
 * Pion vide
 */
public class Pion_vide extends Pion{
	public Pion_vide() {
		super();
	}
	
	@Override
	public String toString() {
		return "_";
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
