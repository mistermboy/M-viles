package es.uniovi.uo252406.buscarciudades;

public class Ciudad {
	private String nombre;
	private double latitud;
	private double longitud;
	
	public Ciudad(String nom, double d, double e)
	{
		setNombre(nom);
		setLatitud(d);
		setLongitud(e);
	}

	/**
	 * @return the latitud
	 */
	public double getLatitud() {
		return latitud;
	}

	/**
	 * @param d the latitud to set
	 */
	public void setLatitud(double d) {
		this.latitud = d;
	}

	/**
	 * @return the longitud
	 */
	public double getLongitud() {
		return longitud;
	}

	/**
	 * @param e the longitud to set
	 */
	public void setLongitud(double e) {
		this.longitud = e;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
