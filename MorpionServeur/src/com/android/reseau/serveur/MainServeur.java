package com.android.reseau.serveur;

import java.io.IOException;

/**
 * classe de lancement du serveur
 */
public class MainServeur
{
	public static void main(String[] a)
	{	
		//Connexion_bdd.envoi_gagne("1", "2");
		//System.out.println(Connexion_bdd.Affiche_score());
		try 
		{
			System.out.println("lancement du serveur");
			new Serveur();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
