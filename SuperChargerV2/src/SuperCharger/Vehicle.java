package SuperCharger;
/*
 * @author Ryan Smith
 * Store information about current vehicle
 */

public class Vehicle {
    // batteries max capacity
    private double batteryCapacity = -1;
    // the charging efficiency of the battery
    private double chargingEfficiency = -1;
    // initial charge
    private double batteryInitialCharge = -1;
    // ending charge
    private double batteryEndingCharge = -1;

    // set battery capacity
    public void setBatteryCapacity(double batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }
    // get battery capacity
    public double getBatteryCapacity() {
        return batteryCapacity;
    }
    // set charging efficiency
    public void setChargingEfficiency(double chargingEfficiency) {
        this.chargingEfficiency = chargingEfficiency / 100;
    }
    // get charging efficiency
    public double getChargingEfficiency() {
        return chargingEfficiency;
    }
    // set initial charge
    public void setBatteryInitialCharge(double batteryInitialCharge) {
        this.batteryInitialCharge = batteryInitialCharge;
    }
    // get initial charge
    public double getBatteryInitialCharge() {
        return batteryInitialCharge;
    }
    // set ending charge
    public void setBatteryEndingCharge(double batteryEndingCharge) {
        this.batteryEndingCharge = batteryEndingCharge;
    }
    // get ending charge
    public double getBatteryEndingCharge() {
        return batteryEndingCharge;
    }
}
