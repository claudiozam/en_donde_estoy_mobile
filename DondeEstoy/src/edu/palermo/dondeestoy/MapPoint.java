package edu.palermo.dondeestoy;

import java.io.Serializable;

import com.google.android.gms.maps.model.LatLng;

public class MapPoint implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4388381502500632493L;
	private double Lat;
	private double Lng;
	private String Device;
	private String Description;
	private String Category;
	

	public MapPoint(String device, LatLng latLng, String description,String category) {
		this.setLocation(latLng);
		this.setDevice(device);
		this.setDescription(description);
		this.setCategory(category);
		
	}

	public MapPoint(Object JSON) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	public LatLng getLocation() {
		return new LatLng(Lat, Lng);
	}

	public void setLocation(LatLng latLong) {
		// mapeo a float, porque LatLng no es serializable.
		Lat = latLong.latitude;
		Lng = latLong.longitude;
	}

	public String getDevice() {
		return Device;
	}

	public void setDevice(String device) {
		Device = device;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		this.Category = category;
	}

	

	

}
