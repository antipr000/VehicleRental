package org.example.VehicleRental.vehicles;

import org.example.VehicleRental.timeslot.TimeSlot;


public class Vehicle extends VehicleOrderingStrategy implements RentalItem {
    private final String type;
    private final String id;
    private final Integer price;
    public final TimeSlot slot;

    public Vehicle(String type, String id, int price) {
        super(price);
        this.type = type;
        this.id = id;
        this.price = price;
        slot = new TimeSlot();
    }

    public Integer getPrice() {
        return this.price;
    }

    public String getType() {
        return this.type;
    }

    public String getId() {
        return this.id;
    }
}
