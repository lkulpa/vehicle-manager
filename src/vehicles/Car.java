package vehicles;

import java.time.LocalDate;

public class Car extends Vehicle {

    public Car(long vehicleID, int mileage, String fuelType, int wheelsNumber, int engineCapacity, String vehicleBrand, LocalDate lastInspectionDate) {
        super(vehicleID, mileage, fuelType, wheelsNumber, engineCapacity, vehicleBrand, lastInspectionDate);
    }

    @Override
    public boolean isInspectionNeeded() {
        if (getVehicleBrand().equals("BMW")) {
            return getMileage() > 7500;
        } else {
            return super.isInspectionNeeded();
        }
    }

    public boolean isTirePressureLow() {
        return getMileage() > 20000;
    }
}