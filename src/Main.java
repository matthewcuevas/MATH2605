import com.sun.prism.shader.AlphaTextureDifference_Color_AlphaTest_Loader;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        double[] AData = {2, 1, 1, 2};
        double[] bData = {1, 0};
        double[] uData = {1, 1};

        Matrix A = new Matrix(2, 2, AData);
        Vector b = new Vector(bData);
        Vector u = new Vector(uData);

        Matrix Ab = Matrix.toAugmented(A, b);
        System.out.println(SolveUtil.jacobi_iter(Ab, u, 10, 1000)[0]);
    }
}
