package com.nextdest.model;

public class Login {
    private int loginID;
    private String username;
    private String password;

    public Login() {

    }

    public Login(String username) { this.username = username;}
    public int getLoginID() { return loginID; }

    public void setLoginID(int loginID) { this.loginID = loginID; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }
}
