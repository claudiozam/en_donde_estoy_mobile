package metodos;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import wlocation.wk.HTService;
import wlocation.wk.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import java.io.IOException;
import java.util.*;
import android.widget.*;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.*;

import domain.CategoryLocation;

public class MetodosMapas extends Activity{

	private String TAG = "MetodosMapas";
	Context objContext;
	public MetodosMapas()
	{
		
	}
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{	    
		 super.onCreate(savedInstanceState);
	
	     setContentView(R.layout.mapapoints);

	     
	     //////////////////////////////////// RETURN ////////////////////////////////////////////////////////////
	    Button buttonReturnAct = (Button) findViewById(R.id.buttonNewSearch);
	    buttonReturnAct.setOnClickListener(new OnClickListener()
	    {     
			public void onClick(View v) 
			{	
				Intent IntHTService = null;				    				    
			    try
			    {
			    	IntHTService=new Intent(getApplicationContext(),HTService.class);
			    }catch(Exception Ex)
			    {
			    	Log.e(TAG,"[onCreate] Exception MetodosMapas Intent: "+Ex);	
			    }
			    try
				{
			    	startActivity(IntHTService);
				}catch(Exception ex)
				{
					Log.i(TAG,"[onCreate] Exception MetodosMapas Start: "+ex);	
				}					
			}
	    });
	    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
	    
	     CategoryLocation[] objCategoryLoc=(CategoryLocation[]) getIntent().getSerializableExtra("DatosCatLoc");
		
	     if(this.ColocarPuntosEnMapa(objCategoryLoc))
		{
			
		}else
		{
			Log.i(TAG, "[Handler] NO SE COLOCARON PUNTOS EN MAPA");		//DEBUG
		}  
		    
	}
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public boolean ColocarPuntosEnMapa(CategoryLocation[] objCatLoc)
	{
		TextView txViewAddress;
		
		Geocoder geocoder = new Geocoder(objContext.getApplicationContext(), Locale.getDefault());
		
   		int x=0;
   		while(x<objCatLoc.length)
   		{
   			List<Address> addresses = null;
   			try {
				addresses = geocoder.getFromLocation(Double.valueOf(objCatLoc[x].getLatitudLocal()),Double.valueOf(objCatLoc[x].getLongitudLocal()), 1);			
				
				
			} catch (NumberFormatException e) {
				Log.i(TAG, "[Handler] Adresses Number format exception: "+e  );		//DEBUG
				
				e.printStackTrace();
			} catch (IOException e) {
				Log.i(TAG, "[Handler] Adresses IO exception: "+e  );		//DEBUG
				e.printStackTrace();
			}
   			Log.i(TAG, "[Handler] Category Id: "+objCatLoc[x].getIdCategory() + " - Category Name: "+objCatLoc[x].getCategoryName()+" - Local Name: "+objCatLoc[x].getLocalName() );		//DEBUG
   			
   			if (addresses.size() > 0) 
   			{
   				
   				StringBuilder strTextLocations = new StringBuilder();
   				for(int i = 0; i < addresses.size(); i++)
   				{
   			        Address address =  addresses.get(i);
   			        int maxIndex = address.getMaxAddressLineIndex();
   			        for (int j = 0; j <= maxIndex; j++ )
   			        {
   			        	strTextLocations.append(address.getAddressLine(j));
   			       		strTextLocations.append(",");
   			        }   
   			        
		   		    strTextLocations.append(address.getLocality());
		   		    strTextLocations.append(",");
		   			strTextLocations.append(address.getPostalCode());
		   			strTextLocations.append("\n\n");
   			    }
   				
   				 txViewAddress=(TextView)findViewById(R.id.TextViewAddress);
   				txViewAddress.setText(strTextLocations.toString());
   			}
   		}
	
		return true;
	}
	
}




    
    
