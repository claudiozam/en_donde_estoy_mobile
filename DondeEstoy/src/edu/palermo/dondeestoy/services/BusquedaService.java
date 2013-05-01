package edu.palermo.dondeestoy.services;

import java.util.List;

import edu.palermo.dondeestoy.Categoria;
import edu.palermo.dondeestoy.Lugar;

public interface BusquedaService {
	
	public List<Categoria> getCategorias();
	public List<Lugar> getLugares();

}
