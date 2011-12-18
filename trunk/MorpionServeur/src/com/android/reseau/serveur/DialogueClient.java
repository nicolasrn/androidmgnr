package com.android.reseau.serveur;

import com.android.reseau.jeu.Jeu;
import com.android.reseau.serveur.element.Grille_serveur;

/**
 * 
 * Thread de dialogue : mets en dialogue les 2 clients et vérifie la grille
 *
 */
public class DialogueClient extends Thread
{
	@SuppressWarnings("unused")
	private SocketJoueur client1, client2;
	private Jeu j;
	/**
	 * Constructeur
	 * @param nom
	 * @param client1
	 * @param client2
	 */
	public DialogueClient(String nom,SocketJoueur client1, SocketJoueur client2) 
	{
		super(nom);
		this.client1 = client1;
		this.client2 = client2;
		this.j = new Jeu (new Grille_serveur (Serveur.tailleGrille), "processus jeu",client1,client2);
		start();
	}
	/**
	 * Verifie la grille de jeu via la classe jeu
	 */
	public void run()
	{
		System.out.println("client 1 et 2 en phase de dialogue");
		
		try 
		{
			j.start();
			j.join();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	
}
