package TollCollection;
/*
    performs the operation of the toll dependent on vehicle type, number of axles, and payment type

    @author Ryan Smith
 */
public class Toll {
    public static double[] totalCharged = new double[3];

    private double amountCharged;

    /*
     vehicle type: 1 for ev, 2 for hybrid, 3 for gas
        * ev gets 50% off
        * Hybrid gets 25% off
     payment type: 1 for card, 2 for cash, 3 for es
        * card adds 15% convenience fee
        * es gets 20% off
    */
    void calculateAmountCharged(int vehicleType, int numAxle, int paymentType) {
        // reset amount charged for each operation
        amountCharged = 0;

    // stores all information need to charge the vehicles
        // base rate
        double baseRate = 1.00;
        // readability variables
        int axleUnder3 = 0;
        int axleOver3 = 1;
        // rate to charge per axle below and above 3
        double[] axleRate = new double[2];
        axleRate[axleUnder3] = 1.00;
        axleRate[axleOver3] = 5.00;
        // readability variables
        int ev = 0;
        int hybrid = 1;
        int gas = 2;
        // discounts specific vehicle types get
        double[] vehiclePriceChange = new double[3];
        vehiclePriceChange[ev] = 0.5;
        vehiclePriceChange[hybrid] = .75;
        vehiclePriceChange[gas] = 1;
        // readability variables
        int card = 0;
        int cash = 1;
        int es = 2;
        // discounts or fees specific payment types get
        double[] paymentTypePriceChange = new double[3];
        paymentTypePriceChange[card] = 1.15;
        paymentTypePriceChange[cash] = 1;
        paymentTypePriceChange[es] = .8;

        int[] axleType = determineAxleCategory(numAxle);

        // amount charged due to axles
        amountCharged = baseRate + (axleRate[axleUnder3] * axleType[axleUnder3]) +
                (axleRate[axleOver3] * axleType[axleOver3]);

        // amount discounted with vehicle type
        calculatePriceChange(vehicleType, vehiclePriceChange);
        // amount charged with payment type
        calculatePriceChange(paymentType, paymentTypePriceChange);
        // store how much each payment type is charged overall
        totalCharged[paymentType] += amountCharged;
    }

    // calculate how many axles 3 and under, and how many above 3
    private int[] determineAxleCategory(int axle) {
        int[] axleType = new int[2];
        if (axle > 3) {
            axleType[0] = 3;
            axleType[1] = axle - axleType[0];
        } else {
            axleType[0] = axle;
        }

        return axleType;
    }

    // price changes due to vehicle type and payment method
    private void calculatePriceChange(int category, double[] priceChange) {
        switch (category) {
            case 0:
                amountCharged *= priceChange[0];
                break;
            case 1:
                amountCharged *= priceChange[1];
                break;
            default:
                amountCharged *= priceChange[2];
                break;
        }
    }

    double getAmountCharged() {
        return amountCharged;
    }

    public static double[] getTotalCharged() {
        return totalCharged;
    }
}
