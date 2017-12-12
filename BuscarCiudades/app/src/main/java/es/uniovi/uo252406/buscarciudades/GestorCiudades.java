package es.uniovi.uo252406.buscarciudades;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class GestorCiudades {
	ArrayList<Ciudad> ciudades;
	ArrayList<Ciudad> ciudadesQuedan= null;
	Random rnd;
	
	public GestorCiudades()
	{
		ciudades= new ArrayList<Ciudad>();
		ciudadesQuedan= new ArrayList<Ciudad>();
		rnd = new Random();
	}
	
	public void addCiudad(Ciudad c)
	{
		ciudades.add(c);
		ciudadesQuedan.add(c);
	}
	
	public Ciudad nextCiudad() throws NoSuchElementException
	{
		Ciudad c;

		if (ciudadesQuedan.size()==0)
		{
			System.out.println("No quedan más ciudades que devolver");
			throw new NoSuchElementException();
		}
		int n= rnd.nextInt(ciudadesQuedan.size());
		c= ciudadesQuedan.get(n);
		ciudadesQuedan.remove(n);
		
		return c;
	}
	
	public int numCiudades()
	{
		return ciudades.size();
	}

	public void reiniciarCiudades() {
		ciudadesQuedan= (ArrayList<Ciudad>)ciudades.clone();
	}
}
