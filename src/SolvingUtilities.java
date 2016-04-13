import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Methods for solving systems of equations and matrix equalities.
 */
public class SolvingUtilities {

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

    public static Matrix readFile(String filename) throws FileNotFoundException, IOException {
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
                    rows = col - 1;
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
            System.out.println("The code throws an exception");
            System.out.println(ex.getMessage());
        } finally {
            if (in!=null) in.close();
        }
        return retur;
    }
}
