package org.example.VehicleRental;

import org.example.VehicleRental.services.RentalService;
import org.example.VehicleRental.storage.IStorage;
import org.example.VehicleRental.storage.InMemoryStorage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
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

                if(lineItems[0].equals("ADD_BRANCH")) {
                    String branchName = lineItems[1];
                    ArrayList<String> vehicleTypes = new ArrayList<>(Arrays.asList(lineItems[2].split(",")));
                    System.out.println(rentalService.addBranch(branchName, vehicleTypes) ? "TRUE" : "FALSE");
                } else if(lineItems[0].equals("ADD_VEHICLE")) {
                    String branchName = lineItems[1];
                    String vehicleType = lineItems[2];
                    String vehicleId = lineItems[3];
                    int price = Integer.parseInt(lineItems[4]);
                    System.out.println(rentalService.addVehicle(branchName, vehicleType, vehicleId, price) ? "TRUE" : "FALSE");
                } else if(lineItems[0].equals("BOOK")) {
                    String branchName = lineItems[1];
                    String vehicleType = lineItems[2];
                    int start = Integer.parseInt(lineItems[3]);
                    int end = Integer.parseInt(lineItems[4]);
                    System.out.println(rentalService.bookVehicle(branchName, vehicleType, start, end));
                } else if(lineItems[0].equals("DISPLAY_VEHICLES")) {
                    String branchName = lineItems[1];
                    int start = Integer.parseInt(lineItems[2]);
                    int end = Integer.parseInt(lineItems[3]);
                    ArrayList<String> vehicleIds = rentalService.displayVehicles(branchName, start, end);
                    if(vehicleIds.size() == 0) {
                        System.out.println();
                    } else {
                        for(int i=0; i<vehicleIds.size()-1; i++) {
                            System.out.print(vehicleIds.get(i) + ",");
                        }
                        System.out.println(vehicleIds.get(vehicleIds.size()-1));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }
}
