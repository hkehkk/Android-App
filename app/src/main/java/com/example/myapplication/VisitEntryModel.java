package com.example.myapplication;

public class VisitEntryModel
{
   String employeeID;
   String date;
   String location;
   String employeeName;
   String clientsName;
   String whoAttended;
   String meetingNotes;

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getClientsName() {
        return clientsName;
    }

    public void setClientsName(String clientsName) {
        this.clientsName = clientsName;
    }

    public String getWhoAttended() {
        return whoAttended;
    }

    public void setWhoAttended(String whoAttended) {
        this.whoAttended = whoAttended;
    }

    public String getMeetingNotes() {
        return meetingNotes;
    }

    public void setMeetingNotes(String meetingNotes) {
        this.meetingNotes = meetingNotes;
    }

    public VisitEntryModel() {

    }

    public VisitEntryModel(String employeeID, String date, String location, String employeeName, String clientsName, String whoAttended, String meetingNotes) {
        this.employeeID = employeeID;
        this.date = date;
        this.location = location;
        this.employeeName = employeeName;
        this.clientsName = clientsName;
        this.whoAttended = whoAttended;
        this.meetingNotes = meetingNotes;
    }
}
