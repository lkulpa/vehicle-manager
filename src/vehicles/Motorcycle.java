package vehicles;

import java.time.LocalDate;

public class Motorcycle extends Vehicle {

    public Motorcycle(long vehicleID, int mileage, String fuelType, int wheelsNumber, int engineCapacity, String vehicleBrand, LocalDate lastInspectionDate) {
        super(vehicleID, mileage, fuelType, wheelsNumber, engineCapacity, vehicleBrand, lastInspectionDate);
    }

    @Override
    public boolean isInspectionNeeded() {
        if (getEngineCapacity() > 300) {
            return getMileage() > 5000;
        } else {
            return super.isInspectionNeeded();
        }
    }

    public boolean isChainGreaseNeeded() {
        return getMileage() > 5000;
    }
}