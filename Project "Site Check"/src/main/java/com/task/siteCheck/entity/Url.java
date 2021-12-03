package com.task.siteCheck.entity;

import com.task.siteCheck.dto.UrlDTO;

import javax.persistence.*;

@Entity
public class Url {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(name = "UserEmail", nullable = false)
    private String email;

    @Column(name = "SiteURL", nullable = false)
    private String url;

    public Url() {
    }

    public Url(String email, String url) {
        this.email = email;
        this.url = url;
    }

    public static Url fromDTO(UrlDTO urlDTO){
        return new Url(urlDTO.getEmail(), urlDTO.getUrl());
    }

    public int getId() {
        return Id;
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
}
