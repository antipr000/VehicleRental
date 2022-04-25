package org.example.VehicleRental.branch;

import org.example.VehicleRental.vehicles.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Branch {
    private final String name;
    private final ArrayList<String> vehicleTypes;

    private TreeSet<Vehicle> vehicles;

    private final IVehicleBookingStrategy vehicleBookingStrategy;
    private final IPriceHikingStrategy priceHikingStrategy;

    public Branch(String name,
                  ArrayList<String> vehicleTypes,
                  IVehicleBookingStrategy vehicleBookingStrategy,
                  IPriceHikingStrategy priceHikingStrategy) {
        this.name = name;
        this.vehicleTypes = vehicleTypes;
        this.vehicles = new TreeSet<>();
        this.vehicleBookingStrategy = vehicleBookingStrategy;
        this.priceHikingStrategy = priceHikingStrategy;
    }

    private boolean isValidVehicle(String vehicleType, String vehicleId) {
        if(vehicleTypes.stream().filter(type -> type.equals(vehicleType)).count() == 0) return  false;
        if(vehicles.stream().filter(vehicle -> vehicle.getId().equals(vehicleId)).count() > 0) return false;
        return true;
    }

    public boolean onboardVehicle(String vehicleType, String vehicleId, int price) {
        if(!isValidVehicle(vehicleType, vehicleId)) return false;
        Vehicle vehicle = new Vehicle(vehicleType, vehicleId, price);
        vehicles.add(vehicle);
        return true;
    }

    public int bookVehicle(String vehicleType, int start, int end) {
        int result = vehicleBookingStrategy.bookVehicle(vehicles, vehicleType, start, end,
                priceHikingStrategy.shouldHikePrice(vehicles, start, end));
        return result;
    }

    public ArrayList<String> displayVehicles(int start, int end) {
        List<String> availableVehicles =
                vehicles
                        .stream()
                        .filter(vehicle -> vehicle.slot.checkIfAvailable(start, end))
                        .map(vehicle -> vehicle.getId())
                        .collect(Collectors.toList());
        return (ArrayList<String>) availableVehicles;
    }
}
