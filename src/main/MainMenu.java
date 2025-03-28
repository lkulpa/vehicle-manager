package main;

import utils.Helpers;
import utils.Database;

import vehicles.Car;
import vehicles.Vehicle;
import vehicles.Motorcycle;

import java.time.LocalDate;

import static utils.Utils.*;
import static utils.Helpers.getVehicleById;
import static utils.Settings.setCurrentMenu;
import static utils.Settings.getTxtExportFilepath;
import static utils.Database.exportDatabaseToTxtFile;

public class MainMenu {

    public static void chooseCommand() {
        String command = getStringInput(showCommands());
        switch (command) {
            case "add":
                addVehicle();
                break;
            case "view":
                viewAllVehicles();
                break;
            case "details":
                showDetailedVehicleInfo();
                break;
            case "simulation":
                goToSimulationMenu();
                break;
            case "workshop":
                goToWorkshopMenu();
                break;
            case "history":
                goToHistoryReportMenu();
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
                Details - view detailed info about chosen vehicle
                Simulation - open simulation menu
                Workshop - open workshop menu
                History - go to vehicle history report menu
                Export - export list of all vehicles to a text file
                Exit - save vehicles and exit the program""";
    }

    private static void addVehicle() {
        long vehicleID = Database.getNumberOfItemsInDB() + 1;
        String vehicleType = Helpers.getVehicleTypeInput("Is your vehicle a car or a motorcycle?").toLowerCase();
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

    private static void goToSimulationMenu() {
        setCurrentMenu("simulation");
    }

    private static void goToWorkshopMenu() {
        setCurrentMenu("workshop");
    }

    private static void goToHistoryReportMenu() {
        setCurrentMenu("history");
    }

    private static void showDetailedVehicleInfo() {
        Vehicle vehicle = getVehicleById();

        System.out.println(vehicle.getVehicleCondition());
        System.out.println(vehicle);
    }

    private static void saveAndExit() {
        Database.serializeVehicleObjects();
        System.exit(0);
    }
}