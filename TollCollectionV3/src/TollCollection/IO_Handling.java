package TollCollection;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Scanner;

/*
 * Handles input and output from .txt files designated by the user.

 * @author Ryan Smith
 */


public class IO_Handling {
    public static void main(String[] args) {

        // file names designated by the user
        String outputFile = "";
        String vehicleInputFile = "";
        String tollConfigFile = "";
        // if the user messes up the cmd line arguments this catches the error.
        try {
            outputFile = args[0];
            vehicleInputFile = args[1];
            tollConfigFile = args[2];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Input and output not correctly specified: " + e.getLocalizedMessage());
            System.exit(0);
        }

        // Initializes tollCollectionStation
        TollCollectionStation tollCollectionStation = new TollCollectionStation(0);
        // holds total number of booths
        int numBooth = 0;
        // keeps running total of vehicles
        int numVehicle = 0;

        // adds date and time stamp of when the program was run
        try (PrintWriter printWriter = new PrintWriter(new FileOutputStream(new File(outputFile), true))) {
            printWriter.println("\nRan on" + Arrays.toString(LocalDateTime.now().toString().split("T", 0)));
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + outputFile);
            System.exit(0);
        }

        // reads toll config set by user
        try (Scanner fileReader = new Scanner(new File(tollConfigFile))) {
            String[] input;

            // total number of booths
            input = fileReader.nextLine().split(":", 2);
            numBooth = Integer.parseInt(input[1]);
            tollCollectionStation = new TollCollectionStation(numBooth);

            // base rate to charge at toll
            input = fileReader.nextLine().split(":", 2);
            tollCollectionStation.setBaseRate(Double.parseDouble(input[1]));

            // rate to charge for surplus axles
            input = fileReader.nextLine().split(":", 2);
            tollCollectionStation.setThreeAndOverRate(Double.parseDouble(input[1]));

            // price change depending on vehicle type
            double[] vehiclePriceChange = new double[3];
            for (int i = 0; i < 3; i++) {
                input = fileReader.nextLine().split(":", 2);
                vehiclePriceChange[i] = Double.parseDouble(input[1]);
            }
            tollCollectionStation.setVehiclePriceChange(vehiclePriceChange);

            // price change depending on  payment type
            double[] paymentTypePriceChange = new double[3];
            for (int i = 0; i < 3; i++) {
                input = fileReader.nextLine().split(":", 2);
                paymentTypePriceChange[i] = Double.parseDouble(input[1]);
            }
            tollCollectionStation.setPaymentTypePriceChange(paymentTypePriceChange);

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + tollConfigFile);
            System.exit(0);
        }

        // reads the vehicles specified by user
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(vehicleInputFile)))) {

            // splits files line by line then by ":"
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] vehicleConfig = line.split(":", 4);

                // vehicle's booth number
                tollCollectionStation.setVehicleBoothNum((Integer.parseInt(vehicleConfig[0])));
                // vehicle type
                tollCollectionStation.setVehicleType(vehicleConfig[1]);
                // vehicle's axle count
                tollCollectionStation.setVehicleAxle(Integer.parseInt(vehicleConfig[2]));
                // vehicle's payment type
                tollCollectionStation.setVehiclePaymentType(vehicleConfig[3]);
                // running total of vehicles processed
                numVehicle++;

                // outputs the amount the current vehicle is being charged
                try (PrintWriter printWriter = new PrintWriter(new FileOutputStream(new File(outputFile), true))) {
                    printWriter.printf("Vehicle #%d was charged $%.2f\n",
                            numVehicle, tollCollectionStation.computeToll());
                }

            }
            // outputs totals
            try (PrintWriter printWriter = new PrintWriter(new FileOutputStream(new File(outputFile), true))) {

                for (int i = 0; i < numBooth; i++) {
                    // outputs current booth's total
                    printWriter.printf("Booth #%d total: $%.2f\n", i + 1, tollCollectionStation.computeBoothTotal(i));
                }
                double[] boothTotals = tollCollectionStation.computeTotal();
                // outputs total from card payments
                printWriter.printf("Total collected from card payments: $%.2f\n", boothTotals[1]);
                // outputs total from cash payments
                printWriter.printf("Total collected from cash payments: $%.2f\n", boothTotals[2]);
                // outputs totals from es payments
                printWriter.printf("Total collected from electronic service payments: $%.2f\n", boothTotals[3]);
                // outputs totals from all booths
                printWriter.printf("Total collected for all booths: $%.2f\n", boothTotals[0]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + vehicleInputFile);
            System.exit(0);
        } catch (IOException e) {
            System.out.println("Unable to read file: " + vehicleInputFile);
            System.exit(0);
        }


    }
}
