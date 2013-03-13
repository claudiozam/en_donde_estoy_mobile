package wlocation.wk;

import wlocation.wk.R;
import android.app.Activity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
//import positioncysand.wk.HTService;


public class WelcomeActivity extends Activity {

	 public static String Welcome = "Welcome:";
	//private TelephonyManager mTelManager = (TelephonyManager)this.getSystemService(TELEPHONY_SERVICE);

	/** Called when the activity is first created. */
	private String pathFiles="/CYS/";
    private String strNameFileTime="cystime.txt";
	private Files objFiles = new Files();
	  
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		Log.i(Welcome,"[onCreate] Dentro OnCreate");
	
		SharedPreferences settings = getSharedPreferences("HT",MODE_PRIVATE);			//DEBUG
		Log.i(Welcome,"[onCreate] CODE ACT: "+settings.getString("CodeActiv",null));	//DEBUG
		
		
		super.onCreate(savedInstanceState);	
	    setContentView(R.layout.welcome);    

	    Log.i(Welcome,"[onCreate] Dentro Content");
	    
	    Button button = (Button) findViewById(R.id.buttonIngresar);
	    
	    
	    TextView txViewSenderTime = (TextView) findViewById(R.id.textViewSenderTime);
	    
	    button.setOnClickListener(new OnClickListener()
	    {     
			public void onClick(View v) 
			{				
				Log.i(Welcome,"[onCreate] Dentro OnClick");
					
				 final EditText edTextTime = (EditText) findViewById(R.id.editTextTime);
				
				
			    ////////////////////////////// TIMER SENDER //////////////////////////////
			    	String strTextTime=edTextTime.getText().toString();
					
					if(strTextTime!=null)			
					{
						SharedPreferences settings = getSharedPreferences("HT",MODE_PRIVATE);			   
						SharedPreferences.Editor editor = settings.edit();
					    			    
					    //	Toast.makeText(appContext,"Mail ingresado: "+mail.getText().toString(),Toast.LENGTH_SHORT).show();
							  
					    editor.putString("HT_Start", "OK" );					    					
					    editor.putString("Timer", strTextTime );
					    editor.putString("StartService", "OK" );
					    
					    editor.commit();
						
					    Intent IntWelcome = null;
					    Log.i(Welcome,"[onCreate] Voy a empezar activity. ");
					    
					    try
					    {
					    	IntWelcome=new Intent(getApplicationContext(),HTService.class);
					    }catch(Exception Ex)
					    {
					    	Log.e(Welcome,"[onCreate] Exception Welcome Intent: "+Ex);	
					    }
					    try
						{
					    	startActivity(IntWelcome);
						}catch(Exception ex)
						{
							Log.i(Welcome,"[onCreate] Exception Welcome Start: "+ex);	
						}
			
					}else
					{
						Toast.makeText(getApplicationContext(),"ERROR - ENTER TIME CORRECTLY",Toast.LENGTH_SHORT).show();
					}	
			    			
			}
	    });
	 
	}	
	
////////////################################ READFILE & SAVEFILE ########################//////////////	
////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void onBackPressed() {
		Log.i("WelcomeActivity", "[onBackPressed] onBackPressed");
	    // do nothing.
	}
	
}