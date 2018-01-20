package com.example.uo252406.pasodatos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uo252406.p3.R;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Bundle b = getIntent().getExtras();
        String st = b.getString("key");
        TextView tV = (TextView) findViewById(R.id.eT);
        tV.setText(st);
        //Libro l = b.getParcelable(MainActivity.OBJETO_LIBRO);
    }


    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(getApplicationContext(),
                    "Funciona correctamente",
                    Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.action_fin) {
            finish();
            return true;
        }
        if (id == R.id.action_atras) {
            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            EditText eT = (EditText) findViewById(R.id.eT);
            bundle.putString("key", eT.getText().toString());
            resultIntent.putExtras(bundle);
            setResult(RESULT_OK,resultIntent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void checkPassword(View view) {
        EditText p1 = (EditText) findViewById(R.id.eTPass);
        EditText p2 = (EditText) findViewById(R.id.eTRepass);
        if (p1.getText().toString().equals(p2.getText().toString())) {
            Toast.makeText(getApplicationContext(),
                    "Las contraseñas coinciden",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(),
                    "Las contraseñas NO coinciden",
                    Toast.LENGTH_SHORT).show();
        }
    }

}
