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

public class ListViewItem extends Activity implements OnClickListener
{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listviewitem_layout);
		Intent i=getIntent();
		int position = (Integer) i.getExtras().get("position");
		String country = (String) i.getExtras().get("country");
		int[] flags = (int[]) i.getExtras().get("flags");
		TextView t= (TextView) findViewById(R.id.txtLeyenda);
		t.setTextColor(Color.WHITE);
		t.setText(country);
		t.setTextSize(30);
		ImageView imageview= (ImageView)findViewById(R.id.imgFlag);
		imageview.setImageResource(flags[position]); 
		
		Button btn =  ( Button ) findViewById(R.id.buttonVerEnElMapa);
		btn.setOnClickListener(this);
		
	}
	
	 @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        getMenuInflater().inflate(R.menu.main, menu);
	        return true;
	    }

	@Override
	public void onClick(View arg0) {
		Intent i=getIntent();
		
		PuntoMapa p = new PuntoMapa(
				i.getExtras().get("country").toString(), 	// titulo
				new LatLng(-34f,-58f),						// posicion
				"Un pais (descripcion)"						// descripcion
				); 
		
		Intent mapa = new Intent(getApplicationContext(),MapaActivity.class);
		mapa.putExtra("puntos",new PuntoMapa[] { p }); 
		
		startActivity(mapa);
	}
}
