package com.example.uo252406.notificaciones;

import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public final static String key="Key";
    NotificationCompat.Builder mBuilder;
    NotificationManager mNotificationManager;
    EditText time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void lanzar(View v) {

        time=(EditText)findViewById(R.id.eTTime);

        //Paso por parámetro el título y el contenido de la notificación
        ConstruirNotificacion("Compra", "Producto adquirido");

        AsyncTaskRunner runner = new AsyncTaskRunner();
        String sleepTime = time.getText().toString();

                /*Si mientras se hace la carga de la tarea asíncrona, giro la pantalla se está lanzando una nueva activity y puede
                ocurrir que la app casque. Posible soluciones:
                 1.fijar la orientación de la pantalla para esa activity en el manifiesto:android:screenOrientation="portrait"
                 2. Mirar la URL: https://androidresearch.wordpress.com/2013/05/10/dealing-with-asynctask-and-screen-orientation/ la cual indica
                  como hacerlo en onPreExecute y en onPostExecute */

        runner.execute(sleepTime);


    }

    public void ConstruirNotificacion(String titulo, String contenido){
        //Instancia del servicio de notificaciones
        mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //construcción de la notificación
        mBuilder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(titulo)
                        .setContentText(contenido);


    }

    private class AsyncTaskRunner extends AsyncTask<String, String, String> {
        ProgressDialog progressDialog;
        private String resp;


        @Override
        //String...params similar a String[] params
        //Método donde está el meollo. Es el que hace la tarea en background
        protected String doInBackground(String... params) {


            publishProgress("La compra se efectuará en "); // Calls onProgressUpdate()

            try {
                int time = Integer.parseInt(params[0])*1000;

                Thread.sleep(time);
                resp = "Notificación actualizada en  " + params[0] + " segundos";
            } catch (InterruptedException e) {
                e.printStackTrace();
                resp = e.getMessage();
            } catch (Exception e) {
                e.printStackTrace();
                resp = e.getMessage();
            }
            return resp;
        }


        //Método que se ejecuta cuando finaliza el doInBackground tomando el resultado de resp
        @Override
        protected void onPostExecute(String result) {
         /*
         //El código siguiente abre otra activity  tomando el resultado de resp
         Intent intent=new Intent(MainActivity.this, Main2Activity.class);
           Bundle mbundle=new Bundle();
           mbundle.putString(key,result);
           intent.putExtras(mbundle);
           startActivity(intent);*/

            finish();
        }

        //metodo que se ejecuta de la IU antes de que el hilo se empiece a ejecutar
        @Override
        protected void onPreExecute() {
            // Si usas múltiples notificaciones sobre el mismo evento, es mejor para tu app intentar actualizar la
            // notificación con nueva información, más que inmediatamente crear una nueva.
            // El ID de la notificación en este ejemplo es 01


            mNotificationManager.notify(001,mBuilder.build());


        }

        // Metodo que se ejecuta cuando es llamado publishProgress(...) en el doInBackground
        @Override
        protected void onProgressUpdate(String... text) {
            progressDialog = ProgressDialog.show(MainActivity.this, text[0],
                    " " + time.getText().toString() + " segundos");


        }
    }


}
