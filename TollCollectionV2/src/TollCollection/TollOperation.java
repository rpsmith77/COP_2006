package TollCollection;

import java.util.Scanner;
/*
    User enters information about the Toll Station and each vehicle that passes through. The program calculates the
    cost of each transaction and keeps a running total. Once all vehicles are processed, the program outputs the total
    for each payment type, each booth, and overall total.

    minimal error checking

    @author Ryan Smith
 */
public class TollOperation {

    public static void main(String[] args) {

        // Create the toll station
        Toll toll = new Toll();
        // invalid input message
        String ERROR_INVALID_INPUT = "Invalid input. Please try again: ";

        // read user input
        Scanner keyboard = new Scanner(System.in);

        // total number of toll booths at this toll station
        System.out.println("Enter the number of toll booths: ");
        int numBooth = keyboard.nextInt();
        while (numBooth <= 0) {
            System.out.println(ERROR_INVALID_INPUT);
            numBooth = keyboard.nextInt();
        }

        // keep track of total at each booth
        double[] tollBooth = new double[numBooth];

        // go through each booth one at a time
        for (int booth = 0; booth < numBooth; booth++) {
            // make sure there is a vehicle to process at this booth
            System.out.printf("Are there any vehicles to process for toll booth #%d? (Y/N): ", booth + 1);
            String checkMoreVehicle = keyboard.next();
            while (!(checkMoreVehicle.toLowerCase().matches("y|n"))) {
                System.out.println(ERROR_INVALID_INPUT);
                checkMoreVehicle = keyboard.next();
            }
            boolean moreVehicles = !checkMoreVehicle.toLowerCase().matches("n");

            // collects information about the vehicle and uses that info to do necessary calculations
            while (moreVehicles) {
                System.out.printf("Current Toll Booth: #%d\n", booth + 1);

                // create a new vehicle object for each toll booth operation
                Vehicle vehicle = new Vehicle();

                // vehicle type input
                System.out.println("Enter how your vehicle is powered (EV for Electric, Hybrid for hybrid," +
                        " or Gas for gasoline): ");
                String vehicleTypeInput = keyboard.next();
                // valid response checker
                while (!(vehicleTypeInput.toLowerCase().matches("ev|hybrid|gas"))) {
                    System.out.println(ERROR_INVALID_INPUT);
                    vehicleTypeInput = keyboard.next();
                }


                // # of axel input
                System.out.println("Enter number of axles: ");
                int numAxle = keyboard.nextInt();
                // valid response checker
                while (numAxle <= 0) {
                    System.out.println(ERROR_INVALID_INPUT);
                    numAxle = keyboard.nextInt();
                }


                // payment type input
                System.out.println("Enter Payment type (Cash for cash, Card for debit or credit card, ES for SunPass)");
                String paymentMethod = keyboard.next();
                while (!(paymentMethod.toLowerCase().matches("cash|card|es"))) {
                    System.out.println(ERROR_INVALID_INPUT);
                    paymentMethod = keyboard.next();
                }

                // giving the vehicle the appropriate information
                vehicle.setVehicleType(vehicleTypeInput);
                vehicle.setNumAxel(numAxle);
                vehicle.setPaymentType(paymentMethod);

                // calculation the amount charged based off of vehicle information
                toll.calculateAmountCharged(vehicle.getVehicleType(), vehicle.getNumAxel(), vehicle.getPaymentType());
                // accumulation of amount charged for current booth
                tollBooth[booth] += toll.getAmountCharged();

                // display how much current vehicle was charged
                System.out.printf("The vehicle was charged $%.2f\n", toll.getAmountCharged());

                // continue or end loop
                System.out.println("Enter any key to enter more transactions. Otherwise enter 'n' to stop: ");
                if (keyboard.next().toLowerCase().equals("n")) {
                    moreVehicles = false;
                }
            }
        }
        // close scanner
        keyboard.close();

        // get total charged for each payment type
        double[] totalCollected = Toll.getTotalCharged();
        // overall total
        double total = 0;
        for (int i = 0; i < 3; i++) {
            total += totalCollected[i];
        }

        String tollFinishedOutputMessage = "The total amount collected from";

        for (int booth = 0; booth < numBooth; booth++) {
            System.out.printf(tollFinishedOutputMessage + "booth #%d : $%.2f\n", booth + 1, tollBooth[booth]);
        }

        System.out.printf(tollFinishedOutputMessage + " all booths: $%.2f\n", total);
        System.out.printf(tollFinishedOutputMessage + " card payments: $%.2f\n", totalCollected[0]);
        System.out.printf(tollFinishedOutputMessage + " cash payments: $%.2f\n", totalCollected[1]);
        System.out.printf(tollFinishedOutputMessage + " es payments: $%.2f\n", totalCollected[2]);

    }

}
