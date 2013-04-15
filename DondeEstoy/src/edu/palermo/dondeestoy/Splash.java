package edu.palermo.dondeestoy;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;

public class Splash extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_layout);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
		Handler x = new Handler();
		x.postDelayed(new SplashHandler(this), 2000);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	class SplashHandler implements Runnable {
		Activity splash;
		
		public SplashHandler(Activity splash) {
			this.splash = splash;
		}
		public void run(){
			startActivity(new Intent(getApplication(),MainActivity.class));
			splash.finish();
		}

		
	}
} 


