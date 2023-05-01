import java.util.Scanner;
import java.util.NoSuchElementException;
import java.io.File;
import java.io.FileNotFoundException;

public class Tester {

	private static int testPassCount = 0;
	private static int testCount = 0;

	public static void main(String[] args) {
		testStackOperations();
		testStackIsGeneric();
		testExceptions();
		testMazeRunner(args);

		System.out.println("Passed " + testPassCount + " / " + testCount + " tests");
	}

	public static void testStackOperations() {
		System.out.println("Stack Operations Tests:");
		MazeStack<Integer> testStack = new MazeStack<Integer>();
		int result = 0;

		displayResults(testStack.isEmpty(), "stack initially empty");

		testStack.push(2);
		testStack.push(4);
		result = testStack.top();
		displayResults(!testStack.isEmpty(), "stack no longer empty");
		displayResults(result==4, "top works after initial push");

		//TODO: add more tests here
		MazeStack<String> s1 = new MazeStack<String>();

		s1.push("First");
		System.out.println(s1.top());
		s1.push("Second");
		System.out.println(s1.top());
		s1.push("Third");
		System.out.println(s1.top());
		s1.push("Top");
		System.out.println(s1.top());


		System.out.println();
	}

	public static void testStackIsGeneric() {
		System.out.println("Stack Generics Tests:");
		MazeStack<Integer> s1 = new MazeStack<Integer>();
		MazeStack<String> s2 = new MazeStack<String>();
		MazeStack<Double> s3 = new MazeStack<Double>();

		int result1;
		String result2;
		double result3;

		s1.push(3);
		s1.push(8);
		s1.push(4);
		s2.push("CSC");
		s2.push("ENGR");
		s3.push(5.5);
		s3.push(9.1);

		result1 = s1.pop();
		result1 = s1.pop();
		result2 = s2.pop();
		result3 = s3.pop();

		displayResults(result1==8, "Integer Stack");
		displayResults(result2.equals("ENGR"), "String Stack");
		displayResults(result3==9.1, "Double Stack");

		result1 = s1.top();
		result2 = s2.top();
		result3 = s3.top();

		displayResults(result1==3, "Integer Stack");
		displayResults(result2.equals("CSC"), "String Stack");
		displayResults(result3==5.5, "Double Stack");

		//TODO: add more tests here

		MazeStack<Integer> s4 = new MazeStack<Integer>();

		int result4;

		s4.push(3);
		s4.push(8);
		s4.push(5);
		s4.push(9);

		result4 = s4.pop();
		displayResults(result4==9, "Integer Stack");
		//System.out.println(s4.top());
		result4 = s4.pop();
		displayResults(result4==5, "Integer Stack");
		//System.out.println(s4.top());
		result4 = s4.pop();
		displayResults(result4==8, "Integer Stack");
		//System.out.println(s4.top());

		MazeStack<Integer> s5 = new MazeStack<Integer>();

		s5.push(4);
		s5.push(9);
		s5.push(8);
		s5.push(13);

		s5.popAll();
		displayResults(s5.isEmpty(), "popAll");

// 		System.out.println(s5.top());	// THIS LINE DOESNT WORK


		System.out.println();
	}

	public static void testExceptions() {
		System.out.println("Stack Exception Tests:");
		MazeStack<Integer> s1 = new MazeStack<Integer>();

		try {
			s1.pop();
			displayResults(false, "popping from empty stack should throw exception");
		} catch (EmptyStackException e) {
			displayResults(true, "popping from empty stack should throw StackEmptyException");
		} catch (Exception e) {
			displayResults(false, "popping from empty stack should throw exception");
		}



		try {
			s1.top();
			displayResults(false, "trying to get top from empty stack should throw exception");
		} catch (EmptyStackException e) {
			displayResults(true, "trying to get top from empty stack should throw StackEmptyException");
		} catch (Exception e) {
			displayResults(false, "trying to get top from empty stack should throw exception");
		}

		//TODO: add more tests here

		System.out.println();
	}

	public static void testMazeRunner(String[] args) {
		if (args.length < 1) {
			displayResults(false, "testing MazeRunner");
            System.err.println("You must specify a maze file");
			System.err.println("Usage: java A5Tester <mazefile>\n");
			return;
        }

		System.out.println("testing MazeRunner with "+args[0]);
		System.out.println("Results are in A5-output.txt");
		Maze maze = initialize(args[0]);
		MazeRunner mr = new MazeRunner(maze);
		boolean solved = mr.solve(maze.getStart(), maze.getFinish());
		mr.printResults(solved);
	}

	/*
	 * Purpose: Creates a maze by reading the contents of an input file
	 * Parameters: String - name of the input file containing maze data
	 * Returns: Maze - the maze created from input file data
	 */
	public static Maze initialize(String data) {
        try {
            Scanner infileScanner = new Scanner(new File(data));

			int rows = infileScanner.nextInt();
			int columns = infileScanner.nextInt();
			int startRow = infileScanner.nextInt();
			int startColumn = infileScanner.nextInt();
			int finishRow = infileScanner.nextInt();
			int finishColumn = infileScanner.nextInt();
			infileScanner.nextLine();

			char[][] mazeData = new char[rows][columns];

			for (int row = 0; row < rows; row++) {
				String line = infileScanner.nextLine();
				for (int col = 0; col < columns; col++) {
					mazeData[row][col] = line.charAt(col);
				}
			}
			return new Maze(mazeData, new MazeLocation(startRow, startColumn), new MazeLocation(finishRow, finishColumn));
		} catch (FileNotFoundException ex) {
			System.out.println("Error scanning file "+data);
			System.exit(2);
		} catch(NoSuchElementException ex) {
			System.out.println("Maze data file is not formatted correctly, should be:");
			System.out.println("<num rows> <num columns>");
			System.out.println("<start row> <start column>");
			System.out.println("<finish row> <finish column>");
			System.out.println("<Maze data>");
			System.out.println("Example:");
			System.out.println("7 8");
			System.out.println("0 1");
			System.out.println("6 6");
			System.out.println("H HHHHHH");
			System.out.println("H    H H");
			System.out.println("HHHH H H");
			System.out.println("H      H");
			System.out.println("H HHHHHH");
			System.out.println("H      H");
			System.out.println("HHHHHH H");
			System.exit(3);
		}
		return null;
	}

	public static void displayResults (boolean passed, String testName) {
       /* There is some magic going on here getting the line number
        * Borrowed from:
        * http://blog.taragana.com/index.php/archive/core-java-how-to-get-java-source-code-line-number-file-name-in-code/
        */

        testCount++;
        if (passed)
        {
            System.out.println ("Passed test: " + testName);
            testPassCount++;
        }
        else
        {
            System.out.println ("Failed test: " + testName + " at line "
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

}
