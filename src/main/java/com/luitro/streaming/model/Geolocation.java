package com.luitro.streaming.model;

public class Geolocation {

    private Double lon;
    private Double lat;

    public Geolocation(Double lon, Double lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }
}
