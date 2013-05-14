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
import android.widget.Toast;
 
public class Settings extends Activity {
	public static final String NombrePref = "DondeEstoyPref";

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings_layout);
		
		final EditText editTextServidor = (EditText) findViewById(R.id.editTextServidor111);
		Button botonGuardar = (Button) findViewById(R.id.buttonServidor1);
		
		final Utils utils = new Utils(this);
        
		editTextServidor.setText(utils.getServerAddress());
       			
		
		botonGuardar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	utils.saveServerAddress(editTextServidor.getText().toString());
            	Toast.makeText(getApplicationContext(), "Servidor Guardado.", Toast.LENGTH_SHORT).show();
            }
       });
	}
    
}