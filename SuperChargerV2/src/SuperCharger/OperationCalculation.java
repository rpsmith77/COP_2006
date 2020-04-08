package SuperCharger;
/*
 * @author Ryan Smith
 * all the calculations for the Super charging station
 */
public class OperationCalculation {
    // whether or not they are a paying customer
    private boolean payingCustomer;
    // keep track of time spent at each outlet. Index 1 is the outlet, index 2 is paying or free
    private double[][] time;
    // keep track of cost at each outlet. Index 1 is the outlet, index 2 is paying or free
    private double[][] cost;
    // total time
    private double totalTime = 0;
    // total free time
    private double totalComplimentaryTime = 0;
    // total charges
    private double totalRevenue = 0;
    // total loss
    private double totalComplimentaryCost = 0;
    // cost per kwh
    private double costPerKWH = 0;
    // kwh used
    private double chargeIntake;
    // % of batter charged
    private double batteryAmountCharged;

    // constructor
    OperationCalculation(int numOutlet) {
        time = new double[numOutlet][2];
        cost = new double[numOutlet][2];
    }

    // set paying customer
    public void setPayingCustomer(boolean payingCustomer) {
        this.payingCustomer = payingCustomer;
    }
    // set cost per kwh
    public void setCostPerKWH(double costPerKWH) {
        this.costPerKWH = costPerKWH;
    }
    // get cost per kwh
    public double getCostPerKWH() {
        return costPerKWH;
    }
    // set kwh used
    public void setChargeIntake(double volt, double amp, double batteryEfficiency) {
        chargeIntake = (((volt * amp)) / 1000) * batteryEfficiency;
    }
    // set % battery charged
    public void setBatteryAmountCharged(double endCharge, double startCharge, double batteryCapacity) {
        batteryAmountCharged = (endCharge - startCharge) / 100 * batteryCapacity;
    }
    // display time of the current vehicle
    public void displayTime() {
        System.out.printf("The vehicle charged in %.2f minutes,", batteryAmountCharged / chargeIntake);
    }
    // calculate complimentary time
    public void setTotalComplimentaryTime(int outlet) {
        totalComplimentaryTime += time[outlet][0];
    }
    // display cost of current vehicle's charging
    public void displayCost(double batteryEfficiency) {
        System.out.printf(" at the cost of $%.2f\n", batteryAmountCharged / batteryEfficiency * costPerKWH);
    }
    // total the time in use for each outlet in each category
    public void setTime(int outlet) {
        if (payingCustomer) {
            time[outlet][1] += batteryAmountCharged / chargeIntake;
        } else {
            time[outlet][0] += batteryAmountCharged / chargeIntake;
        }
    }
    // calculate total time in use
    public void setTotalTime(int outlet) {
        totalTime += time[outlet][0] + time[outlet][1];
    }
    // display toatl time in use
    public void displayTotalTime() {
        System.out.printf("The total time for all vehicles charging at this SuperCharging Station was %.2f minutes\n",
                totalTime);
    }
    // set total money earned
    public void setTotalRevenue(int outlet) {
        totalRevenue += cost[outlet][1];
    }
    // set total money lost due to complimentary charging
    public void setTotalComplimentaryCost(int outlet) {
        totalComplimentaryCost += cost[outlet][0];
    }
    // display total money earned, lost, time lost, and earned - lost
    public void displayTotalProfit() {
        System.out.printf("The SuperCharging Station charged paying users $%.2f and lost $%.2f and %.2f minutes" +
                        " from free users.\n Totaling the profit for the station to $%.2f.\n",
                totalRevenue, totalComplimentaryCost, totalComplimentaryTime, totalRevenue - totalComplimentaryCost);
    }
    // total the costs for each outlet in the correct category
    public void setCost(int outlet, double batteryEfficiency) {
        if (payingCustomer) {
            cost[outlet][1] += batteryAmountCharged / batteryEfficiency * costPerKWH;
        } else {
            cost[outlet][0] += batteryAmountCharged / batteryEfficiency * costPerKWH;
        }
    }
    // display total time an outlet was in use
    public void displayOutletTotalTime(int outlet) {
        System.out.printf("Outlet #%d was used for %.2f minutes.\n", outlet + 1, time[outlet][0] + time[outlet][1]);
    }
    // display total cost for an outlet was
    public void displayOutletTotalCost(int outlet) {
        System.out.printf("Outlet #%d charged paying users $%.2f and lost $%.2f from free users.\n" +
                        " totaling the revenue for outlet #%d to $%.2f.\n",
                outlet + 1, cost[outlet][1], cost[outlet][0], outlet + 1, cost[outlet][1] - cost[outlet][0]);
    }
}
