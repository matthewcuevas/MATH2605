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
