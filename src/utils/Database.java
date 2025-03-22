package utils;

import vehicles.Vehicle;

import java.io.*;
import java.util.ArrayList;

import static utils.Utils.*;
import static utils.Settings.getDatabaseFilepath;

public class Database {

    private static ArrayList<Vehicle> vehicleObjects = new ArrayList<>();

    public static void serializeVehicleObjects() {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(getDatabaseFilepath());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(vehicleObjects);
            objectOutputStream.close();
            fileOutputStream.close();
            System.out.println("Database saved successfully");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deserializeVehicleObjects() {
        if (doesFileExist(getDatabaseFilepath())) {
            FileInputStream fileInputStream = null;
            try {
                fileInputStream = new FileInputStream(getDatabaseFilepath());
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                vehicleObjects = (ArrayList) objectInputStream.readObject();
                objectInputStream.close();
                fileInputStream.close();
                System.out.println("Database loaded successfully");
            } catch (ClassNotFoundException | IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Database file does not exist");
        }
    }

    public static void addVehicleObject(Vehicle vehicle) {
        vehicleObjects.add(vehicle);
    }

    public static void exportDatabaseToTxtFile(String filePath) {
        createFile(filePath);
        for (Vehicle vehicle : getVehicleObjects()) {
            String vehicleObjectAsString = vehicle.toString();
            addLineToTxtFile(vehicleObjectAsString, filePath);
        }
    }

    public static long getNumberOfItemsInDB() {
        return vehicleObjects.size();
    }

    public static ArrayList<Vehicle> getVehicleObjects() {
        return vehicleObjects;
    }
}