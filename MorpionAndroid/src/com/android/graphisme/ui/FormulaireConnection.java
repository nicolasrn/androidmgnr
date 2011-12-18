package com.android.graphisme.ui;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Observable;

import com.android.metier.DataConnexion;
import com.android.morpion.MorpionAndroidActivity;
import com.android.reseau.client.Client;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class FormulaireConnection extends LinearLayout {
	private Formulaire form;
	private Button boutonValider;
	private String optionServeur[], optionPort[];
	private String option[];
	
	public FormulaireConnection(Context context) {
		super(context);
		this.setOrientation(LinearLayout.VERTICAL);
		form = new Formulaire(context);
		
		form.addLigne(new LigneFormulaire(context, "Saisir le pseudo"));
		optionServeur = new String[] {"192.168.1.43"};
		form.addLigne(new LigneFormulaire(context, "Saisir ip serveur", optionServeur));
		optionPort = new String[] {"8000"};
		form.addLigne(new LigneFormulaire(context, "Saisir le port", optionPort));
		
		option = new String[] {"Classique", "Crade"};
		form.addLigne(new LigneFormulaire(context, "version", option));
		addView(form);
		boutonValider = new Button(context);
		boutonValider.setText("Valider");
		boutonValider.setOnClickListener(new ActionFormulaire(this));
		addView(boutonValider);
		Log.v(MorpionAndroidActivity.tag, "taille option : " + option.length + "");
			for(String s : option)
		Log.v(MorpionAndroidActivity.tag, "dans la liste des options : " + s);
	}

	public DataConnexion getData()
	{
		Log.v(MorpionAndroidActivity.tag, "id du selectionn� : " + Integer.parseInt(form.get(3).getData()) + "");
		
		return new DataConnexion(form.get(0).getData(), 
				form.get(1).getData().equals("") ? optionServeur[0] : form.get(1).getData(), 
				form.get(2).getData().equals("") ? optionPort[0] : form.get(2).getData(),
				option[Integer.parseInt(form.get(3).getData())]);
	}
	
	public void desactiverFormulaire()
	{
		desactiverFormulaire("Attente du deuxi�me joueur");
	}
	
	public void desactiverFormulaire(String message)
	{
		form.desactiver();
		boutonValider.setOnClickListener(null);
		if (!message.isEmpty())
			boutonValider.setText(message);
	}
	
	public void activerFormulaire()
	{
		activerFormulaire("Valider");
	}
	
	public void activerFormulaire(String message)
	{
		form.activer();
		boutonValider.setOnClickListener(new ActionFormulaire(this));
		if (!message.isEmpty())
			boutonValider.setText(message);
	}
}

class Formulaire extends LinearLayout
{
	private ArrayList<LigneFormulaire> lignes;
	
	public Formulaire(Context context) {
		super(context);
		this.setOrientation(LinearLayout.VERTICAL);
		this.lignes = new ArrayList<LigneFormulaire>(); 
	}
	
	public void desactiver() {
		for(int i = 0; i < lignes.size(); i++)
			lignes.get(i).desactiver();
	}
	
	public void activer() {
		for(int i = 0; i < lignes.size(); i++)
			lignes.get(i).activer();
	}

	public void addLigne(LigneFormulaire ligne)
	{
		this.lignes.add(ligne);
		addView(ligne);
	}
	
	public LigneFormulaire get(int i)
	{
		return this.lignes.get(i);
	}
}

abstract class GetDataChamp
{
	private LigneFormulaire lf;
	
	public GetDataChamp(LigneFormulaire ligneFormulaire)
	{
		this.lf = ligneFormulaire;
	}

	public abstract String getData();
	
	public Context getContext()
	{
		return this.lf.getContext();
	}
}

class LigneFormulaire extends LinearLayout
{
	private TextView label;
	private View champ;
	private GetDataChamp getDataChamp;
	
	public LigneFormulaire(Context context, String label, String option[]) {
		super(context);
		this.setOrientation(LinearLayout.HORIZONTAL);
		
		this.label = new TextView(context);
		this.label.setText(label);
		this.label.setWidth(150);
		
		if (option != null && option.length > 1)
		{
			RadioGroup rg = new RadioGroup(context);
			rg.setOrientation(RadioGroup.HORIZONTAL);
			
			for(String s : option)
			{
				RadioButton r = new RadioButton(context);
				r.setText(s);
				rg.addView(r);
			}
			((RadioButton)rg.getChildAt(0)).setChecked(true);
			
			this.champ = rg;
			getDataChamp = new GetDataChamp(this) {
				@Override
				public String getData() {
					RadioGroup rg = (RadioGroup)champ;
					for(int i = 0; i < rg.getChildCount(); i++)
					{
						RadioButton r = (RadioButton)rg.getChildAt(i);
						if (r.isChecked())
							return i + "";
					}
					return "" + -1;
				}
			};
		}
		else
		{
			this.champ = new EditText(context);
			
			if (option != null)
				((EditText)champ).setHint(option[0]);
			getDataChamp = new GetDataChamp(this) {
				@Override
				public String getData() {
					return ((EditText)champ).getText().toString();
				}
			};
		}
		
		addView(this.label);
		addView(this.champ, new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
	}
	
	public synchronized void desactiver() {
		label.setEnabled(false);
		champ.setEnabled(false);
	}
	
	public synchronized void activer() {
		label.setEnabled(true);
		champ.setEnabled(true);
	}

	public LigneFormulaire(Context context, String label) {
		this(context, label, null);
	}
	
	public String getData()
	{
		return getDataChamp.getData();
	}
}

class ActionFormulaire extends Observable implements OnClickListener
{
	private FormulaireConnection form;
	
	public ActionFormulaire(FormulaireConnection formulaireConnection) {
		this.form = formulaireConnection;
		this.addObserver((MorpionAndroidActivity)form.getContext());
	}

	@Override
	public void onClick(View v) 
	{
		DataConnexion data = form.getData();
		form.desactiverFormulaire();
		if (!data.get("pseudo").isEmpty())
		{
			try
			{
				data.createClient();
				Client client = data.getClient();
				
				/*envoie des donn�es perso, chaque joueur, type de repr�sentation*/
				
				PrintWriter infoc = new PrintWriter(client.getConnectiona().getOutputStream(), false);
				infoc.println(data.getPseudo());
				infoc.flush();
				
				String info = client.attente();
				data.createInfo(info);
				this.setChanged();
			}
			catch (IOException e) 
			{
				form.activerFormulaire();
				Toast.makeText(form.getContext(), "Erreur de connexion au serveur", Toast.LENGTH_LONG).show();
			}
		}
		else
			Toast.makeText(v.getContext(), "Erreur saisir le pseudo", Toast.LENGTH_LONG).show();

		this.notifyObservers(data);		
	}
	
}