package SuperCharger;

/*
 @author Ryan Smith

 Creates battery object, holds whether or not the customer has to pay
 */

public class Vehicle {
    Battery battery;
    // boolean for whether or not the customer pays.
    private boolean payingCustomer;

    // constructor
    Vehicle() {
        battery = new Battery();
    }

    // set paying customer boolean
    public void setPayingCustomer(boolean payingCustomer) {
        this.payingCustomer = payingCustomer;
    }

    // return boolean payingCustomer as 1 or 0
    public int getPayingCustomer() {
        if (payingCustomer) {
            return 1;
        } else {
            return 0;
        }
    }

    // set battery capacity
    public void setBatteryCapacity(double batteryCapacity) {
        battery.setCapacity(batteryCapacity);
    }

    // get battery capacity
    public double getBatteryCapacity() {
        return battery.getCapacity();
    }

    // set charging efficiency
    public void setBatteryChargeEfficiency(double batteryChargeEfficiency) {
        battery.setChargeEfficiency(batteryChargeEfficiency);
    }
    // get charging efficiency
    public double getBatteryChargeEfficiency() {
        return battery.getChargeEfficiency();
    }

    // set starting charge
    public void setBatteryChargeStart(double batteryChargeStart) {
        battery.setChargeStart(batteryChargeStart);
    }

    // get starting charge
    public double getBatteryChargeStart() {
        return battery.getChargeStart();
    }

    // set ending charge
    public void setBatterChargeEnd(double batterChargeEnd) {
        battery.setChargeEnd(batterChargeEnd);
    }

    // get ending charge
    public double getBatteryChargeEnd() {
        return battery.getChargeEnd();
    }

}
