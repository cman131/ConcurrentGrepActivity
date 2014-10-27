ConcurrentGrepActivity
======================

The Problem
For this activity your team will implement a version of concurrent grep via Callables, Futures and Executors. Your program will be invoked on the command line as follows:

java CGrep pattern [file . . .]

We suggest you familiarize yourself with the java.util.regex package, as you'll use this to do pattern matching. In particular, the pattern argument is an arbitrary regular expression conforming to the syntax recognized by the java.util.regex.Pattern class.

Each file (or the standard input if no files are given) will be scanned for occurrences of the pattern, using one task (i.e., a Callable) for each file. The Futures returned will wrap a Found object, where such objects contain a String with the name of the file that was searched and a List<String> with one entry in the list for each matching line. Each string in the list consists of the line number, a space, and the text of the line itself. The list must be ordered by location of the line in the file (i.e., first matching line at position 0, second matching line at position 1, etc.). The detailed interface to Found objects is up to you.

Note on Matching Lines

A matching line is one where the regular expression matches any part of the line. To match the entire line the anchor characters caret (^) and dollar ($) are used.

End Note

At the start of the program, the one task per file will be submitted to an ExecutorService with a fixed pool of 3 threads. As each task completes, the main program will use the Future object from the task to retrieve the Found object and then print the file name and the list of all matching lines. We use this approach, rather than having the tasks print matching lines directly, so we won't have intermixed output from several concurrent tasks.

When the matching lines for all files have been printed, the main thread ensures the ExecutorService has been properly terminated and then exits.

Hint: Consider using an ExecutorCompletionService decorating the fixed thread pool ExecutorService.

Implementation Notes
Obviously, then, the main method will be in a class CGrep contained in CGrep.java. You are free to create any other java files you need to solve the problem, but all classes must be in the default package. I will compile your source files using the command:

javac *.java

and then run the java command on CGrep with my test data files.
