package org.example.VehicleRental.vehicles;

public class VehicleOrderingStrategy implements Comparable<RentalItem>{
    private final Integer selfPrice;
    protected VehicleOrderingStrategy(Integer price) {
        this.selfPrice = price;
    }

    @Override
    public int compareTo(RentalItem o) {
        return selfPrice.compareTo(o.getPrice());
    }
}
