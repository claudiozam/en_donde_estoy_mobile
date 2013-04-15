package edu.palermo.dondeestoy;
import java.io.Serializable;

import com.google.android.gms.maps.model.LatLng;

public class PuntoMapa implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4388381502500632493L;
	private double Lat; 
	private double Lng;
	private String Titulo;
	private String Descripcion;
	
	public PuntoMapa(String titulo, LatLng ubicacion, String descripcion)
	{
		this.setUbicacion(ubicacion);
		this.setTitulo(titulo);
		this.setDescripcion(descripcion);
	}
	
	public PuntoMapa(Object JSON) throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException();
		// La idea es que mandemos los resultados del api rest y acá "hidratar" el objeto.
	}

	public LatLng getUbicacion() {
		return new LatLng(Lat, Lng);
	}

	public void setUbicacion(LatLng ubicacion) {
		// mapeo a float, porque LatLng no es serializable.
		Lat = ubicacion.latitude;
		Lng = ubicacion.longitude;
	}

	public String getTitulo() {
		return Titulo;
	}

	public void setTitulo(String titulo) {
		Titulo = titulo;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	
}
