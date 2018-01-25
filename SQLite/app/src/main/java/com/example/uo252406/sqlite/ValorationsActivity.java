package com.example.uo252406.sqlite;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;

import java.util.List;


public class ValorationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.valorations);
        ValorationDataSource bd = new ValorationDataSource(getApplicationContext());
        bd.open();
        List<ValorationClass> valorations =  bd.getAllValorations();
        ListView list = (ListView) findViewById(R.id.listView);
        ArrayAdapter<ValorationClass> adapter = new ArrayAdapter<ValorationClass>(this,android.R.layout.simple_list_item_1,valorations);
        list.setAdapter(adapter);
        bd.close();
    }



}
