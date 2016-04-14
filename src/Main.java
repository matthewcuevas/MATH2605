import com.sun.prism.shader.AlphaTextureDifference_Color_AlphaTest_Loader;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        double[] AData = {3, 0, 0, 0, 0, 3, 0, 0, 0, 0, 3, 0, 0, 0, 0, 3};
        double[] uData = {1, 1, 1, 1};
        double[] wData = {4, 3, -1, 3};

        Matrix A = new Matrix(4, 4, AData);
        Vector u = new Vector(uData);
        Vector w = new Vector(uData);

        System.out.println(SolveUtil.power_method(A, u, w, (float) 0.001, 100)[0]);
    }
}
