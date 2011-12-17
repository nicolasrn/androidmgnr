package com.android.reseau.serveur;

import java.io.IOException;

/**
 * classe de lancement du serveur
 */
public class MainServeur
{
	public static void main(String[] a)
	{		
		try 
		{
			new Serveur();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
