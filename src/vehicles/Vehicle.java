package vehicles;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

public class Vehicle implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private static final List<String> vehicleTypes = Arrays.asList("car", "motorcycle");

    private int mileage;
    private long vehicleID;
    private String fuelType;
    private int wheelsNumber;
    private int engineCapacity;
    private String vehicleBrand;
    private LocalDate lastInspectionDate;

    public Vehicle(long vehicleID, int mileage, String fuelType, int wheelsNumber, int engineCapacity, String vehicleBrand, LocalDate lastInspectionDate) {
        this.vehicleID = vehicleID;
        this.mileage = mileage;
        this.fuelType = fuelType;
        this.wheelsNumber = wheelsNumber;
        this.engineCapacity = engineCapacity;
        this.vehicleBrand = vehicleBrand;
        this.lastInspectionDate = lastInspectionDate;
    }

    public boolean isInspectionNeeded() {
        LocalDate currentDate = LocalDate.now();
        long monthsSinceInspection = ChronoUnit.MONTHS.between(lastInspectionDate, currentDate);
        return mileage > 15000 || monthsSinceInspection >= 12;
    }

    public boolean isOilChangeNeeded() {
        return mileage > 10000;
    }

    public static List<String> getVehicleTypes() {
        return vehicleTypes;
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

    public int getWheelsNumber() {
        return wheelsNumber;
    }

    public void setWheelsNumber(int wheelsNumber) {
        if (wheelsNumber >= 0) {
            this.wheelsNumber = wheelsNumber;
        } else throw new IllegalArgumentException("Cannot have negative number of wheels");
    }

    public int getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(int engineCapacity) {
        if (engineCapacity >= 0) {
            this.engineCapacity = engineCapacity;
        } else throw new IllegalArgumentException("Engine capacity cannot be a negative number");
    }

    public LocalDate getLastInspectionDate() {
        return lastInspectionDate;
    }

    public void setLastInspectionDate(LocalDate lastInspectionDate) {
        this.lastInspectionDate = lastInspectionDate;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() +
                "{" +
                "ID=" + vehicleID +
                ", mileage=" + mileage +
                ", fuelType='" + fuelType + '\'' +
                ", wheelsNumber=" + wheelsNumber +
                ", engineCapacity=" + engineCapacity +
                ", vehicleBrand='" + vehicleBrand + '\'' +
                ", lastInspectionDate=" + lastInspectionDate +
                '}';
    }
}