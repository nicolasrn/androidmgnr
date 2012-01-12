package com.android.reseau.serveur;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

/**
 *  class que contient diverse information serveur et lance le processus de jeu
 */
public class Serveur extends Thread
{
	private static final int port = 8000;
	private ServerSocket serv = null;
	public static final int tailleGrille = 3;
	
	public Serveur() throws IOException
	{
		serv = new ServerSocket(port);
		InetAddress adress = InetAddress.getLocalHost();
		System.out.println("lancement du serveur sur " + adress.getHostAddress());
		this.start();
	}
	
	@Override
	public void run() 
	{
		try
		{
			new AcceptationClient("acceptation client", serv);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public ServerSocket getServ() {
		return serv;
	}

	public void setServ(ServerSocket serv) {
		this.serv = serv;
	}

}
