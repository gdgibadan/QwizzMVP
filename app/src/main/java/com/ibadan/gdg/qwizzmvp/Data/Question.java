package com.ibadan.gdg.qwizzmvp.Data;

/**
 * Created by Hamza Fetuga on 9/20/2016.
 */
public class Question {

    private int id;
    private String questionText;
    private String answer;

    public Question(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
