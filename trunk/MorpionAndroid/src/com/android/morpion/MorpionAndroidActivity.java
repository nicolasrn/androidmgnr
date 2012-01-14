package com.android.morpion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MorpionAndroidActivity extends ActivityGlobale //implements Observer
{
	public static final String tag = "MorpionAndroidActivity";
	
	/** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//Log.v(tag, "savedInstanceState : " + (savedInstanceState == null));
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main);
		Button b = (Button) this.findViewById(R.id.button1);
		b.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MorpionAndroidActivity.this, MorpionConnexionActivity.class);
				startActivity(intent);
			}
		});
	}
	
}
