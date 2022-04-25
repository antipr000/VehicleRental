package org.example.VehicleRental.branch;

public interface IPriceHikingStrategy {
    public boolean shouldHikePrice(int numRentalUnitsBooked, int totalNumRentalUnits);
}
