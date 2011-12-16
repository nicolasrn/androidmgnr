package com.android.reseau.jeu;

import java.io.IOException;
import java.io.PrintWriter;

import com.android.metier.Coordonnee;
import com.android.reseau.serveur.SocketJoueur;
import com.android.reseau.serveur.controleur.*;
import com.android.reseau.serveur.element.*;
import com.android.reseau.sql.Connexion_bdd;



public class Jeu extends Thread{
	/**
	 * Grille g : le jeu est composé d'une grille
	 */
	private Grille_serveur g;
	private SocketJoueur client1, client2;
	
	public Jeu (Grille_serveur gr, String nom,SocketJoueur c1,SocketJoueur c2)
	{
		super (nom);
		g = gr;
		client1 = c1;
		client2 = c2;
	}
	
	public Jeu (int nb, String nom)
	{
		super (nom);
		g=new Grille_serveur (nb);
	}
	/**
	 * boucle de jeu coté serveur
	 */
	public void run(){
		int nbcoup = 0;
		
		Controleur_grille c = new Controleur_grille(g);
		boolean gagné = false;
		boolean occupe = false; /** indique si la case est occupée ou pas*/
		Coordonnee cord;
		Pion_vide Object=new Pion_vide();/** sert de référence */
		Controleur_tour ctrltr = new Controleur_tour (client1,client2);
		
		try{
			while (!(gagné || nbcoup == g.getNb_case() * g.getNb_case()))
			{
				System.out.println(g.toString());
				
				do{
					/**
					 * on dit au joueur courant qu'il a le droit de jouer 
					 */
					ctrltr.ordre_jeu();
					
					/**
					 * pour le client serveur c'est ici que l'on lit les données
					 * envoyées par les clients
					 */
					
					cord = ctrltr.getCoordClient();
					
					/**
					 * On teste la case courante est du type Pion_vide
					 * Si oui on peut remplacer le pion vide par un Pion_ami ou Pion_Ennemi
					 */
					if (g.getCase(cord.x, cord.y).getClass()== Object.getClass())
					{
						if (ctrltr.gettour() == 1)
						{
							g.setCase(cord.x, cord.y, new Pion_Ami());
														
						}
						else 
						{
							g.setCase(cord.x, cord.y, new Pion_Ennemi());
						}
						
						if(c.controle(cord.x, cord.y))
						{
							gagné = true;
							continue;
						}
						
						/**
						 * On envoie la mise à jour de la grille aux joueurs 
						 * puis on donne la main au joueur suivant
						 */
						ctrltr.envoiClientConcurant(cord);
						ctrltr.tour_suivant();
						
						nbcoup++;
					}
					else
					{
						occupe = false;
						System.out.println("Case occupée choisir une autre case !!!");
					}				
				} while(occupe);
			}
			
			/**
			 * envoi au client de qui a gagné ????
			 */
			
			ctrltr.envoiResultat(nbcoup == g.getNb_case() * g.getNb_case());
			System.out.println(Connexion_bdd.Affiche_score());
		}
		catch (IOException e) 
		{
			System.out.println("client deco");
			String msg = "deco";
			
			PrintWriter out;
			
			//verfication du joueur dconnecté
			try
			{
				java.io.OutputStream os = client1.getSocket().getOutputStream();
				os.write(msg.getBytes());
				System.out.println("client1 vainqueur");
				
				out = new PrintWriter(client1.getSocket().getOutputStream(), false);
				out.println("deco");
				out.flush();
			}
			catch(IOException ioe)
			{
				System.out.print("client1 deconnecté");
				Connexion_bdd.envoi_abandon(client1.getJoueur(), client2.getJoueur());

			}
			
			//verif client 2
			try
			{
				java.io.OutputStream os = client2.getSocket().getOutputStream();
				os.write(msg.getBytes());
				System.out.println("client2 vainqueur");
				out = new PrintWriter(client2.getSocket().getOutputStream(), false);
				out.println("deco");
				out.flush();
			}
			catch(IOException ioe)
			{
				System.out.println("client2 deconnecté");
				Connexion_bdd.envoi_abandon(client2.getJoueur(), client1.getJoueur());
			
			}
		}
	}
}
