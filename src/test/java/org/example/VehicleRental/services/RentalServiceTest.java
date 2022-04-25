package org.example.VehicleRental.services;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.example.VehicleRental.branch.Branch;
import org.example.VehicleRental.branch.HikePriceAt80;
import org.example.VehicleRental.branch.SelectMinPriceVehicleWithHikeStrategy;
import org.example.VehicleRental.storage.IStorage;
import org.example.VehicleRental.storage.InMemoryStorage;
import org.junit.Test;
import org.junit.Assert;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.Arrays;


public class RentalServiceTest {

    @Test
    public void addBranch_shouldAddBranchToStorage() {
        IStorage storage = mock(InMemoryStorage.class);
        RentalService rentalService = new RentalService(storage);
        ArrayList<String> vehicleTypes = new ArrayList<>();
        ArgumentCaptor<String> branchNameArgumentCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Branch> branchArgumentCaptor = ArgumentCaptor.forClass(Branch.class);

        vehicleTypes.add("Car");
        vehicleTypes.add("Bike");
        boolean response = rentalService.addBranch("B1", vehicleTypes);

        Assert.assertTrue(response);
        verify(storage, times(1)).storeBranch(branchNameArgumentCaptor.capture(),
                branchArgumentCaptor.capture());
    }

    @Test
    public void addVehicle_shouldAddVehicleToBranch() {
        IStorage storage = mock(InMemoryStorage.class);
        RentalService rentalService = new RentalService(storage);
        ArrayList<String> vehicleTypes = new ArrayList<>();
        vehicleTypes.add("Car");
        vehicleTypes.add("Bike");
        Branch dummyBranch = new Branch(
                "B1",
                vehicleTypes,
                new SelectMinPriceVehicleWithHikeStrategy(),
                new HikePriceAt80());
        when(storage.doesBranchExist("B1")).thenReturn(true);
        when(storage.getBranch("B1")).thenReturn(dummyBranch);

        boolean response = rentalService.addVehicle("B1", "Car", "V1", 20);
        Assert.assertTrue(response);
    }

    @Test
    public void addVehicle_returnsFalseWhenVehicleTypeIsInvalid() {
        IStorage storage = mock(InMemoryStorage.class);
        RentalService rentalService = new RentalService(storage);
        ArrayList<String> vehicleTypes = new ArrayList<>();
        vehicleTypes.add("Car");
        vehicleTypes.add("Bike");
        Branch dummyBranch = new Branch(
                "B1",
                vehicleTypes,
                new SelectMinPriceVehicleWithHikeStrategy(),
                new HikePriceAt80());
        when(storage.doesBranchExist("B1")).thenReturn(true);
        when(storage.getBranch("B1")).thenReturn(dummyBranch);

        boolean response = rentalService.addVehicle("B1", "Taxi", "V1", 20);
        Assert.assertFalse(response);
    }

    @Test
    public void bookVehicle_shouldBookMinPriceVehicle() {
        IStorage storage = mock(InMemoryStorage.class);
        RentalService rentalService = new RentalService(storage);
        setupStorageForTest(storage);

        // add 2 vehicles
        rentalService.addVehicle("B1", "Car", "V1", 100);
        rentalService.addVehicle("B1", "Car", "V2", 30);

        int response = rentalService.bookVehicle("B1", "Car", 1, 2);

        Assert.assertEquals(30, response);
    }

    @Test
    public void bookVehicle_shouldHikePriceBy10Percentage() {
        IStorage storage = mock(InMemoryStorage.class);
        RentalService rentalService = new RentalService(storage);
        setupStorageForTest(storage);

        // add 5 vehicles
        rentalService.addVehicle("B1", "Car", "V1", 100);
        rentalService.addVehicle("B1", "Car", "V2", 30);
        rentalService.addVehicle("B1", "Car", "V3", 60);
        rentalService.addVehicle("B1", "Car", "V4", 120);
        rentalService.addVehicle("B1", "Car", "V5", 150);

        // book 4 vehicles
        rentalService.bookVehicle("B1", "Car", 1, 2);
        rentalService.bookVehicle("B1", "Car", 1, 2);
        rentalService.bookVehicle("B1", "Car", 1, 2);
        rentalService.bookVehicle("B1", "Car", 1, 2);

        int response = rentalService.bookVehicle("B1", "Car", 1, 2);

        Assert.assertEquals(165, response);
    }

    @Test
    public void displayVehicles_shouldReturnIdsOfAvailableVehicles() {
        IStorage storage = mock(InMemoryStorage.class);
        RentalService rentalService = new RentalService(storage);
        setupStorageForTest(storage);

        // add 3 vehicles
        rentalService.addVehicle("B1", "Car", "V1", 100);
        rentalService.addVehicle("B1", "Bike", "V2", 30);
        rentalService.addVehicle("B1", "Car", "V3", 60);

        // book 1 vehicle
        rentalService.bookVehicle("B1", "Car", 1, 3);

        ArrayList<String> availableVehicleIds = rentalService.displayVehicles("B1", 1, 3);

        Assert.assertEquals(2, availableVehicleIds.size());
        Assert.assertEquals("V2", availableVehicleIds.get(0)); // Lowest price
        Assert.assertEquals("V1", availableVehicleIds.get(1)); // Highest price
    }

    @Test
    public void endToEndTest() {
        // Testcase from https://codu.ai/coding-problem/navi-vehicle-rental
        IStorage storage = new InMemoryStorage();
        RentalService rentalService = new RentalService(storage);

        boolean response1 = rentalService.addBranch("B1", new ArrayList<>(Arrays.asList("CAR", "BIKE", "VAN")));
        boolean response2 = rentalService.addVehicle("B1", "CAR", "V1", 500);
        boolean response3 = rentalService.addVehicle("B1", "CAR", "V2", 1000);
        boolean response4 = rentalService.addVehicle("B1", "BIKE", "V3", 250);
        boolean response5 = rentalService.addVehicle("B1", "BIKE", "V4", 300);
        boolean response6 = rentalService.addVehicle("B1", "BUS", "V5", 2500);
        int response7 = rentalService.bookVehicle("B1", "VAN", 1, 5);
        int response8 = rentalService.bookVehicle("B1", "CAR", 1, 3);
        int response9 = rentalService.bookVehicle("B1", "BIKE", 2, 3);
        int response10 = rentalService.bookVehicle("B1", "BIKE", 2, 5);
        ArrayList<String> response11 = rentalService.displayVehicles("B1", 1, 5);

        Assert.assertTrue(response1);
        Assert.assertTrue(response2);
        Assert.assertTrue(response3);
        Assert.assertTrue(response4);
        Assert.assertTrue(response5);
        Assert.assertFalse(response6);
        Assert.assertEquals(-1, response7);
        Assert.assertEquals(1000, response8);
        Assert.assertEquals(250, response9);
        Assert.assertEquals(900, response10);
        Assert.assertEquals(1, response11.size());
        Assert.assertEquals("V2", response11.get(0));
    }

    private void setupStorageForTest(IStorage storage) {
        ArrayList<String> vehicleTypes = new ArrayList<>();
        vehicleTypes.add("Car");
        vehicleTypes.add("Bike");
        Branch dummyBranch = new Branch(
                "B1",
                vehicleTypes,
                new SelectMinPriceVehicleWithHikeStrategy(),
                new HikePriceAt80());
        when(storage.doesBranchExist("B1")).thenReturn(true);
        when(storage.getBranch("B1")).thenReturn(dummyBranch);
    }
}
