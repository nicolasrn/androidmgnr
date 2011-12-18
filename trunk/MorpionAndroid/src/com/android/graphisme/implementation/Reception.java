package com.android.graphisme.implementation;

import java.io.IOException;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.android.graphisme.ui.FenetreJeu;
import com.android.metier.Coordonnee;
import com.android.morpion.MorpionAndroidActivity;

/**
 * implementation de l'ecouteur reseau -> client pour la mise a jour de l'état graphique
 */
public class Reception extends Thread 
{
	private FenetreJeu f;
	private GuiHandler handler;
	
	/**
	 * 
	 * @param jeu
	 */
	public Reception(FenetreJeu jeu) 
	{
		f = jeu;
		handler = new GuiHandler(f);
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
			
			Log.v(MorpionAndroidActivity.tag, "ordre : " + ordre);
			if (ordre.equals("joue"))
			{
				//f.getGrille().activerGrille();
                Message msg = handler.obtainMessage();
                msg.arg1 = 1;
                handler.sendMessage(msg);
			}
			else if (ordre.equals("win"))
			{
				/*AlertDialog.Builder builder = new AlertDialog.Builder(f.getContext());
				builder.setMessage("Vous avez gagnŽ !!!")
				       .setCancelable(false).setPositiveButton("ok", new DialogInterface.OnClickListener() {
				           public void onClick(DialogInterface dialog, int id) {
				                dialog.cancel();
				           }
				       });
				AlertDialog alert = builder.create();
				alert.show();*/
				Message msg = handler.obtainMessage();
                msg.arg1 = 2;
                handler.sendMessage(msg);
				//JOptionPane.showMessageDialog(f, "Vous avez gagné", "Resutat", JOptionPane.INFORMATION_MESSAGE);
				//JOptionPane.showMessageDialog(f, f.getIterpret().reception_historique(), "Historique", JOptionPane.INFORMATION_MESSAGE);
				//System.exit(0);
			}
			else if (ordre.equals("lose"))
			{
				/*AlertDialog.Builder builder = new AlertDialog.Builder(f.getContext());
				builder.setMessage("Vous avez perdu ...")
			       .setCancelable(false).setPositiveButton("ok", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			                dialog.cancel();
			           }
			       });
				AlertDialog alert = builder.create();
				alert.show();*/
				Message msg = handler.obtainMessage();
                msg.arg1 = 3;
                handler.sendMessage(msg);
				//JOptionPane.showMessageDialog(f, "Vous avez perdu", "Resutat", JOptionPane.INFORMATION_MESSAGE);
				//JOptionPane.showMessageDialog(f, f.getIterpret().reception_historique(), "Historique", JOptionPane.INFORMATION_MESSAGE);
				//System.exit(0);
			}
			else if (ordre.equals("matchnull"))
			{
				/*AlertDialog.Builder builder = new AlertDialog.Builder(f.getContext());
				builder.setMessage("Pas de bol :p")
				       .setCancelable(false).setPositiveButton("ok", new DialogInterface.OnClickListener() {
				           public void onClick(DialogInterface dialog, int id) {
				                dialog.cancel();
				           }
				       });
				AlertDialog alert = builder.create();
				alert.show();*/
				Message msg = handler.obtainMessage();
                msg.arg1 = 4;
                handler.sendMessage(msg);
				//JOptionPane.showMessageDialog(f, "Feliciation Match Null", "Resutat", JOptionPane.INFORMATION_MESSAGE);
				//accusé de reception pour dire que le joueur ci est le gagnant
				//JOptionPane.showMessageDialog(f, f.getIterpret().reception_historique(), "Historique", JOptionPane.INFORMATION_MESSAGE);
				//System.exit(0);
			}
			else if (ordre.equals("decodeco"))
			{
				/*AlertDialog.Builder builder = new AlertDialog.Builder(f.getContext());
				builder.setMessage("Victoire par abandon")
				       .setCancelable(false).setPositiveButton("ok", new DialogInterface.OnClickListener() {
				           public void onClick(DialogInterface dialog, int id) {
				                dialog.cancel();
				           }
				       });
				AlertDialog alert = builder.create();
				alert.show();*/
				Message msg = handler.obtainMessage();
                msg.arg1 = 5;
                handler.sendMessage(msg);
				//JOptionPane.showMessageDialog(f, "l'autre joueur est deconnecté", "Resutat", JOptionPane.INFORMATION_MESSAGE);
				//JOptionPane.showMessageDialog(f, f.getIterpret().reception_historique(), "Historique", JOptionPane.INFORMATION_MESSAGE);
				//accusé de reception pour dire que le joueur ci est le gagnant
				f.getIterpret().envoi_serveur("deco");
				
				//System.exit(0);
			}
			else
				c = new Coordonnee(ordre);
			
			if (c != null)
			{
                Message msg = handler.obtainMessage();
                msg.arg1 = 6;
                msg.obj = c;
                handler.sendMessage(msg);
				//f.getGrille().getObjetTableauAt(c.x, c.y).activerImage(MImage.img1);
				//f.getGrille().getObjetTableauAt(c.x, c.y).activerImage(FenetreJeu.tabPion[(FenetreJeu.courant+1)%2]);
				//f.getGrille().activerGrille();
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

class GuiHandler extends Handler 
{
	private FenetreJeu f;
	
	public GuiHandler(FenetreJeu f)
	{
		this.f = f;
	}
	
	@Override
	public void handleMessage(Message msg) {
		Log.v(MorpionAndroidActivity.tag, "je suis la c'est dŽja pas si mal");
		AlertDialog.Builder builder;
		AlertDialog alert;
		
		switch(msg.arg1)
		{
		case 1:
			Log.v(MorpionAndroidActivity.tag, "msg.what ok");
			f.getGrille().activerGrille();
			break;
		case 2:
			builder = new AlertDialog.Builder(f.getContext());
			builder.setMessage("Vous avez gagnŽ !!!")
			       .setCancelable(false).setPositiveButton("ok", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			                dialog.cancel();
			           }
			       });
			alert = builder.create();
			alert.show();
			break;
		case 3:
			builder = new AlertDialog.Builder(f.getContext());
			builder.setMessage("Vous avez perdu ...")
		       .setCancelable(false).setPositiveButton("ok", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		                dialog.cancel();
		           }
		       });
			alert = builder.create();
			alert.show();
			break;
		case 4:
			builder = new AlertDialog.Builder(f.getContext());
			builder.setMessage("Pas de bol :p")
			       .setCancelable(false).setPositiveButton("ok", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			                dialog.cancel();
			           }
			       });
			alert = builder.create();
			alert.show();
			break;
		case 5:
			builder = new AlertDialog.Builder(f.getContext());
			builder.setMessage("Victoire par abandon")
			       .setCancelable(false).setPositiveButton("ok", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			                dialog.cancel();
			           }
			       });
			alert = builder.create();
			alert.show();
			break;
		case 6:
			Coordonnee c = (Coordonnee)msg.obj;
			f.getGrille().getObjetTableauAt(c.x, c.y).activerImage(FenetreJeu.tabPion[(FenetreJeu.courant+1)%2]);
			f.getGrille().activerGrille();
			break;
		}
	}
};
