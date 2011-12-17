package com.android.graphisme.implementation;

import java.io.IOException;

import javax.swing.JOptionPane;

import com.android.graphisme.ui.FenetreJeu;
import com.android.metier.Coordonnee;

/**
 * implementation de l'ecouteur reseau -> client pour la mise a jour de l'état graphique
 */
public class Reception extends Thread 
{
	FenetreJeu f;
	
	/**
	 * 
	 * @param jeu
	 */
	public Reception(FenetreJeu jeu) 
	{
		f = jeu;
	}
	
	/**
	 * implementation de l'interface qui agit en fonction des ordres recu
	 * joue : pour dire a la grille de s'activer
	 * win : pour dire que ce joueur a gagné
	 * lose : pour dire que ce joueur a perdu
	 * matchnul : pour dire ex equao
	 * decodeco : pour signaler l'abandon de l'autre joueur
	 */
	public void ReceptionMessage() throws IOException 
	{
		do
		{
			String ordre = f.getIterpret().reception_serveur();
			Coordonnee c = null;
			
			if (ordre.equals("joue"))
			{
				f.getGrille().activerGrille();
			}
			else if (ordre.equals("win"))
			{
				//JOptionPane.showMessageDialog(f, "Vous avez gagné", "Resutat", JOptionPane.INFORMATION_MESSAGE);
				//JOptionPane.showMessageDialog(f, f.getIterpret().reception_historique(), "Historique", JOptionPane.INFORMATION_MESSAGE);
				System.exit(0);
			}
			else if (ordre.equals("lose"))
			{
				//JOptionPane.showMessageDialog(f, "Vous avez perdu", "Resutat", JOptionPane.INFORMATION_MESSAGE);
				//JOptionPane.showMessageDialog(f, f.getIterpret().reception_historique(), "Historique", JOptionPane.INFORMATION_MESSAGE);
				System.exit(0);
			}
			else if (ordre.equals("matchnull"))
			{
				//JOptionPane.showMessageDialog(f, "Feliciation Match Null", "Resutat", JOptionPane.INFORMATION_MESSAGE);
				//accusé de reception pour dire que le joueur ci est le gagnant
				//JOptionPane.showMessageDialog(f, f.getIterpret().reception_historique(), "Historique", JOptionPane.INFORMATION_MESSAGE);
				System.exit(0);
			}
			else if (ordre.equals("decodeco"))
			{
				//JOptionPane.showMessageDialog(f, "l'autre joueur est deconnecté", "Resutat", JOptionPane.INFORMATION_MESSAGE);
				//JOptionPane.showMessageDialog(f, f.getIterpret().reception_historique(), "Historique", JOptionPane.INFORMATION_MESSAGE);
				//accusé de reception pour dire que le joueur ci est le gagnant
				f.getIterpret().envoi_serveur("deco");
				
				System.exit(0);
			}
			else
				c = new Coordonnee(ordre);
			
			if (c != null)
			{
				//f.getGrille().getObjetTableauAt(c.x, c.y).activerImage(MImage.img1);
				f.getGrille().getObjetTableauAt(c.x, c.y).activerImage(FenetreJeu.tabPion[(FenetreJeu.courant+1)%2]);
				f.getGrille().activerGrille();
			}
		}while(true);
	} 
	
	@Override
	public void run()
	{
		try 
		{
			ReceptionMessage();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
