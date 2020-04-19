package TollCollection;

/*
 * converts the number of axles into the number of axles in and out of surplus

 * @author Ryan Smith
 */

public class Axle {
    // array stores # axle in and out of surplus
    private int[] axleType = new int[2];

    // set # axle in and out of surplus
    public void setAxleType(int axle) {
        if (axle > 3) {
            axleType[0] = 3;
            axleType[1] = axle - axleType[0];
        } else {
            axleType[0] = axle;
        }
    }
    public int[] getAxleType() {
        return axleType;
    }
}
