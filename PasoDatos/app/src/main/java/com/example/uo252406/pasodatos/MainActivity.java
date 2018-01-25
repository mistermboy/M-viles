package com.example.uo252406.pasodatos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.uo252406.p3.R;

public class MainActivity extends AppCompatActivity {

        Intent intent;

        public final static int SEGUNDA_ACTIVIDAD=1;

       @Override
       protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
       }

        public void lanzaActivity(View view) {
            intent = new Intent(MainActivity.this, SecondActivity.class);
            Bundle bundle = new Bundle();
            EditText tV = (EditText) findViewById(R.id.eT);
            bundle.putString("key", tV.getText().toString());
            intent.putExtras(bundle);
            startActivityForResult(intent,SEGUNDA_ACTIVIDAD);
        }



        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            /*Recogemos datos*/
            if (resultCode == RESULT_OK) {
                final Bundle mBundle = data.getExtras();
                EditText tV = (EditText) findViewById(R.id.eT);
                String st = mBundle.getString("key");
                tV.setText(String.valueOf(st.length()));
            } else if (resultCode == RESULT_CANCELED) {
                /*Código necesario*/
            }
        }


}
