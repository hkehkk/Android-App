package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
//import com.github.gcacace.signaturepad.views.SignaturePad;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class VisitEntryActivity extends AppCompatActivity
{
    private DatabaseHandler dbHandler;
    DataHolder dHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_entry);

        int employeeId = Integer.parseInt(DataHolder.getData());

        EditText editDateText = findViewById(R.id.editDate);
        EditText editLocationText = findViewById(R.id.editLocation);
        EditText editYourNameText = findViewById(R.id.editYourName);
        EditText editClientsNameText = findViewById(R.id.editClientsName);
        EditText editWhoAttendedText = findViewById(R.id.editWhoAttended);
        EditText editMeetingNotesText = findViewById(R.id.editMeetingNotes);
        Button submitButton = findViewById(R.id.entrySubmitButton);

        dbHandler = new DatabaseHandler(VisitEntryActivity.this);

        editYourNameText.setText(dbHandler.autoPop(employeeId));

        submitButton.setOnClickListener(v -> {
            String date = editDateText.getText().toString();
            String location = editLocationText.getText().toString();
            String employeeName = editYourNameText.getText().toString();
            String clientsName = editClientsNameText.getText().toString();
            String whoAttended = editWhoAttendedText.getText().toString();
            String meetingNotes = editMeetingNotesText.getText().toString();
            String employeeId1 = DataHolder.getData();

            // validating if the text fields are empty or not.
            if (date.isEmpty()  && location.isEmpty() && employeeName.isEmpty() &&
                clientsName.isEmpty() && whoAttended.isEmpty() && meetingNotes.isEmpty())
            {
                Toast.makeText(VisitEntryActivity.this, "Please enter all data.", Toast.LENGTH_SHORT).show();

            }

            dbHandler.addNewEntry( date, location, employeeName, clientsName, whoAttended, meetingNotes, employeeId1);

            Toast.makeText(VisitEntryActivity.this, "Entry has been added.", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setType("text/html");
            intent.putExtra(Intent.EXTRA_SUBJECT, "Hannah's Entry");
            intent.putExtra(Intent.EXTRA_TEXT,  String.format("%s\n%s\n%s\n%s\n%s\n%s\n", date, location, employeeName, clientsName, whoAttended, meetingNotes));
            intent.setData(Uri.parse("mailto:testmyjava2app@gmail.com"));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

            editDateText.setText("");
            editLocationText.setText("");
            editYourNameText.setText("");
            editClientsNameText.setText("");
            editWhoAttendedText.setText("");
            editMeetingNotesText.setText("");
        });

        Button nextPageButton1 = findViewById(R.id.nextPageButton);
        nextPageButton1.setOnClickListener(v -> startActivity(new Intent
                (VisitEntryActivity.this, SignatureActivity.class)));
    }
}