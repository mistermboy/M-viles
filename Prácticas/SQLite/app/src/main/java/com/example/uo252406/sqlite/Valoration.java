package com.example.uo252406.sqlite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

/**
 * Created by UO252406 on 24/10/2017.
 */

public class Valoration extends AppCompatActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.valorate);
    }


    public void valorate(View view){
        ValorationClass v = new ValorationClass();
        EditText course = (EditText) findViewById(R.id.eTSubject);
        EditText comment = (EditText) findViewById(R.id.editText2);
        RatingBar rating = (RatingBar) findViewById(R.id.ratingBar);
        v.setCourse(course.getText().toString());
        v.setComment(comment.getText().toString());
        v.setRating(Math.round(rating.getRating()));

        ValorationDataSource bd = new ValorationDataSource(getApplicationContext());
        bd.open();
        bd.createValoration(v);
        bd.close();

        Toast.makeText(getApplicationContext(),
                "Su valoración ha sido procesada",
                Toast.LENGTH_SHORT).show();
        finish();
    }



}
