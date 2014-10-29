import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.regex.*;


public class Grepper implements Callable<Found>{

	private String fileName;
	private Pattern pattern;

	public Grepper(String fileName, Pattern pattern) {

	}

	public Found call() {
		ArrayList<String> results = new ArrayList<String>();
		Matcher matcher;
		File file = new File(fileName);
		try {
			int lineCount = 1;
			Scanner sc = new Scanner(file);

			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				matcher = this.pattern.matcher(line);
				if ( matcher.find() ) {
					results.add( "Line " + lineCount + " " + line );
				}
				lineCount++;
			}
			sc.close();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return new Found(fileName, results);
	}
}
