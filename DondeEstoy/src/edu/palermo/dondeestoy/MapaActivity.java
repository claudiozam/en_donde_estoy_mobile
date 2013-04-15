package edu.palermo.dondeestoy;

import java.util.*;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener;
import com.google.android.gms.maps.model.*;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

public class MapaActivity extends FragmentActivity implements OnMyLocationChangeListener  {

	private GoogleMap mapa = null;
	private ArrayList<PuntoMapa> Puntos = new ArrayList<PuntoMapa>();
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
/*	    try
	    {*/
			super.onCreate(savedInstanceState);
		    setContentView(R.layout.mapa_layout);
		    mapa = ((SupportMapFragment) getSupportFragmentManager()
		    		.findFragmentById(R.id.mapa)).getMap();
		    mapa.setMyLocationEnabled(true);	
		    
		    Bundle parameters = getIntent().getExtras();
		    if (parameters.containsKey("puntos"))
		    {
		    	Object[] puntos = (Object[]) parameters.getSerializable("puntos");
		    	
		    	if (puntos.length >0)
		    	{
			    	for (int i = 0; i < puntos.length; i++) {
			    		AgregarPunto((PuntoMapa)puntos[i]);
					}
			    	
			    	centrarMapa(((PuntoMapa)puntos[0]).getUbicacion());		    		
		    	}
		    }
		    else
		    {
		    	// centrar en mi ubicacion actual.
		    	mapa.setOnMyLocationChangeListener(this);
		    }
		    
		    Location l = mapa.getMyLocation();
		    if (l!=null) onMyLocationChange(l);
/*	    }
	    catch(Exception ex)
	    {
	    	Log.e("MapaActivity.Oncreate()", ex.toString());
	    }*/
    }
	
	public void onMyLocationChange(Location location) {
        LatLng latlong = new LatLng(location.getLatitude(),
                location.getLongitude());
        centrarMapa(latlong);
    }

	private void centrarMapa(LatLng latlong) {
		mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(latlong, 10));
        mapa.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
        mapa.setOnMyLocationChangeListener(null);
	}
	
	public void AgregarPunto(PuntoMapa p)
	{
			MarkerOptions m = new MarkerOptions()	
				.position(p.getUbicacion())
				.title(p.getTitulo())
				.snippet(p.getDescripcion());
			
			mapa.addMarker(m);
			
			Puntos.add(p); // Guardo el punto por si lo quiero trackear despues.		
	}
}
