package com.android.graphisme.ui.impl.formulaire;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.android.metier.DataConnexion;
import com.android.morpion.MorpionAndroidActivity;

public class HandlerGuiConnexion extends Handler
{
	private ActionFormulaire actionFormulaire;
	
	public HandlerGuiConnexion(ActionFormulaire actionFormulaire) {
		this.actionFormulaire = actionFormulaire;
	}

	@Override
	public void handleMessage(Message msg) {
		super.handleMessage(msg);
		switch(msg.what)
		{
		case 1:
			Log.v(MorpionAndroidActivity.tag, "handler desactivation formulaire");
			actionFormulaire.getForm().desactiverFormulaire();
			break;
		case 2:
			Log.v(MorpionAndroidActivity.tag, "handler notification observer");
			DataConnexion data = (DataConnexion) msg.obj;
			actionFormulaire.setChanged();
			actionFormulaire.notifyObservers(data);

			//Intent intent = new Intent(actionFormulaire.getForm().getContext(), MorpionJeuActivity.class);
			//intent.putExtra("data", data);
			//actionFormulaire.getForm().getContext().startActivity(intent);
			break;
		case 3:
			Log.v(MorpionAndroidActivity.tag, "handler cas activation formulaire");
			actionFormulaire.getForm().activerFormulaire();
			break;
		}
	}
}


