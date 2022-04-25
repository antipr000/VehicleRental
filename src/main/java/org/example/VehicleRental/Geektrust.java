package org.example.VehicleRental;

import org.example.VehicleRental.services.RentalService;
import org.example.VehicleRental.storage.IStorage;
import org.example.VehicleRental.storage.InMemoryStorage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Geektrust {
    public static void main(String[] args) {
        String filePath = args[0];
        IStorage storage = new InMemoryStorage();
        RentalService rentalService = new RentalService(storage);
        try{
            File file = new File(filePath);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String []lineItems = line.split(" ");
                String branchName = "", vehicleType = "", vehicleId = "";
                ArrayList<String> vehicleTypes = new ArrayList<>();
                int price = 0, start = 0, end = 0;

                switch (lineItems[0]) {
                    case "ADD_BRANCH":
                        branchName = lineItems[1];
                        for(int i=2; i<lineItems.length; i++) {
                            vehicleType = lineItems[i];
                            vehicleTypes.add(vehicleType);
                        }
                        System.out.println(rentalService.addBranch(branchName, vehicleTypes));
                        vehicleTypes.clear();
                        break;
                    case "ADD_VEHICLE":
                        branchName = lineItems[1];
                        vehicleType = lineItems[2];
                        vehicleId = lineItems[3];
                        price = Integer.parseInt(lineItems[4]);
                        System.out.println(rentalService.addVehicle(branchName, vehicleType, vehicleId, price));
                        break;
                    case "BOOK":
                        branchName = lineItems[1];
                        vehicleType = lineItems[2];
                        start = Integer.parseInt(lineItems[3]);
                        end = Integer.parseInt(lineItems[4]);
                        System.out.println(rentalService.bookVehicle(branchName, vehicleType, start, end));
                        break;
                    case "DISPLAY_VEHICLES":
                        branchName = lineItems[1];
                        start = Integer.parseInt(lineItems[2]);
                        end = Integer.parseInt(lineItems[3]);
                        ArrayList<String> vehicleIds = rentalService.displayVehicles(branchName, start, end);
                        if(vehicleIds.size() == 0) {
                            System.out.println();
                        } else {
                            for(int i=0; i<vehicleIds.size()-1; i++) {
                                System.out.print(vehicleIds.get(i) + ",");
                            }
                            System.out.println(vehicleIds.get(vehicleIds.size()-1));
                        }
                        break;
                    default:
                        System.out.println("Invalid command");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }
}
