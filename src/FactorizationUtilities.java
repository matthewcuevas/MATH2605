package com.math2605.src;

/**
 * Methods for factorizing matrices.
 */
public class FactorizationUtilities {

    /**
     * LU Factorizes a given Matrix
     * @param A the Matrix to be factorized
     * @return an array of Matrices where the first element is L,
     * and the second element is U, and the third is the error
     */
    public static Object[] lu_fact(Matrix A) {
        Object[] LU = new Matrix[2];
        LU[0] = getL(A);
        LU[1] = getU(A);
        return LU;
    }

    /**
     * This method returns the "L" of the lu factorization
     * @param q the Q matrix to factor/decompose
     * @return the decomposed l matrix
     */
    private static Matrix getL(Matrix q) {
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
    private static Matrix getU(Matrix q) {
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


    /**
     * LU Factorizes a given Matrix
     * @param A the Matrix to be factorized
     * @return an array of Matrices where the first element is L,
     * and the second element is U, and the third is the error
     */
    public static Object[] lu_fact2(Matrix A) {
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

        Matrix UInverse = U.getCopy();
        UInverse.invert();

        Matrix L = Matrix.multiply(A, UInverse);

        Object[] array = {L, U};
        return array;
    }
}
