/* Estimate the time, speed, and cost of charging an Electric Vehicle
 * No error checking present
 * @author Ryan Smith
 */

import java.util.Scanner;

public class EVCharging {
    public static void main(String[] args){

        // Create user input
        Scanner keyboard = new Scanner(System.in);

        // Battery Capacity
        System.out.println("Enter total battery capacity in kWh: ");
        int batteryCapacity = keyboard.nextInt();

        //average mileage efficiency (kWh/100miles)
        System.out.println("Enter how many kWh used to travel 100 miles: ");
        double mileageEfficiency = keyboard.nextDouble();

        //Socket voltage
        System.out.println("Enter voltage of socket used to charge: ");
        int socketVoltage = keyboard.nextInt();

        //Socket amperes
        System.out.println("Enter amperes of socket used to charge: ");
        int socketAmperes = keyboard.nextInt();

        //current charge in %
        System.out.println("Enter starting battery level (ex. for a 20% charge enter 20): ");
        double batteryStart = keyboard.nextDouble();

        //ending charge
        System.out.println("Enter desired battery level (ex. for a 80% charge enter 80): ");
        double batteryEnd = keyboard.nextDouble();

        //charging efficiency
        System.out.println("Enter charging efficiency (ex. for a 90% efficient enter 90): ");
        double chargingEfficiency = (keyboard.nextDouble()) / 100;

        //price per kWh
        System.out.println("Enter the cost per kWh (ex. for $0.13/kWh enter 0.13): ");
        double costPerKWH = keyboard.nextDouble();

        // Close Scanner
        keyboard.close();

        // Calculate the socket output
        double chargeIntake = (((socketVoltage * (double)socketAmperes)) / 1000) * chargingEfficiency;

        // calculate kWh needed to charge batter to desired %
        double batteryAmountCharged = (batteryEnd - batteryStart) / 100 * batteryCapacity;

        // calculate time to charge
        double timeCharge = batteryAmountCharged / chargeIntake;

        // calculate distance gained for 1 hour charging
        double milesGained = (chargeIntake * 100) / mileageEfficiency;

        // calculate charging cost
        double totalCost = batteryAmountCharged * costPerKWH / chargingEfficiency;

        // convert time to hours and minutes
        int hours = (int)Math.floor(timeCharge);
        double minutes = ((((timeCharge * 100) % 100) / 100) * 60);


        // Output
        System.out.printf("Total time to charge is %d hours and ", hours);
        System.out.printf("%.0f minutes\n", minutes);
        System.out.printf("Charging speed is %.2f miles per hour\n", milesGained);
        System.out.printf("Total cost of charging is $%.2f", totalCost);

    }
}

