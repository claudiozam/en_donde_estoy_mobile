package edu.palermo.dondeestoy;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MiUbicacion extends android.support.v4.app.FragmentActivity  implements LocationListener {
	
	private GoogleMap mapa = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
	    try
	    {
			super.onCreate(savedInstanceState);
		    setContentView(R.layout.mi_ubicacion);
		    mapa = ((SupportMapFragment) getSupportFragmentManager()
			        .findFragmentById(R.id.map)).getMap();
		    mapa.setMyLocationEnabled(true);
		    LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		    Criteria criteria = new Criteria();
		    // metodo que obtiene el mejor proveedor de ubicacion en el telefono en el momento dado
		    String provider = locationManager.getBestProvider(criteria, true);
		    if (provider != null) {
			    Location location = locationManager.getLastKnownLocation(provider);
			    if(location!=null){
			       onLocationChanged(location);
			     }
			     // se ejecuta cuando cambia ubicacion o por tiempo
			     locationManager.requestLocationUpdates(provider, 20000, 0, this);
		     }
	    }
	    catch(Exception ex)
	    {
	    	Log.e("MiUbicacion.Oncreate()", ex.getMessage());
	    }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       // getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

	protected boolean isRouteDisplayed() {
		return false;
	}

	@Override
	public void onLocationChanged(Location location) {
	      
		try
	    {
			  mapa.setMyLocationEnabled(true);
		      mapa.clear();
		      
		      if (location!=null)
		      {
		    	  double latitude = location.getLatitude();
			  	  double longitude = location.getLongitude();
				
		         LatLng latLong = new LatLng(latitude, longitude);
		         CameraPosition camPos = new CameraPosition.Builder()
		                				 .target(latLong)   
		                				 .zoom(14)         
		                				 .build();
				 CameraUpdate camUpd3 =
				 CameraUpdateFactory.newCameraPosition(camPos);
				 mapa.animateCamera(camUpd3);
				 mapa.addMarker(new MarkerOptions()
				        		 .position(latLong)
				        		 .title("PUNTO DE INTERES 1"));
				 mapa.animateCamera(camUpd3);
			}
	    }
	    catch(Exception ex)
		{
		   Log.e("MiUbicacion.onLocationChanged()", ex.getMessage());
		}
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub		
	}
}
