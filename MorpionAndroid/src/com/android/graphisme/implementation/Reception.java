package com.android.graphisme.implementation;

import java.io.IOException;
import java.util.Observable;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.text.AndroidCharacter;
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
	private boolean continuer;
	
	/**
	 * 
	 * @param jeu
	 */
	public Reception(FenetreJeu jeu) 
	{
		this.f = jeu;
		this.handler = new GuiHandler(f);
		this.continuer = true;
	}
	
	/**
	 * implementation de l'interface qui agit en fonction des ordres recu
	 * joue : pour dire a la grille de s'activer
	 * win : pour dire que ce joueur a gagné
	 * lose : pour dire que ce joueur a perdu
	 * matchnul : pour dire ex equao
	 * decodeco : pour signaler l'abandon de l'autre joueur
	 * @throws IOException 
	 */
	public void ReceptionMessage() throws IOException 
	{
		while(continuer)
		{
			String ordre = f.getIterpret().reception_serveur();
			Coordonnee c = null;
			
			Log.v(MorpionAndroidActivity.tag, "ordre : " + ordre);
			if (ordre == null)
				Log.e(MorpionAndroidActivity.tag, "erreur ordre est null");
			
			if (ordre.equals("joue"))
			{
				Log.v(MorpionAndroidActivity.tag, "dans joue");
			    Message msg = handler.obtainMessage();
			    msg.what = 1;
			    handler.sendMessage(msg);
			}
			else if (ordre.equals("win"))
			{
			    continuer = false;
				Log.v(MorpionAndroidActivity.tag, "dans win");
				Message msg = handler.obtainMessage();
			    msg.what = 2;
			    //msg.obj = f.getIterpret().reception_historique();
			    handler.sendMessage(msg);
			}
			else if (ordre.equals("lose"))
			{
			    continuer = false;
				Log.v(MorpionAndroidActivity.tag, "dans lose");
				Message msg = handler.obtainMessage();
			    msg.what = 3;
			    //msg.obj = f.getIterpret().reception_historique();
			    handler.sendMessage(msg);
			}
			else if (ordre.equals("matchnull"))
			{
			    continuer = false;
				Log.v(MorpionAndroidActivity.tag, "dans matchnull");
				Message msg = handler.obtainMessage();
			    msg.what = 4;
			    //msg.obj = f.getIterpret().reception_historique();
			    handler.sendMessage(msg);
			}
			else if (ordre.equals("decodeco"))
			{
			    continuer = false;
				Log.v(MorpionAndroidActivity.tag, "dans decodeco");
				//accusé de reception pour dire que le joueur ci est le gagnant
				f.getIterpret().envoi_serveur("deco");
				Message msg = handler.obtainMessage();
			    msg.what = 5;
			    //msg.obj = f.getIterpret().reception_historique();
			    handler.sendMessage(msg);
			}
			else
			{
				Log.v(MorpionAndroidActivity.tag, "reception coordonnŽe : " + ordre);
				c = new Coordonnee(ordre);
				
				Message msg = handler.obtainMessage();
			    msg.what = 6;
			    msg.obj = c;
			    handler.sendMessage(msg);
			}
		}
	
		Log.v(MorpionAndroidActivity.tag, "fin du jeu dans l'attente de l'historique");
		Log.v(MorpionAndroidActivity.tag, "dans l'attente de l'historique");
		String histo = f.getIterpret().reception_historique();
		Log.v(MorpionAndroidActivity.tag, "recut");
		Message msg = handler.obtainMessage();
		msg.what = 7;
		msg.obj = histo;
		handler.sendMessage(msg);
	}
	
	@Override
	public void run()
	{
		try 
		{
			ReceptionMessage();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}

class OnClickAlert implements DialogInterface.OnClickListener
{
	public OnClickAlert() throws Exception
	{
		
	}
	
	@Override
	public void onClick(DialogInterface dialog, int which) 
	{
		dialog.cancel();
	}
}

class EnvoyerHistorique extends Observable
{
	public EnvoyerHistorique(FenetreJeu f)
	{
		this.addObserver((MorpionAndroidActivity)f.getContext());
	}
	
	public void notifyObserv(Object data)
	{
		this.setChanged();
		this.notifyObservers(data);
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
	public void handleMessage(Message msg) 
	{
		try 
		{
			Log.v(MorpionAndroidActivity.tag, "dans le handleMessage");
			
			switch(msg.what)
			{
			case 1:
				Log.v(MorpionAndroidActivity.tag, "activation de la grille");
				f.getGrille().activerGrille();
				break;
			case 2:
				Log.v(MorpionAndroidActivity.tag, "message victoire");
				new AlertDialog
						.Builder(f.getContext())
						.setMessage("Vous avez gagnŽ !!!")
						.setCancelable(false).setPositiveButton("ok", new OnClickAlert())
						.create()
						.show();
				break;
			case 3:
				Log.v(MorpionAndroidActivity.tag, "message defaite");
				new AlertDialog
					.Builder(f.getContext())
					.setMessage("Vous avez perdu ...")
					.setCancelable(false)
					.setPositiveButton("ok", new OnClickAlert())
					.create()
					.show();
				break;
			case 4:
				Log.v(MorpionAndroidActivity.tag, "match null");
				new AlertDialog
					.Builder(f.getContext())
					.setMessage("Match nul :p")
					.setCancelable(false)
					.setPositiveButton("ok", new OnClickAlert())
					.create()
					.show();
				break;
			case 5:
				Log.v(MorpionAndroidActivity.tag, "message abandon");
				new AlertDialog
					.Builder(f.getContext())
					.setMessage("Victoire par abandon")
					.setCancelable(false)
					.setPositiveButton("ok", new OnClickAlert())
					.create()
					.show();
				break;
			case 6:
				Log.v(MorpionAndroidActivity.tag, "activation de l'image");
				Coordonnee c = (Coordonnee)msg.obj;
				f.getGrille().getObjetTableauAt(c.x, c.y).activerImage(FenetreJeu.tabPion[(FenetreJeu.courant+1)%2]);
				//f.getGrille().activerGrille();
				break;
			case 7:
				Log.v(MorpionAndroidActivity.tag, "traitement de l'historique");
				EnvoyerHistorique eh = new EnvoyerHistorique(f);
				eh.notifyObserv(msg.obj);
				break;
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
