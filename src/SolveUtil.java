import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Methods for solving systems of equations and matrix equalities.
 */
public class SolveUtil {
    /**
     * Solves Ax = b when A is lower left triangular
     * @param A a Matrix
     * @param b a Vector
     * @return the Vector that solves the equation
     */
    public static Vector LLTriangularSolve(Matrix A, Vector b) {
        int n = (int) b.getRows();

        double[] solution = new double[n];
        for (int i = 0; i < n; i++) {
            double rhs = b.get(i, 0);
            for (int j = 0; j < i; j++) {
                rhs -= solution[j] * A.get(i,j);
            }

            solution[i] = rhs / A.get(i, i);
        }

        return new Vector(solution);
    }

    /**
     * Solves Ax = b when A is Upper Right triangular
     * @param A a Matrix
     * @param b a Vector
     * @return the vector that solves the equation
     */
    public static Vector URTriangularSolve(Matrix A, Vector b) {
        int n = (int) b.getRows();

        double[] solution = new double[n];
        for (int i = n-1; i >= 0; i--) {
            double rhs = b.get(i, 0);
            for (int j = n-1; j > i; j--) {
                rhs -= solution[j] * A.get(i,j);
            }

            solution[i] = rhs / A.get(i, i);
        }

        return new Vector(solution);
    }

    /**
     * Solves Ax = b with LU Factorization
     * @param A a square Matrix
     * @param b a vector
     */
    public static Vector solve_LU(Matrix A, Vector b) {
        Object[] LU = FactorUtil.lu_fact(A);
        Matrix L = (Matrix) LU[0];
        Matrix U = (Matrix) LU[1];

        Vector y = LLTriangularSolve(L, b);

        return URTriangularSolve(U, y);
    }

    /**
     * Solve Ax = b using Householders QR Factorization
     * @param A a Matrix
     * @param b a Vector
     * @return the solution to this equation
     */
    public static Vector solve_qr_house(Matrix A, Vector b) {
        Object[] QR = FactorUtil.qr_fact_house(A);
        Matrix Q = (Matrix) QR[0];
        Matrix R = (Matrix) QR[1];

        Matrix QTranspose = Q.transpose();

        Vector y = (Vector) Matrix.multiply(QTranspose, b);
        return URTriangularSolve(R, y);
    }

    /**
     * Solve Ax = b using Givens QR Factorization
     * @param A a Matrix
     * @param b a Vector
     * @return the solution to this equation
     */
    public static Vector solve_factor_givens(Matrix A, Vector b) {
        Object[] QR = FactorUtil.qr_fact_givens(A);
        Matrix Q = (Matrix) QR[0];
        Matrix R = (Matrix) QR[1];

        Matrix QTranspose = Q.transpose();

        Vector y = (Vector) Matrix.multiply(QTranspose, b);
        return URTriangularSolve(R, y);
    }
}
