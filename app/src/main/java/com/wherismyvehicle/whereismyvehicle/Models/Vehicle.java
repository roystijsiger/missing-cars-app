package com.wherismyvehicle.whereismyvehicle.Models;

import android.location.Location;
import android.util.Log;

import java.util.ArrayList;

public class Vehicle {
    private int id;
    private String type;
    private String brand;
    private String color;
    private String licensePlate;
    private double lastKnownLongitude;
    private double lastKnownLatitude;
    private Location location;

    private ArrayList<Sighting> sightings;

    public Vehicle(int id, String type, String brand, String color, String licensePlate, double lastKnownLongitude, double lastKnownLatitude, ArrayList<Sighting> sightings) {
        this.id = id;
        this.type = type;
        this.brand = brand;
        this.color = color;
        this.licensePlate = licensePlate;
        this.lastKnownLatitude = lastKnownLatitude;
        this.lastKnownLongitude = lastKnownLongitude;

        this.sightings = sightings;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Vehicle setType(String type) {
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

    public String getLicensePlate() {
        return licensePlate;
    }

    public Vehicle setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
        return this;
    }

    public Location getLocation() {
        Location location = new Location("Dummy proved");
        Log.i("Longitude", "" + lastKnownLongitude);
        Log.i("Latitude", "" + lastKnownLatitude);
        location.setLatitude(lastKnownLatitude);
        location.setLongitude(lastKnownLongitude);
        return location;
    }

    public Vehicle setLocation(Location location) {
        this.location = location;
        return this;
    }

    public ArrayList<Sighting> getSightings() {
        return sightings;
    }

    public String getBrand(){
        return brand;
    }

    public Vehicle setBrand(String brand){
        this.brand = brand;
        return this;
    }
}
