package edu.palermo.dondeestoy;

public class Lugar {
	public Lugar() {
	}

	public Lugar(Long id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	private Long id;
	private String nombre;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getNombre();
	}

}
