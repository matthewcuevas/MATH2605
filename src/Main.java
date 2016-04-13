import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Matrix testMatrix = SolvingUtilities.readFile("data/" + args[0]);
        System.out.println("Original:");
        System.out.println(testMatrix);

        testMatrix = Matrix.invert(testMatrix);
        System.out.println("Inverted:");
        System.out.println(testMatrix);

//        System.out.println("Now to test LU factorization");
//        Matrix[] holdings = FactorizationUtilities.lu_fact(testMatrix);
//        System.out.println("L");
//        System.out.println(holdings[0]);
//        System.out.println("U");
//        System.out.println(holdings[1]);

    }
}
