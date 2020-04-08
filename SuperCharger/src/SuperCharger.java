import java.util.Scanner;
/*
 * Keep track of the time spent and money earned and lost while operating a super charger station
 * @author Ryan Smith
 * no error checking
 *
 * Assumptions:
 *  cost per kwh doesn't fluctuate
 *  perfect input from user`
 */
public class SuperCharger {
    public static void main(String[] args) {

        // read user input
        Scanner keyboard = new Scanner(System.in);

        // establish how many outlets
        System.out.println("Enter how many charging outlets this Super Charging Station has: ");
        int numOutlet = keyboard.nextInt();

        // first element of the array is which outlet is in use, second element keeps track of the voltage and amperes
        // of that charging station
        double[][] chargingStation = new double[numOutlet][2];
        // variable used in array chargingStation for readability
        int volt = 0;
        int amp = 1;

        // keep track of how much time each station is occupied with paying and non paying customers
        double[][] chargingTime = new double[numOutlet][2];
        double[][] chargingCost = new double[numOutlet][2];
        // variable used in array chargingTime for readability
        int free = 0;
        int paying = 1;

        // prompt for cost per kwh
        System.out.println("Enter how much this Super Charging Station charge per kwh(enter as number .12 = $0.12): ");
        double chargingPrice = keyboard.nextDouble();

        // prompt and store voltage and amperes of each outlet
        for (int outlet = 0; outlet < numOutlet; outlet++) {
            System.out.printf("Enter the voltage of outlet #%d: \n", outlet);
            chargingStation[outlet][volt] = keyboard.nextInt();
            System.out.printf("Enter the amperes of outlet #%d: \n", outlet);
            chargingStation[outlet][amp] = keyboard.nextInt();
        }

        // collect data for each outlet
        for (int outlet = 0; outlet < numOutlet; outlet++) {
            // check if outlet was used
            System.out.printf("Are there any vehicles to process for this outlet #%d? (Y/N): ", outlet);
            String checkMoreVehicle = keyboard.next();
            while (!(checkMoreVehicle.toLowerCase().matches("y|n"))) {
                System.out.println("Invalid input. Try again: ");
                checkMoreVehicle = keyboard.next();
            }
            boolean moreVehicles = !checkMoreVehicle.toLowerCase().matches("n");

            // collect data for current outlet
            while (moreVehicles) {
                System.out.printf("Current Station: #%d\n", outlet);

                // current ev's battery capacity
                System.out.println("Enter battery capacity (enter in kwh): ");
                double batteryCapacity = keyboard.nextDouble();

                // current ev's charging efficiency
                System.out.println("Enter charging efficiency (enter without % sign. ex 90% as 90): ");
                double batteryEfficiency = keyboard.nextDouble() / 100;

                // current ev's starting battery level
                System.out.println("Enter starting battery level(enter without % sign. ex 20% as 20): ");
                double startCharge = keyboard.nextDouble();

                // current ev's ending battery level
                System.out.println("Enter ending battery level (enter without % sign. ex 90% as 90):");
                double endCharge = keyboard.nextDouble();

                // checks if this ev has to pay
                System.out.println("Enter 'f' if this vehicle doesn't have to pay, otherwise press 'p' if it does pay: ");
                String checkPay = keyboard.next();
                while (!(checkPay.toLowerCase().matches("f|p"))) {
                    System.out.println("Invalid Entry, try again");
                    checkPay = keyboard.next();
                }

                // calculations for current ev
                double chargeIntake = (((chargingStation[outlet][volt] * chargingStation[outlet][amp]))
                        / 1000) * batteryEfficiency;
                double batteryAmountCharged = (endCharge - startCharge) / 100 * batteryCapacity;

                // variable to display this transaction's cost
                double cost = 0;

                // store time and costs of free and payed vehicles
                if (checkPay.toLowerCase().matches("f")) {
                    chargingTime[outlet][free] += batteryAmountCharged / chargeIntake;
                    chargingCost[outlet][free] += batteryAmountCharged / batteryEfficiency * chargingPrice;
                } else {
                    chargingTime[outlet][paying] += batteryAmountCharged / chargeIntake;
                    chargingCost[outlet][paying] += batteryAmountCharged / batteryEfficiency * chargingPrice;
                    cost = batteryAmountCharged * chargingPrice / batteryEfficiency;
                }
                // display current ev's time and cost
                System.out.printf("The vehicle charged in %.2f minutes,", batteryAmountCharged / chargeIntake);
                System.out.printf(" and the customer was charged $%.2f\n", cost);

                // check if more evs to process for this outlet
                System.out.println("Are there more vehicles to process for this outlet? (Y/N): ");
                checkMoreVehicle = keyboard.next();
                while (!(checkMoreVehicle.toLowerCase().matches("y|n"))) {
                    System.out.println("Invalid input. Try again: ");
                    checkMoreVehicle = keyboard.next();
                }
                if (checkMoreVehicle.toLowerCase().matches("n")) {
                    moreVehicles = false;
                }
            }
        }
        keyboard.close();

        // accumulators for time and costs
        double payingTime = 0;
        double freeTime = 0;
        double payingCost = 0;
        double freeCost = 0;
        // calculate and display total time in use for each outlet and costs at each outlet
        for (int outlet = 0; outlet < numOutlet; outlet++) {
            System.out.printf("Outlet #%d was in use for %.2f minutes\n", outlet, chargingTime[outlet][free]
                    + chargingTime[outlet][paying]);
            payingTime += chargingTime[outlet][paying];
            payingCost += chargingCost[outlet][paying];
            freeTime += chargingTime[outlet][free];
            freeCost += chargingCost[outlet][free];
        }

        // display station totals
        System.out.printf("Total time across all stations %.2f minutes\n", payingTime + freeTime);
        System.out.printf("Total time with a paying customer is %.2f minutes. " +
                "They were charged $%.2f\n", payingTime, payingCost);
        System.out.printf("Total time with a non-paying customer is %.2f minutes. " +
                "They would have been charged $%.2f\n", freeTime, freeCost);
    }
}
