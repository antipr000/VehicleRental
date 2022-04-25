package org.example.VehicleRental.branch;

import org.example.VehicleRental.vehicles.Vehicle;

import java.util.TreeSet;

public class HikePriceAt80 implements IPriceHikingStrategy {
    @Override
    public boolean shouldHikePrice(TreeSet<Vehicle> vehicles, int start, int end) {
        long numRentalUnitsBooked = vehicles.stream().filter(vehicle -> !vehicle.slot.checkIfAvailable(start, end)).count();
        long totalNumRentalUnits = vehicles.size();
        double percentageBooking = (100.0 * numRentalUnitsBooked) / (1.0 * totalNumRentalUnits);
        return percentageBooking >= 80.0;
    }
}
