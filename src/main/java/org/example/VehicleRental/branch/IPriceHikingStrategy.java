package org.example.VehicleRental.branch;

import org.example.VehicleRental.vehicles.Vehicle;

import java.util.TreeSet;

public interface IPriceHikingStrategy {
    public boolean shouldHikePrice(TreeSet<Vehicle> vehicles, int start, int end);
}
