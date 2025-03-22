package main;

import utils.Helpers;
import utils.Database;
import vehicles.Car;
import vehicles.Vehicle;
import vehicles.Motorcycle;

import java.time.LocalDate;

import static utils.Utils.*;
import static utils.Settings.getTxtExportFilepath;
import static utils.Database.exportDatabaseToTxtFile;

public class Interface {

    public static void chooseCommand() {
        String command = getStringInput(showCommands());
        switch (command) {
            case "add":
                addVehicle();
                break;
            case "view":
                viewAllVehicles();
                break;
            case "check":
                checkIfMaintenanceNeeded();
                break;
            case "export":
                exportDatabaseToTxtFile(getTxtExportFilepath());
                break;
            case "exit":
                saveAndExit();
                break;
            default:
                System.out.println("Invalid command entered, please try again.");
        }
    }

    private static String showCommands() {
        return """
                Available commands:
                Add - add a new vehicle to the database
                View - show all information about vehicles from database
                Check - check if a specified vehicle requires maintenance
                Export - export vehicles database to a text file
                Exit - save vehicles and exit the program""";
    }

    private static void addVehicle() {
        long vehicleID = Database.getNumberOfItemsInDB() + 1;
        String vehicleType = Helpers.getVehicleTypeInput(Vehicle.getVehicleTypes(), "Is your vehicle a car or a motorcycle?").toLowerCase();
        String brand = getStringInput("What is your " + vehicleType + "'s brand?");
        int wheelsNumber = getPositiveIntInput("How many wheels does your " + vehicleType + " have?");
        int engineCapacity = getPositiveIntInput("What is the engine's capacity?");
        int mileage = getPositiveIntInput("What is your " + vehicleType + "'s mileage?");
        String fuelType = getStringInput("What fuel types does your " + vehicleType + " use?");
        LocalDate lastInspectionDate = getDateInput("What is the date of your " + vehicleType + "'s last inspection? (YYYY-MM-DD)");

        Vehicle newVehicle;

        if (vehicleType.equals("car")) {
            newVehicle = new Car(vehicleID, mileage, fuelType, wheelsNumber, engineCapacity, brand, lastInspectionDate);
        } else {
            newVehicle = new Motorcycle(vehicleID, mileage, fuelType, wheelsNumber, engineCapacity, brand, lastInspectionDate);
        }

        Database.addVehicleObject(newVehicle);
    }

    private static void viewAllVehicles() {
        if (!Database.getVehicleObjects().isEmpty()) {
            for (Vehicle vehicle : Database.getVehicleObjects()) {
                System.out.println(vehicle.toString());
            }
        } else {
            System.out.println("There are no vehicles in the database yet.");
        }
    }

    private static void checkIfMaintenanceNeeded() {
        int vehicleID = getPositiveIntInput("Select the vehicle to check by its' ID:");
        Vehicle vehicle;

        if (vehicleID <= Database.getNumberOfItemsInDB() && vehicleID > 0) {
            vehicle = Database.getVehicleObjects().get(vehicleID - 1);
        } else {
            System.out.println("Vehicle with this ID does not exist in the database.");
            return;
        }

        if (vehicle.isInspectionNeeded()) {
            System.out.println("WARNING! Vehicle inspection is required");
        }
        if (vehicle instanceof Car) {
            if (((Car) vehicle).isTirePressureLow()) {
                System.out.println("WARNING! Tire pressure is too low!");
            }
        } else if (vehicle instanceof Motorcycle) {
            if (((Motorcycle) vehicle).isChainGreaseNeeded()) {
                System.out.println("WARNING! Chain needs lubricating!");
            }
        }
    }

    public static void saveAndExit() {
        Database.serializeVehicleObjects();
        System.exit(0);
    }
}