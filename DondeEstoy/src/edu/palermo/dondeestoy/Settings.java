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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
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
		final Button botonGuardar = (Button) findViewById(R.id.buttonServidor1);
		final CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox1);
		final Utils utils = new Utils(this);
		checkBox.setChecked(Utils.serviceStart);
		editTextServidor.setText(utils.getServerAddress());

		botonGuardar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				utils.saveServerAddress(editTextServidor.getText().toString());
				Toast.makeText(getApplicationContext(), "Servidor Guardado.",
						Toast.LENGTH_SHORT).show();
			}
		});

		checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				LocationService locationService = LocationService.getInstance();
				if (buttonView.isChecked()) {
					Toast.makeText(getApplicationContext(),
							"Servicio Activado.", Toast.LENGTH_SHORT).show();
					Utils.serviceStart = true;
					inciarServicio();

				} else {
					Toast.makeText(getApplicationContext(),
							"Servicio Desactivado.", Toast.LENGTH_SHORT).show();
					Utils.serviceStart = false;
					locationService.stopSelf();
				}

			}
		});

	}

	private void inciarServicio() {
		getApplicationContext().startService(
				new Intent(this, LocationService.class));
	}
}