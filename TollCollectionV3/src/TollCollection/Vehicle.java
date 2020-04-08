package TollCollection;
/*

    @author Ryan Smith
 */
public class Vehicle {
    private int vehicleType;
    private int numAxel;
    private int paymentType;

    // set vehicle type
    public void setVehicleType(String vehicleTypeInput) {
        switch (vehicleTypeInput.toLowerCase()) {
            case "ev":
                vehicleType = 0;
                break;
            case "hybrid":
                vehicleType = 1;
                break;
            case "gas":
                vehicleType = 2;
                break;
        }
    }

    // set # of axles
    public void setNumAxel(int numAxel) {
        this.numAxel = numAxel;
    }

    // set payment type
    public void setPaymentType(String paymentMethod) {
        switch (paymentMethod.toLowerCase()) {
            case "card":
                paymentType = 0;
                break;
            case "cash":
                paymentType = 1;
                break;
            case "es":
                paymentType = 2;
                break;
        }
    }

    public int getVehicleType() {
        return vehicleType;
    }

    public int getNumAxel() {
        return numAxel;
    }

    public int getPaymentType() {
        return paymentType;
    }
}
