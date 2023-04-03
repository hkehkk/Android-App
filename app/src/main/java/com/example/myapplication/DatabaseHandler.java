package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.github.gcacace.signaturepad.views.SignaturePad;

public class DatabaseHandler extends SQLiteOpenHelper
{
    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Name
    private static final String DATABASE_NAME = "VisitEntries1";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // Table Names
    private static final String EMPLOYEES = "employees";
    private static final String ENTRIES = "entries";
    private static final String SIGNATURES = "signatures";

    //Columns for Employee Table
    public static final String EmployeeId = "Id";
    private static final String EmployeeFirstName = "employeeFirstName";
    private static final String EmployeeLastName = "employeeLastName";
    private static final String EmployeeUserName = "employeeUserName";
    private static final String EmployeePassword = "employeePassword";

    // Columns for Entries Table
    private static final String EntryId_col = "ID";
    private static final String Date_col = "date";
    private static final String Location_col = "location";
    private static final String EmployeeName_col =  "employeeName";
    private static final String ClientsName_col = "clientsName";
    private static final String WhoAttended_col = "whoAttended";
    private static final String MeetingNotes_col = "meetingNotes";
    private static final String EmployeeId_col = "employeeId";

    // Columns for Entries Table
    private static final String SignatureId_col = "ID";
    private static final String EntryId_COL = "entryId";
    private static final String SignaturePad_col = "signaturePad";

    // creating a constructor for our database handler.
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }



    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + EMPLOYEES + " ("
                + EmployeeId + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + EmployeeFirstName + " TEXT,"
                + EmployeeLastName + " TEXT,"
                + EmployeeUserName + " TEXT,"
                + EmployeePassword + " TEXT)";

        String query2 = "CREATE TABLE " + ENTRIES + " ("
                + EntryId_col + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Date_col + " DATETIME,"
                + Location_col + " TEXT,"
                + EmployeeName_col + " TEXT,"
                + ClientsName_col + " TEXT,"
                + WhoAttended_col + " TEXT,"
                + MeetingNotes_col + " TEXT,"
                + EmployeeId_col + " TEXT)";

        String query3 = "CREATE TABLE " + SIGNATURES + " ("
                + SignatureId_col + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + EntryId_COL + " TEXT NOT NULL,"
                + SignaturePad_col + "  BLOB)";
//                + "FOREIGN KEY (EntryId_col) REFERENCES ENTRIES (EntryId_col))";

        // methods to execute above sql queries
        db.execSQL(query);
        db.execSQL(query2);
        db.execSQL(query3);
    }

    public String autoPop(int userId)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String q = String.format("SELECT EmployeeFirstName||' '||EmployeeLastName FROM EMPLOYEES WHERE id = %d", userId);
        Cursor u = db.rawQuery( q, null);

        u.moveToFirst();
        String name = u.getString(0);

        u.close();
        db.close();
        return name;

    }

    public int getUserid(String username, String password) {

        SQLiteDatabase db = this.getReadableDatabase();

        int userid = 0;

        String q = String.format("SELECT Id FROM EMPLOYEES WHERE employeeUserName = '%s' and employeePassword = '%s'", username, password);
        Cursor u = db.rawQuery( q, null);

        u.moveToFirst();

        while(!u.isAfterLast())
        {
            userid = u.getInt(u.getColumnIndex("Id"));
            u.moveToNext();
        }
        u.close();
        db.close();
        return userid;
    }
    // this method is to add new entry to the sqlite database.
    public void addNewEmployee(String employeeFirstName, String employeeLastName, String employeeUsername, String employeePassword)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();
        values.put(EmployeeFirstName, employeeFirstName);
        values.put(EmployeeLastName, employeeLastName);
        values.put(EmployeeUserName, employeeUsername);
        values.put(EmployeePassword, employeePassword);

        db.insert(EMPLOYEES, null, values);
        db.close();
    }
    public void addNewEntry(String
            date, String location, String employeeName, String clientsName, String
            whoAttended, String meetingNotes, String employeeId) {

        // on below line creating a variable for
        // sqlite database and calling writable method
        // writing data in database.
        SQLiteDatabase db = this.getWritableDatabase();

        // creating variable for content values.
        ContentValues values = new ContentValues();
        values.put(Date_col, date);
        values.put(Location_col, location);
        values.put(EmployeeName_col, employeeName);
        values.put(ClientsName_col, clientsName);
        values.put(WhoAttended_col, whoAttended);
        values.put(MeetingNotes_col, meetingNotes);
        values.put(EmployeeId_col, employeeId);

        // added values, now passing
        // content values to table.
        db.insert(ENTRIES, null, values);
        db.close();
    }

    public void addNewSignature(String sp) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(SignaturePad_col, sp);

        db.insert(SIGNATURES, null, values);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + EMPLOYEES);
        db.execSQL("DROP TABLE IF EXISTS " + ENTRIES);
        db.execSQL("DROP TABLE IF EXISTS " + SIGNATURES);
        onCreate(db);
    }
}
