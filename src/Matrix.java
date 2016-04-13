/**
 * Created by Anush on 4/10/2016.
 */
public class Matrix {

    private double[][] matrix;
    private int rows;
    private int columns;
    private double[] nums;

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
        this.nums = nums;

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
    public Matrix(double[][] matrix){
        this.rows = matrix.length;
        this.columns = matrix[0].length;
        this.matrix = matrix;

        int z = 0;
        nums = new double[rows * columns];
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                nums[z++] = matrix[y][x];
            }
        }
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
                    + "matrix.");
        }
        return matrix[row][column];
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
     * Finds the trace of a square matrix
     * @return the sum of the diagonal values of a matrix
     */
    public double trace() {
        if (rows != columns) {
            throw new IllegalArgumentException("Tried to find the trace of a" +
                    " non-square matrix.");
        }

        double sum = 0;
        for (int i = 0; i < rows; i++) {
            sum += matrix[i][i];
        }

        return sum;
    }

    /**
     * Inverts the matrix
     * Code written by SanFoundry
     */
    public void invert() {
        double a[][] = matrix;
        int n = a.length;
        double x[][] = new double[n][n];
        double b[][] = new double[n][n];
        int index[] = new int[n];
        for (int i=0; i<n; ++i)
            b[i][i] = 1;

        // Transform the matrix into an upper triangle
        int o = index.length;
        double c[] = new double[o];

        // Initialize the index
        for (int i = 0; i < o; ++i)
            index[i] = i;

        // Find the rescaling factors, one from each row
        for (int i = 0; i < o; ++i) {
            double c1 = 0;
            for (int j = 0; j < o; ++j) {
                double c0 = Math.abs(a[i][j]);
                if (c0 > c1) c1 = c0;
            }

            c[i] = c1;
        }

        // Search the pivoting element from each column
        int l = 0;
        for (int j = 0; j < o - 1; ++j) {
            double pi1 = 0;
            for (int i = j; i < o; ++i) {
                double pi0 = Math.abs(a[index[i]][j]);
                pi0 /= c[index[i]];
                if (pi0 > pi1) {
                    pi1 = pi0;
                    l = i;
                }
            }

            // Interchange rows according to the pivoting order
            int itmp = index[j];
            index[j] = index[l];
            index[l] = itmp;
            for (int i = j + 1; i < o; ++i) {
                double pj = a[index[i]][j] / a[index[j]][j];

                // Record pivoting ratios below the diagonal
                a[index[i]][j] = pj;

                // Modify other elements accordingly
                for (int m = j + 1; m < o; ++m)
                    a[index[i]][m] -= pj * a[index[j]][m];
            }
        }

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

        matrix = a;
    }

    /**
     * Creates a copy of this Matrix
     * @return a copy of this MAtrix
     */
    public Matrix getCopy() {
        return new Matrix(matrix);
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
        return ret + "\n";
    }
}
