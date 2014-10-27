import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.regex.Pattern;


public class CGrep {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		Pattern pattern = Pattern.compile(args[0]);
		FileInputStream file = new FileInputStream(new File(args[1]));
		
	}

}
