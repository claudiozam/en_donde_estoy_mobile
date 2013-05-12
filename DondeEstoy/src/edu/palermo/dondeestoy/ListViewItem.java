package edu.palermo.dondeestoy;



import com.google.android.gms.maps.model.LatLng;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewItem extends Activity implements OnClickListener {
	PuntoMapa puntomapa;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listviewitem_layout);
		Intent i = getIntent();
		puntomapa = (PuntoMapa)i.getSerializableExtra("objpunto");
		TextView txtViewDevice = (TextView) findViewById(R.id.txtDevice);
		txtViewDevice.setTextColor(Color.BLACK);
		txtViewDevice.setText(puntomapa.getDevice());
		txtViewDevice.setTextSize(30);
		
		TextView txtViewdescription = (TextView) findViewById(R.id.txtdescription);
		txtViewdescription.setTextColor(Color.BLACK);
		txtViewdescription.setText(puntomapa.getDescription());
		txtViewdescription.setTextSize(30);
		
		Button btn = (Button) findViewById(R.id.buttonVerEnElMapa);
		btn.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		Intent i = getIntent();

		//PuntoMapa p = new PuntoMapa(i.getExtras().get("country").toString(), // titulo
			//	new LatLng(-34f, -58f), // posicion
				//"Un pais (descripcion)" // descripcion
		//);

		Intent imapa = new Intent(getApplicationContext(), MapActivity.class);
		imapa.putExtra("puntos", new PuntoMapa[] { puntomapa });
		startActivity(imapa);
	}
	
}
