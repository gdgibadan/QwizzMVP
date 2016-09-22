package com.ibadan.gdg.qwizzmvp.data.model;

/**
 * Created by Hamza Fetuga on 9/20/2016.
 */
public class Capital {

    private String id;
    private String name;

    public Capital(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
