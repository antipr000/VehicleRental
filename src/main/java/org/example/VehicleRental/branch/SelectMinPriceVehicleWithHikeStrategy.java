package org.example.VehicleRental.branch;

import org.example.VehicleRental.vehicles.RentalItem;
import org.example.VehicleRental.vehicles.Vehicle;
import org.example.VehicleRental.vehicles.decorators.InflatedPrice;
import java.util.Optional;
import java.util.TreeSet;

public class SelectMinPriceVehicleWithHikeStrategy implements IVehicleBookingStrategy {
    @Override
    public int bookVehicle(TreeSet<Vehicle> vehicles, String vehicleType, int start, int end, boolean shouldHike) {
        Optional<Vehicle> lowestCostVehicle = vehicles.stream().filter(vehicle ->
                vehicle.getType().equals(vehicleType) && vehicle.slot.checkIfAvailable(start, end)
        ).findFirst();
        if(lowestCostVehicle.isPresent()) {
            lowestCostVehicle.get().slot.bookSlot(start, end);
            RentalItem item = lowestCostVehicle.get();
            if(shouldHike) {
                item = new InflatedPrice(item);
            }
            return item.getPrice() * (end - start);
        } else {
            return -1;
        }
    }
}
