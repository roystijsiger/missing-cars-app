package com.wherismyvehicle.whereismyvehicle.models;

public enum VehicleType {
    Car("Car"),
    MotorCycle("MotorCycle"),
    Scooter("Scooter"),
    Truck("Truck"),
    Bicycle("Bicycle");

    private String vehicleType;

    VehicleType(String vehicleType){
        this.vehicleType = vehicleType;
    }

    @Override public String toString() {
        return vehicleType;
    }
}
