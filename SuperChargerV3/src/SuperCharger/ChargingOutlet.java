package SuperCharger;

/*
 @author Ryan Smith

 Creates a vehicle object, stores the electrical information, and calculates the time and cost of charging.
 */
public class ChargingOutlet {
    Vehicle vehicle;
    // store outlet's voltage
    private double voltage;
    // store outlet's ampere
    private double ampere;
    // store cost per KWH
    private double costPerKWH;
    // time paying[1] v not paying[2]
    private double[] time = new double[2];
    // charging cost paying[1] v not paying[2]
    private double[] cost = new double[2];

    // kw's charged
    private double amountCharged;
    // rate of charge
    private double chargeIntake;

    // constructor
    ChargingOutlet() {
        vehicle = new Vehicle();
    }

    // set voltage
    public void setVoltage(double voltage) {
        this.voltage = voltage;
    }

    // set ampere
    public void setAmpere(double ampere) {
        this.ampere = ampere;
    }

    // set cost per kwh
    public void setCostPerKWH(double costPerKWH) {
        this.costPerKWH = costPerKWH;
    }

    // set battery capacity
    public void setBatteryCapacity(double batteryCapacity) {
        vehicle.setBatteryCapacity(batteryCapacity);
    }

    // set charge efficiency
    public void setBatteryChargeEfficiency(double batteryChargeEfficiency) {
        vehicle.setBatteryChargeEfficiency(batteryChargeEfficiency);
    }

    // set starting charge
    public void setBatteryChargeStart(double chargeStart) {
        vehicle.setBatteryChargeStart(chargeStart);
    }

    // set ending charge
    public void setBatteryChargeEnd(double chargeEnd) {
        vehicle.setBatterChargeEnd(chargeEnd);
    }

    // whether or not the customer has to pay
    public void setPayingCustomer(boolean paying) {
        vehicle.setPayingCustomer(paying);
    }

    // calculate amount charged
    private void calculateAmountCharged() {
        amountCharged = (vehicle.getBatteryChargeEnd() - vehicle.getBatteryChargeStart()) / 100 *
                vehicle.getBatteryCapacity();
    }

    // calculate kw's used
    private void calculateChargeIntake() {
        chargeIntake = (voltage * ampere) / 1000 * vehicle.getBatteryChargeEfficiency();
    }

    // calculate time to charge
    public double calculateTime() {
        calculateAmountCharged();
        calculateChargeIntake();
        double timeCharging = amountCharged / chargeIntake;
        this.time[vehicle.getPayingCustomer()] += timeCharging;
        return timeCharging;
    }

    // total time charging at current outlet
    public double getTime() {
        return time[0] + time[1];
    }

    // total cost's at current outlet
    public double calculateCost() {
        double cost = amountCharged / vehicle.getBatteryChargeEfficiency() * costPerKWH;
        this.cost[vehicle.getPayingCustomer()] += cost;
        if (vehicle.getPayingCustomer() == 1) {
            return cost;
        } else {
            return 0;
        }
    }

    // get revenue
    public double getCostPaying() {
        return cost[1];
    }

    // get loss
    public double getCostFree() {
        return cost[0];
    }

    // get net profit
    public double getNetProfit() {
        return cost[1] - cost[0];
    }
}
