package com.example.tenisu.models;

public class Statistics {

    private double imc;
    private double medianHeight;
    private String countryWithHighestWinRatio;

    public Statistics(double imc, double medianHeight, String countryWithHighestWinRatio) {
        this.imc = imc;
        this.medianHeight = medianHeight;
        this.countryWithHighestWinRatio = countryWithHighestWinRatio;
    }

    public double getImc() {
        return imc;
    }

    public double getMedianHeight() {
        return medianHeight;
    }

    public String getCountryWithHighestRatio() {
        return countryWithHighestWinRatio;
    }
}
