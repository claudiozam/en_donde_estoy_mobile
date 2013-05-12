package edu.palermo.dondeestoy.services;

import java.util.List;

import edu.palermo.dondeestoy.Category;
import edu.palermo.dondeestoy.Place;

public interface BusquedaService {
	
	public List<Category> getCategorias();
	public List<Place> getLugares();

}
