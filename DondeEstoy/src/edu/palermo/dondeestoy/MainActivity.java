package edu.palermo.dondeestoy;

import edu.palermo.dondeestoy.bo.BaseResponse;
import edu.palermo.dondeestoy.bo.LocationPoint;
import edu.palermo.dondeestoy.bo.NearLocationPointsResponse;
import edu.palermo.dondeestoy.rest.ApiService;
import edu.palermo.dondeestoy.rest.ApiServiceException;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class MainActivity extends Activity {

	private static String TAG = "MainActivity";
	private String imeiActual = "";

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_layout);

		Utils utils = new Utils(this);
		imeiActual = utils.getIMEI();

		GridView gv = (GridView) findViewById(R.id.gridView);

		// this.RegistrarDevice();
		//ejemploDeLlamadaAlAPI();
		this.Inciarservicio();

		gv.setAdapter(new ImageAdapter(this));
		gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parentView, View iv,
					int position, long id) {
				Intent i;
				switch (position) {
				case 0:

					i = new Intent(getApplicationContext(), MapActivity.class);
					// i.putExtra("id", position); ??

					/*
					 * // Puntos de ejemplo! en este boton no deberian estar.
					 * i.putExtra("puntos", new PuntoMapa[] { new
					 * PuntoMapa("punto 1", new
					 * com.google.android.gms.maps.model.LatLng(10, 10),
					 * "Este es el punto 1" ), new PuntoMapa("punto 2", new
					 * com.google.android.gms.maps.model.LatLng(4, 4),
					 * "Este es el punto 2" ), new PuntoMapa("punto 3", new
					 * com.google.android.gms.maps.model.LatLng(6, 6),
					 * "Este es el punto 3" ) } );
					 */

					startActivity(i);
					break;

				case 1:
					i = new Intent(getApplicationContext(), Searcher.class);
					startActivity(i);
					break;

				case 2:
					i = new Intent(getApplicationContext(), Settings.class);
					startActivity(i);
					break;

				}

			}

		});

	}

	private void RegistrarDevice() {
		new Thread(new Runnable() {
			public void run() {
				ApiService apiService = new ApiService();
				try {
					BaseResponse baseResponse = apiService.postCreateDevice(
							imeiActual, "Usuario Android",
							Utils.PERSONAL_LOCATION_CATEGORY_ID,
							Utils.MOVIL_LOCATION_TYPE_ID);

					if (baseResponse.getCode().equals("000")) {
						Log.i(TAG, "Device registrado");
					} else {
						Log.i(TAG, "El Device existe en la base de datos");
					}

					baseResponse = apiService.postUpdateLocation(-59.9999,
							-34.3332, imeiActual);
					Log.i(TAG, "Registrado");
				} catch (ApiServiceException e) {
					// Toast.makeText(getApplicationContext(),
					// "Error al registrar el celular en el servidor",
					// Toast.LENGTH_SHORT).show();
					// TODO Auto-generated catch block
					Log.e(TAG, "Error al registrar el DEVICE", e);
				}

			}
		}).start();

	}

	private void Inciarservicio() {
		getApplicationContext().startService(
				new Intent(this, LocationService.class));
	}

	// private void ejemploDeLlamadaAlAPI() {

	// new Thread() {
	// public void run() {
	// ApiService apiService = new ApiService();
	//
	//
	// NearLocationPointsResponse locationPointsResponse = null;
	// try {
	// locationPointsResponse = apiService
	// .getNearLocationPoints(25.158, 30.588);
	// } catch (ApiServiceException e) {
	// // TODO Auto-generated catch block
	// Log.e("ERROR LLAMADO", e.getMessage());
	// e.printStackTrace();
	// }
	// if (locationPointsResponse.getCode().equals("000")) {
	// for (LocationPoint locationPoint : locationPointsResponse
	// .getList()) {
	// Log.i("TEST", locationPoint.getLatitude() + " - "
	// + locationPoint.getLongitude());
	// }
	// } else if (locationPointsResponse.getCode().equals("600")) {
	// Log.i("Test",
	// "No encontre nada en un radio de 5 kilomestros");
	// }
	// }
	// }.start();
	//
	// }

	@Override
	public void onBackPressed() {
		Log.d(this.getLocalClassName(), "onBackPressed()");
		this.finish();
	}
}