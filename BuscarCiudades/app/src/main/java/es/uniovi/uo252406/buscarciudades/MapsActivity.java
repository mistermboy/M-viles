package es.uniovi.uo252406.buscarciudades;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mapa;

    private GestorCiudades gc;		// Lista de ciudades
    private Ciudad c;				// Ciudad a tratar
    private int contCiudades;		// ¿Cuántas ciudades quedán?

    private TextView campoCiudad;	// TextView para poner la ciudad
    private Button botonAceptar;    // Botón para confirmar una localización
    private Button botonSiguiente;  // Botón para pedir la siguiente ciudad

    private Marker posUsuario;		// Posición marcada por el usuario
    private Marker posReal;			// Posición real de la ciudad

    private Circle[] circles = new Circle[3];
    private Polyline linea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        campoCiudad= (TextView) findViewById(R.id.campoCiudad); // Enlaza con el TextView
        botonAceptar= (Button) findViewById(R.id.botonAceptar);
        botonSiguiente= (Button) findViewById(R.id.botonSiguiente);



        // Crea ciudades para el juego y las mete en el gestor
        crearCiudades();

        mapFragment.getMapAsync(this);

    }

    /**
     * Crea ciudades y las mete en el Gestor para luego ir recuperándolas
     */
    private void crearCiudades() {
        gc= new GestorCiudades();
        gc.addCiudad(new Ciudad("La Coruña",43.362437, -8.411027));
        gc.addCiudad(new Ciudad("Málaga", 36.725547, -4.420937));
        gc.addCiudad(new Ciudad("Zamora", 41.504773, -5.746081));
        gc.addCiudad(new Ciudad("Ciudad Real", 38.979611, -3.929980));
        gc.addCiudad(new Ciudad("Soria", 41.766639, -2.479112));
        gc.addCiudad(new Ciudad("Madrid", 40.416439, -3.704607));
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapa = googleMap;

        // Como ejemplo recuperamos la primera ciudad y mostramos su marcador
        // Poner nombre ciudad y continuar dinámica del juego

        // Recupera la referencia a los controles
        UiSettings controles= mapa.getUiSettings();
        // Muestra el control de zoom
        controles.setZoomControlsEnabled(true);
        // Deshabilita el gesto de zoom
        controles.setZoomGesturesEnabled(false);

        contCiudades= gc.numCiudades();
        c= gc.nextCiudad();
        campoCiudad.setText(c.getNombre());
        contCiudades--;


        vistaInicialDelMapa();

        // Captura evento LongClick sobre el mapa
        // Registra una instancia que implementa OnMapLongClickListener
        mapa.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener()
        {
            // El método que recibe el callback,
            // parámetro LatLng con el punto donde se pulsó
            public void onMapLongClick(LatLng punto)
            {
                // Crea un marcador en el punto y lo añade al mapa
                MarkerOptions marcadorOpciones= new MarkerOptions()
                        .position(punto)
                        .title("Marcador creado por el usuario");
                mapa.addMarker(marcadorOpciones);
            }
        });
        

    }

    private  void vistaInicialDelMapa() {
        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(40.4378698,-3.8196211),15));
        mapa.animateCamera(CameraUpdateFactory.zoomTo(5.4f));
        mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
    }

    /**
     * Click sobre el botón aceptar
     * @param view
     */
    public void onClickAceptar(View view) {
        LatLng posCiudad = new LatLng(c.getLatitud(), c.getLongitud());
        posReal= mapa.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.estrella32))
                .anchor(0.5f,0.5f)
                .position(posCiudad)
                .title(c.getNombre()));
        for (int i=0;i<3;i++){
            circles[i]=mapa.addCircle(new CircleOptions().center(posCiudad).radius(25000*(i+1)));
        }
        
       // linea = mapa.addPolyline(new PolylineOptions().add(posCiudad).add(posUsuario.getPosition()));
        
        mapa.animateCamera(CameraUpdateFactory.newLatLngZoom(posCiudad,8));
	}

	/**
     * Click sobre el botón siguiente
     * @param view
     */
    public void onClickSiguiente(View view) {
        mapa.clear();
        if (contCiudades > 0) {
            //Pasar a la siguiente ciudad
            c = gc.nextCiudad();
            campoCiudad.setText(c.getNombre());
            contCiudades--;
        } else {
            Toast.makeText(this, "Juego terminado", Toast.LENGTH_LONG);
        }
        vistaInicialDelMapa();
    }

    

}
