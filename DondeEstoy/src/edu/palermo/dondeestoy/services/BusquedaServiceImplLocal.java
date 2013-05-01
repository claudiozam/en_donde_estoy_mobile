package edu.palermo.dondeestoy.services;

import java.util.ArrayList;
import java.util.List;

import edu.palermo.dondeestoy.Categoria;
import edu.palermo.dondeestoy.Lugar;

public class BusquedaServiceImplLocal implements BusquedaService {

	@Override
	public List<Categoria> getCategorias() {
		// TODO Auto-generated method stub
		
		List<Categoria> categorias = new ArrayList<Categoria>();
        
    	categorias.add(new Categoria(new Long("0"), "todos"));
    	categorias.add(new Categoria(new Long("1"), "cines"));
    	categorias.add(new Categoria(new Long("2"), "restaurantes"));
    	categorias.add(new Categoria(new Long("3"), "hoteles"));
    	

		return categorias;
	}

	@Override
	public List<Lugar> getLugares() {
		// TODO Auto-generated method stub
				
		List<Lugar> lugares = new ArrayList<Lugar>();
        
        lugares.add(new Lugar(new Long("0"), "todos"));
        lugares.add(new Lugar(new Long("1"), "microcentro"));
        lugares.add(new Lugar(new Long("2"), "recoleta"));
        lugares.add(new Lugar(new Long("3"), "palermo"));
        lugares.add(new Lugar(new Long("4"), "belgano"));
        lugares.add(new Lugar(new Long("5"), "nuñez"));
        lugares.add(new Lugar(new Long("6"), "saavedra"));
		return lugares;
	}
	
	

}
