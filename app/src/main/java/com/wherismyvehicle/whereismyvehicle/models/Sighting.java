package com.wherismyvehicle.whereismyvehicle.Models;

import android.location.Location;

public class Sighting {
    private int id;
    private Vehicle vehicle;
    private String photo;
    private Double longitude;
    private Double latitude;

    public Sighting(int id, Vehicle vehicle, Location location, String photo) {
        this.id = id;
        this.vehicle = vehicle;
        this.longitude = location.getLongitude();
        this.latitude = location.getLatitude();
        this.photo = photo;
    }

    public Sighting(Location location, String photo) {
        this.longitude = location.getLongitude();
        this.latitude = location.getLatitude();
        this.photo = photo;
    }


    public int getId() {
        return id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Sighting setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        return this;
    }

    public Location getLocation() {
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

    public Sighting setPhoto(String photo) {
        this.photo = photo;
        return this;
    }
}
