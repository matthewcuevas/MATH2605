import javax.xml.bind.ValidationException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("Hello and welcome to our Calc 3 Project");
        System.out.println("Authors: Anush Mattapalli, Matthew Cuevas, Prerak Upadhyaya");
        System.out.println("");

        System.out.println("lu_fact\t\t\tqr_fact_house\tqr_fact_givens\n" +
                "solve_lu\t\tsolve_qr_house\tsolve_qr_givens\n" +
                "jacobi_iter\t\tgs_iter\t\t\tpower_method " +
                "\n\nSpecify a method from the list above:");
        String method = in.nextLine();

        java.io.File outfile = new java.io.File("solution.txt");
        java.io.PrintWriter output = new java.io.PrintWriter(outfile);

        Object[] solution;
        switch (method) {
            case "lu_fact":
                System.out.println("Input the path of the matrix A:");
                solution =
                        FactorUtil.lu_fact(IO.readFileMatrix(in.nextLine()));

                output.println("L:" + (Matrix) solution[0]);
                output.println("U:" + (Matrix) solution[1]);
                output.println("error:" + (double) solution[2]);
                break;
            case "qr_fact_house":
                System.out.println("Input the path of the matrix A:");
                solution =
                        FactorUtil.qr_fact_house(IO.readFileMatrix(in.nextLine()));

                output.println("Q:" + (Matrix) solution[0]);
                output.println("R:" + (Matrix) solution[1]);
                output.println("error: " + (double) solution[2]);
                break;
            case "qr_fact_givens":
                System.out.println("Input the path of the matrix A:");
                solution =
                        FactorUtil.qr_fact_givens(IO.readFileMatrix(in.nextLine()));

                output.println("Q:" + (Matrix) solution[0]);
                output.println("R:" + (Matrix) solution[1]);
                output.println("error: " + (double) solution[2]);
                break;
            case "solve_lu":
                System.out.println("Input the path of the matrix Ab:");
                solution = SolveUtil.solve_LU(IO.readFileNonSymmetricalMatrix(in.nextLine()));

                output.println("x:" + (Matrix) solution[0]);
                output.println("error: " + (double) solution[1]);
                break;
            case "solve_qr_house":
                System.out.println("Input the path of the matrix Ab:");
                solution =
                        SolveUtil.solve_qr_house(IO.readFileNonSymmetricalMatrix(in.nextLine()));

                output.println("x:" + (Matrix) solution[0]);
                output.println("error: " + (double) solution[1]);
                break;
            case "solve_qr_givens":
                System.out.println("Input the path of the matrix Ab:");
                solution =
                        SolveUtil.solve_qr_givens(IO.readFileNonSymmetricalMatrix(in.nextLine()));

                output.println("x:" + (Matrix) solution[0]);
                output.println("error:" + (double) solution[1]);
                break;
            case "jacobi_iter":
                System.out.println("Input the path of the matrix Ab:");
                Matrix jAb = IO.readFileNonSymmetricalMatrix(in.nextLine());
                System.out.println("Input the path of the initial guess u:");
                Vector ju = Matrix.toVector(IO.readFileVector(in.nextLine(), jAb.getRows()));
                System.out.println("Input a tolerance:");
                float jtolerance = in.nextFloat();
                System.out.println("Input the number of iterations:");
                int jiterations = in.nextInt();

                solution = SolveUtil.jacobi_iter(jAb, ju, jtolerance, jiterations);

                output.println("x:" + (Vector) solution[0]);
                output.println("iterations: " + (Integer) solution[1]);
                output.println("error " + (double) solution[2]);
                break;
            case "gs_iter":
                System.out.println("Input the whole path of the matrix Ab:");
                Matrix gsAb = IO.readFileNonSymmetricalMatrix(in.nextLine());
                System.out.println("Input the path of the initial guess u:");
                Vector gsu = Matrix.toVector(IO.readFileVector(in.nextLine(), gsAb.getRows()));
                System.out.println("Input a tolerance:");
                float gstolerance = in.nextFloat();
                System.out.println("Input the number of iterations:");
                int gsiterations = in.nextInt();

                solution = SolveUtil.gs_iter(gsAb, gsu, gstolerance, gsiterations);
                output.println("x:" + (Vector) solution[0]);
                output.println("iterations: " + (Integer) solution[1]);
                output.println("error " + (double) solution[2]);
                break;
            case "power_method":
                System.out.println("Input the path of the matrix A:");
                Matrix pmA = IO.readFileMatrix(in.nextLine());
                System.out.println("Input the path of the initial guess u:");
                Vector pmu = Matrix.toVector(IO.readFileVector(in.nextLine(), pmA.getRows()));
                System.out.println("Input the path of the auxiliary vector w:");
                Vector pmw = Matrix.toVector(IO.readFileVector(in.nextLine(), pmA.getRows()));
                System.out.println("Input a tolerance:");
                float pmtolerance = in.nextFloat();
                System.out.println("Input the number of iterations:");
                int pmiterations = in.nextInt();

                solution =
                        SolveUtil.power_method(pmA, pmu, pmw, pmtolerance, pmiterations);
                output.println("eigenvalue: " + (double) solution[0]);
                output.println("eigenvector:" + (Vector) solution[1]);
                output.println("iterations: " + (Integer) solution[2]);
                break;
        }

        output.close();

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
