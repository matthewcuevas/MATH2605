import com.sun.prism.shader.AlphaTextureDifference_Color_AlphaTest_Loader;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        double[] AData = {1, 2, 1, -4, 7, 1, -1, -2, -1};
        double[] uData = {1, 1, 1};
        double[] wData = {1, 0, 0};

        Matrix A = new Matrix(3, 3, AData);
        Vector u = new Vector(uData);
        Vector w = new Vector(uData);

        System.out.println(SolveUtil.power_method(A, u, w, (float) 0.001, 2)[0]);
    }
}
