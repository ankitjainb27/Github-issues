package com.zopper.retrofitexample.models;

import java.io.Serializable;

/**
 * Created by ankitjain1 on 27/11/16.
 */

public class Issues implements Serializable {

    private String body;
    private String title;
    private int number;

    public String getBody() {
        return body;
    }

    public String getTitle() {
        return title;
    }

    public int getNumber() {
        return number;
    }
}
