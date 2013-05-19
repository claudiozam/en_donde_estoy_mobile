package edu.palermo.dondeestoy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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
import edu.palermo.dondeestoy.bo.FindLocationsResponse;
import edu.palermo.dondeestoy.bo.Location;
import edu.palermo.dondeestoy.bo.Responseclass;
import edu.palermo.dondeestoy.bo.Responseclass.Response_CategoriasDisponibles;
import edu.palermo.dondeestoy.rest.ApiService;
import edu.palermo.dondeestoy.rest.ApiServiceException;


public class Searcher extends Activity {

	private Button botonBuscar;
	private static String TAG = "Searcher";
	private Spinner spinnerCategoria;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_layout);
		
		botonBuscar = (Button) findViewById(R.id.buttonBuscar);
		spinnerCategoria = (Spinner) findViewById(R.id.spinnerCategoria);
				
		Log.i(TAG, "Antes de buscar las categorias");
		BuscarCategoriasTask buscarCategoriasTask = new BuscarCategoriasTask();
		buscarCategoriasTask.execute();

		botonBuscar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				eventoDelBotonBuscar(arg0);
			}

		});
		
		//ejemploBuscar();
	}
	
	//Es solo un ejemplo cambiar a AsyncTask......
	private void ejemploBuscar() {
		new Thread(new Runnable() {
			public void run() {
				ApiService apiService = new ApiService();
				try {
					
					//Ejemplo de busqueda bar lindo, id categoria = 2 con un limite de 30 registros
					//FindLocationsResponse findLocationsResponse = apiService.findLocations("bar lindo", 2, 30);
					
					//Enviar la categoria id = 0 es igual a buscar en todas las categorias
					FindLocationsResponse findLocationsResponse = apiService.findLocations("", 2, 30);
					
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
		//falta obtener los filtros de la pantalla
		Filters filtrosBusqueda = new Filters();
		filtrosBusqueda.setIdCategory(2);
		filtrosBusqueda.setResultsCount(30);
		filtrosBusqueda.setText("bar lindo");
		
		//Se llama a asink Task para la busqueda de lugares
		BuscarLocationsTask buscarLocationsTask = new BuscarLocationsTask();
		buscarLocationsTask.execute(filtrosBusqueda);

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
	
	private void cargarSpinner(Category[] categorias) {
		
		Log.i(TAG, "#####  CARGANDO LAS CATEGORIAS  ############");
		
		edu.palermo.dondeestoy.Category miCategoria = null;		
		ArrayList<edu.palermo.dondeestoy.Category> listaCategorias = new ArrayList<edu.palermo.dondeestoy.Category>();
						
		for (edu.palermo.dondeestoy.bo.Category category : categorias) {
			miCategoria = new edu.palermo.dondeestoy.Category();
			miCategoria.setNombre(category.getCategoriaId() + " " +  category.getName());
			
			listaCategorias.add(miCategoria);
			
		}
		ArrayAdapter<edu.palermo.dondeestoy.Category> dataAdapterCategoria = null;
		dataAdapterCategoria = new ArrayAdapter<edu.palermo.dondeestoy.Category>(this, android.R.layout.simple_spinner_item, listaCategorias);
		
		dataAdapterCategoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerCategoria.setAdapter(dataAdapterCategoria);
		
	}
	
	private void cargarResultadosBusqueda(Location[] list) {
		Intent intent = new Intent(this, ListResult.class);
		
		ArrayList<MapPoint> listaResultados = new ArrayList<MapPoint>();
		MapPoint mp = null;
		
		for (Location location: list){
			mp = new MapPoint(location.getDevice(), new LatLng(location.getLatitude(), location.getLongitude()), location.getDevice_description(), location.getCategory());
			listaResultados.add(mp);
		}
		
		intent.putExtra("resultadoBusqueda", listaResultados);
		
		this.startActivity(intent);
	}
	
	//Async task para traer las categoria disponibles
	class BuscarCategoriasTask extends AsyncTask<Void, Void, Responseclass.Response_CategoriasDisponibles>{
		


		@Override
		protected Response_CategoriasDisponibles doInBackground(Void... arg0) {
			Log.i("BuscarCategoriasTask", "##### doInBackground(Void... arg0)  ############");

			try {
				ApiService apiService = new ApiService();
				Response_CategoriasDisponibles categoriasDisponibles = apiService.getCategoriasDisponibles();
				
				if(categoriasDisponibles.getCode().equals("000")){
					return categoriasDisponibles;
				}else if(categoriasDisponibles.getCode().equals("600")){
					Log.e("BuscarCategoriasTask.doInBackground", "No se encontraron datos");
															
					return null;
				}
										
			} catch (ApiServiceException e) {
				Log.e("BuscarCategoriasTask.doInBackground", e.getMessage());
			}
			
			return null;
		}
		
		@Override
		protected void onPostExecute(Response_CategoriasDisponibles result) {

			if (result != null){
				
				edu.palermo.dondeestoy.bo.Category[] categorias = result.getCategorias();			
				cargarSpinner(categorias);		
			}
		}
		
	}
	
	//Async Task para la busqueda de lugares segun filtros
	class BuscarLocationsTask extends AsyncTask <Filters, Void, FindLocationsResponse>{
		
		ProgressDialog progressDialog;
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = ProgressDialog.show(Searcher.this, "",
                    "Procesando...");
		}
		
		@Override
		protected FindLocationsResponse doInBackground(Filters... params) {
			
			try {
				
				ApiService apiService = new ApiService();
				FindLocationsResponse findLocationsResponse = apiService.findLocations(params[0].getText() , params[0].getIdCategory(), params[0].getResultsCount());
				
				if(findLocationsResponse.getCode().equals("000")){
					return findLocationsResponse;
				}else if(findLocationsResponse.getCode().equals("600")){
					Log.e("BuscarLocationsTask.doInBackground", "No se encontraron datos");
					
					return null;
				}
				
			} catch (ApiServiceException e) {
				Log.e("BuscarLocationsTask.doInBackground", e.getMessage());
			}
			
			return null;
		}
		
		@Override
		protected void onPostExecute(FindLocationsResponse result) {
			//Cargo el ArrayList<PuntoMapa> para mostrar en el listResult
			
			if(result != null){
				cargarResultadosBusqueda(result.getList());				
			}
			progressDialog.dismiss();
		}		
	}
		
}
