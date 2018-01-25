package com.example.uo252406.shared;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText tVName = (EditText) findViewById(R.id.eTName);
    EditText tVSurname = (EditText) findViewById(R.id.eTApellido);
    EditText tVEmail = (EditText) findViewById(R.id.eTEmail);

    SharedPreferences nameSharedPreferences;


    final SharedPreferences.Editor nameEditor = nameSharedPreferences.edit();

    /*
    SharedPreferences surnameSharedPreferences;
    SharedPreferences emailSharedPreferences;

    final SharedPreferences.Editor surnameEditor = surnameSharedPreferences.edit();
    final SharedPreferences.Editor emailEditor = emailSharedPreferences.edit();

*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameSharedPreferences=getSharedPreferences("name", Context.MODE_PRIVATE);
        /*
        surnameSharedPreferences=getSharedPreferences("surname", Context.MODE_PRIVATE);
        emailSharedPreferences=getSharedPreferences("email", Context.MODE_PRIVATE);
        */

    }

    public void setShared(View view){

        nameEditor.putString("name",tVName.toString());

        nameEditor.commit();
/*
        surnameEditor.putString("surname",tVSurname.toString());
        emailEditor.putString("email",tVEmail.toString());
        surnameEditor.commit();
        emailEditor.commit();
       */
    }
/*
    public void getShared(View view){
        SharedPreferences mSharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);

        String name = nameSharedPreferences.getString("name","Default name");
        String surname = surnameSharedPreferences.getString("surname","Default surname");
        String email = emailSharedPreferences.getString("email","Default email");

        tVName.setText(name);
        tVSurname.setText(surname);
        tVEmail.setText(email);
    }

    public void clean(View view){
        tVName.setText("");
        tVSurname.setText("");
        tVEmail.setText("");
    }
    */

}
