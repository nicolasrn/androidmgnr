package com.android.reseau.interpretation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import com.android.metier.Coordonnee;
import com.android.reseau.client.Client;

/**
 * Interpreteur qui sert de lien entre le client et le serveur
 * 
 * Client : Socket client
 *
 */
public class Interpreteur {

	private Client client;
	
	public Interpreteur(Client client)
	{
		this.client = client;
	}
	/**
	 * 
	 * @param coord
	 * @throws IOException
	 */
	public void envoi_serveur(Coordonnee coord) throws IOException
	{
		PrintWriter out = new PrintWriter(client.GetSocket().getOutputStream());
		out.println(coord.toString());
		out.flush();
	}
	/**
	 * 
	 * @return String : renvoi le message receptionné du serveur
	 * @throws IOException
	 */
	public String reception_serveur() throws IOException
	{
		BufferedReader in = new BufferedReader (new InputStreamReader(client.GetSocket().getInputStream()));
		return in.readLine();
	}
/**
 * 
 * @return String : receptionne l'historique envoyé du serveur
 * @throws IOException
 */
	public String reception_historique() throws IOException
	{
		String chaine=reception_serveur().replace(";", "\n");
		System.out.println(chaine);
		return chaine;
	}

	/**
	 * envoi le message au serveur 
	 * @param msg
	 * @throws IOException
	 */
	public void envoi_serveur(String msg) throws IOException 
	{
		PrintWriter out = new PrintWriter(client.GetSocket().getOutputStream());
		out.println(msg);
		out.flush();
	}
}
