package com.android.morpion;

import java.util.Observable;
import java.util.Observer;

import com.android.graphisme.ui.FormulaireConnection;
import com.android.metier.DataConnexion;

import android.content.Intent;
import android.os.Bundle;

public class MorpionConnexionActivity extends ActivityGlobale implements Observer {
	private DataConnexion data;
	private FormulaireConnection form = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		form = new FormulaireConnection(this);
		setContentView(form);
	}

	@Override
	public void update(Observable observable, Object data) {
		if (data instanceof DataConnexion)
		{
			this.data = (DataConnexion) data;
			MyApp app = ((MyApp)getApplicationContext());
			app.setData(this.data);
			Intent intent = new Intent(this, MorpionJeuActivity.class);
			startActivity(intent);
		}
	}

	public DataConnexion getData() {
		return data;
	}

	public void setData(DataConnexion data) {
		this.data = data;
	}

}
