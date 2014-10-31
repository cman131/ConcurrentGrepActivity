import java.io.Console;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;


/**
 * Main CGREP class for concurrent lock
 * /
 */

/* We don't need an entire class implementation, just seemed like a lot
 * of stuff to put in one main() method.
 */
public class CGrep {

	final ExecutorService executor;
	final ExecutorCompletionService<Found> completionService;
	final static int THREAD_POOL_SIZE =3;
	final static int NUM_SEARCHES = 3;

	public CGrep() {
		// Create our executor
		executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
		// Now wrap it in a nice cozy completion service
		completionService = new ExecutorCompletionService<Found>(executor);

		// We will want this so that the executor shuts down properly if the
		// application is closed while the threads are running.
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
		    @Override
		    public void run() {
		        executor.shutdown();
		        while (true) {
		            try {
		                // Wait for the service to exit
		                if (executor.awaitTermination(5, TimeUnit.SECONDS)) {
		                    break;
		                }
		            } catch (InterruptedException e) {
		            }
		        }
		        System.out.println("Closing now");
		    }
		}));
		
	}

	private void exampleOfUsage() {
		
		/* FOR ADDING DATA TO BE QUERIED */
		
		Grepper someGrep = new Grepper("", null);
		executor.submit(someGrep);  // This will start running immediately
		
		
		/* FOR READING THE DATA FROM THE FUTURE */
		
		/* completionService.take() will wait until the next Found is completed
			which satisfied the requirement that we print the results as they
			are completed */
		// Loop through the futures in the list
		for(int i = 0; i < NUM_SEARCHES; i++) {
			Future<Found> future = null;
			try {
				future = completionService.take();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}

			// Now we have our future, get the final resulting Found object
			Found result = null;
			try {
				result = future.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}

			// Print the results of this query
			if (result == null) {
				// If the results are null, print error message
				System.err.println("Whoops! Query number " + i + " came back null");
				return;
			}
			System.out.println(result);
			
		}
	}

	/**
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
        //Check if no args plz TODO

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
