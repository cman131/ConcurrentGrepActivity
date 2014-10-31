import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Grepper implements Callable<Found> {

    private String fileName;
    private Pattern pattern;

    public Grepper(String fileName, Pattern pattern) {
        this.fileName = fileName;
        this.pattern = pattern;
    }

    public Found call() {
        ArrayList<String> results = new ArrayList<String>();
        Matcher matcher;
        File file = new File(fileName);
        Scanner sc = null;
        try {
            int lineCount = 1;
            sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                matcher = this.pattern.matcher(line);
                if (matcher.find()) {
                    results.add("Line " + lineCount + ": " + line);
                }
                lineCount++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            // Sorry, just that if there is an exception, this would never have been closed.
            if (sc != null) {
                sc.close();
            }
        }
        return new Found(fileName, results);
    }
}
