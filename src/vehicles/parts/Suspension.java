package vehicles.parts;

public class Suspension extends VehiclePart {

    public Suspension(String vehicleType) {
        if (vehicleType.equalsIgnoreCase("car")) {
            setDamageScale(0.4);
        } else {
            setDamageScale(0.6);
        }
    }
}