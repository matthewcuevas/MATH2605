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
     * Solves Ax = b when D is a Diagonal
     * @param A a Matrix
     * @param b a Vector
     * @return the solution to this equation
     */
    public static Vector DiagonalSolve(Matrix A, Vector b) {
        double[] xData = new double[b.getRows()];
        for (int i = 0; i < b.getRows(); i++) {
            xData[i] = b.get(i, 0) / A.get(i, i);
        }

        return new Vector(xData);
    }

    /**
     * Solves Ax = b with LU Factorization
     * @param Ab an augmented Matrix
     */
    public static Vector solve_LU(Matrix Ab) {
        Matrix A = (Matrix) Matrix.fromAugmented(Ab)[0];
        Vector b = (Vector) Matrix.fromAugmented(Ab)[1];

        Object[] LU = FactorUtil.lu_fact(A);
        Matrix L = (Matrix) LU[0];
        Matrix U = (Matrix) LU[1];

        Vector y = LLTriangularSolve(L, b);

        return URTriangularSolve(U, y);
    }

    /**
     * Solve Ax = b using Householders QR Factorization
     * @param Ab an augmented Matrix
     * @return the solution to this equation
     */
    public static Vector solve_qr_house(Matrix Ab) {
        Matrix A = (Matrix) Matrix.fromAugmented(Ab)[0];
        Vector b = (Vector) Matrix.fromAugmented(Ab)[1];

        Object[] QR = FactorUtil.qr_fact_house(A);
        Matrix Q = (Matrix) QR[0];
        Matrix R = (Matrix) QR[1];

        Matrix QTranspose = Q.transpose();

        Vector y = (Vector) Matrix.multiply(QTranspose, b);
        return URTriangularSolve(R, y);
    }

    /**
     * Solve Ax = b using Givens QR Factorization
     * @param Ab an augmented Matrix
     * @return the solution to this equation
     */
    public static Vector solve_factor_givens(Matrix Ab) {
        Matrix A = (Matrix) Matrix.fromAugmented(Ab)[0];
        Vector b = (Vector) Matrix.fromAugmented(Ab)[1];

        Object[] QR = FactorUtil.qr_fact_givens(A);
        Matrix Q = (Matrix) QR[0];
        Matrix R = (Matrix) QR[1];

        Matrix QTranspose = Q.transpose();

        Vector y = (Vector) Matrix.multiply(QTranspose, b);
        return URTriangularSolve(R, y);
    }

    /**
     * Uses the Jacobi Iteration method to approximate Ax = b
     * @param Ab an augmented Matrix
     * @param u a Vector; the starting guess
     * @param tolerance error tolerance
     * @param M max iterations
     * @return the approximated solution
     */
    public static Object[] jacobi_iter(Matrix Ab, Vector u, float tolerance, int M) {
        Matrix A = (Matrix) Matrix.fromAugmented(Ab)[0];
        Vector b = (Vector) Matrix.fromAugmented(Ab)[1];

        Matrix L = jacobiL(A);
        Matrix U = jacobiU(A);
        Matrix D = jacobiD(A);
        Matrix LUSum = Matrix.sum(L, U);

        Object[] solution = new Object[2];

        Vector[] guesses = new Vector[M + 1];
        guesses[0] = u;

        for (int i = 1; i < M + 1; i++) {
            Matrix RHS = Matrix.sum(Matrix.multiply(LUSum,
                    guesses[i - 1]).negate(), b);
            guesses[i] = DiagonalSolve(D, Matrix.toVector(RHS));
            solution[0] = guesses[i];

            // TODO: Implement Infinity Norm Check
        }

        return solution;
    }

    /**
     * Uses the Jacobi Iteration method to approximate Ax = b
     * @param Ab an augmented Matrix
     * @param u a Vector; the starting guess
     * @param tolerance error tolerance
     * @param M max iterations
     * @return the approximated solution
     */
    public static Object[] gs_iter(Matrix Ab, Vector u, float tolerance, int M) {
        Matrix A = (Matrix) Matrix.fromAugmented(Ab)[0];
        Vector b = (Vector) Matrix.fromAugmented(Ab)[1];

        Matrix L = jacobiL(A);
        Matrix U = jacobiU(A);
        Matrix D = jacobiD(A);
        Matrix LDSum = Matrix.sum(L, D);

        Object[] solution = new Object[2];

        Vector[] guesses = new Vector[M + 1];
        guesses[0] = u;

        for (int i = 1; i < M + 1; i++) {
            Matrix RHS = Matrix.sum(Matrix.multiply(U,
                    guesses[i - 1]).negate(), b);
            guesses[i] = LLTriangularSolve(LDSum, Matrix.toVector(RHS));
            solution[0] = guesses[i];

            // TODO: Implement Infinity Norm Check
        }

        return solution;
    }

    public static Matrix jacobiL(Matrix A) {
        double[][] LData = new double[A.getRows()][A.getColumns()];
        for (int i = 1; i < A.getRows(); i++) {
            for (int j = 0; j < i; j++) {
                LData[i][j] = A.get(i, j);
            }
        }

        return new Matrix(LData);
    }

    public static Matrix jacobiU(Matrix A) {
        double[][] UData = new double[A.getRows()][A.getColumns()];
        for (int i = 0; i < A.getRows() - 1; i++) {
            for (int j = A.getColumns() - 1; j > i; j--) {
                UData[i][j] = A.get(i, j);
            }
        }

        return new Matrix(UData);
    }

    public static Matrix jacobiD(Matrix A) {
        double[][] DData = new double[A.getRows()][A.getColumns()];
        for (int i = 0; i < A.getRows(); i++) {
            DData[i][i] = A.get(i, i);
        }

        return new Matrix(DData);
    }
}
