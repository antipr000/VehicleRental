package org.example.VehicleRental.branch;

import org.example.VehicleRental.vehicles.Vehicle;

import java.util.TreeSet;

public interface IVehicleBookingStrategy {
    public int bookVehicle(TreeSet<Vehicle> vehicles, String vehicleType, int start, int end, boolean shouldHike);
}
