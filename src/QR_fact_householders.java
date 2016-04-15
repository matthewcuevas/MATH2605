/**
 * Created by Anush on 4/14/2016.
 */
public class QR_fact_householders {
    /**
     * Gets the Q matrix for QR factorization
     * uses Householders Reflections
     *
     * @param a input matrix
     * @return Matrix representation of Q matrix
     */
    public static Matrix getQ(Matrix a) {
        Matrix curr = a;
        Matrix householders = null;
        for (int i = 0; i < a.getColumns() - 1; i++) {
            Matrix H = getHouseholderReflection(curr, i);
            householders = (householders == null) ? H : Matrix.multiply(householders, H);
            curr = H.multiply(H, curr);
        }
        return householders;
    }

    /**
     * Gets the R matrix for QR factorization
     * uses Householders Reflections
     *
     * @param a input matrix
     * @return Matrix representation of R matrix
     */
    public static Matrix getR(Matrix a) {
        Matrix curr = a;
        for (int i = 0; i < a.getColumns() - 1; i++) {
            curr = Matrix.multiply(getHouseholderReflection(curr, i), curr);
        }
        return curr;
    }

    /**
     * Gets a Householder Reflection matrix
     * for the given Matrix at the given column number
     *
     * @param a Matrix to find Householdr reflection of
     * @param col column to find Householder reflection of
     *            (columns start from 0)
     * @return a Householder Reflection matrix
     */
    private static Matrix getHouseholderReflection(Matrix a, int col) {
        //II-2uut
        //II - 2vvt/vmag^2

        //copies input matrix 2d double array
        double[][] input = a.getCopy().getArray();

        double[][] output = new double[input.length][input.length];

        //number of columns in Householder Hat Matrix
        int columns = input.length - col;



        //gets identity matrix
        Matrix identity = Matrix.getIdentityMatrix(columns);

        //gets x
        double[] x1 = new double[columns];
        for (int i = col; i < col + columns; i++ ) {
            x1[i - col] = input[i][col];
        }

        //gets v
        double[] v = x1;
        v[0] += Vector.norm(new Vector(v));

        //gets VVt
        Matrix VVt = getVVt(v);

        //gets 2uut
        Matrix uut2 = Matrix.multiply(VVt, 2.0 / Math.pow((Vector.norm(new Vector(v))), 2));

        //gets Hhat matrix as 2d double array
        Matrix hHat = new Matrix(identity.minus(uut2).getArray());

        //turns Hhat into Householder matrix
        for (int i = input.length - 1; i >= 0; i--) {
            for (int j = input.length - 1; j >= 0; j--) {
                int x = i - col;
                int y = j - col;
                if (x >= 0 && y >= 0) {
                    output[i][j] = hHat.get(x, y);
                } else if (i == j) {
                    output[i][j] = 1;
                } else {
                    output[i][j] = 0;
                }
            }
        }
        Matrix retur = new Matrix(output);
        return retur;
    }

    /**
     * Gets the V*Vt matrix
     *
     * @param v double array representation of V vector
     * @return Matrix representation of V*Vt matrix
     */
    private static Matrix getVVt(double[] v) {
        double[][] VVtMatrix = new double[v.length][v.length];
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v.length; j++) {
                VVtMatrix[i][j] = v[i] * v[j];
            }
        }
        return new Matrix(VVtMatrix);
    }




}
