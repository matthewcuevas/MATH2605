import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        double[] ANums = {2, -1, 3, 4, 2, 1, -6, -1, 2};
        double[] bNums = {4, 7, -5};

        Matrix A = new Matrix(3, 3, ANums);
        Vector b = new Vector(bNums);

        System.out.println("A:" + A);
        System.out.println("b:" + b);

        // Matrix L = (Matrix) FactorUtil.lu_fact(A)[0];
        // Matrix U = (Matrix) FactorUtil.lu_fact(A)[1];
        // System.out.println("L:" + L);
        // System.out.println("U:" + U);

        // Vector y = SolveUtil.LLTriangularSolve(L, b);
        Vector x = SolveUtil.solve_LU(A, b);

        // System.out.println("y:" + y);
        System.out.println("x:" + x);
    }
}
