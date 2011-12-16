package com.android.reseau.serveur.element;

/**
 * Classe abstraite pion, un pion est posé sur une case 
 */
public abstract class Pion
{
	public Pion() 
	{
		super();
	}

	public abstract String toString ();
	public abstract boolean equals (Object obj);
	
}