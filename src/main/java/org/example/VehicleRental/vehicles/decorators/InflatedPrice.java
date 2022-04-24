package org.example.VehicleRental.vehicles.decorators;

import org.example.VehicleRental.vehicles.RentalItem;
import org.example.VehicleRental.vehicles.Vehicle;

public class InflatedPrice extends PriceDecorator{
    public InflatedPrice(RentalItem rentalItem) {
        super(rentalItem);
    }

    @Override
    public Integer getPrice() {
        return this.rentalItem.getPrice() + (int)Math.ceil(0.1 * this.rentalItem.getPrice());
    }
}
