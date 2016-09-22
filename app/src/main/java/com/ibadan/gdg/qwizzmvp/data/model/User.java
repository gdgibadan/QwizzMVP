package com.ibadan.gdg.qwizzmvp.data.model;

/**
 * Created by Hamza Fetuga on 9/20/2016.
 */
public class User {

    public static final String PREF_KEY = "username";

    private String username;

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
