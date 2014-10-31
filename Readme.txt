Concurrent Grep Activity
Software Concurrency SWEN 342

John, Geoff, Conor, Kocsen


INSTALLATION

To run the concurrent grepper:
1. run "javac *.java" in the src folder where all the code lives
2. run "java CGrep pattern [file1, file2 ....]


Example run (test files provided):
>java CGrep potato file1.txt file2.txt

Notes:
The concurrent Grepper will search as many files as you please, when a match is found it will print
out the matching line in the file.
If nothing is found, there will be no output.
