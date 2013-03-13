package wlocation.wk;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import wlocation.wk.Files;
import wlocation.wk.R;

public class HowToUseHt extends Activity {
	
	public static String HowToUseHt = "HowToUseHt:";
	/** Called when the activity is first created. */
	public String strImeiID=null; 
	private String pathFiles="/CYS/";
    private String strNameFileCode="cyscod.txt";
	private Files objFiles = new Files();
	public String getDeviceID() 
    {
    	  TelephonyManager TelephonyMgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
    	  String DEVICE_ID = TelephonyMgr.getDeviceId();
    	  return DEVICE_ID;
    }

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{	    
		 super.onCreate(savedInstanceState);
	
	     setContentView(R.layout.howtouseht);
	   	     
	     Button button = (Button) findViewById(R.id.buttonEmpezarAhora);
	    	
	     //////////////////////IMEI//////////////////////////////////////////
	    
	    TextView txIDUser =(TextView) findViewById(R.id.TextViewNuevoUsuario);
	    strImeiID=txIDUser.getText().toString();	    
	    strImeiID=strImeiID+this.getDeviceID();
	    Log.i(HowToUseHt,"[onCreate] IMEI:"+strImeiID);
	    txIDUser.setText(strImeiID);	    
	    ///////////////////////////////////////////////////////////////////
	    
	    //////////////////////////////CODE//////////////////////////////
	    Log.i(HowToUseHt,"[onCreate] VOY POR CODE ");
	    String strCodActUser = null;
	    try
	    {
	    	//strCodActUser=objFiles.ReadFile(pathFiles,this.strNameFileCode);
	    	SharedPreferences settings = getSharedPreferences("HT",MODE_PRIVATE);			//DEBUG
			//Log.i(HowToUseHt,"[onCreate] CODE ACT: "+settings.getString("CodeActiv",null));	//DEBUG
			strCodActUser=settings.getString("CodeActiv",null);
	    	Log.i(HowToUseHt,"[onCreate] CODE LEIDO:"+strCodActUser);
	    }catch(Exception ex)
	    {
	    	Log.i(HowToUseHt,"[onCreate] Exception Code: "+ex);
	    }
	    
	    
	    if(strCodActUser==null)
	    {
		    Random rd=new Random();
			 strCodActUser=String.valueOf(rd.nextInt(1000000));				
			strCodActUser="CYS"+strCodActUser;	    
		    TextView txCodeUser = (TextView) findViewById(R.id.TextViewCode);
		    txCodeUser.setText(txCodeUser.getText().toString()+strCodActUser);
		    
		    Log.i(HowToUseHt,"[onCreate] CODE NULL - NEW CODE: "+strCodActUser);
		    
		   // objFiles.SaveFile(pathFiles,this.strNameFileCode,strCodActUser);
	    }else
	    {
	    	TextView txCodeUser = (TextView) findViewById(R.id.TextViewCode);
		    txCodeUser.setText(txCodeUser.getText().toString()+strCodActUser);
	    }
	    ////////////////////////////////////////////////////////////////////
     
	    final String fnlstrCodeAct=strCodActUser;
	    
	    button.setOnClickListener(new OnClickListener()
	    {     

			public void onClick(View v) {
				
				Log.i(HowToUseHt,"Dentro OnClick");	
									
				
				SharedPreferences settings = getSharedPreferences("HT",MODE_PRIVATE);			   
				SharedPreferences.Editor editor = settings.edit();
			    			    
			    //	Toast.makeText(appContext,"Mail ingresado: "+mail.getText().toString(),Toast.LENGTH_SHORT).show();
					  
				    editor.putString("HT_Start", "OK" );					    					
				    editor.putString("CodeActiv",fnlstrCodeAct  );				    					
				    editor.putString("ImeiID",strImeiID );
				    
				    editor.commit();
					
				    try
				    {
				    	startActivity(new Intent(getApplicationContext(),WelcomeActivity.class));
				    }catch(Exception ex)
				    {
				    	Log.e(HowToUseHt,"Exception startAct How: "+ex);
				    }
			//		startActivity(new Intent(appContext,PositionCYS.class));   
					
			}
	    });
	    // TODO Auto-generated method stub
	}

	@Override
	public void onBackPressed() {
		Log.i("HowToUseHT", "[onBackPressed] onBackPressed");
	    // do nothing.
	}

	
}
