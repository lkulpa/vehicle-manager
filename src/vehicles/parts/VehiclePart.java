package vehicles.parts;

import java.io.Serializable;

public class VehiclePart implements Serializable {

    private double damageScale = 1;
    private double health;

    public VehiclePart() {
        health = 100;
    }

    public void applyFlatDamage(double damageAmount) {
        if (damageAmount >= health) {
            health = 0;
        } else if (damageAmount > 0) {
            health -= damageAmount;
        }
    }

    public void applyDrivingDamage(int mileage) {
        double damageAmount = (double) mileage / 1000 * damageScale;
        applyFlatDamage(damageAmount);
    }

    public void repair() {
        if (health > 0) {
            health = 100;
        }
    }

    public String getPartStatus() {
        String partName = this.getClass().getSimpleName();
        if (health == 100) {
            return partName + ": Perfect condition";
        } else if (health > 70) {
            return partName + ": Good condition";
        } else if (health > 35) {
            return partName + ": WARNING! Damaged, repairs needed.";
        } else if (health > 0) {
            return partName + ": WARNING! Critical condition, repairs needed. Further use can cause the part to break";
        } else {
            return partName + ": WARNING! Part broken. Replacement required";
        }
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getDamageScale() {
        return damageScale;
    }

    public void setDamageScale(double damageScale) {
        this.damageScale = damageScale;
    }
}