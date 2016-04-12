package com.math2605.src;

/**
 * Created by matthew on 4/12/16.
 */
public class Utilities {
    public static Vector triangularSolve(Matrix A, Vector b) {
        int n = (int) b.magnitude();

        double[] solution = new double[n];
        for (int i = 0; i < n; i++) {
            double rhs = b.get(i, 0);
            for (int j = 0; j < i; j++) {
                rhs -= solution[i-1] * A.get(i,j);
            }

            solution[i] = rhs / A.get(i, i);
        }

        return new Vector(solution);
    }
}
