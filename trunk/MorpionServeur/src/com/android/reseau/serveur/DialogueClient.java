package com.android.reseau.serveur;

import java.io.IOException;
import java.io.PrintWriter;

import com.android.reseau.jeu.Jeu;
import com.android.reseau.serveur.element.Grille_serveur;
import com.android.reseau.sql.Connexion_bdd;

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
			
			System.out.println("le jeu est terminer maintenant 'historique !!");
			Thread.sleep(1000);
			
			PrintWriter out = new PrintWriter(client1.getSocket().getOutputStream()), 
						outg = new PrintWriter(client2.getSocket().getOutputStream());
			
			System.out.println("envoie score perdant");
			out.println(Connexion_bdd.Affiche_score());
			out.flush();
			System.out.println("fin envoie score perdant");
			
			System.out.println("envoie score gagnant");
			outg.println(Connexion_bdd.Affiche_score());
			outg.flush();
			System.out.println("fin envoie score gagnant");
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
