package SuperCharger;
/*
 * @author Ryan Smith
 * Store information about each outlet
 */
public class ChargingOutlet {
    // voltage of this outlet
    private double voltage = 0;
    // ampere of this outlet
    private double ampere = 0;
    // set voltage
    public void setVoltage(double voltage) {
        this.voltage = voltage;
    }
    // get voltage
    public double getVoltage() {
        return voltage;
    }
    // set ampere
    public void setAmpere(double ampere) {
        this.ampere = ampere;
    }
    // get ampere
    public double getAmpere() {
        return ampere;
    }

}
