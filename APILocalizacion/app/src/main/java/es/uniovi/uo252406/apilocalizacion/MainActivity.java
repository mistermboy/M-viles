/************************************************************
 * Código punto de partida para una app para localización
 * Incluye el código que gestiona la interfaz de usuario
 * Preparado para incluir la parte de localización por parte
 * del estudiante
 *
 * Software para Dispositivos Móviles
 * Escuela de Ingeniería Informática - Universidad de Oviedo
 */

package es.uniovi.uo252406.apilocalizacion;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;

import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.text.DateFormat;
import java.util.Date;

import android.location.Address;
import android.location.Geocoder;
import android.text.TextUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, LocationListener {
    // Etiqueta para los Log
    private static final String TAG = "ActLocalizacion-App";
    private static final int MY_PERMISSIONS_ACCESS_FINE_LOCATION = 1;

    // Widgets
    protected Button mStartUpdatesButton;
    protected Button mStopUpdatesButton;
    protected TextView mLastUpdateTimeTextView;
    protected TextView mLatitudeTextView;
    protected TextView mLongitudeTextView;

    protected Button mBuscaDirButton;
    protected TextView mDireccion;

    // Texto para los text view
    protected String mLatitudeLabel;
    protected String mLongitudeLabel;
    protected String mLastUpdateTimeLabel;
    private String mDireccionOutput;

    // Localización geográfica
    protected Location localizacionActual;
    private GoogleApiClient clienteLocalizacion;
    private LocationRequest peticionLoc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Relaciona código con los widgets de la UI.
        mStartUpdatesButton = (Button) findViewById(R.id.start_updates_button);
        mStopUpdatesButton = (Button) findViewById(R.id.stop_updates_button);
        mLatitudeTextView = (TextView) findViewById(R.id.latitude_text);
        mLongitudeTextView = (TextView) findViewById(R.id.longitude_text);
        mLastUpdateTimeTextView = (TextView) findViewById(R.id.last_update_time_text);

        mBuscaDirButton = (Button) findViewById(R.id.buscadir_button);
        mDireccion = (TextView) findViewById(R.id.direccion_text);

        // Asigna texto a las etiquetas.
        mLatitudeLabel = getResources().getString(R.string.latitude_label);
        mLongitudeLabel = getResources().getString(R.string.longitude_label);
        mLastUpdateTimeLabel = getResources().getString(R.string.last_update_time_label);
        mDireccionOutput = getResources().getString(R.string.direccionText);

        // TODO: Completar acciones necesarias
        pedirPermisos();
        crearClienteLoc();
        crearPeticionLoc();
    }

    private void crearClienteLoc() {
        if (clienteLocalizacion == null) {
            clienteLocalizacion = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addApi(LocationServices.API)
                    .build();
        }
    }

    protected void crearPeticionLoc() {
        peticionLoc = new LocationRequest();
        peticionLoc.setInterval(10000);
        peticionLoc.setPriority(
                LocationRequest.PRIORITY_HIGH_ACCURACY);
    }


    /* Esto es un fragmento de código que se corresponde con la petición de permisos
       necesarios para realizar la geolocalización. Este fragmento de código es necesario
       a partir de android 5. Se debe incluir en el onCreate() antes de utilizar cualquiera
       de los métodos relacionados. */

    private void pedirPermisos() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

            } else {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_ACCESS_FINE_LOCATION);

            }
        }
    }


    @Override
    protected void onStart() {
        super.onStart();

        clienteLocalizacion.connect();
    }


    @Override
    protected void onStop() {
        super.onStop();

        clienteLocalizacion.disconnect();
    }

    /**
     * Actualiza los valores de los text view con la localización y el momento
     * de su actualización
     * @param ubicacion posición recogida
     * @param mLastUpdateTime
     */
    private void actualizarInterfaz(Location ubicacion, Date mLastUpdateTime) {
        double lat;
        double lon;
        String ultAct = DateFormat.getTimeInstance().format(mLastUpdateTime);


        if (ubicacion == null) {
            Log.e(TAG, "No hay valor en ubicacion");
            lat = lon = 0;
        } else {
            lat = ubicacion.getLatitude();
            lon = ubicacion.getLongitude();
        }

        mLatitudeTextView.setText(String.format("%s: %f", mLatitudeLabel, lat));
        mLongitudeTextView.setText(String.format("%s: %f", mLongitudeLabel, lon));
        mLastUpdateTimeTextView.setText(String.format("%s: %s", mLastUpdateTimeLabel,
                ultAct));

        Toast.makeText(this, getResources().getString(R.string.location_updated_message),
                Toast.LENGTH_SHORT).show();

    }

    /**
     * Actualiza la dirección en la interfaz de usuario
     */
    protected void mostrarDireccion(String direccion) {
        mDireccion.setText(direccion);
    }


    /**
     * Click sobre el botón para empezar a realizar la actualización de la posición
     * @param view
     */
    public void startUpdatesButtonHandler(View view) {
        // TODO: Empezar a recoger actualizaciones
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(
                clienteLocalizacion, peticionLoc, this);

    }

    /**
     * Click sobre el botón para para de realizar la actualización automática de la paosición
     * @param view
     */
    public void stopUpdatesButtonHandler(View view) {
        // TODO: Finalizar recogida de actualizaciones
        LocationServices.FusedLocationApi.removeLocationUpdates
                (clienteLocalizacion,this);
    }

    /**
     * El usuario hace click para buscar la dirección
     * @param view
     */
    public void buscadirButtonHandler(View view) {
        // TODO: lanzar servicio para recuperar dirección
        mostrarDireccion(recuperarDireccion(localizacionActual));
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        //si llegamos aquí es que tenemos conexión con el proovedor de geolocalizacion
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        localizacionActual = LocationServices.FusedLocationApi.getLastLocation(clienteLocalizacion);
        actualizarInterfaz(localizacionActual,new Date());
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {
        localizacionActual = location;
        actualizarInterfaz(localizacionActual,new Date());
    }


    /**
     * Gestiona la llamada síncrona a Geocoder
     * Hace la petición con el parámetro posición y comprueba los posibles errores
     * Si hay error devuelve el mensaje de error, si no hay error concatena las líneas
     * de la dirección y la devuelve
     * Establece la variable estado a ERROR (-1) si se detecta algún error
     * @param posicion parámetro Location con la posición de la que queremos recuperar la dirección
     * @return Cadena con el mensaje de error, si lo hubo, con la dirección completa
     */
    private String recuperarDireccion(Location posicion) {
        final int ERROR = -1;
        final int OK= 0;

        // Instanciamos objeto Geocoder limita las búsquedas a la región definida
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        int estado = OK;
        String errorMessage = "Error al recuperar la dirección";
        String direccion= null;

        // Lista de direcciones que devolverá Geocoder.
        List<Address> addresses = null;

        try {
            /* getFromLo0cation() devuelve un array de direcciones que coinciden con el área
               que rodea la latitud / longitud que se proporciona. Los resultados son la
               mejor opción, pero no se asegura la precisión    */
            addresses = geocoder.getFromLocation(
                    posicion.getLatitude(),
                    posicion.getLongitude(),
                    // Aquí limitamos el número de direcciones devueltas
                    1);
        } catch (IOException ioException) {
            // Captura problemas de red y otros problemas de entrada/salida.
            errorMessage = getString(R.string.service_not_available);
            Log.e(TAG, errorMessage, ioException);
            estado= ERROR;
        } catch (IllegalArgumentException illegalArgumentException) {
            // Captura valores no válidos para la latitud o longitud.
            errorMessage = getString(R.string.invalid_lat_long_used);
            Log.e(TAG, errorMessage + ". " +
                    "Latitud = " + posicion.getLatitude() +
                    ", Longitud = " + posicion.getLongitude(), illegalArgumentException);
            estado= ERROR;
        }

        // Gestiona el caso donde no se encontró ninguna dirección.
        if (addresses == null || addresses.size()  == 0) {
            if (errorMessage.isEmpty()) {
                errorMessage = getString(R.string.no_address_found);
                Log.e(TAG, errorMessage);
                estado= ERROR;
            }
        } else {
            // Tenemos el array de direcciones
            Address address = addresses.get(0);
            ArrayList<String> addressFragments = new ArrayList<String>();

            /* Recupera las líneas de la dirección usando {@code getAddressLine},
               y las une para devolverlas. Se podrían haber utilizado otros métodos para
               para recuperar la dirección completa {@link android.location.address}:
             */
            for(int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                addressFragments.add(address.getAddressLine(i));
            }
            // junta todas las líneas utilizando el salto de línea
            direccion= TextUtils.join(System.getProperty("line.separator"), addressFragments);
            Log.i(TAG, getString(R.string.address_found));
        }

        if (estado==ERROR)
            return errorMessage;
        else
            return direccion;
    }

}
