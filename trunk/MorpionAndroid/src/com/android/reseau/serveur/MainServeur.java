package com.android.reseau.serveur;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * classe de lancement du serveur
 */
public class MainServeur
{
	private static Serveur s;
	private static JFrame frame;
	private static JButton connection, deconnection;
	
	public static void main(String[] a)
	{
		frame = new JFrame("fenetre Serveur");
		connection = new JButton("Connecter");
		deconnection = new JButton("Deconnection");
		frame.getContentPane().setLayout(new FlowLayout());
		frame.getContentPane().add(connection);
		frame.getContentPane().add(deconnection);
		
		deconnection.setEnabled(false);
		
		connection.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				try 
				{
					s = new Serveur();
					connection.setEnabled(false);
					deconnection.setEnabled(true);
				}
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		});
		
		deconnection.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!connection.isEnabled())
				{
					try
					{
						s.getServ().close();
						s.interrupt();
						s = null;

						connection.setEnabled(true);
						deconnection.setEnabled(false);
					}
					catch (IOException e) 
					{
						e.printStackTrace();
					}
				}	
			}
		});
		
		frame.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent arg0) {
				
			}
			
			@Override
			public void windowIconified(WindowEvent arg0) {
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent arg0) {
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent arg0) {
				
			}
			
			@Override
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			}
			
			@Override
			public void windowClosed(WindowEvent arg0) {
				System.exit(0);
			}
			
			@Override
			public void windowActivated(WindowEvent arg0) {
				
			}
		});
		frame.pack();
		frame.setVisible(true);
	}
}
