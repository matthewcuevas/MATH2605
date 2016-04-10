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

}
