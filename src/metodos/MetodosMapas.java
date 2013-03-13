package metodos;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import domain.CategoryLocation;

public class MetodosMapas {

	private String TAG = "MetodosMapas";
	Context objContext;
	public MetodosMapas()
	{
		
	}
	public boolean ColocarPuntosEnMapa(CategoryLocation[] objCatLoc)
	{
		Geocoder geocoder = new Geocoder(objContext.getApplicationContext(), Locale.getDefault());
   		int x=0;
   		while(x<objCatLoc.length)
   		{
   			try {
				List<Address> addresses = geocoder.getFromLocation(Double.valueOf(objCatLoc[x].getLatitudLocal()),Double.valueOf(objCatLoc[x].getLongitudLocal()), 1);
			} catch (NumberFormatException e) {
				Log.i(TAG, "[Handler] Adresses Number format exception: "+e  );		//DEBUG
				
				e.printStackTrace();
			} catch (IOException e) {
				Log.i(TAG, "[Handler] Adresses IO exception: "+e  );		//DEBUG
				e.printStackTrace();
			}
   			Log.i(TAG, "[Handler] Category Id: "+objCatLoc[x].getIdCategory() + " - Category Name: "+objCatLoc[x].getCategoryName()+" - Local Name: "+objCatLoc[x].getLocalName() );		//DEBUG
   		}
	
		return true;
	}
	
}
