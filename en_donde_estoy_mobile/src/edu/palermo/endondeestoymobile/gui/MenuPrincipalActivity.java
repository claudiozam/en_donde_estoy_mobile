package edu.palermo.endondeestoymobile.gui;

import java.util.ArrayList;

import libreria.Categoria;
import libreria.CategoryPoints;
import libreria.Gps;
import libreria.MetodosGral;
import libreria.MetodosRequest;
import edu.palermo.endondeestoymobile.R;
import edu.palermo.endondeestoymobile.R.layout;
import edu.palermo.endondeestoymobile.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuPrincipalActivity extends Activity {

	MetodosRequest metreq;
	MetodosGral metgrl;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        
        metreq=new MetodosRequest();
        metgrl=new MetodosGral(this.getApplicationContext());
        
        
        ///////////////////////#############################BOTONES REQUEST ###########################################
        /////////////
        Button buttonreqnear = (Button) findViewById(R.id.buttonReqNearLoc);
        buttonreqnear.setOnClickListener(new OnClickListener()
        {     
    		public void onClick(View v) 
    		{		
    			Gps gpsloc =new Gps();
    			gpsloc.setLat(-23.3444);
    			gpsloc.setLong(-32.2322);
    			try
    			{
	    			ArrayList<CategoryPoints> listcatpoint=metreq.obtenerLocacionesCercanas(gpsloc);
	    			if(listcatpoint!=null)
	    			{
		    			int x=0;
		    			for(CategoryPoints lstpoint:listcatpoint)
		    			{
		    				Log.i("MENU PRINCIPAL","[buttonnear] Category "+x+": "+lstpoint.getCategoryName());
		    				x++;
		    			}
	    			}
    			}catch(Exception e)
    			{
    				Log.e("MENU PRINCIPAL","[buttonnear]EXCEPTION: "+e);
    			}
    		}
        });
        
        /////////////
        Button buttonreqlogin = (Button) findViewById(R.id.buttonReqLogin);
        buttonreqlogin.setOnClickListener(new OnClickListener()
        {     
    		public void onClick(View v) 
    		{	
    			String struser="pepe";
    			String strpass="12345678";
    			String imei=metgrl.getDeviceID();
	    		try
	    		{
	    			if(metreq.verificarUseryPass(struser, strpass, imei))
	    			{
	    				Log.i("MENU PRINCIPAL","[buttonlogin] LOGUEADO");
	    			}else
	    			{
	    				Log.i("MENU PRINCIPAL","[buttonlogin] NO LOGUEADO");
	    			}
	    		}catch(Exception e)
				{
					Log.e("MENU PRINCIPAL","[buttonlogin]EXCEPTION: "+e);
				}
    		}
        });
        
        /////////////
	    Button buttonreqcreate = (Button) findViewById(R.id.buttonReqCrearNewDevice);
	    buttonreqcreate.setOnClickListener(new OnClickListener()
	    {     
			public void onClick(View v) 
			{	
				String namedevice="La cosecha";
				String categoria="Verduleria";
				try
				{
					if(metreq.crearNuevoDevice(namedevice, categoria))
					{
	    				Log.i("MENU PRINCIPAL","[buttoncrear] Creado");
	    			}else
	    			{
	    				Log.i("MENU PRINCIPAL","[buttoncrear] NO CREADO");
	    			}
				}catch(Exception e)
				{
					Log.e("MENU PRINCIPAL","[buttoncrear]EXCEPTION: "+e);
				}
			}
	    });
	    
	    /////////////
	    Button buttonreqdown = (Button) findViewById(R.id.buttonReqDownCat);
	    buttonreqdown.setOnClickListener(new OnClickListener()
	    {     
			public void onClick(View v) 
			{	
				String version="00.12.23";
				try
				{
					ArrayList<Categoria> lstcat=metreq.descargarCategoriasDisponibles(version);
					if(lstcat!=null)
					{
						int i=0;
						for(Categoria cat:lstcat)
						{
							Log.i("MENU PRINCIPAL","[buttondownload] Categoria "+i+" :"+cat.getNombreCategoria());
							i++;
						}
					}
				}catch(Exception e)
				{
					Log.e("MENU PRINCIPAL","[buttondownload]EXCEPTION: "+e);
				}
			}
	    });
	    
	    /////////////
	    Button buttonrequpd = (Button) findViewById(R.id.buttonReqUpdateLoc);
	    buttonrequpd.setOnClickListener(new OnClickListener()
	    {     
			public void onClick(View v) 
			{	
				Gps gpsco =new Gps();
				gpsco.setLat(-23.3444);
				gpsco.setLong(-32.2322);
				try
				{
					if(metreq.actualizarPosicion(gpsco))
					{
	    				Log.i("MENU PRINCIPAL","[buttonupdate] UPDETEADO");
	    			}else
	    			{
	    				Log.i("MENU PRINCIPAL","[buttonlogin] NO UPDETEADO");
	    			}
				}catch(Exception e)
				{
					Log.e("MENU PRINCIPAL","[buttonlogin]EXCEPTION: "+e);
				}
				
			}
	    });
    
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu_principal, menu);
        return true;
    }
    

    
}
