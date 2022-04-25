package org.example.VehicleRental.branch;

import org.example.VehicleRental.vehicles.Vehicle;
import org.junit.Assert;
import org.junit.Test;

import java.util.TreeSet;

public class HikePriceAt80Test {
    @Test
    public void shouldHikePrice_returnsTrueForGreaterThan80Percentage() {
        HikePriceAt80 hikePriceAt80 = new HikePriceAt80();
        TreeSet<Vehicle> vehicles = createVehicles(6, 5);
        boolean response = hikePriceAt80.shouldHikePrice(vehicles, 1, 3);
        Assert.assertTrue(response);
    }

    @Test
    public void shouldHikePrice_returnsFalseForLessThan80Percentage() {
        HikePriceAt80 hikePriceAt80 = new HikePriceAt80();
        TreeSet<Vehicle> vehicles = createVehicles(4, 3);
        System.out.println(vehicles.size());
        boolean response = hikePriceAt80.shouldHikePrice(vehicles, 1, 3);
        Assert.assertFalse(response);
    }

    private TreeSet<Vehicle> createVehicles(int numVehicles, int numBooked) {
        TreeSet<Vehicle> vehicles = new TreeSet<>();
        Vehicle []v = new Vehicle[numVehicles+1];
        for (int i=0; i<numVehicles; i++) {
            v[i] = new Vehicle("CAR", "V" + i, 200 + i);
            if(numBooked > 0) {
                v[i].slot.bookSlot(1, 3);
                numBooked--;
            }
            vehicles.add(v[i]);
        }
        return vehicles;
    }
}
