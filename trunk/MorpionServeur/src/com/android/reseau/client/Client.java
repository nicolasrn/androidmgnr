package com.android.reseau.client;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import com.android.reseau.interpretation.Interpreteur;

/**
 * Classe client qui comporte toutes les informations de connnections
 * 
 * @param pseudo : pseudo du joueur 
 * @paramtype : type de jeu ici crade ou classique 
 * @param host : ip du serveur 
 * @param port : port du serveur 
 * @param connectiona : socket de connection au serveur de jeu
 * 
 */
public class Client {
	private String pseudo, type, host;
	private int port;
	private Socket connectiona;

	public Client(String pseudo, String host, String port, String type)
			throws NumberFormatException, UnknownHostException, IOException {
		super();

		this.pseudo = pseudo;
		this.type = type;
		this.host = host;

		this.port = Integer.parseInt(port);

		this.connectiona = new Socket(InetAddress.getByName(host), this.port);
	}

	/**
	 * envoie de donnée vers le serveur lors de la connection initiale du
	 * serveur
	 */
	public void envoye() {
		BufferedWriter out;
		System.out.println("connexion client");

		try {
			Scanner in = new Scanner(connectiona.getInputStream());

			System.out.println(in.nextLine());
			out = new BufferedWriter(new OutputStreamWriter(
					connectiona.getOutputStream()));

			System.out.println(pseudo + " " + type + " " + host + " " + port);
			out.write(pseudo + " " + type + " " + host + " " + port + "\n");
			// out.flush();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * dŽconnexion de la socket client
	 * @throws IOException
	 */
	public void close() throws IOException
	{
		this.connectiona.close();
	}

	/**
	 * Attent un message du serveur via l'interpreteur
	 * 
	 * @return renvoi une chaine reçu du serveur via l'interpreteur
	 * @throws IOException
	 */
	public String attente() throws IOException {
		Interpreteur i = new Interpreteur(this);
		return i.reception_serveur();
	}

	/**
	 * 
	 * @return socket serveur
	 */
	public Socket GetSocket() {
		return connectiona;
	}

	/**
	 * 
	 * @return String : l'adresse ip du serveur
	 */
	public String getHost() {
		return host;
	}

	/**
	 * Modifie l'ip
	 * 
	 * @param host
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * 
	 * @return int : renvoi le port du serveur
	 */
	public int getPort() {
		return port;
	}

	/**
	 * Modifie le port serveur
	 * 
	 * @param port
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * 
	 * @return String : renvoi le pseudo du joueur
	 */
	public String getPseudo() {
		return pseudo;
	}

	/**
	 * Modifie le pseudo du joueur
	 * 
	 * @param pseudo
	 */
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	/**
	 * 
	 * @return String : le type d'image pour le jeu courant classique ou dégueu
	 */
	public String getType() {
		return type;
	}

	/**
	 * Modifie le type de représentation d'image
	 * 
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 
	 * @return la socket du serveur
	 */
	public Socket getConnectiona() {
		return connectiona;
	}

	/**
	 * @return String : renvoi les caracteristiques du serveur
	 */
	public String toString() {
		return "" + pseudo + " " + type + " " + host + " " + port + " "
				+ connectiona;
	}
}
