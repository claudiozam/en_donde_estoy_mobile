package domain;

public class Gps 
{
	
	public static String TAG = "Gps";
	
	private String Latitud;
	private String Longitud;
	private String Velocidad;
	private String Altitud;
	private String strImei;
	private String strID;
	private String strCode;
	private String strBattery;
	
	public Gps()
	{
		
	}
	public Gps(String Idc,String Lat,String Long,String Alti,String Speed,String Imei,String Code,String Battery)
	{
		Latitud=Lat;
		Longitud=Long;
		Velocidad=Speed;
		Altitud=Alti;
		strImei=Imei;
		strID=Idc;
		strCode=Code;
		strBattery=Battery;
	}
	
	 public String GetLatitud()
	 {
		 return Latitud;
	 }
	 public String GetLongitud()
	 {
		 return Longitud;
	 }
	 public String GetAltitud()
	 {
		 return Altitud;
	 }
	 public String GetVelocidad()
	 {
		 return Velocidad;
	 }
	 
	 public void SetLat(String Lat)
	 {
			Latitud=Lat;

	 }
	 public void SetLong(String Long)
	 {
			Longitud=Long;

	 }
	 public void SetVeloc(String Speed)
	 {
			Velocidad=Speed;

	 }
	 public void SetAltit(String Alti)
	 {
			Altitud=Alti;
					 
	 }
	 public void SetIdTipoCel(String IdTipocel)
	 {

			strID=IdTipocel;

	 }
	 public void SetImei(String sImei)
	 {		 
		 strImei=sImei;
		 
	 }
	 public void SetBattery(String sBattery)
	 {
		
			strBattery=sBattery;
	 }
	 public void SetCode(String Code)
	 {
			strCode=Code; 
	 }
}
