package com.example.jecihjoy.pharmacyasistant.Model;

/**
 * Created by Jecihjoy on 4/13/2018.
 */

public class users {
    private String fname, lname, username,phone, email, location, pass1, pass2;
    private int id;

    public users(int id, String fname, String lname, String username, String phone, String email,
                 String location, String pass1, String pass2) {

        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.location = location;
        this.pass1 = pass1;
        this.pass2 = pass2;
        this.id = id;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPass1(String pass1) {
        this.pass1 = pass1;
    }

    public void setPass2(String pass2) {
        this.pass2 = pass2;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getUsername() {
        return username;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getLocation() {
        return location;
    }

    public String getPass1() {
        return pass1;
    }

    public String getPass2() {
        return pass2;
    }

    public int getId() {
        return id;
    }
}
