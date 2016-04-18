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

    public static Matrix readFileNonSymmetricalMatrix(String filename) throws FileNotFoundException, IOException {
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

        return new Matrix(matrix);
    }

    public static Matrix readFileVector(String filename, int length) throws FileNotFoundException, IOException {
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
                    columns = 1;
                    rows = length;
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

        return new Matrix(matrix);
    }

    /**
     * writes array of header (1 x 2) & double array to a csv file
     * @param headers 1 x 2 String array of headers
     * @param values values in table with two columns
     * @param filename filename. exclude .csv, no spaces/special characters
     * @throws Exception idk
     */
    public static void writeToNEColumnCSV(String[] headers, double[][] values, String filename) throws Exception {

        // append .csv to create valid csv filename
        filename = filename + ".csv";

        // create new file with title FILENAME.csv
        java.io.File dataCSV = new java.io.File(filename);

        // Create new writer to write to FILENAME.csv
        java.io.PrintWriter outfile = new java.io.PrintWriter(dataCSV);

        if (headers.length == 1) {
            outfile.write(headers[0]);
        } else if (headers.length > 1) {
            outfile.write(headers[0]);

            for (int i = 1; i < headers.length; i++) {
                outfile.write("," + headers[i]);
            }
        }

        // write data values from values to .csv file
        for (int i=0; i < values.length; i++) {
            outfile.write("\n" + values[i][0]);

            for (int j = 1; j < values[0].length; j++) {
                outfile.write("," + values[i][j]);
            }
        }
        outfile.close();
    }



    /**
     * writes array of header (1 x 2) & double array to a csv file
     *
     * @param headers  1 x 2 String array of headers
     * @param values   values in table with two columns
     * @param filename filename. exclude .csv, no spaces/special characters
     * @throws Exception idk
     */
    public static void writeToNColumnCSV(String[] headers, double[][] values, String filename) throws Exception {

        // append .csv to create valid csv filename
        filename = filename + ".csv";

        // create new file with title FILENAME.csv
        java.io.File dataCSV = new java.io.File(filename);

        // Create new writer to write to FILENAME.csv
        java.io.PrintWriter outfile = new java.io.PrintWriter(dataCSV);
        int n = headers.length;

        if (n == 1) {
            outfile.write(headers[0]);
        } else if (n > 1) {
            outfile.write(headers[0]);

            for (int i = 1; i < n; i++) {
                outfile.write(" " + headers[i]);
            }
        }

        // write data values from values to .csv file
        for (int i = 0; i < values.length; i++) {
            outfile.write("\n" + values[i][0]);

            for (int j = 1; j < values[0].length; j++) {
                outfile.write(" " + values[i][j]);
            }
        }
        outfile.close();
    }
}
