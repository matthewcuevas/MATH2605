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

    public static Vector normalize(Vector vector) {
        double[] normalizedData = new double[vector.getRows()];
        for (int i = 0; i < vector.getRows(); i++) {
            normalizedData[i] = vector.get(i, 0) / vector.magnitude();
        }

        return new Vector(normalizedData);
    }

    /**
     * Rotates a given vector by a given angle clockwise
     * @param vector a 2 x 2 vector
     * @param angle an angle in radians
     * @return the vector, rotated by the given angle
     */
    public static Vector rotate(Vector vector, double angle) {
        double[] RMNums = {Math.cos(angle), Math.sin(angle),
                -Math.sin(angle), Math.cos(angle)};
        Matrix rotationMatrix = new Matrix(2, 2, RMNums);

        return Matrix.toVector(Matrix.multiply(rotationMatrix, vector));
    }

    /**
     * Calculates the dot product of Vectors
     * @param u a Vector
     * @param v a Vector
     * @return the dot product of u and v
     */
    public static double dot(Vector u, Vector v) {
        double product = 0;
        for (int i = 0; i < u.getRows(); i++) {
            product += u.get(i, 0) * v.get(i, 0);
        }

        return product;
    }

    /**
     * Projects a vector onto another vector
     * @param u a vector
     * @param v a vector
     * @return the projection of u onto v
     */
    public static Vector project(Vector u, Vector v) {
        double[] projNums = new double[v.getRows()];
        double scalar = dot(u, v) / (Math.pow(v.magnitude(), 2));
        for (int i = 0; i < v.getRows(); i++) {
            projNums[i] = v.get(i, 0) * scalar;
        }

        return new Vector(projNums);
    }

    /**
     * Gets the norm of this Vector
     *
     * @return vector norm
     */
    public static double norm(Vector v) {
        double sum = 0;
        for (int i = 0; i < v.getRows(); i++) {
            sum += Math.pow(v.get(i,0), 2);
        }
        return Math.pow(sum, 0.5);
    }
}
