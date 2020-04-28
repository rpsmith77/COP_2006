package SuperCharger;
/*
 @author Ryan Smith

 The super charger station that creates an array of charging outlet objects
 */

public class SuperChargerStation {
    // initialize ChargingOutlet object
    ChargingOutlet[] chargingOutlet;

    // constructor
    SuperChargerStation(int numOutlet) {
        chargingOutlet = new ChargingOutlet[numOutlet];
        for (int outlet = 0; outlet < numOutlet; outlet++) {
            chargingOutlet[outlet] = new ChargingOutlet();
        }
    }

    // set voltage
    public void setOutletVoltage(int numOutlet, double voltage) {
        chargingOutlet[numOutlet].setVoltage(voltage);
    }

    // set ampere
    public void setOutletAmpere(int numOutlet, double ampere) {
        chargingOutlet[numOutlet].setAmpere(ampere);
    }

    // set cost per kwh
    public void setOutletCostPerKWH(int numOutlet, double costPerKWH) {
        chargingOutlet[numOutlet].setCostPerKWH(costPerKWH);
    }

    // set battery capacity
    public void setBatteryCapacity(int numOutlet, double batteryCapacity) {
        chargingOutlet[numOutlet].setBatteryCapacity(batteryCapacity);
    }

    // set charge efficiency
    public void setBatteryChargeEfficiency(int numOutlet, double batteryChargeEfficiency) {
        chargingOutlet[numOutlet].setBatteryChargeEfficiency(batteryChargeEfficiency);
    }

    // set starting charge
    public void setBatteryChargeStart(int numOutlet, double chargeStart) {
        chargingOutlet[numOutlet].setBatteryChargeStart(chargeStart);
    }

    // set ending charge
    public void setBatteryChargeEnd(int numOutlet, double chargeEnd) {
        chargingOutlet[numOutlet].setBatteryChargeEnd(chargeEnd);
    }

    // set boolean Paying customer
    public void setPayingCustomer(int numOutlet, boolean paying) {
        chargingOutlet[numOutlet].setPayingCustomer(paying);
    }

    // calculate time of current vehicle
    public double calculateTime(int numOutlet) {
        return chargingOutlet[numOutlet].calculateTime();
    }

    // calculate cost of charging current vehicle
    public double calculateCost(int numOutlet) {
        return chargingOutlet[numOutlet].calculateCost();
    }

    // get time
    public double getTime(int numOutlet) {
        return chargingOutlet[numOutlet].getTime();
    }

    // get cost of paying customers
    public double getCostPaying(int numOutlet) {
        return chargingOutlet[numOutlet].getCostPaying();
    }

    // get lost revenue from non paying customers
    public double getCostFree(int numOutlet) {
        return chargingOutlet[numOutlet].getCostFree();
    }

    // get net profit
    public double getNetProfit(int numOutlet) {
        return chargingOutlet[numOutlet].getNetProfit();
    }
}
