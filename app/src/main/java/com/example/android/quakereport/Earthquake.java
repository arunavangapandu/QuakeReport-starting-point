package com.example.android.quakereport;

/**
 * Created by vvangapandu on 6/5/17.
 */

public class Earthquake {

    private double magnitude;

    private String city;

    private long time;

    public Earthquake(double magnitude, String city, long time) {
        this.magnitude = magnitude;
        this.city = city;
        this.time = time;
    }

    // Get the magnitude of the earthquake

    public double getMagnitude() {

        return magnitude;
    }

    // Get the location of the earthquake occurrred

    public String getCity()
    {
        return city;
    }

    // Get the time of the earthquake occurred

    public long getTime() {

        return time;
    }


}
