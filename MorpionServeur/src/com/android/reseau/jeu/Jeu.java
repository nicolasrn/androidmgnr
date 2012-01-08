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
	 * Grille g : le jeu est composÈ d'une grille
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
	 * boucle de jeu cotÈ serveur
	 */
	public void run(){
		System.out.println("lancement du jeu");
		int nbcoup = 0;
		
		Controleur_grille c = new Controleur_grille(g);
		boolean gagne = false, matchNull = false;
		Controleur_tour ctrltr = new Controleur_tour (client1, client2);
		
		try{
			while (!(gagne || matchNull))
			{
				ctrltr.ordre_jeu();
				
				Coordonnee coord = ctrltr.getCoordClient();
				if (g.getCase(coord.x, coord.y).getClass().equals(Pion_vide.class))
					if (ctrltr.gettour() == 1)
						g.setCase(coord.x, coord.y, new Pion_Ami());					
					else
						g.setCase(coord.x, coord.y, new Pion_Ennemi());
				
				gagne = c.controle(coord.x, coord.y);
				
				ctrltr.envoiClientConcurant(coord);
				ctrltr.tour_suivant();
				nbcoup++;
				
				matchNull = (nbcoup == g.getNb_case() * g.getNb_case());
				System.out.println("grille après modification\n" + g.toString());
			}
			ctrltr.envoiResultat(matchNull);
		}
		catch (IOException e) 
		{
			e.printStackTrace();
			System.out.println("client deco");
			String msg = "deco";
			
			PrintWriter out;
			
			//verfication du joueur dconnectÈ
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
				System.out.print("client1 deconnectÈ");
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
				System.out.println("client2 deconnectÈ");
				Connexion_bdd.envoi_abandon(client2.getJoueur(), client1.getJoueur());
			}
		}
	}
}
