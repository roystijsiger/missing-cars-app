package com.wherismyvehicle.whereismyvehicle.models;

import android.location.Location;

@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class Sighting {
    private Vehicle vehicle;
    private String photo;
    private Double longitude;
    private Double latitude;

    public Sighting(Location location, String photo) {
        this.longitude = location.getLongitude();
        this.latitude = location.getLatitude();
        this.photo = photo;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Sighting setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        return this;
    }

    public Location getLocation() {
        // Locations cannot be initialized without a dummy location.
        Location location = new Location("Dummy provider");

        location.setLatitude(this.latitude);
        location.setLongitude(this.longitude);

        return location;
    }

    public Sighting setLocation(Location location) {
        this.longitude = location.getLongitude();
        this.latitude = location.getLatitude();
        return this;
    }

    public String getPhoto() {
        return photo;
    }
}
