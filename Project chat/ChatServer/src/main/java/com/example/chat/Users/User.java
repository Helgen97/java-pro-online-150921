package com.example.chat.Users;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class User {
    private String login;
    private String password;
    private boolean isOnline;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.isOnline = false;
    }

    public String getLogin() {
        return login;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public String getPassword() {
        return password;
    }

    public String toGson() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }

    @Override
    public String toString() {
        String status;
        if (isOnline) {
            status = "online";
        } else {
            status = "offline";
        }
        return "User: " + login + " - " + status;
    }

}
