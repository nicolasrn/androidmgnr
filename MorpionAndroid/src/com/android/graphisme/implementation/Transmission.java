package com.android.graphisme.implementation;

import java.io.IOException;

import javax.swing.JOptionPane;

import android.widget.Toast;

import com.android.graphisme.composant.Case;
import com.android.graphisme.composant.Grille;
import com.android.graphisme.ui.FenetreJeu;

/**
 * classe qui assure la transmission serveur-client et vice et versa
 * classe qui a le role de classe Adapter
 */
public class Transmission
{
	FenetreJeu f;
	
	/**
	 * 
	 * @param jeu
	 */
	public Transmission(FenetreJeu jeu) {
		f = jeu;
	}
	
	/**
	 * envoie la coordonnées courante de la grille vers le serveur pour l'analyse du jeu
	 * bloque le joueur qui viens de jouer 
	 * les boutons sont remplacé par des images
	 */
	public void transmettreMessage(Case caseGrille)
	{
		try 
		{
			//envoie de la coordonnée par le client
			System.out.println(Grille.coord);
			f.getIterpret().envoi_serveur(Grille.coord);
			
			f.getGrille().desactiverGrille();
			//reception de l'action ordonnée par le serveur a effectuer par le client
			caseGrille.activerImage(FenetreJeu.tabPion[FenetreJeu.courant]);
		}
		catch (IOException e) {
			e.printStackTrace();
			Toast.makeText(f.getContext(), "Erreur de transmission", Toast.LENGTH_LONG).show();
		} 
	}
}
