/**
 * Created by Anush on 4/10/2016.
 */
public class Matrix {

    private double[][] matrix;
    private int rows;
    private int columns;

    /**
     * Creates a matrix from the number of rows and columns
     * @param rows number of rows
     * @param columns number of columns
     */
    public Matrix(int rows, int columns) {
        matrix = new double[rows][columns];
        this.rows = rows;
        this.columns = columns;
    }

    /**
     * Dynamic constructor that makes the matrix with the doubles passed in
     * If (2, 2, new double[]{1, 2, 3, 4}) are passed, the result will be:
     * 1  2
     * 3  4
     * @param rows the number of rows
     * @param columns the number of columns
     * @param nums a double array with the numbers
     * @throws IllegalArgumentException if length of nums is not equal to m x n
     */
    public Matrix(int rows, int columns, double[] nums) {
        if (nums.length != rows * columns) {
            throw new IllegalArgumentException("Tried to make a " + rows + " by "
                    + columns + " matrix with " + nums.length + " elements.");
        }
        this.rows = rows;
        this.columns = columns;

        int z = 0;
        matrix = new double[rows][columns];
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                matrix[y][x] = nums[z++];
            }
        }
    }

    /**
     * Instantiates a Matrix based on a 2-D array of values
     * @param matrix a 2-D array of values
     */
    public Matrix(double[][] matrix) {
        this.rows = matrix.length;
        this.columns = matrix[0].length;
        this.matrix = new double[rows][columns];
        for(int i=0; i < matrix.length; i++)
            for(int j=0; j < matrix[i].length; j++)
                this.matrix[i][j] = matrix[i][j];
    }

    /**
     * Returns the number of rows in matrix (height)
     * @return number of rows
     */
    public int getRows() {
        return rows;
    }
    /**
     * Returns the number of columns in matrix (width)
     * @return total number of columns
     */
    public int getColumns() {
        return columns;
    }


    /**
     * Puts a value in a matrix where required
     * @param row the row wanted
     * @param column the column wanted
     * @param num the value to store into the matrix
     * @throws IllegalArgumentException if parameters are not in proper range
     */
    public void set(int row, int column, double num) {
        if (row < 0 || row >= rows || column < 0 || column >= columns) {
            throw new IllegalArgumentException("Try setting a value " +
                    "that's actually in the matrix. Remember (0,0) is " +
                    "the first element");
        }

        matrix[row][column] = num;
    }

    /**
     * Gets a value from the matrix. Row then Column
     * @param row the row where the desired data is
     * @param column the column where the desired data is
     * @return the value at this row and column
     * @throws IllegalArgumentException if args are not in proper range
     */
    public double get(int row, int column) {
        if (row < 0 || row >= rows || column < 0 || column >= columns) {
            throw new IllegalArgumentException("Tried to get value at row " + row
                    + " and column " + column + " of a " + rows + " by " +  columns
                    + " matrix.");
        }
        return matrix[row][column];
    }

    /**
     * Finds the inverse of a only 2x2 Matrix;
     * @return the inverse of the matrix
     */
    public Matrix findInverse() {
        double[][] inverse = new double[2][2];
        double a,b,c,d;
        double det = this.determinant();

        a = matrix[0][0]/det;
        b = matrix[0][1]/det;
        c = matrix[1][0]/det;
        d = matrix[1][1]/det;

        inverse[0][0] = d;
        inverse[0][1] = -b;
        inverse[1][0] = -c;
        inverse[1][1] = a;

        return new Matrix(inverse);
    }


    /**
     * Gets the trace of a matrix
     * @return trace
     */
    public double getTrace() {
        double trace = 0;
        int dim = matrix.length;

        for (int x = 0; x < dim; x++) {
            trace = trace + matrix[x][x];
        }
        return trace;
    }



    /**
     * returns the determinant of this matrix
     *
     * @return a double of the determinant of this matrix
     */
    public double determinant() {
        if (rows != columns) {
            throw new IllegalArgumentException("Row and Column dimensions must be equivalent");
        }
        if (rows < 1) {
            throw new IllegalArgumentException("Dimensions must be at least 1x1");
        }
        return determinant(matrix, rows);
    }

    /**
     * determinant helper method
     *
     * @param myMatrix      the double[][] representing the matrix
     * @param dimensionSize the number of rows (or columns)
     * @return a double of the determinant
     */
    private static double determinant(double myMatrix[][], int dimensionSize) {
        double determinant = 0;
        if (dimensionSize == 1) { // 1x1 matrix
            determinant = myMatrix[0][0];
        } else if (dimensionSize == 2) { // 2x2 matrix
            determinant = myMatrix[0][0] * myMatrix[1][1] - myMatrix[0][1] * myMatrix[1][0];
        } else { // 3x3 or greater
            for (int miniMatrixNo = 0; miniMatrixNo < dimensionSize; miniMatrixNo++) {
                double[][] miniMatrix = new double[dimensionSize - 1][];
                for (int k = 0; k < (dimensionSize - 1); k++) {
                    miniMatrix[k] = new double[dimensionSize - 1];
                }
                for (int row = 1; row < dimensionSize; row++) {
                    int j2 = 0;
                    for (int column = 0; column < dimensionSize; column++) {
                        if (column == miniMatrixNo)
                            continue;
                        miniMatrix[row - 1][j2] = myMatrix[row][column];
                        j2++;
                    }
                }
                determinant = determinant + Math.pow(-1.0, miniMatrixNo) * myMatrix[0][miniMatrixNo] * determinant(miniMatrix, dimensionSize - 1);
            }
        }
        return determinant;
    }

    /**
     * This method gets the raw double matrix
     * @return the internal matrix
     */
    public double[][] getArray() {
        return matrix;
    }

    /**
     * Gets the infinity norm of the Matrix, i.e. the max of the sum of absolute
     * values of a row in the Matrix
     *
     * @return norm of the Matrix
     */
    public double getNorm() {
        double max = 0;
        for (int i = 0; i < matrix.length; i++) {
            double sum = 0;
            for (int j = 0; j < matrix[0].length; j++) {
                sum += Math.abs(matrix[i][j]);
            }

            if (sum > max) {
                max = sum;
            }
        }

        return max;
    }

    /**
     * Returns the sum of two matrices
     * @param m1 a Matrix
     * @param m2 another Matrix
     * @return a matrix object that represents the sum of the two matrices given
     * @throws IllegalArgumentException if matrices have different dimensions
     */
    public static Matrix sum(Matrix m1, Matrix m2) {
        if (m1 == null || m2 == null) {
            throw new IllegalArgumentException("Matrix might be null lol");
        }

        //thought this might be helpful
        if (m1.rows != m2.rows || m1.columns != m2.columns) {
            throw new IllegalArgumentException("Tried to add a " + m1.rows
                    + " by " + m1.columns + " matrix to a " + m2.rows
                    + " by " + m2.columns + " matrix.");
        }

        Matrix retur = new Matrix(m1.rows, m1.columns);
        for (int y = 0; y < retur.rows; y++) {
            for (int x = 0; x < retur.columns; x++) {
                retur.set(y, x, m1.get(y, x) + m2.get(y, x));
            }
        }
        return retur;
    }

    /**
     * Subtracts the given matrix from this matrix
     *
     * @throws IllegalArgumentException if the matrix is null or dimensions differ
     * @param mat Matrix to subtract from this one
     * @return this matrix - mat
     */
    public Matrix minus(Matrix mat) {
        if (mat == null) {
            throw new IllegalArgumentException("You can't subtract a null matrix");
        }
        if (mat.getColumns() != getColumns() || mat.getRows() != getRows()) {
            throw new IllegalArgumentException("You can't subtract matrices with different dimensions");
        }
        double[][] a = mat.getArray();
        double[][] sub = new double[mat.getRows()][mat.getColumns()];
        for (int i = 0; i < mat.getRows(); i++) {
            for (int j = 0; j < mat.getColumns(); j++) {
                sub[i][j] = matrix[i][j] - a[i][j];
            }
        }

        Matrix retur = new Matrix(sub);
        return retur;
    }

    /**
     * Returns the product of two matrices
     * @param m1 a Matrix
     * @param m2 another Matrix
     * @return a matrix object that represents the product of the two matrices given
     * @throws IllegalArgumentException if matrices have incompatible dimensions
     * or are null
     */
    public static Matrix multiply(Matrix m1, Matrix m2) {
        if (m1 == null || m2 == null) {
            throw new IllegalArgumentException("Matrix might be null lol");
        }

        if (m1.columns != m2.rows) {
            throw new IllegalArgumentException("Tried to multiply a " + m1.rows
                    + " by " + m1.columns + " matrix by a " + m2.rows
                    + " by " + m2.columns + " matrix.");
        }

        Matrix retur = new Matrix(m1.rows, m2.columns);
        for (int y = 0; y < m1.rows; y++) {
            for (int x = 0; x < m2.columns; x++) {
                double val = 0;
                for (int z = 0; z < m1.columns; z++) {
                    val += m1.get(y, z) * m2.get(z, x);
                }
                retur.set(y, x, val);
            }
        }


        return retur;
    }

    /**
     * Matrix multplication by a factor double
     * @param m1 original matrix
     * @param factor the multiplication factor
     * @return the new matrix
     */
    public static Matrix multiply(Matrix m1, double factor) {
        for (int i = 0; i < m1.getRows(); i++) {
            for (int j = 0; j < m1.getColumns(); j++) {
                m1.set(i, j, factor * m1.get(i, j));
            }
        }

        return m1;
    }

    /**
     * Inverts the matrix
     * Code written by SanFoundry
     */
    public static Matrix invert(Matrix A) {
        double[][] a = A.matrix;
        int n = a.length;
        double x[][] = new double[n][n];
        double b[][] = new double[n][n];
        int index[] = new int[n];
        for (int i=0; i<n; ++i)
            b[i][i] = 1;

        // Transform the matrix into an upper triangle
        gaussian(a, index);

        // Update the matrix b[i][j] with the ratios stored
        for (int i=0; i<n-1; ++i)
            for (int j=i+1; j<n; ++j)
                for (int k=0; k<n; ++k)
                    b[index[j]][k]
                            -= a[index[j]][i]*b[index[i]][k];

        // Perform backward substitutions
        for (int i=0; i<n; ++i) {
            x[n-1][i] = b[index[n-1]][i]/a[index[n-1]][n-1];
            for (int j=n-2; j>=0; --j) {
                x[j][i] = b[index[j]][i];
                for (int k=j+1; k<n; ++k) {
                    x[j][i] -= a[index[j]][k]*x[k][i];
                }

                x[j][i] /= a[index[j]][j];
            }
        }

        return new Matrix(x);
    }

    /**
     * Helper method for finding the inverse of a Matrix
     */
    private static void gaussian(double a[][], int index[]) {
        int n = index.length;
        double c[] = new double[n];

        // Initialize the index
        for (int i=0; i<n; ++i)
            index[i] = i;

        // Find the rescaling factors, one from each row
        for (int i=0; i<n; ++i) {
            double c1 = 0;
            for (int j=0; j<n; ++j) {
                double c0 = Math.abs(a[i][j]);
                if (c0 > c1) c1 = c0;
            }

            c[i] = c1;
        }

        // Search the pivoting element from each column
        int k = 0;
        for (int j=0; j<n-1; ++j) {
            double pi1 = 0;
            for (int i=j; i<n; ++i) {
                double pi0 = Math.abs(a[index[i]][j]);
                pi0 /= c[index[i]];
                if (pi0 > pi1) {
                    pi1 = pi0;
                    k = i;
                }
            }

            // Interchange rows according to the pivoting order
            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            for (int i=j+1; i<n; ++i) {
                double pj = a[index[i]][j]/a[index[j]][j];

                // Record pivoting ratios below the diagonal
                a[index[i]][j] = pj;

                // Modify other elements accordingly
                for (int l=j+1; l<n; ++l)
                    a[index[i]][l] -= pj*a[index[j]][l];
            }
        }
    }

    /**
     * Negates each element in the Matrix
     */
    public Matrix negate() {
        double a[][] = new double[rows][columns];
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++) {
                a[i][j] = -matrix[i][j];
            }
        }

        return new Matrix(a);
    }

    /**
     * Tranposes this Matrix
     * @return A ^ T
     */
    public Matrix transpose() {
        double[][] t = new double[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                t[j][i] = matrix[i][j];
            }
        }
        return new Matrix(t);
    }

    /**
     * Creates a copy of this Matrix
     * @return a copy of this Matrix
     */
    public Matrix getCopy() {
        Matrix X = new Matrix(rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                X.set(i, j, matrix[i][j]);
            }
        }

        return X;
    }

    /**
     * Converts an n x 1 Matrix into a vector
     * @param A a Matrix
     * @return a vector with the same data as A
     */
    public static Vector toVector(Matrix A) {
        if (A.getColumns() == 1) {
            double[] data = new double[A.getRows()];
            for (int i = 0; i < A.getRows(); i++) {
                data[i] = A.get(i, 0);
            }
            return new Vector(data);
        } else {
            throw new IllegalArgumentException("Matrix must be n x 1");
        }
    }

    /**
     * Creates an augmented matrix of A and b
     * @param A a Matrix
     * @param b a Vector
     * @return a Matrix that contains A and b
     */
    public static Matrix toAugmented(Matrix A, Vector b) {
        Matrix X = new Matrix(A.getRows(), A.getColumns() + 1);
        for (int i = 0; i < A.getRows(); i++) {
            for (int j =0; j < A.getColumns(); j++) {
                X.set(i, j, A.get(i, j));
            }
        }

        for (int i = 0; i < A.getRows(); i++) {
            X.set(i, A.getColumns(), b.get(i, 0));
        }

        return X;
    }

    /**
     * Splits an augmented matrix into A and b
     * @param A a Matrix
     * @return an Object array where the first element is the Matrix and the
     * second is the vector
     */
    public static Object[] fromAugmented(Matrix A) {
        double[][] ANums = new double[A.getRows()][(A.getColumns() - 1)];
        for (int i = 0; i < A.getRows(); i++) {
            for (int j = 0; j < A.getColumns() - 1; j++) {
                ANums[i][j] = A.get(i, j);
            }
        }

        double[] bNums = new double[A.getRows()];
        for (int i = 0; i < A.getRows(); i++) {
            bNums[i] = A.get(i, A.getColumns() - 1);
        }

        Object[] Ab = new Object[2];
        Ab[0] = new Matrix(ANums);
        Ab[1] = new Vector(bNums);
        return Ab;
    }

    /**
     * Gets a square, n x n Identity Matrix
     *
     * @throws IllegalArgumentException if n < 1
     * @param n the number of rows and columns
     * @return an n x n Identity matrix
     */
    public static Matrix getIdentityMatrix(int n) {
        if (n < 1) {
            throw new IllegalArgumentException(
                    "You can't have an identity matrix with less than one row/columns");
        }
        double[][] identity = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    identity[i][j] = 1;
                } else {
                    identity[i][j] = 0;
                }
            }
        }
        return new Matrix(identity);
    }

    @Override
    public String toString() {
        String ret = "\n";
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                ret = ret + String.format("%2.4f", matrix[y][x]) + "\t";
            }
            ret = ret + "\n";
        }
        return ret;
    }
}
