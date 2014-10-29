import java.util.ArrayList;

/**
 * Created by kocsen on 10/29/14.
 * <p/>
 * Class used to encapsulate the results of the Grepper
 */
public class Found {
    private final String filename;
    private ArrayList<String> matchingLines;


    public Found(String filename, ArrayList<String> matchingLines) {
        this.filename = filename;
        this.matchingLines = matchingLines;
    }

}
