/**
 * Created by Anush on 4/10/2016.
 */
public class Matrix {

    public double[][] matrix;
    public int rows;
    public int columns;

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
            throw new IllegalArgumentException("Tried to set value at row " + row
                    + " and column " + column + " of a " + rows + " by " +  columns
                    + "matrix.");
        }
        matrix[row][column] = num;
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
            throw new IllegalArgumentException("Tried to add one or more null " +
                    "matrices.");
        }
        if (m1.rows != m2.rows || m1.columns != m2.columns) {
            throw new IllegalArgumentException("Tried to add a " + m1.rows
                    + " by " + m1.columns + " matrix to a " + m2.rows
                    + " by " + m2.columns + " matrix.");
        }
        Matrix ret = new Matrix(m1.rows, m1.columns);
        for (int y = 0; y < ret.rows; y++) {
            for (int x = 0; x < ret.columns; x++) {
                ret.set(y, x, m1.get(y, x) + m2.get(y, x));
            }
        }
        return ret;
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
}
