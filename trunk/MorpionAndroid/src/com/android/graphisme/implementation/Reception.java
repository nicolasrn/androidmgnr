package com.android.graphisme.implementation;

import java.io.IOException;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.android.graphisme.ui.FenetreJeu;
import com.android.metier.Coordonnee;
import com.android.morpion.MorpionAndroidActivity;

/**
 * implementation de l'ecouteur reseau -> client pour la mise a jour de l'�tat graphique
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
	 * win : pour dire que ce joueur a gagn�
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
			if (ordre != null)
			{
				if (ordre.equals("joue"))
				{
	                Message msg = handler.obtainMessage();
	                msg.arg1 = 1;
	                handler.sendMessage(msg);
				}
				else if (ordre.equals("win"))
				{
					Message msg = handler.obtainMessage();
	                msg.arg1 = 2;
	                msg.obj = f.getIterpret().reception_historique();
	                handler.sendMessage(msg);
				}
				else if (ordre.equals("lose"))
				{
					Message msg = handler.obtainMessage();
	                msg.arg1 = 3;
	                msg.obj = f.getIterpret().reception_historique();
	                handler.sendMessage(msg);
				}
				else if (ordre.equals("matchnull"))
				{
					Message msg = handler.obtainMessage();
	                msg.arg1 = 4;
	                msg.obj = f.getIterpret().reception_historique();
	                handler.sendMessage(msg);
				}
				else if (ordre.equals("decodeco"))
				{
					Message msg = handler.obtainMessage();
	                msg.arg1 = 5;
	                msg.obj = f.getIterpret().reception_historique();
	                handler.sendMessage(msg);
					//accus� de reception pour dire que le joueur ci est le gagnant
					f.getIterpret().envoi_serveur("deco");
					System.exit(0);
				}
				else
				{
					Log.v(MorpionAndroidActivity.tag, "ordre dans coordonn�e : " + ordre);
					c = new Coordonnee(ordre);
				}
				
				if (c != null)
				{
	                Message msg = handler.obtainMessage();
	                msg.arg1 = 6;
	                msg.obj = c;
	                handler.sendMessage(msg);
				}
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
	
	private class onClickAlert implements DialogInterface.OnClickListener
	{
		@Override
		public void onClick(DialogInterface dialog, int which) 
		{
			System.exit(0);
		}
	}
	
	public GuiHandler(FenetreJeu f)
	{
		this.f = f;
	}
	
	@Override
	public void handleMessage(Message msg) {
		Log.v(MorpionAndroidActivity.tag, "je suis la c'est d�ja pas si mal");
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
			builder.setMessage("Vous avez gagn� !!!\n" + msg.obj)
			       .setCancelable(false).setPositiveButton("ok", new onClickAlert());
			alert = builder.create();
			alert.show();
			break;
		case 3:
			builder = new AlertDialog.Builder(f.getContext());
			builder.setMessage("Vous avez perdu ...\n" + msg.obj)
		       .setCancelable(false).setPositiveButton("ok", new onClickAlert());
			alert = builder.create();
			alert.show();
			break;
		case 4:
			builder = new AlertDialog.Builder(f.getContext());
			builder.setMessage("Match nul :p\n" + msg.obj)
			       .setCancelable(false).setPositiveButton("ok", new onClickAlert());
			alert = builder.create();
			alert.show();
			break;
		case 5:
			builder = new AlertDialog.Builder(f.getContext());
			builder.setMessage("Victoire par abandon\n" + msg.obj)
			       .setCancelable(false).setPositiveButton("ok", new onClickAlert());
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
