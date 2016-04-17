import javax.xml.bind.ValidationException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("Hello and welcome to our Calc 3 Project");
        System.out.println("Authors: Anush Mattapalli, Matthew Cuevas, Prerak Upadhyaya");
        System.out.println("");

        System.out.println("Please input:");
        System.out.println("1: Problem 1 (need more descriptions)");
        System.out.println("2: Problem 2 (need more descriptions)");
        System.out.println("3: Problem 3 (need more descriptions)");
        int num = in.nextInt();

        switch (num) {
            case 1: Problems.problem_1();
                break;
            case 2: Problems.problem_2();
                break;
            case 3: Problems.problem_3();
                break;
        }

//        Problems.problem_1();

//        // LU Factorization
//        double[] AData = {6, 0, 2, 24, 1, 8, -12, 1, -3};
//        Matrix A = new Matrix(3, 3, AData);
//
//        double[] array = {1.0 ,2.0 ,3.9};
//        Vector b = new Vector(array);
//
//        Matrix Ab = Matrix.toAugmented(A, b);
//
//        Object[] qranswers = SolveUtil.solve_qr_house(Ab);
//        Matrix ans1 = (Matrix) qranswers[0];
//        double ans2 = (double) qranswers[1];
//
//        System.out.println("Answer 1");
//        System.out.println(ans1);
//
//        System.out.println("Error");
//        System.out.println(ans2);

//       // LU Factorization*/
//        Object[] LU = FactorUtil.lu_fact(A);
//        Matrix L = (Matrix) LU[0];
//        Matrix U = (Matrix) LU[1];
//        double error = (double) LU[2];
//
//        System.out.println("LU Factorization\n");
//        System.out.println("L:" + L);
//        System.out.println("U:" + U);
//        System.out.println("error: " + error + "\n");
//
//        // LU Solve
//        double[] bData = {1, 7, 0};
//        Vector b = new Vector(bData);
//
//        Object[] LU_Solve = SolveUtil.solve_LU(Matrix.toAugmented(A, b));
//        Vector x = (Vector) LU_Solve[0];
//        error = (double) LU_Solve[1];
//
//        System.out.println("LU Solve\n");
//        System.out.println("x:" + x);
//        System.out.println("error: " + error + "\n");
//
//        // QR Solve House
//        double[] BData = {1, 2, 0, 0, 1, 1, 1, 0, 1};
//        double[] QData = {Math.pow(2, -.5), Math.pow(3, -.5), -Math.pow(6, -.5),
//                0, Math.pow(3, -.5), 2 * Math.pow(6, -.5),
//                Math.pow(2, -.5), -Math.pow(3, -.5), Math.pow(6, -.5)};
//        double[] RData = {Math.pow(2, .5), Math.pow(2, .5), Math.pow(2, -.5),
//                0, Math.pow(3, .5), 0, 0, 0, Math.pow(6, .5) * .5};
//        double[] cData = {-1, 1, 3};
//
//        Matrix Q = new Matrix(3, 3, QData);
//        Matrix R = new Matrix(3, 3, RData);
//        Vector c = new Vector(cData);
//
//        Object[] QR_Solve = SolveUtil.solve_QR(Q, R, c);
//        x = (Vector) QR_Solve[0];
//        error = (double) QR_Solve[1];
//
//        System.out.println("QR Solve\n");
//        System.out.println("x:" + x);
//        System.out.println("error: " + error + "\n");
//
//        // Jacobi Iteration
//        double[] CData = {5, -2, 3, -3, 9, 1, 2, -1, -7};
//        double[] dData = {-1, 2, 3};
//        double[] uData = {1, 0, 0};
//
//        Matrix C = new Matrix(3, 3, CData);
//        Vector d = new Vector(dData);
//        Vector u = new Vector(uData);
//
//        Object[] Jacobi = SolveUtil.jacobi_iter(
//                Matrix.toAugmented(C, d), u, (float) 0.001, 1000);
//        x = (Vector) Jacobi[0];
//        int iterations = (Integer) Jacobi[1];
//        error = (double) Jacobi[2];
//
//        System.out.println("Jacobi Iteration\n");
//        System.out.println("x:" + x);
//        System.out.println("iterations: " + iterations);
//        System.out.println("error: " + error + "\n");
//
//        // Gauss-Seidel Iteration
//        Object[] GS = SolveUtil.gs_iter(
//                Matrix.toAugmented(C, d), u, (float) 0.001, 1000);
//        x = (Vector) GS[0];
//        iterations = (Integer) GS[1];
//        error = (double) GS[2];
//
//        System.out.println("Gauss-Seidel Iteration\n");
//        System.out.println("x:" + x);
//        System.out.println("iterations: " + iterations);
//        System.out.println("error: " + error + "\n");
//
//        // Power Method
//        double[] DData = {0, 11, -5, -2, 17, -7, -4, 26, -10};
//        double[] uNoughtData = {1, 1, 1};
//        double[] wData = {1, 0, 0};
//
//        Matrix D = new Matrix(3, 3, DData);
//        u = new Vector(uNoughtData);
//        Vector w = new Vector(wData);
//
//        Object[] PowerMethod = SolveUtil.power_method(A, u, w, (float) 0.001, 1000);
//        double lambda = (double) PowerMethod[0];
//        x = (Vector) PowerMethod[1];
//        iterations = (Integer) PowerMethod[2];
//
//        System.out.println("Power Method");
//        System.out.println("lambda: " + lambda);
//        System.out.println("x: " + x);
//        System.out.println("iterations: " + iterations);
    }
}
