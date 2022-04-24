package org.example.VehicleRental.vehicles.decorators;

import org.example.VehicleRental.vehicles.RentalItem;
import org.example.VehicleRental.vehicles.Vehicle;

public abstract class PriceDecorator implements RentalItem {
    protected final RentalItem rentalItem;
    PriceDecorator(RentalItem rentalItem) {
        this.rentalItem = rentalItem;
    }
    public abstract Integer getPrice();

    public String getType() {
        return null;
    }
}
