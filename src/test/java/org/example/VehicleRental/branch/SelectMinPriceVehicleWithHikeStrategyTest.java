package org.example.VehicleRental.branch;

import org.example.VehicleRental.vehicles.Vehicle;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.TreeSet;

public class SelectMinPriceVehicleWithHikeStrategyTest {

    @Test
    public void bookVehicle_shouldReturnMinPricedVehicle() {
        Vehicle v1 = new Vehicle("CAR", "V1", 100);
        Vehicle v2 = new Vehicle("CAR", "V2", 30);

        SelectMinPriceVehicleWithHikeStrategy strategy = new SelectMinPriceVehicleWithHikeStrategy();
        int price = strategy.bookVehicle(
                new TreeSet<>(Arrays.asList(v1, v2)),
                "CAR",
                1,
                3,
                false);
        Assert.assertEquals(60, price);
    }

    @Test
    public void bookVehicle_shouldReturnHikedPrice() {
        Vehicle v1 = new Vehicle("CAR", "V1", 100);
        Vehicle v2 = new Vehicle("CAR", "V2", 30);

        SelectMinPriceVehicleWithHikeStrategy strategy = new SelectMinPriceVehicleWithHikeStrategy();
        int price = strategy.bookVehicle(
                new TreeSet<>(Arrays.asList(v1, v2)),
                "CAR",
                1,
                3,
                true);
        Assert.assertEquals(66, price);
    }
}
