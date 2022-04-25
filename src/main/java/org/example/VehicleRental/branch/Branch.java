package org.example.VehicleRental.branch;

import apple.laf.JRSUIUtils;
import org.example.VehicleRental.vehicles.RentalItem;
import org.example.VehicleRental.vehicles.Vehicle;
import org.example.VehicleRental.vehicles.decorators.InflatedPrice;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Branch {
    private final String name;
    private final ArrayList<String> vehicleTypes;

    private TreeSet<Vehicle> vehicles;

    private int numVehiclesBooked = 0;

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

    private boolean isValidVehicle(String vehicleType) {
        if(vehicleTypes.stream().filter(type -> type == vehicleType).count() == 0) return  false;
        return true;
    }

    public boolean onboardVehicle(String vehicleType, String vehicleId, int price) {
        if(!isValidVehicle(vehicleType)) return false;
        Vehicle vehicle = new Vehicle(vehicleType, vehicleId, price);
        vehicles.add(vehicle);
        return true;
    }

    public int bookVehicle(String vehicleType, int start, int end) {
        int result = vehicleBookingStrategy.bookVehicle(vehicles, vehicleType, start, end,
                priceHikingStrategy.shouldHikePrice(numVehiclesBooked, vehicles.size()));
        if(result != -1) {
            numVehiclesBooked += 1;
        }
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
