package com.example.geoutils;

public class RestrictedRegion extends Region {
    private SubRegion parentSubRegion;

    public RestrictedRegion(String name, double latitude, double longitude, int user, long timestamp, SubRegion parentSubRegion) {
        super(name, latitude, longitude, user, timestamp);
        this.parentSubRegion = parentSubRegion;
    }

    public SubRegion getParentSubRegion() {
        return parentSubRegion;
    }
}
