package edu.palermo.dondeestoy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
 
public class Configuracion extends Activity {
	public static final String NombrePref = "DondeEstoyPref";

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.configuracion_layout);
		
		final EditText servidor = (EditText) findViewById(R.id.editTextServidor);
		Button botonGuardar = (Button) findViewById(R.id.buttonServidor1);
		SharedPreferences settings = getSharedPreferences(NombrePref,0);
		servidor.setText(settings.getString("SERVIDOR", "Servidor por defecto"));
		
		
		botonGuardar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	SharedPreferences settings = getSharedPreferences(NombrePref,0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("SERVIDOR", servidor.getText().toString());
                
                editor.commit();
            }
       });
	}
    
}