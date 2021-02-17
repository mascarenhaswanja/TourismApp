package com.example.touristapp;

public class User {
    String email;
    String password;
    boolean rememberMe;

    public User(String email, String password, boolean rememberMe) {
        this.email = email;
        this.password = password;
        this.rememberMe = rememberMe;
    }

}
