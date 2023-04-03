package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    private DatabaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        EditText firstNameEdit = findViewById(R.id.firstNameEdit);
        EditText lastNameEdit = findViewById(R.id.lastNameEdit);
        EditText usernameEdit = findViewById(R.id.usernameEdit);
        EditText passwordEdit = findViewById(R.id.passwordEdit);
        Button submitButton1 = findViewById(R.id.submitButton1);

      dbHandler = new DatabaseHandler(RegistrationActivity.this);

        submitButton1.setOnClickListener(v -> {
            String firstName = firstNameEdit.getText().toString();
            String lastName = lastNameEdit.getText().toString();
            String username = usernameEdit.getText().toString();
            String password = passwordEdit.getText().toString();

            if (firstName.isEmpty()  && lastName.isEmpty() && username.isEmpty() &&
                    password.isEmpty())
            {
                Toast.makeText(RegistrationActivity.this, "Please enter all data.", Toast.LENGTH_SHORT).show();
                return;
            }

            dbHandler.addNewEmployee( firstName, lastName, username, password);

            Toast.makeText(RegistrationActivity.this, "Entry has been added.", Toast.LENGTH_SHORT).show();
            firstNameEdit.setText("");
            lastNameEdit.setText("");
            usernameEdit.setText("");
            passwordEdit.setText("");
        });

        Button backToLoginButton = findViewById(R.id.backToLoginButton);
        backToLoginButton.setOnClickListener(v -> startActivity(new Intent
                (RegistrationActivity.this, MainActivity.class)));
    }


}