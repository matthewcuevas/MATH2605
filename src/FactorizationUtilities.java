

/**
 * Methods for factorizing matrices.
 */
public class FactorizationUtilities {

    /**
     * LU Factorizes a given Matrix
     * @param A the Matrix to be factorized
     * @return an array of Matrices where the first element is L,
     * and the second element is U
     */
    public static Matrix[] lu_fact(Matrix A) {
        Matrix[] LU = new Matrix[2];
        LU[0] = getL(A);
        LU[1] = getU(A);
        return LU;
    }

    /**
     * This method returns the "L" of the lu factorization
     * @param q the Q matrix to factor/decompose
     * @return the decomposed l matrix
     */
    public static Matrix getL(Matrix q) {
        Matrix L = new Matrix(q.getRows(), q.getColumns());
        for (int i = 0; i < q.getRows(); i++) {
            for (int j = 0; j < q.getColumns(); j++) {
                if (i > j) {
                    L.set(i, j, q.get(i, j));
                } else if (i == j) {
                    L.set(i, j, 1.0);
                } else {
                    L.set(i, j, 0.0);
                }
            }
        }
        return L;
    }

    /**
     * This method returns the "U" of the lu factorization
     * @param q the Q matrix to factor/decompose
     * @return the decomposed u matrix
     */
    public static Matrix getU(Matrix q) {
        Matrix U = new Matrix(q.getRows(), q.getColumns());
        for (int i = 0; i < q.getRows(); i++) {
            for (int j = 0; j < q.getColumns(); j++) {
                if (i <= j) {
                    U.set(i, j, q.get(i,j));
                } else {
                    U.set(i, j, 0.0);
                }
            }
        }
        return U;
    }
}
