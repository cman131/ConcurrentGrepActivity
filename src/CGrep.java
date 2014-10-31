import java.io.Console;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;


/**
 * Main CGREP class for concurrent lock
 * /
 */

/* We don't need an entire class implementation, just seemed like a lot
 * of stuff to put in one main() method.
 */
public class CGrep {

    /**
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        //Check if no args plz
        if (args.length <= 0) {
            System.out.println("Please argumentize.");
            System.exit(1);
        }

        ArrayList<String> files = new ArrayList<String>();
        Pattern pattern = Pattern.compile(args[0]);

        if (args.length == 1) {
            String input;
            Console console = System.console();
            input = console.readLine("Enter input:");
        } else {
            files.addAll(Arrays.asList(args).subList(1, args.length));
        }


        // Create the Grep Executor
        GrepExecutor ge = new GrepExecutor();
        ge.beginSearch(files, pattern);

    }
}
