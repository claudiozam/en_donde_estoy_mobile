package edu.palermo.dondeestoy;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.gms.maps.model.LatLng;

import edu.palermo.dondeestoy.bo.Category;
import edu.palermo.dondeestoy.bo.Responseclass;
import edu.palermo.dondeestoy.bo.Responseclass.Response_CategoriasDisponibles;
import edu.palermo.dondeestoy.rest.ApiService;
import edu.palermo.dondeestoy.rest.ApiServiceException;

public class Searcher extends Activity {

	private Button botonBuscar;

	private Spinner spinnerCategoria;
	private Spinner spinnerLugar;
	
	private ArrayAdapter<edu.palermo.dondeestoy.Category> dataAdapterCategoria = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_layout);
		
		botonBuscar = (Button) findViewById(R.id.buttonBuscar);
		spinnerCategoria = (Spinner) findViewById(R.id.spinnerCategoria);
		spinnerLugar = (Spinner) findViewById(R.id.spinnerBarrio);
		
		Log.i("Searcher", "########## Antes de buscar las categorias ###########");
		BuscarCategoriasTask buscarCategoriasTask = new BuscarCategoriasTask();
		buscarCategoriasTask.execute();

		//BusquedaService busquedaService = new BusquedaServiceImplLocal();

		//ArrayAdapter<Category> dataAdapterCategoria = null;
		
		//new ArrayAdapter<Category>(this, android.R.layout.simple_spinner_item, busquedaService.getCategorias());

		//dataAdapterCategoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		//spinnerCategoria.setAdapter(dataAdapterCategoria);



		botonBuscar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				eventoDelBotonBuscar(arg0);
			}

		});
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
	
	public void cargarSpinner(Category[] categorias) {
		
		Log.i("Searcher", "#####  CARGANDO LAS CATEGORIAS  ############");
		
		edu.palermo.dondeestoy.Category miCategoria = new edu.palermo.dondeestoy.Category();
		
		ArrayList<edu.palermo.dondeestoy.Category> listaCategorias = new ArrayList<edu.palermo.dondeestoy.Category>();
						
		for (edu.palermo.dondeestoy.bo.Category category : categorias) {
			miCategoria.setNombre(category.getName() + " " + category.getNombredevice());
			
			listaCategorias.add(miCategoria);
			
		}
		
		dataAdapterCategoria = new ArrayAdapter<edu.palermo.dondeestoy.Category>(this, android.R.layout.simple_spinner_item, listaCategorias);
		
		dataAdapterCategoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerCategoria.setAdapter(dataAdapterCategoria);
		
	}
	
	//Async task para traer las categoria disponibles
	class BuscarCategoriasTask extends AsyncTask<Void, Void, Responseclass.Response_CategoriasDisponibles>{

		@Override
		protected Response_CategoriasDisponibles doInBackground(Void... arg0) {
			Log.i("BuscarCategoriasTask", "##### doInBackground(Void... arg0)  ############");

			try {
				ApiService apiService = new ApiService();
				Response_CategoriasDisponibles categoriasDisponibles = apiService.getCategoriasDisponibles();
								
				return categoriasDisponibles;
				
			} catch (ApiServiceException e) {
				// TODO Auto-generated catch block
				Log.e("BuscarCategoriasTask.doInBackground", e.getMessage());
			}
			
			return null;
		}
		
		@Override
		protected void onPostExecute(Response_CategoriasDisponibles result) {
			
			Log.i("DENTRO DE onPostExecute", result.toString());

			if (result != null){
				
				edu.palermo.dondeestoy.bo.Category[] categorias = result.getCategorias();			
				cargarSpinner(categorias);
				
			}
		}
		
	}
	
	
}
