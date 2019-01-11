package com.whereismyvehicle.android.whereismyvehicle.Models;

import android.location.Location;

public class Sighting {
    private int id;
    private Vehicle vehicle;
    private Location location;
    private String photo;

    public Sighting(int id, Vehicle vehicle, Location location, String photo) {
        this.id = id;
        this.vehicle = vehicle;
        this.location = location;
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
        return location;
    }

    public Sighting setLocation(Location location) {
        this.location = location;
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
