package com.example.uo252406.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText tVName = (EditText) findViewById(R.id.eTName);
    private EditText tVSurname = (EditText) findViewById(R.id.eTApellido);
    private EditText tVEmail = (EditText) findViewById(R.id.eTEmail);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void setShared(){
        SharedPreferences mSharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);

        final SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putString("name",tVName.toString());
        mEditor.putString("name",tVSurname.toString());
        mEditor.putString("name",tVEmail.toString());
        mEditor.commit();

    }

    public void getShared(){
        SharedPreferences mSharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        String name = mSharedPreferences.getString("name","Default name");
        String surname = mSharedPreferences.getString("surname","Default surname");
        String email = mSharedPreferences.getString("email","Default email");

        tVName.setText(name);
        tVSurname.setText(surname);
        tVEmail.setText(email);
    }

    public void clean(){
        tVName.setText("");
        tVSurname.setText("");
        tVEmail.setText("");
    }


}
