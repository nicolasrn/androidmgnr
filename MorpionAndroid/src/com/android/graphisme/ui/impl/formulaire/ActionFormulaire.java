package com.android.graphisme.ui.impl.formulaire;

import java.util.Observable;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.android.graphisme.ui.FormulaireConnection;
import com.android.metier.DataConnexion;
import com.android.morpion.MorpionAndroidActivity;
import com.android.morpion.MorpionConnexionActivity;
import com.android.morpion.MorpionJeuActivity;

public class ActionFormulaire extends Observable implements OnClickListener
{
	private FormulaireConnection form;
	
	public ActionFormulaire(FormulaireConnection formulaireConnection) {
		this.form = formulaireConnection;
		this.addObserver((MorpionConnexionActivity)form.getContext());
	}

	public FormulaireConnection getForm() {
		return form;
	}

	@Override
	public void onClick(View v) 
	{
		Log.v(MorpionAndroidActivity.tag, "Dans le onClick");
		Thread t = null;
		DataConnexion data = form.getData();
		Log.v(MorpionAndroidActivity.tag, "données récupérées avant thread : " + data);
		
		if (!data.get("pseudo").equals(""))
		{
			t = new ThreadActionFormulaire(this, data);
			t.start();
		}
		else
		{
			Log.v(MorpionAndroidActivity.tag, "pseudo vide");
			Toast.makeText(v.getContext(), "Erreur saisir le pseudo", Toast.LENGTH_LONG).show();
		}
	}

	public void setChanged() {
		Log.v(MorpionAndroidActivity.tag, "setchanged ok");
		super.setChanged();
	}
	
	public void notifyObservers(Object data)
	{
		Log.v(MorpionAndroidActivity.tag, "notifyobservers ok");
		super.notifyObservers(data);
	}
}