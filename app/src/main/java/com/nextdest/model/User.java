package com.nextdest.model;

import android.graphics.Bitmap;

public class User {
    private String personID;
    private String userName;
    private String email;
    private String firstName;
    private String lastName;
    private Bitmap image;

    public User(){

    }

    public User(String userName, String email, String firstName, String lastName) {
        this.userName = userName;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;

    }
    public String getPersonID() {  return personID; }

    public void  setPersonID(String personID) {this.personID = personID;}

    public String getUserName() { return userName; }

    public void setUserName(String userName) { this.userName = userName; }

    public String getEmail() { return email;}

    public void setEmail(String email) { this.email = email; }

    public String getFirstName() {return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public Bitmap getImage() {  return image; }

    public void setImage(Bitmap image) {  this.image = image; }
}
