package com.example.uo252406.fragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements TituloFragment.OnLibroSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState != null) {
                return;
            }


            TituloFragment primerfragment = new TituloFragment();

            primerfragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, primerfragment).commit();


        }
    }

    @Override
    public void onLibroSeleccionado(int position) {

        LibroFragmento newFragment = new LibroFragmento();
        Bundle args = new Bundle();
        args.putInt(LibroFragmento.ARG_POSITION,position);
        newFragment.setArguments(args);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();


        transaction.replace(R.id.fragment_container,newFragment);
        transaction.addToBackStack(null);

        transaction.commit();


    }
}
