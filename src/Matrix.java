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

    public Matrix multiply(Matrix m1, Matrix m2) {
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
}
