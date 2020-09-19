package com.example.pruebaparcial02.modelo;

import java.io.Serializable;

public class ModelEdiciones implements Serializable {

    private String issue_id;
    private String cover;
    private String title;
    private String volume;
    private String number;
    private String year;
    private String doi;

    public ModelEdiciones(String issue_id, String cover, String title, String volume, String number, String year, String doi) {
        this.issue_id = issue_id;
        this.cover = cover;
        this.title = title;
        this.volume = volume;
        this.number = number;
        this.year = year;
        this.doi = doi;
    }

    public String getIssue_id() {
        return issue_id;
    }

    public void setIssue_id(String issue_id) {
        this.issue_id = issue_id;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }
}
