
package wlocation.wk;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.TextView;

public class GPSData extends Activity
{
	private int CantidadAct=0;
	
	public GPSData()
	{
		//
	}

	public boolean updateCoordinates(double dbLatitud,double dbLongitud,double dbAltitud,float flVelocidad,String strImei,String strBattery)
	{	
		Log.i("GPSData","[updateCoordinates] UPDATE COORDINATES na tela" );
		//Bundle savedInstanceState = null;
		//super.onCreate(savedInstanceState);
		//setContentView(R.layout.main);		
		Log.i("GPSData","[updateCoordinates] DSP LAYOUT" );
		//appContext = this.getApplicationContext();
		Log.i("GPSData","[updateCoordinates]DSP APPLICATION" );
		
		
		try
		{
			
			Log.i("GPSData","[updateCoordinates] EN TRY" );	
			   TextView cantLat = (TextView) findViewById(R.id.CantidadAct);
			   TextView textAltitud = (TextView) findViewById(R.id.Altitud);
			   TextView textVelocidad = (TextView) findViewById(R.id.Velocidad);
		       TextView textLat = (TextView) findViewById(R.id.textView1);
		       TextView textLong = (TextView) findViewById(R.id.textView2);
		       TextView textImei = (TextView) findViewById(R.id.imei);
		       TextView textBatt = (TextView) findViewById(R.id.BatteryID);
		       TextView textCode = (TextView) findViewById(R.id.CodeID);
		       
		       Log.i("GPSData","[updateCoordinates]CRREE TEXTBOX" );	//DEBUG
		       SharedPreferences settings = getSharedPreferences("HT",MODE_PRIVATE);				      			  
		       Log.i("GPSData","[updateCoordinates]CRREE SHARED" );	//DEBUG
		       
		       textLat.setText("Latitud = "+dbLatitud);
		       textLong.setText("Longitud = "+dbLongitud);
		       textAltitud.setText("Altitud = "+dbAltitud);
		       textVelocidad.setText("Velocidad = "+flVelocidad);
		       textImei.setText("IMEI = "+strImei);
		       textCode.setText("CodeAct = "+settings.getString("CodeActiv",null));
		       textBatt.setText("Battery = "+strBattery);
		       
		       cantLat.setText("Cantidad de Act ="+CantidadAct);
		       CantidadAct++;
		       
		       Log.i("GPSData","[updateCoordinates]ACTUALICE TELA" );
		}catch (Exception ex)
		{
			
		}
	       return true;
	  // 	Log.i("PositionCYSActivity","[updateCoordinates] BATTERY: "+objService.strGetBatteryLvl());
	   	
	}	
}