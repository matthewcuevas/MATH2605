import java.util.ArrayList;

/**
 * Created by Anush on 4/12/2016.
 */
public class QR_fact {
    Matrix matrix;
    ArrayList<Matrix> Givens;
    private Matrix Q;
    private Matrix R;
    public static double error;

    QR_fact(Matrix matrix) {
        this.matrix = matrix;
        Givens = new ArrayList<>(10);
        R = findR(this.matrix);
        Q = findQ();
        this.error = findError();
    }

    public Matrix getQ() {
        return Q;
    }

    public Matrix getR() {
        return R;
    }

    public Matrix findR(Matrix matrix) {
        double r;
        double c;
        double s;

        for(int j = 0; j < matrix.getColumns(); j++) {
            int pivot = j;
            for(int i = pivot + 1; i < matrix.getRows(); i++) {
                double b = matrix.get(i, j);
                double a = matrix.get(pivot, pivot);
                r = Math.sqrt(b * b + a * a);
                c = a / r;
                s = (-1. * b) / r;

                double[][] newG = new double[matrix.getRows()][matrix.getColumns()];
                for (int k = 0; k < matrix.getColumns(); k++) {
                    newG[k][k] = 1.;
                }
                newG[pivot][pivot] = c;
                newG[i][i] = c;
                newG[j][i] = -1. * s;
                if (i > j) {
                    newG[i][j] = s;
                } else {
                    newG[j][i] *= -1;
                    newG[i][j] = -1 * s;
                }

                Matrix newGivens = new Matrix(newG);
                Givens.add(newGivens.transpose());

                matrix = newGivens.multiply(newGivens, matrix);

            }
        }
        return matrix;
    }

    public Matrix findQ() {
        Matrix Q = Givens.get(0);
        for(int i = 0; i < Givens.size(); i++) {
            if(i == 0) {
                Q = Givens.get(i);
            }
            if(i + 1 < Givens.size()) {
                Q = Q.multiply(Q, Givens.get(i + 1));
            }

        }

        return Q;
    }

    public double findError() {
        error = Q.multiply(Q,R).minus(matrix).getNorm();
        return error;
    }

    public double getError() {
        return this.error;
    }
}
