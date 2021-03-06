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
		// ejemploDeLlamadaAlAPI();
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

				case 3:
					finish();
					break;

				}

			}

		});

	}

	
	private void Inciarservicio() {
		if (Utils.serviceStart) {
			getApplicationContext().startService(
					new Intent(this, LocationService.class));
		}
	}


	@Override
	public void onBackPressed() {
		Log.d(this.getLocalClassName(), "onBackPressed()");
		this.finish();
	}
}