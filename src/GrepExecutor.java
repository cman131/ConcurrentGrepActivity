import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.regex.Pattern;

/**
 * Created by Geoff on 10/31/14.
 * <p/>
 * The executor
 */
public class GrepExecutor {

    final ExecutorService executor;
    final ExecutorCompletionService<Found> completionService;
    final static int THREAD_POOL_SIZE = 3;
    final static int NUM_SEARCHES = 3;

    public GrepExecutor() {
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

    /**
     * Method called to start grepping the filenames with our Grepper!
     *
     * @param filenames
     * @param pattern
     */
    public void beginSearch(ArrayList<String> filenames, Pattern pattern) {

		/* FOR ADDING DATA TO BE QUERIED */

        for (String filename : filenames) {
            executor.submit(new Grepper(filename, pattern));  // This will start running immediately
        }


		/* FOR READING THE DATA FROM THE FUTURE */

		/* completionService.take() will wait until the next Found is completed
            which satisfied the requirement that we print the results as they
			are completed */
        // Loop through the futures in the list
        for (int i = 0; i < filenames.size(); i++) {
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
}
