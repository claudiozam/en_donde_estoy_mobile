package edu.palermo.endondeestoymobile.gui;

import android.app.Activity;

/*
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

import edu.palermo.endondeestoymobile.R;
import edu.palermo.endondeestoymobile.R.layout;
import edu.palermo.endondeestoymobile.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MapaActivity extends android.support.v4.app.FragmentActivity {

	private GoogleMap mapa = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        
        mapa = ((SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map)).getMap();
        
        
        LatLng palermo = new LatLng(-34.596918, -58.417724);
        
        CameraPosition camPos = new CameraPosition.Builder()
                .target(palermo)   //Centramos el mapa en Madrid
                .zoom(14)         //Establecemos el zoom en 19
                .build();
         
        CameraUpdate camUpd3 =
            CameraUpdateFactory.newCameraPosition(camPos);
         
        mapa.animateCamera(camUpd3);
        
        
        mapa.addMarker(new MarkerOptions()
        .position(new LatLng(-34.596918, -58.417724))
        .title("En donde estoy?"));
    }

}

*/

public class MapaActivity extends Activity
{
	public MapaActivity()
	{
		
	}
}
