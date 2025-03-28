package main;

import vehicles.parts.*;
import vehicles.Vehicle;

import java.time.LocalDate;

import static utils.Utils.getStringInput;
import static utils.Helpers.getVehicleById;
import static utils.Helpers.getVehiclePart;
import static utils.Settings.setCurrentMenu;

public class WorkshopMenu {

    public static void chooseCommand() {
        String command = getStringInput(showCommands());
        switch (command) {
            case "repair":
                repairVehiclePart();
                break;
            case "replace":
                replaceVehiclePart();
                break;
            case "inspection":
                performVehicleInspection();
                break;
            case "back":
                back();
                break;
            default:
                System.out.println("Invalid command entered, please try again.");
        }
    }

    private static String showCommands() {
        return """
                Available commands:
                Repair - choose and repair a vehicle's part
                Replace - choose and replace a vehicle's part with a new one
                Inspection - perform a maintenance inspection for a chosen vehicle
                Back - go back to main menu
                """;
    }

    private static void performVehicleInspection() {
        Vehicle vehicle = getVehicleById();
        System.out.println("--- Inspection log ---");
        for (VehiclePart part : vehicle.getVehiclePartsDictionary().values()) {
            System.out.println(part.getPartStatus());
        }
        vehicle.setLastInspectionDate(LocalDate.now());
        vehicle.addEventToHistory(LocalDate.now(), "Vehicle inspection");
    }

    private static void repairVehiclePart() {
        Vehicle vehicle = getVehicleById();
        String partName = getStringInput("Which vehicle part would you like to repair?");
        VehiclePart vehiclePart = getVehiclePart(vehicle, partName);
        if (vehiclePart.getHealth() == 100) {
            System.out.println("Part is in perfect condition, no repairs needed");
        } else if (vehiclePart.getHealth() == 0) {
            System.out.println("Part is fully broken, cannot be repaired");
        } else {
            vehiclePart.repair();
            vehicle.addEventToHistory(LocalDate.now(), partName + " repair");
            System.out.println("Repair successful");
        }
    }

    private static void replaceVehiclePart() {
        Vehicle vehicle = getVehicleById();
        String partName = getStringInput("Which vehicle part would you like to replace?");
        String vehicleType = vehicle.getVehicleType();
        switch (partName.toLowerCase()) {
            case "brakes":
                vehicle.setBrakes(new Brakes(vehicleType));
                break;
            case "engine":
                vehicle.setEngine(new Engine(vehicleType, vehicle.getEngine().getCapacity()));
                break;
            case "wheels":
                vehicle.setWheels(new Wheels(vehicleType, vehicle.getWheels().getNumberOfWheels()));
                break;
            case "lights":
                vehicle.setLights(new Lights());
                break;
            case "suspension":
                vehicle.setSuspension(new Suspension(vehicleType));
                break;
            default:
                System.out.println("Invalid part name");
        }
        vehicle.addEventToHistory(LocalDate.now(), partName + " replacement");
    }

    private static void back() {
        setCurrentMenu("main");
    }
}