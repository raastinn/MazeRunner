public class MazeRunner {
	Maze mazeToSolve;
	MazeStack<MazeLocation> path;
	FilePrinter fileWriter;

	public MazeRunner(Maze aMaze) {
		mazeToSolve = aMaze;
		path = new MazeStack<MazeLocation>();
		fileWriter = new FilePrinter();
	}

	/*
	 * Purpose: Determines whether there is a path from start to finish in this maze
	 * Parameters: MazeLocation start - starting coordinates of this maze
	 *			   MazeLocation finish - finish coordinates of this maze
	 * Returns: true if there is a path from start to finish
	 */
	public boolean solve(MazeLocation start, MazeLocation finish) {
		fileWriter.println("Searching maze from start: "+start+" to finish: "+finish);
		path.push(start);
		return findPath(start, finish);
	}

	/*
	 * Purpose: Recursively determines if there is a path from cur to finish
	 * Parameters: MazeLocation cur - current cordinates in this maze
	 *			   MazeLocation finish - goal coordinates of this maze
	 * Returns: true if there is a path from cur to finish
	 *
	 * NOTE: This method updates the Maze's mazeData array when locations
	 *       are visited to an 'o', and further updates locations to an 'x'
	 *       if it is determined they lead to dead ends. If the finish
	 *       location is found, the Stack named path should contain all of
	 *       loations visited from the start location to the finish.
	 */
	private boolean findPath(MazeLocation cur, MazeLocation finish) {
		int row = cur.getRow();
		int col = cur.getCol();
		mazeToSolve.setChar(row, col, 'o');
		fileWriter.println("\n"+mazeToSolve.toString());

		int finishRow = finish.getRow();
		int finishCol = finish.getCol();

		int maxRows = mazeToSolve.getRows();
		int maxCols = mazeToSolve.getCols();

		if (cur.equals(finish)) {
			return true;
		}
		else if (row+1 < maxRows && mazeToSolve.getChar(row+1, col) == ' ') { // GOING DOWN
			row += 1;
			mazeToSolve.setChar(row, col, 'o');
			MazeLocation temp = new MazeLocation(row, col);
			path.push(temp);
			return findPath(temp, finish);
		}
		else if (col+1 < maxCols && mazeToSolve.getChar(row, col+1) == ' ') { // GOING RIGHT
				col += 1;
				mazeToSolve.setChar(row, col, 'o');
				MazeLocation temp = new MazeLocation(row, col);
				path.push(temp);
				return findPath(temp, finish);
		}
		else if (row-1 >= 0 && mazeToSolve.getChar(row-1, col) == ' ') { // GOING UP
			row -= 1;
			mazeToSolve.setChar(row, col, 'o');
			MazeLocation temp = new MazeLocation(row, col);
			path.push(temp);
			return findPath(temp, finish);
		}
		else if (col-1 >= 0 && mazeToSolve.getChar(row, col-1) == ' ') { // GOING LEFT
				col -= 1;
				mazeToSolve.setChar(row, col, 'o');
				MazeLocation temp = new MazeLocation(row, col);
				path.push(temp);
				return findPath(temp, finish);
		} else if (row + 1 < maxRows && col + 1 < maxCols && row-1 >= 0 && col-1 >= 0){
			if (mazeToSolve.getChar(row+1, col) != ' ' && mazeToSolve.getChar(row, col+1) != ' '
						&& mazeToSolve.getChar(row-1, col) != ' ' && mazeToSolve.getChar(row, col-1) != ' ') {
							//System.out.println(mazeToSolve.getChar(row, col));
							mazeToSolve.setChar(row, col, 'x');
							path.pop();

			}
			return findPath(path.top(), finish);
		}
		else {
			return false;
		}

	}


	/*
	 * Purpose: Creates a string of maze locations found in the stack
	 * Parameters: None
	 * Returns: A String representation of maze locations
	 */
	public String getPathToSolution() {
		String details = "";
		while(!path.isEmpty()) {
			details = path.pop() + "\n" + details;
		}
		return details;
	}

	/*
	 * Purpose: Print the results of the maze run. Outputs the locations
	 *          visited on the path from start to finish if the maze is
	 *          solvable, or that no path was found if it is not.
	 * Parameters: boolean - whether or not the maze was solved
	 * Returns void - nothing
	 */
	public void printResults(boolean solved) {
		if (solved) {
			fileWriter.println("\n*** Maze Solved ***");
			fileWriter.println(getPathToSolution());
		} else {
			fileWriter.println("\n--- No path to solution found ---");
		}
		fileWriter.close();
	}
}
