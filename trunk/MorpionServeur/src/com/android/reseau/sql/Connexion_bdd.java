package com.android.reseau.sql;

import java.sql.*;
/**
 * 
 * Classe de connexion à la BdD
 *
 */
public class Connexion_bdd
{
	private volatile static Connection conn;

	private static String url = "jdbc:mysql://localhost:3306/morpion";
	private static String user = "root";
	private static String passwd = "rrnn2602";
/**
 * Enregistre une partie gagnée
 * @param c1
 * @param c2
 */
	public synchronized static void envoi_gagne(String c1, String c2)
	{
		try 
		{
			Statement stat = getInstance().createStatement();
			java.sql.Date sDate = new java.sql.Date(System.currentTimeMillis());

			stat.executeUpdate("INSERT INTO historique(joueur_1, joueur_2, date, resultat) VALUES ('"
							+ c1
							+ "','"
							+ c2
							+ "','"
							+ sDate.toString()
							+ "','" + c1 + " a gagné')");
			stat.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
/**
 * Enregistre dans BdD une partie nulle 
 * @param c1
 * @param c2
 */
	public synchronized static void envoi_nul(String c1, String c2) 
	{
		try
		{
			Statement stat = getInstance().createStatement();
			java.sql.Date sDate = new java.sql.Date(System.currentTimeMillis());

			stat.executeUpdate("INSERT INTO historique(joueur_1, joueur_2, date, resultat) VALUES ('"
							+ c1
							+ "','"
							+ c2
							+ "','"
							+ sDate.toString()
							+ "','match nul')");

			stat.close();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
/**
 * Enregistre dans la BdD une partie abandonnée
 * @param c1
 * @param c2
 */
	public synchronized static void envoi_abandon(String c1, String c2)
	{
		try
		{
			Statement stat = getInstance().createStatement();
			java.sql.Date sDate = new java.sql.Date(System.currentTimeMillis());
			stat.executeUpdate("INSERT INTO historique(joueur_1, joueur_2, date, resultat) VALUES ('"
							+ c1
							+ "','"
							+ c2
							+ "','"
							+ sDate.toString()
							+ "','" + c1 + " a abandonné, quel lâche')");

			stat.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Méthode permettant de vérifier s'il y a deja une instance de
	 * Connexion_bdd créée
	 */
	private static Connection getInstance()
	{
		if (conn == null)
		{
			try 
			{
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("DRIVER OK ! ");

				conn = DriverManager.getConnection(url, user, passwd);
			}
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}

		return conn;
	}
/**
 * 
 * @return retourne l'historique de jeu
 */
	public synchronized static String Affiche_score()
	{
		try 
		{
			String requete = "SELECT * FROM historique";
			Statement stat = getInstance().createStatement();

			String res_retour = "";

			ResultSet result = stat.executeQuery(requete);

			while (result.next())
			{
				String j1, j2, score, date;
				j1 = result.getString(2);
				j2 = result.getString(3);
				date = result.getString(4);
				score = result.getString(5);

				res_retour += j1 + " contre " + j2 + " - le " + date
						+ " - issue : " + score + ';';
			}
			return res_retour;
		}
		catch (SQLException e)
		{
			return null;
		}
	}
}
