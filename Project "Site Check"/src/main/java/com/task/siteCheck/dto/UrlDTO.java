package com.task.siteCheck.dto;

import com.task.siteCheck.entity.Url;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class UrlDTO {
    private String email;
    private String url;

    private UrlDTO(){

    }

    public UrlDTO(String email, String url) {
        this.email = email;
        this.url = url;
    }

    public static UrlDTO of(Url url){
        return new UrlDTO(url.getEmail(), url.getUrl());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static int check(String URL){
        int code = 0;
        try {
            java.net.URL url = new URL(URL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            code = urlConnection.getResponseCode();
        } catch (IOException ex) {
            ex.getCause();
        }
        return code;
    }
}
