package SuperCharger;
/*
  @author Ryan Smith

  This handles the IO for the Super Charger Station
 */

import java.io.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Scanner;

public class IO_Handling {
    public static void main(String[] args) {
        // initializing superChargerStation
        SuperChargerStation superChargerStation = new SuperChargerStation(0);

        // store total number of outlets
        int numOutlet = 0;
        // running total of vehicles processed
        int numVehicle = 0;

        // default file names
        String outputFile = "output.txt";
        String stationConfigFile = "stationConfig.txt";
        String vehicleInputFile = "vehicleInput.txt";

        // cmd line args
        try {
            outputFile = args[0];
            vehicleInputFile = args[1];
            stationConfigFile = args[2];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Input and output not correctly specified: " + e.getLocalizedMessage());
        }

        // set date and time on output file to help differentiate
        try (PrintWriter printWriter = new PrintWriter(new FileOutputStream(new File(outputFile), true))) {
            printWriter.println("\nRan on" + Arrays.toString(LocalDateTime.now().toString().split("T", 0)));
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + outputFile);
            System.exit(0);
        }

        // read station config
        try (Scanner fileReader = new Scanner(new File(stationConfigFile))) {
            String[] input;

            // number of outlets
            input = fileReader.nextLine().split(":", 2);
            numOutlet = Integer.parseInt(input[1]);
            superChargerStation = new SuperChargerStation(numOutlet);


            // voltage and amp for each outlet
            for (int outlet = 0; outlet < numOutlet; outlet++) {
                input = fileReader.nextLine().split(":", 2);
                superChargerStation.setOutletVoltage(outlet, Double.parseDouble(input[1]));
                input = fileReader.nextLine().split(":", 2);
                superChargerStation.setOutletAmpere(outlet, Double.parseDouble(input[1]));

            }

            // cost per KWH
            input = fileReader.nextLine().split(":", 2);
            for (int outlet = 0; outlet < numOutlet; outlet++) {
                superChargerStation.setOutletCostPerKWH(outlet, Double.parseDouble(input[1]));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + stationConfigFile);
            e.printStackTrace();
        }

        // read vehicle input
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(vehicleInputFile)))) {
            String line;
            // until no more vehicles
            while ((line = bufferedReader.readLine()) != null) {
                // split the input into categories
                String[] vehicle = line.split(";", 6);
                // extract relevant information
                String[] vehicleConfig = new String[6];
                for (int i = 0; i < 6; i++) {
                    String[] buffer = vehicle[i].split(":", 2);
                    vehicleConfig[i] = buffer[1];
                }
                // outlet vehicle is plugged into
                int currentOutlet = Integer.parseInt(vehicleConfig[0]) - 1;

                // vehicle's battery capacity
                superChargerStation.setBatteryCapacity(currentOutlet, Double.parseDouble(vehicleConfig[1]));
                // charging efficiency
                superChargerStation.setBatteryChargeEfficiency(currentOutlet, Double.parseDouble(vehicleConfig[2]));
                // starting charge
                superChargerStation.setBatteryChargeStart(currentOutlet, Double.parseDouble(vehicleConfig[3]));
                // ending charge
                superChargerStation.setBatteryChargeEnd(currentOutlet, Double.parseDouble(vehicleConfig[4]));
                // whether or not they have to pay
                superChargerStation.setPayingCustomer(currentOutlet, Boolean.parseBoolean(vehicleConfig[5]));
                // running total of vehicles
                numVehicle++;

                // output current vehicles charge time and amount payed
                try (PrintWriter printWriter = new PrintWriter(new FileOutputStream(new File(outputFile), true))) {
                    printWriter.printf("Vehicle #%d charged on Outlet #%d for %.2f minutes at the price of $%.2f\n",
                            numVehicle, currentOutlet + 1, superChargerStation.calculateTime(currentOutlet),
                            superChargerStation.calculateCost(currentOutlet));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + vehicleInputFile);
            System.exit(0);
        } catch (IOException e) {
            System.out.println("Unable to read file: " + vehicleInputFile);
            System.exit(0);
        }

        try (PrintWriter printWriter = new PrintWriter(new FileOutputStream(new File(outputFile), true))) {
            double totalTime = 0;
            double totalNetProfit = 0;
            // outputs each outlet's totals
            for (int outlet = 0; outlet < numOutlet; outlet++) {
                printWriter.printf("Outlet #%d total time in use: %.2f\n", outlet + 1,
                        superChargerStation.getTime(outlet));
                printWriter.printf("Outlet #%d total amount of money customers charged: $%.2f\n", outlet + 1,
                        superChargerStation.getCostPaying(outlet));
                printWriter.printf("Outlet #%d total amount of money lost due to free charging: $%.2f\n", outlet + 1,
                        superChargerStation.getCostFree(outlet));
                printWriter.printf("Outlet #%d total net profit: $%.2f\n", outlet + 1,
                        superChargerStation.getNetProfit(outlet));
                totalTime += superChargerStation.getTime(outlet);
                totalNetProfit += superChargerStation.getNetProfit(outlet);
            }
            // output overall totals
            printWriter.printf("This super charging station charged for %.2f minutes at a net profit of $%.2f.\n",
                    totalTime, totalNetProfit);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + vehicleInputFile);
            System.exit(0);
        }
    }
}
