package edu.palermo.dondeestoy;

import java.util.ArrayList;

import android.content.Intent;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import edu.palermo.dondeestoy.bo.LocationPoint;
import edu.palermo.dondeestoy.bo.NearLocationPointsResponse;
import edu.palermo.dondeestoy.rest.ApiService;

public class MapaActivity extends FragmentActivity implements
		OnMyLocationChangeListener, OnMarkerClickListener {

	private GoogleMap mapa = null;
	private LatLng posicionActual;
	private ArrayList<PuntoMapa> Puntos = new ArrayList<PuntoMapa>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		try {

			super.onCreate(savedInstanceState);
			setContentView(R.layout.mapa_layout);
			mapa = ((SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(R.id.mapa)).getMap();
			mapa.setMyLocationEnabled(true);
			mapa.setOnMarkerClickListener(this);

			Bundle parameters = getIntent().getExtras();
			if (parameters != null && parameters.containsKey("puntos")) {
				Object[] puntos = (Object[]) parameters
						.getSerializable("puntos");

				if (puntos.length > 0) {
					for (int i = 0; i < puntos.length; i++) {
						AgregarPunto((PuntoMapa) puntos[i]);
					}
					centrarMapa(((PuntoMapa) puntos[0]).getUbicacion());
				}
			} else {
				// centrar en mi ubicacion actual.
				mapa.setOnMyLocationChangeListener(this);
			}

		} catch (Exception ex) {
			Log.e("MapaActivity.Oncreate()", ex.toString());
		}

	}

	public void onMyLocationChange(Location location) {
		LatLng latlong = new LatLng(location.getLatitude(),
				location.getLongitude());
		posicionActual = latlong;
		Log.d("MapaActivity.onMyLocationChange()", "Llamada a async task");
		// centrarMapa(latlong);

		NearpositionsTask nearpositionsTask = new NearpositionsTask();
		nearpositionsTask.execute(posicionActual);

	}

	private void centrarMapa(LatLng latlong) {
		mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(latlong, 10));
		mapa.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
		mapa.setOnMyLocationChangeListener(null);
	}

	public void AgregarPunto(PuntoMapa p) {
		Log.i("PUNTOS", Double.toHexString(p.getUbicacion().latitude));
		MarkerOptions m = new MarkerOptions().position(p.getUbicacion())
				.title(p.getTitulo()).snippet(p.getDescripcion());
		
		mapa.addMarker(m);
		Puntos.add(p); // Guardo el punto por si lo quiero trackear despues.
	}

	class NearpositionsTask extends
			AsyncTask<LatLng, Void, NearLocationPointsResponse> {

		@Override
		protected NearLocationPointsResponse doInBackground(LatLng... latlng) {
			try {

				// TODO Auto-generated method stub
				if (latlng == null) {
					return null;
				}
				ApiService apiService = new ApiService();
				NearLocationPointsResponse locationPointsResponse = apiService
						.getNearLocationPoints(25.158, 30.588);
				if (locationPointsResponse.getCode().equals("000")) {
					return locationPointsResponse;
				} else if (locationPointsResponse.getCode().equals("600")) {
					Log.d("Test",
							"No encontre nada en un radio de 5 kilomestros");
					return null;
				}
				return locationPointsResponse;
			}

			catch (Exception ex) {
				Log.e("NearpositionsTask.doInBackground", ex.getMessage());
				return null;
			}
		}

		@Override
		protected void onPostExecute(NearLocationPointsResponse resultPoints) {
			// Finaliza la ejecucion y actualizo GUI
			mapa.clear();
			if (resultPoints != null) {
				for (LocationPoint locationPoint : resultPoints.getList()) {
					PuntoMapa p = new PuntoMapa(locationPoint.getCategory(),
							new LatLng(locationPoint.getLatitude(),
									locationPoint.getLongitude()),
							locationPoint.getCategory());
					AgregarPunto(p);
					// cuando se tenga la api definitiva no se debe centrar mas
					// en las ubicaciones que se van a agregando.
					centrarMapa(p.getUbicacion());
				}
			}
		}
	}

	@Override
	public boolean onMarkerClick(Marker arg0) {
		Log.d("", Double.toString(arg0.getPosition().latitude));
		
		Intent newListViewActivity = new Intent(this, ListViewItem.class);
		newListViewActivity.putExtra("position", 1);
		newListViewActivity.putExtra("country", ListResultado.countries[1]);
		newListViewActivity.putExtra("flags", ListResultado.flags);
		startActivity(newListViewActivity);
		return true;
	}
}