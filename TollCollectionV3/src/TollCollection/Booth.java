package TollCollection;

/*
 * Calculates the amount to charge and keeps track of total charged to each payment type.

 * @author Ryan Smith
 */

public class Booth {

    // toll amount for each payment type (0: card, 1: cash, 2: es)
    private double[] tollAmount = new double[3];

    // compute toll
    public double computeToll(double baseRate, double threeAndOverRate, double[] vehicleTypePriceChange,
                              double[] paymentTypePriceChange, int[] axle, int paymentType,
                              int vehicleType){

        double toll = baseRate * axle[0] + threeAndOverRate * axle[1];
        toll *= vehicleTypePriceChange[vehicleType];
        toll *= paymentTypePriceChange[paymentType];

        // running total dependent on payment type
        tollAmount[paymentType]+= toll;

        return toll;

    }

    // get total for all payment types
    public double getTotalTollAmount() {
        return tollAmount[0]+tollAmount[1]+tollAmount[2];
    }

    // get total for each payment type
    public double getPaymentTotal(int paymentType){
        return tollAmount[paymentType];
    }
}
