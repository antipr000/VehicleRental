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

    public Branch(String name, ArrayList<String> vehicleTypes, IVehicleBookingStrategy vehicleBookingStrategy) {
        this.name = name;
        this.vehicleTypes = vehicleTypes;
        this.vehicles = new TreeSet<>();
        this.vehicleBookingStrategy = vehicleBookingStrategy;
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
        double percentageBooked = (1.0 * numVehiclesBooked) / (1.0 * vehicles.size()) * 100.0;
        int result = vehicleBookingStrategy.bookVehicle(vehicles, vehicleType, start, end,
                percentageBooked > 80.0);
        if(result != -1) {
            numVehiclesBooked += 1;
        }
        return result;
    }

    public void displayVehicles(int start, int end) {
        List<Vehicle> availableVehicles =
                vehicles
                        .stream()
                        .filter(vehicle -> vehicle.slot.checkIfAvailable(start, end)).collect(Collectors.toList());
        if(availableVehicles.size() == 0) return;
        for(int i=0; i<availableVehicles.size()-1; i++) System.out.print(availableVehicles.get(i).getId() + ",");
        System.out.println(availableVehicles.get(availableVehicles.size()-1));
    }
}
