package org.example.VehicleRental.storage;

import org.example.VehicleRental.branch.Branch;

import java.util.HashMap;

public class InMemoryStorage implements IStorage {
    HashMap<String, Branch> store;

    public InMemoryStorage(){
        store = new HashMap<>();
    }

    @Override
    public Branch getBranch(String name) {
        return store.get(name);
    }

    @Override
    public void storeBranch(String name, Branch b) {
        store.put(name, b);
    }

    @Override
    public boolean doesBranchExist(String name) {
        return store.containsKey(name);
    }
}
