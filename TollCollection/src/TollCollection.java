/*
 * Take and interpret toll booth data
 * @author Ryan Smith
 * minimal error checking
 */

import java.util.Scanner;

public class TollCollection {
    public static void main(String[] args) {
        // first index is vehicle type(ev, hybrid, gas) second index is # of axles(1,2,3,3+)
        // third is payment type (cash, card, es)
        // charge[vehicle type][# of axles][payment type]
        double[][][] charge = new double[3][4][3];
        // increase readability of the charge array
        int ev = 0;
        int hybrid = 1;
        int gas = 2;
        int axle1 = 0;
        int axle2 = 1;
        int axle3 = 2;
        int axle3p = 3;
        int cash = 0;
        int card = 1;
        int es = 2;
        // EV costs
        charge[ev][axle1][cash] = 1.00;
        charge[ev][axle1][card] = 1.50;
        charge[ev][axle1][es] = .50;
        charge[ev][axle2][cash] = 2.00;
        charge[ev][axle2][card] = 3.00;
        charge[ev][axle2][es] = .50;
        charge[ev][axle3][cash] = 4.00;
        charge[ev][axle3][card] = 5.50;
        charge[ev][axle3][es] = .50;
        charge[ev][axle3p][cash] = 4.00;
        charge[ev][axle3p][card] = 5.50;
        charge[ev][axle3p][es] = .50;
        // Hybrid vehicles costs
        charge[hybrid][axle1][cash] = 1.50;
        charge[hybrid][axle1][card] = 2.00;
        charge[hybrid][axle1][es] = 1.00;
        charge[hybrid][axle2][cash] = 3.00;
        charge[hybrid][axle2][card] = 4.00;
        charge[hybrid][axle2][es] = 1.50;
        charge[hybrid][axle3][cash] = 4.00;
        charge[hybrid][axle3][card] = 5.50;
        charge[hybrid][axle3][es] = 3.00;
        charge[hybrid][axle3p][cash] = 5.00;
        charge[hybrid][axle3p][card] = 6.50;
        charge[hybrid][axle3p][es] = 4.50;
        // Gasoline vehicles costs
        charge[gas][axle1][cash] = 3.00;
        charge[gas][axle1][card] = 3.50;
        charge[gas][axle1][es] = 2.50;
        charge[gas][axle2][cash] = 4.00;
        charge[gas][axle2][card] = 5.00;
        charge[gas][axle2][es] = 3.50;
        charge[gas][axle3][cash] = 6.50;
        charge[gas][axle3][card] = 7.50;
        charge[gas][axle3][es] = 5.00;
        charge[gas][axle3p][cash] = 8.00;
        charge[gas][axle3p][card] = 9.50;
        charge[gas][axle3p][es] = 10.00;

        // declare variables that will take user input and make it applicable to the charge array
        int vehicle;
        int axle;
        int payment;

        // read user input
        Scanner keyboard = new Scanner(System.in);

        // Booth input
        System.out.println("Enter how many lanes is the road is at the toll: ");
        int numBooth = keyboard.nextInt();

        // create array for each booth that holds total for each payment type
        double[][] booths = new double[numBooth][3];


        // loop to enter multiple vehicles
        boolean moreVehicles = true;
        while (moreVehicles) {

            // which booth
            System.out.println("Enter toll booth # used by the car (# left to right starting with 0): ");
            int booth = keyboard.nextInt();
            // valid response checker
            while (!((booth <= numBooth - 1) && (booth >= 0))) {
                System.out.println("Invalid booth number. Please try again: ");
                booth = keyboard.nextInt();
            }

            // vehicle type
            System.out.println("Enter how your vehicle is powered (EV for Electric, Hybrid for hybrid," +
                    " or Gas for gasoline): ");
            String vehicleTypeInput = keyboard.next();
            // valid response checker
            while (!(vehicleTypeInput.toLowerCase().matches("ev|hybrid|gas"))) {
                System.out.println("Invalid vehicle type. Please try again");
                vehicleTypeInput = keyboard.next();
            }

            // assign vehicle type
            switch (vehicleTypeInput.toLowerCase()) {
                case "ev":
                    vehicle = ev;
                    break;
                case "hybrid":
                    vehicle = hybrid;
                    break;
                case "gas":
                    vehicle = gas;
                    break;
                default:
                    System.out.println("Invalid input, starting this vehicle's transaction over");
                    continue;
            }

            // # of axles
            System.out.println("Enter number of axles: ");
            int numAxle = keyboard.nextInt();
            // valid response checker
            while (numAxle <= 0) {
                System.out.println("Invalid # of axle. Please try again.");
                numAxle = keyboard.nextInt();
            }

            // assign # of axle
            if (numAxle > 3) {
                axle = 3;
            } else {
                axle = numAxle - 1;
            }

            // payment type
            System.out.println("Enter Payment type (Cash for cash, Card for debit or credit card, ES for SunPass)");
            String paymentMethod = keyboard.next();
            while (!(paymentMethod.toLowerCase().matches("cash|card|es"))) {
                System.out.println("Invalid payment type. Please try again");
                paymentMethod = keyboard.next();
            }

            // assign payment type
            switch (paymentMethod.toLowerCase()) {
                case "cash":
                    payment = cash;
                    break;
                case "card":
                    payment = card;
                    break;
                case "es":
                    payment = es;
                    break;
                default:
                    System.out.println("Invalid input start this vehicle's transaction over");
                    continue;
            }
            System.out.printf("Booth #%d charged that car $%.2f \n", booth, charge[vehicle][axle][payment]);

            // keep track of total for each payment type at each booth
            booths[booth][payment] += charge[vehicle][axle][payment];

            // continue or end loop
            System.out.println("Enter any key to enter more transactions. Otherwise enter 'n' to stop: ");
            if (keyboard.next().toLowerCase().equals("n")) {
                moreVehicles = false;
            }
        }

        keyboard.close();

        double cashTotal = 0;
        double cardTotal = 0;
        double esTotal = 0;

        for (int i = 0; i < numBooth; i++) {
            cashTotal += booths[i][cash];
        }
        for (int i = 0; i < numBooth; i++) {
            cardTotal += booths[i][card];
        }
        for (int i = 0; i < numBooth; i++) {
            esTotal += booths[i][es];
        }
        double total = cardTotal + esTotal + cashTotal;

        for (int i = 0; i < numBooth; i++) {
            System.out.printf("Booth #%d collected $%6.2f \n", i, (booths[i][cash] + booths[i][card] + booths[i][es]));
        }

        // display each payment type total and overall total
        System.out.printf("Total cash collected: $%25.2f\n", cashTotal);
        System.out.printf("Total Debit/Credit collected: $%17.2f\n", cardTotal);
        System.out.printf("Total Electronic Service collected: $%11.2f\n", esTotal);
        System.out.printf("The total toll collected for all booths: $%6.2f\n", total);


    }
}