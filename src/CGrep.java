import java.io.Console;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.regex.Pattern;


/**
 * Main CGREP class for concurrent lock
 * /
 */

public class CGrep {

    /**
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        Pattern pattern = Pattern.compile(String.format("^%s$", args[0]));
        if (args.length == 1) {
            String input;
            Console console = System.console();
            input = console.readLine("Enter input:");
        } else {
            ArrayList<String> files = new ArrayList<String>();
            for (int c = 1; c < args.length; c++) {
                files.add(args[c]);
            }
        }
    }
}
