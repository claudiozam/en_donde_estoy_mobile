package edu.palermo.dondeestoy;

import android.app.Activity;
import android.os.Bundle;

public class Configuracion extends Activity {
	public static final String NombrePref = "DondeEstoyPref";
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.configuracion_layout);
	}

}