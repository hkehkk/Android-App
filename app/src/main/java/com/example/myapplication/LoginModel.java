package com.example.myapplication;

public class LoginModel
{
    int EmployeeId;
    String firstName;
    String lastName;
    String username;
    String password;

    public int getUserId() {
        return EmployeeId;
    }

    public void setUserId(int userId) {
        this.EmployeeId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //Constructors
    public LoginModel()
    {

    }

    public LoginModel(int userId, String firstName, String lastName, String username, String password) {
        this.EmployeeId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }


}
