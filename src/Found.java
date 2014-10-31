import java.util.ArrayList;

/**
 * Created by kocsen on 10/29/14.
 * <p/>
 * Class used to encapsulate the results of the Grepper
 */
public class Found {
    private final String filename;
    private ArrayList<String> matchingLines;


    /**
     * Data container for whatever the Grepper found in the file.
     *
     * @param filename      - The file name that the Grepper searched through
     * @param matchingLines - Array
     *                      Formatted: ["$line# $actualline"...]
     */
    public Found(String filename, ArrayList<String> matchingLines) {
        this.filename = filename;
        this.matchingLines = matchingLines;
    }

    public ArrayList<String> getMatchingLines() {
        return this.matchingLines;
    }
    
    @Override
    public String toString() {
    	// A nice and fancy print out of the results for another class to print
    	StringBuilder retString = new StringBuilder();
    	retString.append("File: "+filename+"\n");
    	retString.append("----------------\n");
    	retString.append("Matching Lines:\n");
    	
    	// Totally fancy list of matching lines
    	for(String str : matchingLines){
    		retString.append(str+"\n");
    	}
    	return retString.toString();
    }

}
