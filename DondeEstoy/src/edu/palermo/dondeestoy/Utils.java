package edu.palermo.dondeestoy;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import android.R.integer;
import android.content.Context;
import android.content.SharedPreferences;

public class Utils {

	private Context context;
	private static final String NombrePref = "DondeEstoyPref";
	public static int MOVIL_LOCATION_TYPE_ID = 5;
	public static int PERSONAL_LOCATION_CATEGORY_ID = 5;
	public static Boolean serviceStart=true;
	 
	public static Map<String, Integer> categoryImages=Createmap();
	static  Map  Createmap()  {
        Map<String, Integer> aMap = new  HashMap<String,Integer>();
        aMap.put("TAXI", R.drawable.taxi);
        aMap.put("PERSONAL", R.drawable.personal);
        aMap.put("COMERCIO", R.drawable.comercio);
        aMap.put("PERSONAL", R.drawable.personal);
        aMap.put("INSTITUTO", R.drawable.instituto);
        aMap.put("COLECTIVO", R.drawable.colectivo);
        aMap.put("DEFAULT", R.drawable.personal);
        return Collections.unmodifiableMap(aMap);
    }
	
	
	public Utils(Context context) {
		this.context = context;
	}
	
	public void saveIMEI(String imei) {
		SharedPreferences settings = context.getSharedPreferences(NombrePref, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("IMEI", imei);
        editor.commit();
	}
	
	public String getIMEI() {
		SharedPreferences settings = context.getSharedPreferences(NombrePref, Context.MODE_PRIVATE);
		return settings.getString("IMEI", "");
	}
	
	public void saveServerAddress(String serverAddres) {
		SharedPreferences settings = context.getSharedPreferences(NombrePref, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("ServerAddres", serverAddres);
        editor.commit();
	}
	
	public String getServerAddress() {
		SharedPreferences settings = context.getSharedPreferences(NombrePref, Context.MODE_PRIVATE);
		return settings.getString("ServerAddres", "palermomov.no-ip.org:3000");
	}
	
}

