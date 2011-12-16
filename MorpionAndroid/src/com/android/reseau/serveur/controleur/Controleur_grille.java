package com.android.reseau.serveur.controleur;

import com.android.reseau.serveur.element.*;

/**
 * Un controleur de grille vérifie si un joueur a gagné 
 * gc : le controleur doit avoir acces à la grille de jeu
 */
public class Controleur_grille {

	private Grille_serveur gc;
	
	public Controleur_grille (Grille_serveur g)
	{
		gc=g;
	}
	/**
	 * 
	 * @param x
	 * @param y
	 * @return boolean : renvoi vrai si la ligne comporte les même motifs
	 */
	public boolean controle_ligne(int x,int y){
		for(int j = 0 ; j < gc.getNb_case() ; j++)
 		{
			if (!gc.getCase(j, y).equals(gc.getCase(x, y)))
				return false;
		}
		return true;
	}
	/**
	 * 
	 * @param x
	 * @param y
	 * @return boolean : renvoi vrai si la colonne comporte les même motifs
	 */
	public boolean controle_colonne(int x,int y){
		for(int i = 0 ; i < gc.getNb_case() ; i++)
		{
			if (!gc.getCase(x, i).equals(gc.getCase(x, y)))
				return false;
		}
		return true;
	}
	/**
	 * 
	 * @param x
	 * @param y
	 * @return boolean : renvoi vrai si les diagonales comportent les même motifs
	 */
	public boolean controle_diag(int x,int y){
		if (x == y)
		{
			for (int i = 0; i<gc.getNb_case();i++)
			{
				if (!gc.getCase(i, i).equals(gc.getCase(x, y)))
					return false;
			}
		}
		else if (x + y == gc.getNb_case()-1)
		{
			for (int i = 0; i < gc.getNb_case();i++)
			{
				if (! gc.getCase(i, (gc.getNb_case()-1)-i).equals(gc.getCase(x, y)))
					return false;
			}
			
		}
		else
		{
			return false;
		}
		return true;
	}
	/**
	 * 
	 * @param x
	 * @param y
	 * @return renvoie vrai si une diagonale, une ligne ou une colonne est complète
	 */
	public boolean controle (int x, int y )
	{
		return (controle_colonne(x, y)||controle_ligne(x,y)||controle_diag(x,y));
	}
}
