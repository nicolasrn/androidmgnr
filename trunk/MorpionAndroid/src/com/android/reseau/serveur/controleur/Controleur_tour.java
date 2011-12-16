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
		
		out = new PrintWriter(tab_socket[tour].getSocket().getOutputStream(), false);
		out.println("joue");
		out.flush();		
	}
	
	/**
	 * Reçoit du joueur courant les coordonnées du pion ajouté 
	 * @return Coordonnee
	 * @throws IOException
	 */
	public Coordonnee getCoordClient() throws IOException
	{
		BufferedReader lect1;
		lect1 = new BufferedReader(new InputStreamReader(tab_socket[tour].getSocket().getInputStream()));
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
		PrintWriter out;
		out = new PrintWriter(tab_socket[(tour+1) % 2].getSocket().getOutputStream(), false);

		out.println(c.toString());
		out.flush();
		
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
		outg = new PrintWriter(tab_socket[tour].getSocket().getOutputStream(), false);
		out = new PrintWriter(tab_socket[(tour+1) % 2].getSocket().getOutputStream(), false);
		
		
		if (!matchNull)
		{
			//le gagnant est le joueur courant
			outg.println("win");
			outg.flush();
			
			Connexion_bdd.envoi_gagne(tab_socket[tour].getJoueur(), tab_socket[(tour+1)%2].getJoueur());
			
			// le perdant
			out.println("lose");
			out.flush();
		}
		else
		{
			//le gagnant est le joueur courant
			outg.println("matchnull");
			outg.flush();
			
			Connexion_bdd.envoi_nul(tab_socket[tour].getJoueur(), tab_socket[(tour+1)%2].getJoueur());
			
			// le perdant
			out.println("matchnull");
			out.flush();
		}
		out.println(Connexion_bdd.Affiche_score());
		out.flush();
		outg.println(Connexion_bdd.Affiche_score());
		outg.flush();
	}
}
