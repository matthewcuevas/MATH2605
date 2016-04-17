import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by matthew on 4/13/16.
 */
public class IO {

    public static Matrix readFileMatrix(String filename) throws FileNotFoundException, IOException {
        BufferedReader in = null;
        int rows = 0;
        int col = 0;
        Matrix retur = null;
        try {
            int row = 0;
            int i = 0;
            in = new BufferedReader(new FileReader(filename));
            String line = in.readLine();
            while(line != null) {
                if (i == 0) {
                    String[] column = line.split(" ");
                    col = column.length;
                    rows = col;
                    // rows = col - 1; for a non symmetrical matrix;
                    retur = new Matrix(rows, col);
                } else {
                    String[] tokens = line.split(" ");
                    for (int j = 0; j < col; j++) {
                        retur.set(row, j, Double.parseDouble(tokens[j]));
                    }
                    row++;
                }
                if (i != 0) {
                    line = in.readLine();
                }
                i++;
            }
        } catch (Exception ex) {
            System.out.println("The code throws an exception"); // nice
            System.out.println(ex.getMessage());
        } finally {
            if (in!=null) in.close();
        }
        return retur;
    }

    public double[][] readFileNonSymmetricalMatrix(String filename) throws FileNotFoundException, IOException {
        BufferedReader in = null;
        int rows = 0;
        int columns = 0;
        double [][] matrix = null;
        try {
            int lineNum = 0;
            int row = 0, i = 0;
            in = new BufferedReader(new FileReader(filename));
            String line = in.readLine();
            while(line != null) {
                if (i == 0) {
                    String[] column = line.split(" ");
                    columns = column.length;
                    rows = columns - 1;
                    matrix = new double[rows][columns];
                } else {
                    String [] tokens = line.split(" ");
                    for (int j = 0; j < columns; j++) {
                        matrix[row][j] = Double.parseDouble(tokens[j]);
                    }
                    row++;
                }
                if (i != 0) {
                    line = in.readLine();
                }
                i++;
            }
        } catch (Exception ex) {
            System.out.println("The code throws an exception");
            System.out.println(ex.getMessage());
        } finally {
            if (in!=null) in.close();
        }
        return matrix;
    }
}
