package com.wherismyvehicle.whereismyvehicle.Models;

import android.location.Location;

import java.util.ArrayList;

public class Vehicle {
    private int id;
    private VehicleType type;
    private String color;
    private String licencePlate;
    private Location location;

    private ArrayList<Sighting> sightings;

    public Vehicle(int id, VehicleType type, String color, String licencePlate, Location location, ArrayList<Sighting> sightings) {
        this.id = id;
        this.type = type;
        this.color = color;
        this.licencePlate = licencePlate;
        this.location = location;
        this.sightings = sightings;
    }

    public int getId() {
        return id;
    }

    public VehicleType getType() {
        return type;
    }

    public Vehicle setType(VehicleType type) {
        this.type = type;
        return this;
    }

    public String getColor() {
        return color;
    }

    public Vehicle setColor(String color) {
        this.color = color;
        return this;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public Vehicle setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
        return this;
    }

    public Location getLocation() {
        return location;
    }

    public Vehicle setLocation(Location location) {
        this.location = location;
        return this;
    }

    public ArrayList<Sighting> getSightings() {
        return sightings;
    }
}
