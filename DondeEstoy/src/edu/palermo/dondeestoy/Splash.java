package edu.palermo.dondeestoy;

import edu.palermo.dondeestoy.bo.BaseResponse;
import edu.palermo.dondeestoy.rest.ApiService;
import edu.palermo.dondeestoy.rest.ApiServiceException;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.widget.GridView;
import android.widget.Toast;

public class Splash extends Activity {
	
	private static String TAG = "Splash";
	private String imeiActual =""; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_layout);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
		
		final TelephonyManager tm = (TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);
		
		Utils utils = new Utils(this);
		imeiActual = tm.getDeviceId();
		Log.i(TAG, "IMEI: " + imeiActual);
		
		String serverAddress = utils.getServerAddress();
		Log.i(TAG, "ServerAddress: " + serverAddress);
		
		utils.saveIMEI(imeiActual);
		
		ApiService.setServerAddress(serverAddress);
		
		Handler x = new Handler();
		x.postDelayed(new SplashHandler(), 2000);
				
		
	}


	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	class SplashHandler implements Runnable {
		public void run(){						
			startActivity(new Intent(getApplication(),MainActivity.class));
		}

		
	}
} 


