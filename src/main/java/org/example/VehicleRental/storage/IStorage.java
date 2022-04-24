package org.example.VehicleRental.storage;

import org.example.VehicleRental.branch.Branch;

public interface IStorage {
    public Branch getBranch(String name);
    public void storeBranch(String name, Branch b);
    public boolean doesBranchExist(String name);
}
