package vehicles.parts;

public class Engine extends VehiclePart {

    private int capacity;

    public Engine(String vehicleType, int capacity) {
        this.capacity = capacity;
        if (vehicleType.equalsIgnoreCase("car")) {
            setDamageScale(0.5);
        } else {
            setDamageScale(0.6);
        }
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}