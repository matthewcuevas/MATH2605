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
        // Part I.
        double[] AData = {1, 1.0/3, 1.0/9, 1.0/3, 1, 1.0/3, 1.0/9, 1.0/3, 1};
        double[] bData = {0.9, 0.1, 0.3};
        double[] xExactData = {39.0 / 40, -13.0/40, 12.0/40};

        Matrix A = new Matrix(3, 3, AData);
        Vector b = new Vector(bData);
        Vector xExact = new Vector(xExactData);

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
                sum += ((Vector) JacobiResults[j][0]).get(i, 0);
            }

            xJacobiData[i] = sum / 100;
        }
        Vector xJacobiAvg = new Vector(xJacobiData);

        double errorJacobiAvg = Vector.sum(xJacobiAvg,
                xExact.negate()).getNorm();

        Object[][] GSResults = new Object[100][3];
        for (int i = 0; i < 100; i++) {
            GSResults[i] = SolveUtil.gs_iter(Matrix.toAugmented(A, b),
                    xNought[i], (float) 0.00005, 100);
        }

        double[] xGSData = new double[3];
        for (int i = 0; i < 3; i++) {
            double sum = 0;
            for (int j = 0; j < 100; j++) {
                sum += ((Vector) GSResults[j][0]).get(i, 0);
            }

            xGSData[i] = sum / 100;
        }
        Vector xGSAvg = new Vector(xGSData);

        double errorGSAvg = Vector.sum(xGSAvg,
                xExact.negate()).getNorm();

        double sum = 0;
        for (int i = 0; i < 100; i++) {
            sum += ((Integer) JacobiResults[i][1]) * 1.0 / (Integer) GSResults[i][1];
        }
        double iterationRatioAvg = sum / 100;

        System.out.println("I.\n");
        System.out.println("Jacobi:");
        System.out.println(xJacobiAvg);
        System.out.println("error: " + errorJacobiAvg);

        System.out.println("Gauss-Seidel");
        System.out.println(xGSAvg);

        System.out.println("Average ratio of iterations (Jacobi / Gauss-Seidel):");
        System.out.println(iterationRatioAvg);
        System.out.println("error: " + errorGSAvg + "\n");

        // Part II.
        double[] A2Data = {1, 2, 2, 1};
        double[] b2Data = {3, 3};
        double[] xExact2Data = {1, 1};
        double[] xNought2Data = {0, 0};

        Matrix A2 = new Matrix(2, 2, A2Data);
        Vector b2 = new Vector(b2Data);
        Matrix Ab2 = Matrix.toAugmented(A2, b2);
        Vector xExact2 = new Vector(xExact2Data);
        Vector xNought2 = new Vector(xNought2Data);

        double[] errorJacobi = new double[9];
        double[] errorGS = new double[9];
        for (int i = 2; i <= 10; i++) {
            errorJacobi[i - 2] = (double) SolveUtil.jacobi_iter(Ab2,
                    xNought2, (float) 0.00005, i)[2];
            errorGS[i - 2] = (double) SolveUtil.gs_iter(Ab2,
                    xNought2, (float) 0.00005, i)[2];
        }
    }

    /**
     * Performs calculations for Problem 3: Convergence of Power Method
     */
    public static void problem_3() {

    }
}
