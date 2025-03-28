package vehicles;

import vehicles.parts.*;

import java.util.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Vehicle implements Serializable {

    private int mileage;
    private long vehicleID;
    private String fuelType;
    private String vehicleBrand;
    private LocalDate lastInspectionDate;
    private String vehicleType;

    private Brakes brakes;
    private Engine engine;
    private Wheels wheels;
    private Lights lights;
    private Suspension suspension;

    private Map<String, VehiclePart> vehiclePartsDictionary = new HashMap<>();
    private Map<LocalDate, String> vehicleHistory = new TreeMap<>();

    public Vehicle(long vehicleID, int mileage, String fuelType, int wheelsNumber, int engineCapacity, String vehicleBrand, LocalDate lastInspectionDate) {
        this.vehicleID = vehicleID;
        this.mileage = mileage;
        this.fuelType = fuelType;
        this.vehicleBrand = vehicleBrand;
        this.lastInspectionDate = lastInspectionDate;
        this.vehicleType = this.getClass().getSimpleName();

        vehicleHistory.put(lastInspectionDate, "Vehicle inspection");

        brakes = new Brakes(vehicleType);
        engine = new Engine(vehicleType, engineCapacity);
        wheels = new Wheels(vehicleType, wheelsNumber);
        lights = new Lights();
        suspension = new Suspension(vehicleType);

        vehiclePartsDictionary.put("brakes", brakes);
        vehiclePartsDictionary.put("engine", engine);
        vehiclePartsDictionary.put("wheels", wheels);
        vehiclePartsDictionary.put("lights", lights);
        vehiclePartsDictionary.put("suspension", suspension);

        for (VehiclePart part : vehiclePartsDictionary.values()) {
            part.applyDrivingDamage(mileage);
        }
    }

    public boolean isInspectionNeeded() {
        LocalDate currentDate = LocalDate.now();
        long monthsSinceInspection = ChronoUnit.MONTHS.between(lastInspectionDate, currentDate);
        return mileage > 15000 || monthsSinceInspection >= 12;
    }

    public boolean canItDrive() {
        for (VehiclePart part: vehiclePartsDictionary.values()) {
            if (part.getHealth() == 0) {
                return false;
            }
        }
        return true;
    }

    public void addEventToHistory(LocalDate date, String text) {
        vehicleHistory.merge(date, text, (existingText, newText) -> existingText + "\n" + newText);
    }

    public String getVehicleCondition() {
        return "--- Vehicle parts condition ---\n" +
                "Engine: " + engine.getHealth() + "/100\n" +
                "Brakes: " + brakes.getHealth() + "/100\n" +
                "Wheels: " + wheels.getHealth() + "/100\n" +
                "Suspension: " + suspension.getHealth() + "/100\n" +
                "Lights: " + lights.getHealth() + "/100";
    }

    public Map<String, VehiclePart> getVehiclePartsDictionary() {
        return vehiclePartsDictionary;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() +
                "{" +
                "ID=" + vehicleID +
                ", mileage=" + mileage +
                ", fuelType='" + fuelType + '\'' +
                ", wheelsNumber=" + wheels.getNumberOfWheels() +
                ", engineCapacity=" + engine.getCapacity() +
                ", vehicleBrand='" + vehicleBrand + '\'' +
                ", lastInspectionDate=" + lastInspectionDate +
                '}';
    }

    public long getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(long vehicleID) {
        this.vehicleID = vehicleID;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        if (mileage >= 0) {
            this.mileage = mileage;
        } else throw new IllegalArgumentException("Mileage can't be a negative number");
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public void setVehicleBrand(String vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public LocalDate getLastInspectionDate() {
        return lastInspectionDate;
    }

    public void setLastInspectionDate(LocalDate lastInspectionDate) {
        this.lastInspectionDate = lastInspectionDate;
    }

    public Brakes getBrakes() {
        return brakes;
    }

    public void setBrakes(Brakes brakes) {
        this.brakes = brakes;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Wheels getWheels() {
        return wheels;
    }

    public void setWheels(Wheels wheels) {
        this.wheels = wheels;
    }

    public Lights getLights() {
        return lights;
    }

    public void setLights(Lights lights) {
        this.lights = lights;
    }

    public Suspension getSuspension() {
        return suspension;
    }

    public void setSuspension(Suspension suspension) {
        this.suspension = suspension;
    }

    public Map<LocalDate, String> getVehicleHistory() {
        return vehicleHistory;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
}