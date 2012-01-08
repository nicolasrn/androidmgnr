package com.android.reseau.serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
/**
 * 
 * Thread d'acceptation de 2 clients 
 * 
 *
 */
public class AcceptationClient extends Thread
{
	private ServerSocket serveur = null;
	private SocketJoueur client1, client2;
	
	public AcceptationClient(String nom, ServerSocket s)
	{
		super(nom);
		serveur=s;
		this.start();
	}
	/**
	 * Attend que 2 clients soient connectés avant de lancer le jeu
	 */
	@Override
	
	public void run() 
	{
		while(true)
		{
			try
			{
				System.out.println("acceptation client");
				client1 = new SocketJoueur(serveur.accept());
				client2 = new SocketJoueur(serveur.accept());
				envoieInfo(recupInfo(client1),recupInfo(client2));
				
				new DialogueClient("dialogue1",client1, client2);
			}
			catch (IOException e)
			{
				e.printStackTrace();
				if(client1 == null)
					System.out.println("client 1 nul");
				if(client2 == null)
					System.out.println("client 2 nul");
			}
		}
	}
	/**
	 * Récupère le pseudo du client
	 * @param client
	 * @return
	 * @throws IOException
	 */
	private String recupInfo(SocketJoueur client) throws IOException
	{
		BufferedReader lect = new BufferedReader(new InputStreamReader(client.getSocket().getInputStream()));
		return lect.readLine();
	}
	/**
	 * Recupère les informations de jeu ( pseudo, ... ) aux 2 clients et les renvoi
	 * @param info1
	 * @param info2
	 * @throws IOException
	 */
	private void envoieInfo(String info1,String info2) throws IOException
	{
		PrintWriter infoc1 = new PrintWriter(client1.getSocket().getOutputStream(), false);
		PrintWriter infoc2 = new PrintWriter(client2.getSocket().getOutputStream(), false);
		
		/*pour accuser de reception*/
		infoc1.println(info1 + ";1;" + info2 + ";0;" + Serveur.tailleGrille);
		infoc1.flush();
		infoc2.println(info2 + ";0;" + info1 + ";1;" + Serveur.tailleGrille);
		infoc2.flush();
		
		client1.setJoueur(info1);
		client2.setJoueur(info2);
	}
}
