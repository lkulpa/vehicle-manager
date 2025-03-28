package utils;

import vehicles.Vehicle;
import vehicles.parts.VehiclePart;

import static utils.Utils.getStringInput;
import static utils.Settings.getVehicleTypes;
import static utils.Utils.getPositiveIntInput;

public class Helpers {

    public static String getVehicleTypeInput(String message) {
        while (true) {
            String vehicleType = getStringInput(message).toLowerCase();
            if (getVehicleTypes().contains(vehicleType)) {
                return vehicleType;
            } else {
                System.out.println("This is not a valid vehicle type, please try again.");
            }
        }
    }

    public static Vehicle getVehicleById() {
        Vehicle vehicle = null;

        while (vehicle == null) {
            int vehicleId = getPositiveIntInput("Select the vehicle by its' ID:");
            if (vehicleId <= Database.getNumberOfItemsInDB() && vehicleId > 0) {
                vehicle = Database.getVehicleObjects().get(vehicleId - 1);
            } else {
                System.out.println("Vehicle with this ID does not exist in the database.");
            }
        }
        return vehicle;
    }

    public static VehiclePart getVehiclePart(Vehicle vehicle, String partName) {
        return vehicle.getVehiclePartsDictionary().get(partName.toLowerCase());
    }
}