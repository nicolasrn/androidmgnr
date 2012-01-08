package com.android.reseau.serveur.controleur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import com.android.metier.Coordonnee;
import com.android.reseau.serveur.SocketJoueur;
import com.android.reseau.sql.Connexion_bdd;


/**
 * indique l'ordre de jeu
 */
public class Controleur_tour {
	private SocketJoueur tab_socket [];
	/**
	 * tour : indique le numero du joueur autorisé à jouer 
	 */
	private int tour ;
	
	/**
	 * 
	 * @param c1
	 * @param c2
	 */
	public Controleur_tour  (SocketJoueur c1, SocketJoueur c2){
		tab_socket = new SocketJoueur[2];
		tab_socket [0] = c1;
		tab_socket [1] = c2;
		tour = 0;
	}
	
	/**
	 * passe au tour suivant
	 *
	 */
	public void tour_suivant (){
		tour = (tour+1) % 2;
	}
	
	/**
	 * Indique au joueur courant qu'il a la main en fonction du numéro du tour
	 * @throws IOException
	 */
	public void ordre_jeu() throws IOException{
		PrintWriter out;
		
		System.out.println("envoie d'un ordre jeu");
		out = new PrintWriter(tab_socket[tour].getSocket().getOutputStream(), false);
		out.println("joue");
		out.flush();
		System.out.println("fin envoie d'un ordre jeu");
	}
	
	/**
	 * Reçoit du joueur courant les coordonnées du pion ajouté 
	 * @return Coordonnee
	 * @throws IOException
	 */
	public Coordonnee getCoordClient() throws IOException
	{
		System.out.println("reception coordonnŽe client");
		BufferedReader lect1;
		lect1 = new BufferedReader(new InputStreamReader(tab_socket[tour].getSocket().getInputStream()), 8*1024);
		return new Coordonnee (lect1.readLine());
	}
	
	/**
	 * Envoi au joueur concurant les coordonnées du pion ajouté par le joueur courant
	 * @param c
	 * @throws IOException
	 * 
	 */
	public void envoiClientConcurant (Coordonnee c) throws IOException
	{
		System.out.println("envoie des coordonnŽes au client suivant");
		PrintWriter out;
		out = new PrintWriter(tab_socket[(tour+1) % 2].getSocket().getOutputStream(), false);
		
		out.println(c.toString());
		out.flush();
		System.out.println("fin envoie des coordonnŽes au client suivant");
	}
	
	public int gettour()
	{
		return tour;
	}
	
	/**
	 * Envoi les message de fin de jeu ==> gagné au joueur qui doit gagné perdu à l'autre
	 * @throws IOException  
	 */
	public void envoiResultat(boolean matchNull) throws IOException
	{
		PrintWriter out,outg;
		outg = new PrintWriter(tab_socket[(tour+1)%2].getSocket().getOutputStream(), false);
		out = new PrintWriter(tab_socket[tour].getSocket().getOutputStream(), false);
		
		if (!matchNull)
		{
			//le gagnant est le joueur courant
			System.out.println("envoie de la victoire");
			outg.println("win");
			outg.flush();
			System.out.println("fin envoie de la victoire");
			
			Connexion_bdd.envoi_gagne(tab_socket[(tour+1)%2].getJoueur(), tab_socket[tour].getJoueur());
			
			// le perdant
			System.out.println("envoie de la defaite");
			out.println("lose");
			out.flush();
			System.out.println("fin envoie de la defaite");
		}
		else
		{
			//le gagnant est le joueur courant
			System.out.println("envoie du match null 1");
			outg.println("matchnull");
			outg.flush();
			System.out.println("fin envoie du match null 1");
			
			Connexion_bdd.envoi_nul(tab_socket[tour].getJoueur(), tab_socket[(tour+1)%2].getJoueur());
			
			// le perdant
			System.out.println("envoie du match null 2");
			out.println("matchnull");
			out.flush();
			System.out.println("fin envoie du match null 2");
		}
		System.out.println("envoie score perdant");
		out.println(Connexion_bdd.Affiche_score());
		out.flush();
		System.out.println("fin envoie score perdant");
		
		System.out.println("envoie score gagnant");
		outg.println(Connexion_bdd.Affiche_score());
		outg.flush();
		System.out.println("fin envoie score gagnant");
	}
}
