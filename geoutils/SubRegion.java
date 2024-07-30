package com.example.geoutils;

public class SubRegion extends Region {
    private Region parentRegion;

    public SubRegion(String name, double latitude, double longitude, int user, long timestamp, Region parentRegion) {
        super(name, latitude, longitude, user, timestamp);
        this.parentRegion = parentRegion;
    }

    public Region getParentRegion() {
        return parentRegion;
    }
}
