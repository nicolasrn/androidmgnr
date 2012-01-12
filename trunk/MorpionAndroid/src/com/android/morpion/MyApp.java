package com.android.morpion;

import com.android.metier.DataConnexion;

import android.app.Application;

public class MyApp extends Application {
	private DataConnexion data;

	public DataConnexion getData() {
		return data;
	}

	public void setData(DataConnexion data) {
		this.data = data;
	}
	
}
