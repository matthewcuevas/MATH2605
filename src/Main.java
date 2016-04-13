import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Test your methods here");

        //apparently some exceptions are thrown doing this. ill fix em later
        Matrix testMatrix = SolvingUtilities.readFile(args[0]);
        System.out.println("this is the beautiful matrix passed in");
        System.out.println(testMatrix);

    }
}
