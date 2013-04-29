package edu.palermo.dondeestoy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import edu.palermo.dondeestoy.services.BusquedaService;
import edu.palermo.dondeestoy.services.BusquedaServiceImplLocal;

public class Busqueda extends Activity {

	private Button botonBuscar;

	
	private Spinner spinnerCategoria;
	private Spinner spinnerLugar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.busqueda_layout);
		botonBuscar = (Button) findViewById(R.id.buttonBuscar);
		
				
		spinnerCategoria = (Spinner)  findViewById(R.id.spinnerCategoria);
		spinnerLugar = (Spinner)  findViewById(R.id.spinnerBarrio);

    	BusquedaService busquedaService = new BusquedaServiceImplLocal();

        ArrayAdapter<Categoria> dataAdapterCategoria = new ArrayAdapter<Categoria>(this,
    		android.R.layout.simple_spinner_item, busquedaService.getCategorias());
        
        dataAdapterCategoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoria.setAdapter(dataAdapterCategoria);
        
    	
        ArrayAdapter<Lugar> dataAdapterLugar = new ArrayAdapter<Lugar>(this,
    		android.R.layout.simple_spinner_item, busquedaService.getLugares());
        
        dataAdapterLugar.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLugar.setAdapter(dataAdapterLugar);
		
		
		
		
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
