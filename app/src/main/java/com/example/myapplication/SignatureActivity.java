package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.gcacace.signaturepad.views.SignaturePad;

public class SignatureActivity extends AppCompatActivity {

    private Button submitButton;
    private DatabaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signature);

        SignaturePad signaturePad = findViewById(R.id.signaturePad);
        Button saveButton = findViewById(R.id.saveButton);
        Button clearButton = findViewById(R.id.clearButton);
        Button submitButton = findViewById(R.id.exitButton);

        //disable both buttons at start
        saveButton.setEnabled(false);
        clearButton.setEnabled(false);

        //change screen orientation to portrait mode
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        signaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning()
            {

            }

            @Override
            public void onSigned()
            {
                saveButton.setEnabled(true);
                clearButton.setEnabled(true);
            }

            @Override
            public void onClear()
            {
                saveButton.setEnabled(false);
                clearButton.setEnabled(false);
            }
        });

       // EditText signature = findViewById(R.id.signaturePad);

        saveButton.setOnClickListener(v ->
        {
            Toast.makeText(SignatureActivity.this,
                    "Signature Saved", Toast.LENGTH_SHORT).show();
        });

        clearButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                signaturePad.clear();
            }
        });

        Button exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(v -> startActivity(new Intent
                (SignatureActivity.this, MainActivity.class)));

        dbHandler = new DatabaseHandler(SignatureActivity.this);

//        submitButton.setOnClickListener(v -> {
//            String date = signaturePad.getText().toString();

    }
}