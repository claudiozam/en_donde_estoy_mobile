package edu.palermo.dondeestoy;

import android.content.Context;
import android.content.SharedPreferences;

public class Utils {

	private Context context;
	private static final String NombrePref = "DondeEstoyPref";
	public static int MOVIL_LOCATION_TYPE_ID = 5;
	public static int PERSONAL_LOCATION_CATEGORY_ID = 5;
	
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
		return settings.getString("ServerAddres", "10.129.11.46:3000");
	}
	
}
