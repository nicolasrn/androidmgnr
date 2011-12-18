package com.android.reseau.serveur;

import java.net.Socket;

/**
 * classe enveloppe qui contient le nom du joueur et la socket correspondante
 */
public class SocketJoueur 
{
	private String joueur;
	private Socket socket;
	
	/**
	 * construit l'objet avec une socket
	 * @param socket
	 */
	public SocketJoueur(Socket socket) {
		this.socket = socket;
	}
	
	/**
	 * modifie le nom joueur
	 * @param joueur
	 */
	public void setJoueur(String joueur) {
		this.joueur = joueur;
	}
	
	/**
	 * 
	 * @return String
	 */
	public String getJoueur()
	{
		return joueur;
	}

	/**
	 * 
	 * @return Socket
	 */
	public Socket getSocket() {
		return socket;
	}

	/**
	 * modifie la socket
	 * @param socket
	 */
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
}
