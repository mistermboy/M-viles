package es.uniovi.uo252406.buscarciudades;

import java.util.NoSuchElementException;


public class PruebaCiudad {

	public static void main(String[] args)
	{
		GestorCiudades gc= new GestorCiudades();
		gc.addCiudad(new Ciudad("La Coruña",43.362437, -8.411027));
		gc.addCiudad(new Ciudad("Málaga",36.725547, -4.420937));
		gc.addCiudad(new Ciudad("Zamora",41.504773, -5.746081));
		gc.addCiudad(new Ciudad("Ciudad Real",38.979611, -3.929980));
		gc.addCiudad(new Ciudad("Soria",41.766639, -2.479112));
		gc.addCiudad(new Ciudad("Madrid",40.416439, -3.704607));
		
		int numCiudades= gc.numCiudades()+2;
		for (int i= 0; i<numCiudades; i++)
		{
			try {
				Ciudad c= gc.nextCiudad();
				System.out.println("Ciudad "+i+": "+c.getNombre());
				System.out.println("Coor. "+c.getLatitud()+", "+c.getLongitud());				
			}
			catch(NoSuchElementException e) {
				System.out.println("No hay más ciudades");
				System.out.println(e);	
				gc.reiniciarCiudades();
			}
		}
	}
}
