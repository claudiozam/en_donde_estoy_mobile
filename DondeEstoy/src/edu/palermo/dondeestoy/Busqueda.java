package edu.palermo.dondeestoy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Busqueda extends Activity {

	private Button botonBuscar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.busqueda_layout);
		botonBuscar = (Button) findViewById(R.id.buttonBuscar);
		botonBuscar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				eventoDelBotonBuscar(arg0);
			}

		});
	}

	private void eventoDelBotonBuscar(View arg0) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, ListResultado.class);
		// TODO setear los datos de los filtros.
		this.startActivity(intent);
	}

}
