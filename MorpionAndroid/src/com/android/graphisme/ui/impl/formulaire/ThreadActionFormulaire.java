package com.android.graphisme.ui.impl.formulaire;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.UnknownHostException;

import android.os.Message;
import android.util.Log;

import com.android.metier.DataConnexion;
import com.android.morpion.MorpionAndroidActivity;
import com.android.reseau.client.Client;

public class ThreadActionFormulaire extends Thread
{
	private DataConnexion data;
	private ActionFormulaire actionFormulaire;
	private HandlerGuiConnexion handler;
	
	public ThreadActionFormulaire(ActionFormulaire actionFormulaire, DataConnexion data)
	{
		Log.v(MorpionAndroidActivity.tag, "instanciation du thread");
		this.actionFormulaire = actionFormulaire;
		this.data = data;
		this.handler = new HandlerGuiConnexion(this.actionFormulaire);
	}
	
	@Override
	public void run()
	{
		try
		{
			Log.v(MorpionAndroidActivity.tag, "Lancement du run du thread");
			this.data.createClient();
			Log.v(MorpionAndroidActivity.tag, "données récupérées : " + this.data);
			Client client = this.data.getClient();
			//actionFormulaire.getForm().desactiverFormulaire();
			//comme on est dans un thread il faut déléguer la tache a un handler
			Message msg = handler.obtainMessage(1);
			handler.sendMessage(msg);
			
			/*envoie des donnÈes perso, chaque joueur, type de reprÈsentation*/
			
			PrintWriter infoc = new PrintWriter(client.getConnectiona().getOutputStream(), false);
			infoc.println(data.getPseudo());
			infoc.flush();
			
			String info = client.attente();
			this.data.createInfo(info);
			//ceci n'est pas possible car implique une action graphique donc on passe par le handler
			//actionFormulaire.setChanged();
			//actionFormulaire.notifyObservers(data);
			//ceci est bon car la répercution graphique est gérer par un handler
			msg = handler.obtainMessage(2);
			msg.obj = this.data;
			handler.sendMessage(msg);
		} 
		catch (NumberFormatException e)
		{
			e.printStackTrace();
			//Log.e(MorpionAndroidActivity.tag, "erreur NumberFormatException : " + e.getMessage() + " " + e.getCause());
			//actionFormulaire.getForm().activerFormulaire();
			//idem en cas d'erreur
			Message msg = handler.obtainMessage(3);
			handler.sendMessage(msg);
		} 
		catch (UnknownHostException e) 
		{
			e.printStackTrace();
			//actionFormulaire.getForm().activerFormulaire();
			//idem en cas d'erreur
			Message msg = handler.obtainMessage(3);
			handler.sendMessage(msg);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			//actionFormulaire.getForm().activerFormulaire();
			//idem en cas d'erreur
			Message msg = handler.obtainMessage(3);
			handler.sendMessage(msg);
		}
	}
}