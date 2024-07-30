package com.example.geoutils;

import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.LatLng;

public class Region {
    private String name;
    private double latitude;
    private double longitude;
    private int user;
    private long timestamp;
    private Marker marker;

    public Region(String name, double latitude, double longitude, int user, long timestamp) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.user = user;
        this.timestamp = timestamp;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }
}
