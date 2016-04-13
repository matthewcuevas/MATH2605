/**
 * Created by Anush on 4/10/2016.
 */
public class Vector extends Matrix {

    /**
     * Basic Vector constructor
     * @param size the size of the vector
     */
    public Vector(int size) {
        super(size, 1);
    }

    /**
     * constructs a vector from the data provided
     * @param data
     */
    public Vector(double[] data) {
        super(data.length, 1, data);
    }

    /**
     * Calculates the magnitude of a vector
     * @return the magnitude of the vector
     */
    public double magnitude() {
        double ret = 0;
        for (int i = 0; i < getRows(); i++) {
            ret += get(i, 0) * get(i, 0);
        }
        return Math.sqrt(ret);
    }

}
