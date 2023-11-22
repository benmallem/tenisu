package com.example.tenisu.models;

import java.util.Arrays;

public class Player {

    private int id;
    private String firstname;
    private String lastname;
    private String shortname;
    private String sex;
    private Country country;
    private String picture;
    private PlayerData data;

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getShortname() {
        return shortname;
    }

    public String getSex() {
        return sex;
    }

    public Country getCountry() {
        return country;
    }

    public String getPicture() {
        return picture;
    }

    public PlayerData getData() {
        return data;
    }

    public double getWinRatio() {
        int[] playedMatches = this.getData().getLast();
        return (double) Arrays.stream(playedMatches).sum() / playedMatches.length;
    }

    public double getImc() {
        double height = (double) this.getData().getHeight() / 100;
        double weight = (double) this.getData().getWeight() / 1000;
        return weight / (height * height);
    }
}
