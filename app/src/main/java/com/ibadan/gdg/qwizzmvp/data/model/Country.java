package com.ibadan.gdg.qwizzmvp.data.model;

/**
 * Created by Hamza Fetuga on 9/20/2016.
 */
public class Country {

    private final String id;
    private final String name;


    public Country(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
