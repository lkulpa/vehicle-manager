package vehicles.parts;

public class Brakes extends VehiclePart {

    public Brakes(String vehicleType) {
        if (vehicleType.equalsIgnoreCase("car")) {
            setDamageScale(0.7);
        } else {
            setDamageScale(0.8);
        }
    }
}