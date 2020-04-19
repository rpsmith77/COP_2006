package TollCollection;

/*
 * stores and interprets the vehicle information specified by the user

 * @author Ryan Smith
 */
public class Vehicle {

    // booth number
    private int boothNum;
    // vehicle type as an int
    private int vehicleType;
    // payment type as an int
    private int paymentType;
    // crete axle
    Axle axle;


    // constructor
    Vehicle() {
        axle = new Axle();
    }

    // vehicle's booth number
    public void setBoothNum(int boothNum) {
        this.boothNum = boothNum;
    }
    public int getBoothNum() {
        return boothNum;
    }

    // vehicle's axle in and out of surplu
    public void setAxle(int axle) {
        this.axle.setAxleType(axle);
    }
    public int[] getAxle(){
        return axle.getAxleType();
    }

    // vehicle type
    public void setVehicleType(String vehicleType) {
        switch (vehicleType) {
            case "ev":
                this.vehicleType = 0;
                break;
            case "hybrid":
                this.vehicleType = 1;
                break;
            case "gas":
                this.vehicleType = 2;
                break;
        }
    }
    public int getVehicleType() {
        return vehicleType;
    }

    // payment type
    public void setPaymentType(String paymentType) {
        switch (paymentType) {
            case "card":
                this.paymentType = 0;
                break;
            case "cash":
                this.paymentType = 1;
                break;
            case "es":
                this.paymentType = 2;
                break;
        }
    }
    public int getPaymentType() {
        return paymentType;
    }

}
