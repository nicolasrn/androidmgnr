package com.android.reseau.serveur.element;
/**
 * Classe de la grille de jeu côté serveur
 * grille : grille de jeu composée de pion
 * nbre : nombre de cases
 */
public class Grille_serveur {
	
	private Pion [][] grille;
	private int nbre;
	
	public Grille_serveur (int nbre_case)
	{
		super();
		grille = new Pion [nbre_case][nbre_case];
		nbre = nbre_case;
		for ( int i = 0;i < nbre;i++ )
		{
			for ( int j = 0;j < nbre;j++ )
			{
				grille[i][j] = new Pion_vide();
			}
		}
	}
	/**
	 * renvoi une représentation de la grille sous forme de String
	 */
	public String toString(){
		String result="";
		for ( int i = 0;i < nbre;i++ )
		{
			for ( int j = 0;j < nbre;j++ )
			{
				result+=" | "+grille[i][j].toString();
			}
			result+=" |\n";
		}
		return result;
	}
	public int getNb_case(){
		return nbre;
	}
	/**
	 * @param x
	 * @param y
	 * @return Pion : renvoi une case qui a comme coordonné x et y
	 */
	
	public Pion getCase(int x,int y){
		return grille [y][x];
	}
	/**
	 * Modifie la case x,y parle pion p
	 * @param x
	 * @param y
	 * @param p
	 */
	public void setCase(int x,int y, Pion p)
	{
		grille[y][x]=p;
	}
	/**
	 * 
	 * @return retourne la grille 
	 */
	public Pion [][] getGrille(){
		return grille;
	}
	
}
