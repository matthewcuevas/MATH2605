import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        double[] uData = {1, 2, 5};
        double[] vData = {-1, 3, 4};

        Vector u = new Vector(uData);
        Vector v = new Vector(vData);

        System.out.println(Vector.dot(u, v));
        System.out.println(Vector.project(u, v));
    }
}
