import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Matrix A = SolveUtil.readFile("data/2_A.dat");
        //Vector b = SolveUtil.readFile("data/2_b.dat");
        System.out.println(A);
        //System.out.println(b);
    }
}
