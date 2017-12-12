package com.example.uo252406.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by UO252406 on 21/11/2017.
 */

public class LibroFragmento extends Fragment {

    final static String ARG_POSITION = "position";
    int mCurrentPosition=-1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstaceState){

        return inflater.inflate(R.layout.libro_vista,container,false);
    }

    @Override
    public void onStart(){
        super.onStart();

        Bundle args = getArguments();

        if(args != null){
            mCurrentPosition=args.getInt(ARG_POSITION);
            updateLibroView(mCurrentPosition);
        }
    }


    public void updateLibroView(int position){
        TextView libro = (TextView) getActivity().findViewById(R.id.libro);
        libro.setText(Libro.detalles[position]);
    }

}
