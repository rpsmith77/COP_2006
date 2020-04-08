package SuperCharger;
/*
 * @author Ryan Smith
 *
 * Calculate the charging time and cost of charging each vehicle that comes to this electric vehicle charging station.
 * It also needs to fit these requirements for my COP 2006 class.
 *
 Each supercharging station has multiple stalls, which should be user configurable.
 A stall may have multiple EVs charged during a specific period.  Assume that that the number of EVs charged at a particular stall
 is available after the fact, which should be configurable, i.e., user provided.  That is, you are not doing a live count or monitoring
 of EV charging.  There is no requirement to use a real timer.
 The charging time of each EV depends on factors you used in Assignment 01.
 The charging cost of each EV depends on factors you used in Assignment 01, except that the supercharging
 station decides the cost per kWh, which should be configurable.
 The supercharging station provides complimentary (free) charging to certain models of EV.
 You have the flexibility to design the complimentary scheme. Make it configurable.

 The supercharging station would like a report with the following information computed between specified start and end periods:
 	The charging time and cost of each EV. You can reuse this part from Assignment 01 or Exam 01.
 	The total charging time of all vehicles that used a particular stall.
	The total charging time used at the supercharging station, i.e., of all EVs at all the stalls.
	The total charging cost, i.e., revenue generated from, from all vehicles that the stalls at the supercharging station.
	The total charging cost and time that were provided complimentary at the supercharging station.
 */

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    // create a scanner that works in functions
    private static Scanner keyboard = new Scanner(System.in);

    // prompting the user and collecting input
    public static double getInputDouble(String message){
        System.out.println(message);
        return keyboard.nextDouble();
    }
    public static int getInputInt(String message){
        System.out.println(message);
        return keyboard.nextInt();
    }
    public static String getInputString(String message){
        System.out.println(message);
        return keyboard.next();
    }
    public static void displayError(){
        System.out.println("Invalid input try again");
        keyboard.next();
    }

    // checks if there are any vehicles to process for current outlet
    private static boolean isMoreVehicles(int outlet, String message) {
        String checkMoreVehicle = "";
        do {
            try {
                System.out.printf(message, outlet + 1);
                checkMoreVehicle = keyboard.next();
            } catch (InputMismatchException i) {
                displayError();
            }
        } while (!(checkMoreVehicle.toLowerCase().matches("y|n")));
        return !checkMoreVehicle.toLowerCase().matches("n");
    }

    public static void main(String[] args) {

        // number of outlets at this station
        int numOutlet = 0;
        do {
            try {
                numOutlet = getInputInt("Enter how many charging outlets this Super Charging Station has: ");
            } catch (InputMismatchException i) {
                displayError();
            }
        } while (numOutlet <= 0);

        // initialize calculation class
        OperationCalculation calculation = new OperationCalculation(numOutlet);
        // set station's cost per kwh
        do {
            try {
                calculation.setCostPerKWH(getInputDouble("Enter how much this Super Charging Station charge per" +
                        " kwh(enter as number .12 = $0.12): "));
            } catch (InputMismatchException i) {
                displayError();
            }
        } while (calculation.getCostPerKWH() <= 0);

        // initialize charging outlet class for each outlet at the station
        ChargingOutlet[] chargingOutlet = new ChargingOutlet[numOutlet];
        // initialize the vehicle class
        Vehicle vehicle = new Vehicle();
        // go through the vehicles one outlet at a time
        for (int outlet = 0; outlet < chargingOutlet.length; outlet++) {
            // initialize current outlet
            chargingOutlet[outlet] = new ChargingOutlet();

            // check if this outlet has any vehicles to process
            boolean moreVehicles = isMoreVehicles(outlet,
                    "Are there any vehicles to process for this outlet #%d? (Y/N): ");

            // ensures voltage and amperes is only asked for once since it's not varying from vehicle to vehicle
            boolean haveVoltAmpAmount = false;

            // runs if there are more vehicles to process
            while (moreVehicles) {
                System.out.printf("Current Station: #%d\n", outlet + 1);
                // runs only once
                if (!haveVoltAmpAmount) {
                    // collect voltage information
                    do {
                        try {
                            chargingOutlet[outlet].setVoltage(getInputDouble("Enter the voltage: "));
                        } catch (InputMismatchException i) {
                            displayError();
                        }
                    } while (chargingOutlet[outlet].getVoltage() <= 0);
                    // collect ampere information
                    do {
                        try {
                            chargingOutlet[outlet].setAmpere(getInputDouble("Enter the Ampere of outlet: "));
                        } catch (InputMismatchException i) {
                            displayError();
                        }
                    } while (chargingOutlet[outlet].getAmpere() <= 0);
                    haveVoltAmpAmount = true;
                }
                // set battery capacity of current vehicle
                do {
                    try {
                        vehicle.setBatteryCapacity(getInputDouble("Enter battery capacity (enter in kwh): "));
                    } catch (InputMismatchException i) {
                        displayError();
                    }
                } while (vehicle.getBatteryCapacity() <= 0);
                // set charging efficiency of current vehicle
                do {
                    try {
                        vehicle.setChargingEfficiency(getInputDouble("Enter charging efficiency " +
                                "(enter without % sign. ex 90% as 90): "));
                    } catch (InputMismatchException i) {
                        displayError();
                    }
                } while (vehicle.getChargingEfficiency() <= 0 && vehicle.getChargingEfficiency() > 1);
                // set initial charge
                do {
                    try {
                        vehicle.setBatteryInitialCharge(getInputDouble(
                                "Enter starting battery level(enter without % sign. ex 20% as 20): "));
                    } catch (InputMismatchException i) {
                        displayError();
                    }
                } while (vehicle.getBatteryInitialCharge() < 0 && vehicle.getBatteryInitialCharge() > 100);
                // set ending charge
                do {
                    try {
                        vehicle.setBatteryEndingCharge(getInputDouble("Enter ending battery level " +
                                "(enter without % sign. ex 90% as 90):"));
                    } catch (InputMismatchException i) {
                        displayError();
                    }
                } while (vehicle.getBatteryEndingCharge() < 0 && vehicle.getBatteryInitialCharge() >= 100
                        && vehicle.getBatteryEndingCharge() > vehicle.getBatteryInitialCharge());
                // check if paying or complimentary customer
                String checkPay = "";
                do {
                    try {
                        checkPay = getInputString("Enter 'f' if this vehicle doesn't have to pay," +
                                " otherwise press 'p' if it does pay: ");
                    } catch (InputMismatchException i) {
                        displayError();
                    }
                } while (!(checkPay.toLowerCase().matches("f|p")));
                // set paying customer
                calculation.setPayingCustomer(checkPay.toLowerCase().matches("p"));
                // calculate charging in take
                calculation.setChargeIntake(chargingOutlet[outlet].getVoltage(), chargingOutlet[outlet].getAmpere(),
                        vehicle.getChargingEfficiency());
                // calculate amount charged
                calculation.setBatteryAmountCharged(vehicle.getBatteryEndingCharge(), vehicle.getBatteryInitialCharge(),
                        vehicle.getBatteryCapacity());
                // set and display time charged
                calculation.setTime(outlet);
                calculation.displayTime();
                // set and display cost of charging
                calculation.setCost(outlet, vehicle.getChargingEfficiency());
                calculation.displayCost(vehicle.getChargingEfficiency());

                // check if there are more vehicles to process
                moreVehicles = isMoreVehicles(outlet,
                        "Are there any more vehicles to process for this outlet #%d? (Y/N): ");
            }
        }
        // total time and costs for both paying and complimentary customers
        for (int outlet = 0; outlet < numOutlet; outlet++) {
            calculation.setTotalTime(outlet);
            calculation.setTotalComplimentaryTime(outlet);
            calculation.displayOutletTotalTime(outlet);
            calculation.setTotalRevenue(outlet);
            calculation.setTotalComplimentaryCost(outlet);
            calculation.displayOutletTotalCost(outlet);
        }
        // display total time and profit
        calculation.displayTotalTime();
        calculation.displayTotalProfit();

    }


}
