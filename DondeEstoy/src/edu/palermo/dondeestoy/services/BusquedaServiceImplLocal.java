package edu.palermo.dondeestoy.services;

import java.util.ArrayList;
import java.util.List;

import edu.palermo.dondeestoy.Category;
import edu.palermo.dondeestoy.Place;

public class BusquedaServiceImplLocal implements BusquedaService {

	@Override
	public List<Category> getCategorias() {
		// TODO Auto-generated method stub
		
		List<Category> categorias = new ArrayList<Category>();
        
    	categorias.add(new Category(new Long("0"), "todos"));
    	categorias.add(new Category(new Long("1"), "cines"));
    	categorias.add(new Category(new Long("2"), "restaurantes"));
    	categorias.add(new Category(new Long("3"), "hoteles"));
    	

		return categorias;
	}

	@Override
	public List<Place> getLugares() {
		// TODO Auto-generated method stub
				
		List<Place> lugares = new ArrayList<Place>();
        
        lugares.add(new Place(new Long("0"), "todos"));
        lugares.add(new Place(new Long("1"), "microcentro"));
        lugares.add(new Place(new Long("2"), "recoleta"));
        lugares.add(new Place(new Long("3"), "palermo"));
        lugares.add(new Place(new Long("4"), "belgano"));
        lugares.add(new Place(new Long("5"), "nuñez"));
        lugares.add(new Place(new Long("6"), "saavedra"));
		return lugares;
	}
	
	

}
