import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by matthew on 4/14/16.
 */
public class Problems {

    /**
     * Performs calculations for Problem 1: The Hilbert Matrix
     */
    public static void problem_1() {
        Matrix[] hilberts = new Matrix[19];
        for (int i = 2; i <= hilberts.length + 1; i++) {
            double[][] hilbert = new double[i][i];
            double[] bData = new double[i];
            for (int j = 1; j <= i; j++) { // should be <=?
                for (int k = 1; k <= i; k++) { // should be <=?
                    double divideBy = (double) (j + k - 1);
                    divideBy = 1/divideBy;
                    hilbert[j - 1][k - 1] = divideBy;
                }
                double powerOf = (double) i/3;
                bData[j - 1] = Math.pow(0.1, powerOf);

            }
            Vector bVector = new Vector(bData); // answer b
            hilberts[i - 2] = Matrix.toAugmented(new Matrix(hilbert), bVector);
        }

        // Store the error tables for each iteration and solution type to be
        //      outputted to Excel.
        double[][] luErrorTable = new double[19][2];
        double[][] givensErrorTable = new double[19][2];
        double[][] houseHoldersErrorTable = new double[19][2];

        Object[][] luSolutions = new Object[19][2]; // to store answer and error

        for (int i = 2; i <= luSolutions.length + 1; i++) {
            luSolutions[i - 2] = SolveUtil.solve_LU(hilberts[i - 2]); // take A and B and calculate x and error
//            Vector answer = (Vector) luSolutions[i - 2][0];
            double error = (double) luSolutions[i - 2][1];
            luErrorTable[i-2][0] = (double) i;
            luErrorTable[i-2][1] = error;
//            System.out.println("for dimension of " + i + ", answer is:\n" + answer);
//            System.out.println(i + " With the error of: " + error);
        }

        String[] headers = {"iteration", "error"};

        try {
            IO.writeTo2ColumnCSV(headers, luErrorTable, "src/data/luError");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Object[][] givensSolutions = new Object[19][2];

        for (int i = 2; i <= givensSolutions.length + 1; i++) {
            givensSolutions[i - 2] = SolveUtil.solve_qr_givens(hilberts[i - 2]);
//            Vector answer = (Vector) givensSolutions[i - 2][0];
            double error = (double) givensSolutions[i - 2][1];
            givensErrorTable[i-2][0] = (double) i;
            givensErrorTable[i-2][1] = error;
        }

        try {
            IO.writeTo2ColumnCSV(headers, givensErrorTable, "src/data/givensError");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Object[][] householderSolutions = new Object[19][2];

        for (int i = 2; i <= householderSolutions.length + 1; i++) {
            householderSolutions[i - 2] = SolveUtil.solve_qr_house(hilberts[i - 2]);
//            Vector answer = (Vector) householderSolutions[i - 2][0];
            double error = (double) householderSolutions[i - 2][1];
            houseHoldersErrorTable[i-2][0] = (double) i;
            houseHoldersErrorTable[i-2][1] = error;
        }

        try {
            IO.writeTo2ColumnCSV(headers, houseHoldersErrorTable, "src/data/householdersError");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

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
    public static void problem_3() throws IOException {
        int max = 300;
        ArrayList<Matrix> twoHundredMatrixes= SolveUtil.generate2by2(max);
        //first 200 elements are the iterations required for the 300 a's
        //last 200 elements are the iterations required for the 300 a-1's
        int[] iterationsArray = new int[max * 2];
        double[] traceArray = new double[max];
        double[] determinantArray = new double[max];
        float tol = 5/100000;
        int maxIter = 100;
        double[] array = {1.0, 0.0};
        Vector u = new Vector(array);
        Vector w = new Vector(array);

        BufferedWriter deter = null;
        BufferedWriter trace = null;
        BufferedWriter iters = null;
        BufferedWriter itersInv = null;



        for (int i = 0; i < max; i++) {
            Matrix firstInput = twoHundredMatrixes.get(i);
            Matrix firstInverse = firstInput.findInverse();

            Object[] answers = SolveUtil.power_method(firstInput, u, w, tol, maxIter);
            iterationsArray[i] = (Integer) answers[2];

            traceArray[i] = firstInput.getTrace();
            determinantArray[i] = firstInput.determinant();


            Object[] answers2 = SolveUtil.power_method(firstInverse, u, w, tol, maxIter);
            iterationsArray[i + max] = (Integer) answers2[2];

        }

        try {
            deter = new BufferedWriter(new FileWriter("Determinant"));
            trace = new BufferedWriter(new FileWriter("Trace"));
            iters = new BufferedWriter(new FileWriter("Iterations"));
            itersInv = new BufferedWriter(new FileWriter("Iterations A-1"));

            deter.write("Determinant");
            trace.write("Trace");
            iters.write("Iterations A");
            itersInv.write("Iterations A-1");

            for (int i = 0; i < max; i++) {
                deter.write(determinantArray[i] + "\n");
                trace.write(traceArray[i] + "\n");
                iters.write(iterationsArray[i] + "\n");
                itersInv.write(iterationsArray[i + max] + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            deter.close();
            trace.close();
            itersInv.close();
            iters.close();
        }

        System.out.println("4 Things were written to a file called Problem 3.txt");
        System.out.println("Iterations of A and A-1");
        System.out.println("The trace of A");
        System.out.println("The determinant of A");
        System.out.println("Check the graphs for these 4 values plotted on a scatter graph \n");

        double[] array1 = {-2, 1, 2, 0, 2, 3, 2, 1, -2};
        Matrix A = new Matrix(3, 3, array1);
        double p1 = -5/2;
        double p2 = 5/2;
        double[] arr = {1.0, 0.0, 0.0};
        Vector u1 = new Vector(arr);
        Vector w1 = new Vector(arr);

        Matrix finalized = Matrix.invert(A.minus((Matrix.multiply(Matrix.getIdentityMatrix(3), p1))));
        Object[] finalizedanswers = SolveUtil.power_method(finalized, u1, w1, tol, maxIter);
        double eigenvalue = (double) finalizedanswers[0];

        Matrix finalized1 = Matrix.invert(A.minus((Matrix.multiply(Matrix.getIdentityMatrix(3), p2))));
        Object[] finalizedanswers1 = SolveUtil.power_method(finalized1, u1, w1, tol, maxIter);
        double eigenvalue1 = (double) finalizedanswers1[0];

// penis

        System.out.println("Now, we take into consideration the matrix A here");
        System.out.println(A);
        System.out.println("Using the power method on the matrix (A-pI)-1");
        System.out.println("The eigenvalue is " + eigenvalue + " using a p of -2.5");
        System.out.println("The eigenvalue is " + eigenvalue1 + " using a p of 2.5");



    }
}
