import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Test your methods here");

        Matrix testMatrix = SolvingUtilities.readFile("src/" + args[0]);
        System.out.println("this is the beautiful matrix passed in");
        System.out.println(testMatrix);

//        System.out.println("Now to test LU factorization");
//        Matrix[] holdings = FactorizationUtilities.lu_fact(testMatrix);
//        System.out.println("L");
//        System.out.println(holdings[0]);
//        System.out.println("U");
//        System.out.println(holdings[1]);

    }
}
