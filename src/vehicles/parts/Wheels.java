package vehicles.parts;

public class Wheels extends VehiclePart {

    private int numberOfWheels;

    public Wheels(String vehicleType, int numberOfWheels) {
        this.numberOfWheels = numberOfWheels;
        if (vehicleType.equalsIgnoreCase("car")) {
            setDamageScale(0.8);
        } else {
            setDamageScale(1);
        }
    }

    public int getNumberOfWheels() {
        return numberOfWheels;
    }

    public void setNumberOfWheels(int numberOfWheels) {
        this.numberOfWheels = numberOfWheels;
    }
}