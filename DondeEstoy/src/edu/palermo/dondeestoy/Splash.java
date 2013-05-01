package edu.palermo.dondeestoy;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.widget.GridView;

public class Splash extends Activity {

	public static final String NombrePref = "DondeEstoyPref";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_layout);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
		Handler x = new Handler();
		x.postDelayed(new SplashHandler(), 2000);
		
		final TelephonyManager tm = (TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);
		
		SharedPreferences settings = getSharedPreferences(NombrePref,0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("IMEI", tm.getDeviceId());
        
        editor.commit();
		
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


