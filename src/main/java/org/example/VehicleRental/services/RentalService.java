package org.example.VehicleRental.services;

import org.example.VehicleRental.branch.Branch;
import org.example.VehicleRental.branch.IVehicleBookingStrategy;
import org.example.VehicleRental.branch.SelectMinPriceVehicleWithHikeStrategy;
import org.example.VehicleRental.storage.IStorage;

import java.util.ArrayList;

public class RentalService {
    IStorage storage;

    public RentalService(IStorage storage) {
        this.storage = storage;
    }

    public boolean addBranch(String name, ArrayList<String> vehicleTypes) {
        if(storage.doesBranchExist(name)) return false;
        IVehicleBookingStrategy bookingStrategy = new SelectMinPriceVehicleWithHikeStrategy();
        Branch branch = new Branch(name, vehicleTypes, bookingStrategy);
        storage.storeBranch(name, branch);
        return true;
    }

    public boolean addVehicle(String branchName, String vehicleType, String vehicleName, int price) {
        if(!storage.doesBranchExist(branchName)) return false;
        Branch branch = storage.getBranch(branchName);
        return branch.onboardVehicle(vehicleType, vehicleName, price);
    }

    public int bookVehicle(String branchName, String vehicleType, int startTime, int endTime) {
        if(!storage.doesBranchExist(branchName)) return -1;
        Branch branch = storage.getBranch(branchName);
        return branch.bookVehicle(vehicleType, startTime, endTime);
    }

    public void displayVehicles(String branchName, int startTime, int endTime) {
        if(!storage.doesBranchExist(branchName)) return;
        Branch branch = storage.getBranch(branchName);
        branch.displayVehicles(startTime, endTime);
    }
}
