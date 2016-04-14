import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        double[] ANums = {-1, -1, 1, 0, 1, 1, 1, 1, 0};
        double[] bNums = {-1, 2, 2};

        Matrix A = new Matrix(3, 3, ANums);
        Vector b = new Vector(bNums);

        Matrix augmented = Matrix.toAugmented(A, b);
        System.out.println(SolveUtil.solve_LU(augmented));
    }
}
