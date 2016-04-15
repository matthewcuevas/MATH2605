import java.util.Random;

/**
 * Created by matthew on 4/14/16.
 */
public class Problems {

    /**
     * Performs calculations for Problem 1: The Hilbert Matrix
     */
    public static void problem_1() {

    }

    /**
     * Performs calculations for Problem 2: Convergence of Iterative Methods
     */
    public static void problem_2() {
        double[] AData = {1, 1.0/3, 1.0/9, 1.0/3, 1, 1.0/3, 1.0/9, 1.0/3, 1};
        double[] bData = {0.9, 0.1, 0.3};

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

        Object[][] JacobiResults = new Object[100][3];
        for (int i = 0; i < 100; i++) {
            JacobiResults[i] = SolveUtil.jacobi_iter(Matrix.toAugmented(A, b),
                    xNought[i], (float) 0.00005, 100);
        }

        double[] xJacobiData = new double[3];
        for (int i = 0; i < 3; i++) {
            double sum = 0;
            for (int j = 0; j < 100; j++) {
                sum += ((Vector) JacobiResults[i][0]).get(i, 0);
            }

            xJacobiData[i] = sum / 100;
        }
        Vector xJacobiAvg = new Vector(xJacobiData);
        System.out.println(xJacobiAvg);

        Object[][] GSResults = new Object[100][3];
        for (int i = 0; i < 100; i++) {
            GSResults[i] = SolveUtil.gs_iter(Matrix.toAugmented(A, b),
                    xNought[i], (float) 0.00005, 100);
        }

        double[] xGSData = new double[3];
        for (int i = 0; i < 3; i++) {
            double sum = 0;
            for (int j = 0; j < 100; j++) {
                sum += ((Vector) GSResults[i][0]).get(i, 0);
            }

            xGSData[i] = sum / 100;
        }
        Vector xGSAvg = new Vector(xGSData);
        System.out.println(xGSAvg);
    }

    /**
     * Performs calculations for Problem 3: Convergence of Power Method
     */
    public static void problem_3() {

    }
}
