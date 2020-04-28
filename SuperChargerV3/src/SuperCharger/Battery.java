package SuperCharger;

/*
 @author Ryan Smith

 Holds battery capacity, charging efficiency, and starting and ending charge.
 */

public class Battery {
    // battery capacity
    private double capacity;
    // charge efficiency
    private double chargeEfficiency;
    // starting charge
    private double chargeStart;
    // ending charge
    private double chargeEnd;

    // set capacity
    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    // get capacity
    public double getCapacity() {
        return capacity;
    }

    // set efficiency
    public void setChargeEfficiency(double chargeEfficiency) {
        // convert to percentage
        this.chargeEfficiency = chargeEfficiency / 100;
    }

    // get efficiency
    public double getChargeEfficiency() {
        return chargeEfficiency;
    }

    // set starting charge
    public void setChargeStart(double chargeStart) {
        this.chargeStart = chargeStart;
    }

    // get starting charge
    public double getChargeStart() {
        return chargeStart;
    }

    // get ending charge
    public double getChargeEnd() {
        return chargeEnd;
    }

    // set ending charge
    public void setChargeEnd(double chargeEnd) {
        this.chargeEnd = chargeEnd;
    }
}
