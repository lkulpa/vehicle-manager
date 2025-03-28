package main;

import vehicles.Vehicle;
import vehicles.parts.VehiclePart;

import java.time.LocalDate;

import static utils.Utils.getStringInput;
import static utils.Helpers.getVehicleById;
import static utils.Settings.setCurrentMenu;
import static utils.Utils.getPositiveIntInput;

public class SimulationMenu {

    public static void chooseCommand() {
        String command = getStringInput(showCommands());
        switch (command) {
            case "drive":
                drive();
                break;
            case "crash":
                crash();
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
                Drive - add mileage
                Crash - simulate a crash
                Back - go back to main menu
                """;
    }

    private static void drive() {
        Vehicle vehicle = getVehicleById();
        if (vehicle.canItDrive()) {
            int distance = getPositiveIntInput("How many kilometers do you want to drive?");

            for (VehiclePart part : vehicle.getVehiclePartsDictionary().values()) {
                part.applyDrivingDamage(distance);
            }

            vehicle.setMileage(vehicle.getMileage() + distance);
        } else {
            System.out.println("One or more vehicle parts are broken, can't drive");
        }
    }

    private static void crash() {
        Vehicle vehicle = getVehicleById();

        for (VehiclePart part : vehicle.getVehiclePartsDictionary().values()) {
            part.applyFlatDamage(50);
        }
        vehicle.addEventToHistory(LocalDate.now(), "Vehicle crash");
    }

    private static void back() {
        setCurrentMenu("main");
    }
}