package TollCollection;

/*
 * stores toll configuration and collects totals from the booths.

 * @author Ryan Smith
 */

public class TollCollectionStation {

    // initialize booths and vehicle
    Booth[] booths;
    Vehicle vehicle;

    // base rate to charge per axle
    private double baseRate;
    // rate to charge ber surplus axle
    private double threeAndOverRate;
    // price change dependent on vehicle
    private double[] vehiclePriceChange = new double[3];
    // price change dependent on payment type
    private double[] paymentTypePriceChange = new double[3];

    // constructor
    TollCollectionStation(int numBooth) {
        booths = new Booth[numBooth];
        for(int i = 0; i < numBooth; i++){
            booths[i] = new Booth();
        }
        vehicle = new Vehicle();
    }

    // booth of current vehicle
    public void setVehicleBoothNum(int boothNum) {
        vehicle.setBoothNum(boothNum);
    }

    // vehicle type
    public void setVehicleType(String vehicleType) {
        vehicle.setVehicleType(vehicleType);
    }

    // number of axle
    public void setVehicleAxle(int numAxle) {
        vehicle.setAxle(numAxle);
    }
    public int[] getVehicleAxle() {
        return vehicle.getAxle();
    }

    // payment type
    public void setVehiclePaymentType(String paymentType) {
        vehicle.setPaymentType(paymentType);
    }

    // set base rate
    public void setBaseRate(double baseRate) {
        this.baseRate = baseRate;
    }

    // set surplus rate
    public void setThreeAndOverRate(double threeAndOverRate) {
        this.threeAndOverRate = threeAndOverRate;
    }

    // set price change dependent on vehicle type
    public void setVehiclePriceChange(double[] vehiclePriceChange) {
        this.vehiclePriceChange = vehiclePriceChange;
    }
    // set price change dependent on payment type
    public void setPaymentTypePriceChange(double[] paymentTypePriceChange) {
        this.paymentTypePriceChange = paymentTypePriceChange;
    }

    // compute toll
    public double computeToll() {
        return booths[vehicle.getBoothNum()-1].computeToll(baseRate, threeAndOverRate, vehiclePriceChange,
                paymentTypePriceChange, getVehicleAxle(), vehicle.getVehicleType(), vehicle.getPaymentType());
    }

    // compute total for individual booth
    public double computeBoothTotal(int numBooth){
        return booths[numBooth].getTotalTollAmount();
    }

    // compute totals for all booths
    public double[] computeTotal() {
        double[] boothTotals = new double[4];
        for (Booth booth : booths) {
            boothTotals[0] += booth.getTotalTollAmount();
            boothTotals[1] += booth.getPaymentTotal(0);
            boothTotals[2] += booth.getPaymentTotal(1);
            boothTotals[3] += booth.getPaymentTotal(2);
        }

        return boothTotals;
    }
}
