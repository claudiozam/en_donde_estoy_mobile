package edu.palermo.dondeestoy;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ProgressDialog;
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
import edu.palermo.dondeestoy.rest.ApiServiceException;

public class MapActivity extends FragmentActivity implements
		OnMyLocationChangeListener, OnMarkerClickListener {

	private GoogleMap mapa = null;
	private HashMap<String, MapPoint> pointMarkers = new HashMap<String, MapPoint>();
	private LatLng currentPosition;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {

			super.onCreate(savedInstanceState);
			setContentView(R.layout.map_layout);
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
						addPoint((MapPoint) puntos[i]);
					}
					centerMap(((MapPoint) puntos[0]).getLocation());
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
		currentPosition = latlong;
		Log.d("MapaActivity.onMyLocationChange()", "Llamada a async task");
		NearpositionsTask nearpositionsTask = new NearpositionsTask();
		nearpositionsTask.execute(currentPosition);
	}

	private void centerMap(LatLng latlong) {
		mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(latlong, 10));
		mapa.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
		mapa.setOnMyLocationChangeListener(null);
	}

	public void addPoint(MapPoint p) {
		MarkerOptions m = new MarkerOptions().position(p.getLocation())
				.title(p.getDevice()).snippet(p.getDescription());
		Marker objMarker = mapa.addMarker(m);
		pointMarkers.put(objMarker.getId(), p);
	}

	class NearpositionsTask extends
			AsyncTask<LatLng, Void, NearLocationPointsResponse> {
		ProgressDialog progressDialog;
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = ProgressDialog.show(MapActivity.this, "",
                    "Procesando...");
		}

		@Override
		protected NearLocationPointsResponse doInBackground(LatLng... latlng) {
			try {
				if (latlng == null) {
					return null;
				}
				ApiService apiService = new ApiService();
				NearLocationPointsResponse locationPointsResponse = apiService
						.getNearLocationPoints(latlng[0].latitude,
								latlng[0].longitude);
				if (locationPointsResponse.getCode().equals("000")) {
					return locationPointsResponse;
				} else if (locationPointsResponse.getCode().equals("600")) {
					Log.d("Test",
							"No encontre nada en un radio de 5 kilomestros");
					return null;
				}
				Log.i("postescute", locationPointsResponse.getCode());
				return null;
			}

			catch (ApiServiceException ex) {
				Log.e("NearpositionsTask.doInBackground", ex.getMessage());
				return null;
			}
		}

		@Override
		protected void onPostExecute(NearLocationPointsResponse resultPoints) {
			// Finaliza la ejecucion y actualizo GUI
			mapa.clear();
			if (resultPoints != null) {
			Log.d("MapActivity.onPostExecute", String.valueOf(resultPoints.getList().length) );
				for (LocationPoint locationPoint : resultPoints.getList()) {
					MapPoint mapPoint = new MapPoint(locationPoint.getDevice(),
							new LatLng(locationPoint.getLatitude(),
									locationPoint.getLongitude()),
							locationPoint.getDevice_description(),
							locationPoint.getCategory());

					addPoint(mapPoint);
					centerMap(mapPoint.getLocation());
				}
			}
			else
			{
				mapa.setOnMyLocationChangeListener(null);
			}
			progressDialog.dismiss();
		}
	}

	@Override
	public boolean onMarkerClick(Marker marker) {
		Intent intentListView = new Intent(this, ListViewItem.class);
		MapPoint mapPoint = pointMarkers.get(marker.getId());
		intentListView.putExtra("objpunto", mapPoint);
		startActivity(intentListView);
		return true;
	}
}