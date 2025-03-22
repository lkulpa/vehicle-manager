package utils;

import java.util.List;

import static utils.Utils.getStringInput;

public class Helpers {

    public static String getVehicleTypeInput(List <String> viableTypes, String message) {
        while (true) {
            String vehicleType = getStringInput(message).toLowerCase();
            if (viableTypes.contains(vehicleType)) {
                return vehicleType;
            } else {
                System.out.println("This is not a valid vehicle type, please try again.");
            }
        }
    }
}