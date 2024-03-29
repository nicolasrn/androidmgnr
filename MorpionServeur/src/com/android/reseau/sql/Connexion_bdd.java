package com.android.reseau.sql;

import java.sql.*;

/**
 * 
 * Classe de connexion � la BdD
 * 
 */
public class Connexion_bdd {
	private volatile static Connection conn;

	private static String url = "jdbc:mysql://localhost:3306/morpion";
	private static String user = "root";
	private static String passwd = "";

	/**
	 * Enregistre une partie gagn�e
	 * 
	 * @param c1
	 * @param c2
	 */
	public synchronized static void envoi_gagne(String c1, String c2) {
		try {
			Statement stat = getInstance().createStatement();
			Timestamp sDate = new Timestamp(System.currentTimeMillis());

			String sql = "INSERT INTO historique(joueur_1, joueur_2, date, resultat) VALUES ('"
					+ c1
					+ "','"
					+ c2
					+ "','"
					+ sDate.toString()
					+ "','"
					+ c1
					+ " a gagne')";
			System.out.println("sql : " + sql);
			stat.executeUpdate(sql);
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Enregistre dans BdD une partie nulle
	 * 
	 * @param c1
	 * @param c2
	 */
	public synchronized static void envoi_nul(String c1, String c2) {
		try {
			Statement stat = getInstance().createStatement();
			Timestamp sDate = new Timestamp(System.currentTimeMillis());

			stat.executeUpdate("INSERT INTO historique(joueur_1, joueur_2, date, resultat) VALUES ('"
					+ c1
					+ "','"
					+ c2
					+ "','"
					+ sDate.toString()
					+ "','match nul')");

			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Enregistre dans la BdD une partie abandonn�e
	 * 
	 * @param c1
	 * @param c2
	 */
	public synchronized static void envoi_abandon(String c1, String c2) {
		try {
			Statement stat = getInstance().createStatement();
			Timestamp sDate = new Timestamp(System.currentTimeMillis());

			stat.executeUpdate("INSERT INTO historique(joueur_1, joueur_2, date, resultat) VALUES ('"
					+ c1
					+ "','"
					+ c2
					+ "','"
					+ sDate.toString()
					+ "','"
					+ c1
					+ " a abandonne, quel lache')");

			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * M�thode permettant de v�rifier s'il y a deja une instance de
	 * Connexion_bdd cr��e
	 */
	private static Connection getInstance() {
		if (conn == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("DRIVER OK ! ");

				conn = DriverManager.getConnection(url, user, passwd);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return conn;
	}

	/**
	 * 
	 * @return retourne l'historique de jeu
	 */
	public synchronized static String Affiche_score() {
		try {
			String requete = "SELECT * FROM historique;";
			System.out.println(requete);
			Statement stat = getInstance().createStatement();

			String res_retour = "";

			ResultSet result = stat.executeQuery(requete);
			result.beforeFirst();
			
			while (result.next()) {
				String j1, j2, score, date;
				j1 = result.getString(2);
				j2 = result.getString(3);
				date = result.getString(1);
				score = result.getString(4);
				
				res_retour += j1 + "-" + j2 + "-" + date + "-" + score + ';';
			}
			return res_retour;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
