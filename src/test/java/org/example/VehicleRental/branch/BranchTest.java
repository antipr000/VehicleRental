package org.example.VehicleRental.branch;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class BranchTest {
    IVehicleBookingStrategy vehicleBookingStrategy;
    IPriceHikingStrategy priceHikingStrategy;

    ArrayList<String> vehicleTypes;

    String branchName;
    public BranchTest() {
        vehicleBookingStrategy = new SelectMinPriceVehicleWithHikeStrategy();
        priceHikingStrategy = new HikePriceAt80();
        vehicleTypes = new ArrayList<>(Arrays.asList("CAR", "BIKE"));
        branchName = "B1";
    }

    @Test
    public void onboardVehicle_returnsTrueForCorrectVehicleType() {
        Branch b = createNewBranchForTest();
        boolean response = b.onboardVehicle("CAR", "V1", 100);
        Assert.assertTrue(response);
    }

    @Test
    public void onboardVehicle_returnsFalseForIncorrectVehicleType() {
        Branch b = createNewBranchForTest();
        boolean response = b.onboardVehicle("VAN", "V1", 200);
        Assert.assertFalse(response);
    }

    @Test
    public void onboardVehicle_returnsFalseForDuplicateVehicleId() {
        Branch b = createNewBranchForTest();
        b.onboardVehicle("CAR", "V1", 300);
        boolean response = b.onboardVehicle("BIKE", "V1", 200);
        Assert.assertFalse(response);
    }

    @Test
    public void bookVehicle_shouldReturnMinCostVehicle() {
        Branch b = createNewBranchForTest();
        b.onboardVehicle("CAR", "V1", 300);
        b.onboardVehicle("CAR", "V2", 200);
        b.onboardVehicle("CAR", "V3", 250);

        int response = b.bookVehicle("CAR", 1, 2);
        Assert.assertEquals(200, response);
    }

    @Test
    public void bookVehicle_shouldReturnMinus1WhenNoVehicleIsAvailableOfType() {
        Branch b = createNewBranchForTest();
        b.onboardVehicle("CAR", "V1", 300);
        b.bookVehicle("CAR", 1, 2);
        int response = b.bookVehicle("CAR", 1, 3);
        Assert.assertEquals(-1, response);
    }

    @Test
    public void displayVehicle_returnsVehiclesAvailableInSortedPrice() {
        Branch b = createNewBranchForTest();
        b.onboardVehicle("CAR", "V1", 300);
        b.onboardVehicle("CAR", "V2", 200);
        b.onboardVehicle("CAR", "V3", 250);

        b.bookVehicle("CAR", 1, 3);
        ArrayList<String> response = b.displayVehicles(1, 2);
        Assert.assertEquals(2, response.size());
        Assert.assertEquals("V3", response.get(0));
        Assert.assertEquals("V1", response.get(1));
    }

    private Branch createNewBranchForTest() {
        return new Branch(branchName, vehicleTypes, vehicleBookingStrategy, priceHikingStrategy);
    }
}
