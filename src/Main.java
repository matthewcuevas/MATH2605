import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        double[] ANums = {-1, -1, 1, 0, 1, 1, 1, 1, 0};
        double[] bNums = {-1, 2, 2};

        Matrix A = new Matrix(3, 3, ANums);
        Vector b = new Vector(bNums);

        System.out.println("x: " + SolveUtil.solve_LU(A, b));

        double[] QNums = {Math.pow(2, -.5), 0, -Math.pow(2, -.5),
                0, 1, 0, -Math.pow(2, -.5), 0, -Math.pow(2, -.5)};
        double[] RNums = {-Math.pow(2, .5), -Math.pow(2, .5),
                Math.pow(2, -.5), 0, 1, 1, 0, 0, -Math.pow(2, -.5)};

        Matrix Q = new Matrix(3, 3, QNums);
        Matrix R = new Matrix(3, 3, RNums);

        System.out.println("Q:" + Q);
        System.out.println("R:" + R);

        Matrix QTranspose = Q.transpose();

        Vector y = Matrix.toVector(Matrix.multiply(QTranspose, b));
        System.out.println("y:" + y);
        System.out.println("x:" + SolveUtil.URTriangularSolve(R, y));
    }
}
