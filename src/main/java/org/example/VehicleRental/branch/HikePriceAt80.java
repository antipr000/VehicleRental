package org.example.VehicleRental.branch;

public class HikePriceAt80 implements IPriceHikingStrategy {
    @Override
    public boolean shouldHikePrice(int numRentalUnitsBooked, int totalNumRentalUnits) {
        double percentageBooking = (100.0 * numRentalUnitsBooked) / (1.0 * totalNumRentalUnits);
        return percentageBooking >= 80.0;
    }
}
