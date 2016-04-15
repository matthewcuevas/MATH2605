import java.util.Random;

/**
 * Created by matthew on 4/14/16.
 */
public class Problems {

    /**
     * Performs calculations for Problem 1: The Hilber Matrix
     */
    public static void problem_1() {

    }

    /**
     * Performs calculations for Problem 2: Convergence of Iterative Methods
     */
    public static void problem_2() {
        double[] AData = {1, 1/3, 1/9, 1/3, 1, 1/3, 1/9, 1/3, 1};
        double[] bData = {0.9, 0.3, 0.1};

        Matrix A = new Matrix(3, 3, AData);
        Vector b = new Vector(bData);

        Vector[] xNought = new Vector[100];
        Random rand = new Random();
        for (int i = 0; i < 100; i++) {
            double[] xData = new double[3];
            xData[0] = rand.nextFloat() * 20 - 10;
            xData[1] = rand.nextFloat() * 20 - 10;
            xData[2] = rand.nextFloat() * 20 - 10;

            xNought[i] = new Vector(xData);
        }

        Object[][] JacobiResults = new Object[3][100];
        for (int i = 0; i < 100; i++) {

        }
    }

    /**
     * Performs calculations for Problem 3: Convergence of Power Method
     */
    public static void problem_3() {

    }
}
