package com.android.morpion;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Observer;

import com.android.graphisme.ui.FenetreJeu;
import com.android.graphisme.ui.FormulaireConnection;
import com.android.metier.DataConnexion;
import com.android.reseau.client.Client;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MorpionAndroidActivity extends Activity implements Observer
{
	public static final String tag = "MorpionAndroidActivity";
	private FormulaireConnection form;
	private FenetreJeu fen;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		form = new FormulaireConnection(this);
		setContentView(form);
	}
	
	@Override
	public void update(Observable observable, Object data) {
		//connexion au serveur puis lancer l'interface du morpion
		try 
		{
			DataConnexion dataConnexion = (DataConnexion) data;
			Log.d(tag, "données recues : " + dataConnexion);
			
			Client c = new Client(dataConnexion.getPseudo(),
					dataConnexion.getIp(),
					dataConnexion.getPort(),
					dataConnexion.getIdAvatar()); 
			
			//la grille est construite par le serveur (parametres sont juste transmis)
			fen = new FenetreJeu(this, 3);
			setContentView(fen);
		}
		catch (NumberFormatException e) 
		{
			e.printStackTrace();
		}
		catch (UnknownHostException e) 
		{
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}