package com.zopper.retrofitexample.models;

/**
 * Created by ankitjain1 on 27/11/16.
 */

public class Comments {
    private String body;
    private User user;

    public String getBody() {
        return body;
    }

    public User getUser() {
        return user;
    }

    public class User {
        private String login;

        public String getLogin() {
            return login;
        }
    }
}
