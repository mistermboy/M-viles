package com.example.uo252406.fragment;

import android.support.v4.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by UO252406 on 21/11/2017.
 */

public class TituloFragment extends ListFragment {

    OnLibroSelectedListener   libroItem;

    public interface OnLibroSelectedListener{
        public void onLibroSeleccionado(int position);
    }


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //Creamos un listView
        int layout=android.R.layout.simple_list_item_1;

        // Creamos un adapter de list view para mostrar los títulos
        setListAdapter(new ArrayAdapter<String>(getActivity(),layout,Libro.titulos));
    }

    @Override
    public void onStart(){
        super.onStart();
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);


        try{
            libroItem = (OnLibroSelectedListener) getActivity();
        }catch(ClassCastException e){
            throw new ClassCastException(getActivity().toString()+ "Debers de implementar onLibroSeleccionado");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long i){
        libroItem.onLibroSeleccionado(position);
    }

}
