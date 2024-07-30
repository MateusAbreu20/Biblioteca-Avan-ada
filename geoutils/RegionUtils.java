package com.example.geoutils;

import com.google.android.gms.maps.model.LatLng;

import java.util.Queue;

public class RegionUtils {

    private static final double NEW_REGION_DISTANCE = 10000; // 10 km
    private static final double SUB_REGION_DISTANCE = 5000;  // 5 km

    public static void addRegionToQueue(
            Queue<Region> regionQueue,
            Queue<SubRegion> subRegionQueue,
            Queue<RestrictedRegion> restrictedRegionQueue,
            LatLng newLocation,
            int user,
            long timestamp
    ) {
        // Find the closest region
        Region closestRegion = findClosestRegion(regionQueue, newLocation);
        if (closestRegion == null || distance(new LatLng(closestRegion.getLatitude(), closestRegion.getLongitude()), newLocation) > NEW_REGION_DISTANCE) {
            // Create and add a new region
            Region newRegion = createNewRegion("Region " + (regionQueue.size() + 1), newLocation.latitude, newLocation.longitude, user, timestamp);
            regionQueue.add(newRegion);
        } else if (distance(new LatLng(closestRegion.getLatitude(), closestRegion.getLongitude()), newLocation) <= NEW_REGION_DISTANCE &&
                distance(new LatLng(closestRegion.getLatitude(), closestRegion.getLongitude()), newLocation) > SUB_REGION_DISTANCE) {
            // Create and add a new subregion
            SubRegion newSubRegion = createNewSubRegion("SubRegion " + (subRegionQueue.size() + 1), newLocation.latitude, newLocation.longitude, user, timestamp, closestRegion);
            subRegionQueue.add(newSubRegion);
        } else if (distance(new LatLng(closestRegion.getLatitude(), closestRegion.getLongitude()), newLocation) <= SUB_REGION_DISTANCE) {
            // Find the closest subregion
            SubRegion closestSubRegion = findClosestSubRegion(subRegionQueue, newLocation);
            if (closestSubRegion != null) {
                // Create and add a new restricted region
                RestrictedRegion newRestrictedRegion = createNewRestrictedRegion("RestrictedRegion " + (restrictedRegionQueue.size() + 1), newLocation.latitude, newLocation.longitude, user, timestamp, closestSubRegion);
                restrictedRegionQueue.add(newRestrictedRegion);
            }
        }
    }

    private static Region findClosestRegion(Queue<Region> regionQueue, LatLng newLocation) {
        Region closestRegion = null;
        double closestDistance = Double.MAX_VALUE;

        // Check the distance to existing regions
        for (Region region : regionQueue) {
            double distance = distance(new LatLng(region.getLatitude(), region.getLongitude()), newLocation);
            if (distance < closestDistance) {
                closestDistance = distance;
                closestRegion = region;
            }
        }
        return closestRegion;
    }

    private static SubRegion findClosestSubRegion(Queue<SubRegion> subRegionQueue, LatLng newLocation) {
        SubRegion closestSubRegion = null;
        double closestDistance = Double.MAX_VALUE;

        // Check the distance to existing subregions
        for (SubRegion subRegion : subRegionQueue) {
            double distance = distance(new LatLng(subRegion.getLatitude(), subRegion.getLongitude()), newLocation);
            if (distance < closestDistance) {
                closestDistance = distance;
                closestSubRegion = subRegion;
            }
        }
        return closestSubRegion;
    }

    public static double distance(LatLng start, LatLng end) {
        final int R = 6371; // Radius of the earth in km
        double latDistance = Math.toRadians(end.latitude - start.latitude);
        double lonDistance = Math.toRadians(end.longitude - start.longitude);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(Math.toRadians(start.latitude)) * Math.cos(Math.toRadians(end.latitude)) *
                        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c * 1000; // convert to meters
    }

    private static Region createNewRegion(String name, double latitude, double longitude, int user, long timestamp) {
        // Create a new Region object with the provided details
        return new Region(name, latitude, longitude, user, timestamp);
    }

    private static SubRegion createNewSubRegion(String name, double latitude, double longitude, int user, long timestamp, Region parentRegion) {
        // Create a new SubRegion object with the provided details
        return new SubRegion(name, latitude, longitude, user, timestamp, parentRegion);
    }

    private static RestrictedRegion createNewRestrictedRegion(String name, double latitude, double longitude, int user, long timestamp, SubRegion parentSubRegion) {
        // Create a new RestrictedRegion object with the provided details
        return new RestrictedRegion(name, latitude, longitude, user, timestamp, parentSubRegion);
    }
}
