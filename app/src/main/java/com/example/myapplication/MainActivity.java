package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//This class controls the activity I've created in my main activity
//Takes all code from (extends) AppCompatActvity to allow me to use all code from there
//public class so it can be accessed from anywhere in app
public class MainActivity extends AppCompatActivity
{
    private DatabaseHandler dbHandler;
    DataHolder dHolder;

    public void login(View view)
    {
        EditText usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        EditText passwordEditText = (EditText) findViewById(R.id.passwordEditText);

        Log.i("Info", "Button pressed");
        Log.i("Username", usernameEditText.getText().toString());
        Log.i("Password", passwordEditText.getText().toString());

        Toast.makeText(this, "Welcome, " + usernameEditText.getText().toString() + "!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EditText username = findViewById(R.id.usernameEditText);
        EditText password = findViewById(R.id.passwordEditText);
        Button loginButton = findViewById(R.id.loginButton);

        dbHandler = new DatabaseHandler(MainActivity.this);

        loginButton.setOnClickListener(v ->
        {
            String username1 = username.getText().toString();
            String password1 = password.getText().toString();

            int userId = dbHandler.getUserid(username1, password1);
            if ( userId == 0)
            {
                Toast.makeText(MainActivity.this, "Please try again.", Toast.LENGTH_SHORT).show();

            }
            else
            {
                Toast.makeText(MainActivity.this, "Successful Login.", Toast.LENGTH_SHORT).show();

                String data = DataHolder.getData();
                DataHolder.setData(String.valueOf(userId));

                startActivity(new Intent
                        (MainActivity.this, VisitEntryActivity.class));

            }
        });

        Button registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(v -> startActivity(new Intent
                (MainActivity.this, RegistrationActivity.class)));

    }
}


