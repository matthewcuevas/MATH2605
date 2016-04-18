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
        LU[2] = Matrix.sum(Matrix.multiply(L, U), A.negate()).getNorm();

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
        Object[] QR = new Object[3];

        QR[0] = QR_fact_householders.getQ(A);
        QR[1] = QR_fact_householders.getR(A);
        QR[2] = Matrix.sum(Matrix.multiply((Matrix) QR[0], (Matrix) QR[1]),
                A.negate()).getNorm();

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
        Object[] QR = new Object[3];

        QR_fact_givens qrMaker = new QR_fact_givens(A);
        QR[0] = qrMaker.getQ();
        QR[1] = qrMaker.getR();
        QR[2] = Matrix.sum(Matrix.multiply((Matrix) QR[0], (Matrix) QR[1]),
                A.negate()).getNorm();

        return QR;

    }
}
