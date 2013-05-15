package edu.palermo.dondeestoy;

import java.util.ArrayList;

import com.google.android.gms.maps.model.LatLng;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import edu.palermo.dondeestoy.bo.BaseResponse;
import edu.palermo.dondeestoy.bo.FindLocationsResponse;
import edu.palermo.dondeestoy.bo.Location;
import edu.palermo.dondeestoy.rest.ApiService;
import edu.palermo.dondeestoy.rest.ApiServiceException;
import edu.palermo.dondeestoy.services.BusquedaService;
import edu.palermo.dondeestoy.services.BusquedaServiceImplLocal;

public class Searcher extends Activity {

	private Button botonBuscar;
	private static String TAG = "Searcher";
	
	private Spinner spinnerCategoria;
	private Spinner spinnerLugar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_layout);
		botonBuscar = (Button) findViewById(R.id.buttonBuscar);

		spinnerCategoria = (Spinner) findViewById(R.id.spinnerCategoria);
		spinnerLugar = (Spinner) findViewById(R.id.spinnerBarrio);

		BusquedaService busquedaService = new BusquedaServiceImplLocal();

		ArrayAdapter<Category> dataAdapterCategoria = new ArrayAdapter<Category>(
				this, android.R.layout.simple_spinner_item,
				busquedaService.getCategorias());

		dataAdapterCategoria
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerCategoria.setAdapter(dataAdapterCategoria);

		ArrayAdapter<Place> dataAdapterLugar = new ArrayAdapter<Place>(this,
				android.R.layout.simple_spinner_item,
				busquedaService.getLugares());

		dataAdapterLugar
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerLugar.setAdapter(dataAdapterLugar);

		botonBuscar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				eventoDelBotonBuscar(arg0);
			}

		});
		
		ejemploBuscar();
	}
	
	private void ejemploBuscar() {
		new Thread(new Runnable() {
			public void run() {
				ApiService apiService = new ApiService();
				try {
					
					//Ejemplo de busqueda bar lindo, id categoria = 2 con un limite de 30 registros
					//FindLocationsResponse findLocationsResponse = apiService.findLocations("bar lindo", 2, 30);
					
					//Enviar la categoria id = 0 es igual a buscar en todas las categorias
					FindLocationsResponse findLocationsResponse = apiService.findLocations("", 0, 30);
					
					if (findLocationsResponse.getCode().equals("000")) {
						
						Log.i(TAG, "Mostrando resultados de la busqeuda");
						
						Location[] locations = findLocationsResponse.getList();
						for(Location l : locations) {
							Log.i(TAG, l.toString());
						}
					} else {
						Log.i(TAG, "Error en la busqueda: " + findLocationsResponse.getMessage());
					}
				} catch (ApiServiceException e) {
					// Toast.makeText(getApplicationContext(),
					// "Error al registrar el celular en el servidor",
					// Toast.LENGTH_SHORT).show();
					// TODO Auto-generated catch block
					Log.e(TAG, "Error al buscar", e);
				}

			}
		}).start();

	}

	private void eventoDelBotonBuscar(View arg0) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, ListResult.class);
		// TODO setear los datos de los filtros.
		intent.putExtra("resultadoBusqueda", setPuntosMapa());
		this.startActivity(intent);
	}
	//se agrego este metodo para cargar listview
	private ArrayList<MapPoint> setPuntosMapa() {
		ArrayList<MapPoint> PuntosMapa = new ArrayList<MapPoint>();
		double latitude=-34.6128;
		double longitude=-58.4304;
		for (int i = 0; i < 20; i++) {
			
			PuntosMapa.add(new MapPoint(String.valueOf(i), new LatLng(
					latitude,longitude ), "PRUEBA" + String.valueOf(i),
					"PERSONAL"));
			latitude=latitude+ 0.002;
			longitude=longitude+0.002;
		}
		return PuntosMapa;
	}
}
