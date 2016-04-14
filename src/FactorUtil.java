import java.util.ArrayList;

/**
 * Methods for factorizing matrices.
 */
public class FactorUtil {

    /**
     * LU Factorizes a given Matrix
     *
     * @param A the Matrix to be factorized
     * @return an array of Matrices where the first element is L,
     * and the second element is U, and the third is the error
     */
    public static Object[] lu_fact(Matrix A) {
        int n = A.getRows();

        Matrix U = A.getCopy();
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                double factor = U.get(j, i) / U.get(i, i);
                for (int k = 0; k < n; k++) {
                    U.set(j, k, U.get(j, k) - factor * U.get(i, k));
                }
            }
        }
        Matrix L = Matrix.multiply(A, Matrix.invert(U));

        Object[] LU = new Object[3];
        LU[0] = L;
        LU[1] = U;
        // TODO: Find ||LU-A||

        return LU;
    }

    /**
     * QR Factorize a given Matrix using the Householders method
     *
     * @param A the matrix to be factorized
     * @return an array where the first element is Q,
     * and the second element is R, and the third is the error
     */
    public static Object[] qr_fact_house(Matrix A) {
        Object[] QR = new Matrix[2];
        // TODO: QR Factorize A

        return QR;
    }

    /**
     * QR Factorize a given Matrix using the Givens method
     *
     * @param A the matrix to be factorized
     * @return an array where the first element is Q,
     * and the second element is R, and the third is the error
     */
    public static Object[] qr_fact_givens(Matrix A) {
        Object[] QR = new Matrix[2];
        // TODO: QR Factorize A

        return QR;
    }
}
