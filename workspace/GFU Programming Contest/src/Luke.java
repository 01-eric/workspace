/* import java.util.*;

public class Luke {

	Scanner in = new Scanner(System.in);
	String[][] grid;
	int n;
	int y;
	String[] outputs;
	final String HYPERSPACE;
	final String OBSTRUCTION;
	final String LUKE;
	final String INTERSECTION;
	final String PASSED;
	
	public Luke () {
		HYPERSPACE = ".";
		OBSTRUCTION = "X";
		LUKE = "L";
		INTERSECTION = "I";
		PASSED = "P";
		n = Integer.parseInt(in.nextLine());
		for (int i = 0; i < n; i++) {
			grid[i] = in.nextLine().split("");
		}
		y = Integer.parseInt(in.nextLine());
		outputs = new String[y];
		int count = 0;
		for (int i = 0; i < y; i++) {
			String[] input = in.nextLine().split(" ");
			int r = Integer.parseInt(input[0]);
			int c = Integer.parseInt(input[1]);
			if (checkPath(r, c)) {
				outputs[count] = "yes";
			}
			else {
				outputs[count] = "no";
			}
			count++;
		}
	}
	
	public static void main(String[] args) {
		new Luke();
	}

	public boolean checkPath(int r, int c) {
		boolean isLuke = false;
		if (isIntersection(r, c)) {
			grid[r][c] = INTERSECTION;
		}
		if (r + 1 < n && grid[r + 1][c].equals(LUKE)) {
			isLuke = true;
		}
		if (r - 1 > -1 && grid[r - 1][c].equals(LUKE)) {
			isLuke = true;
		}
		if (c + 1 < n && grid[r][c + 1].equals(LUKE)) {
			isLuke = true;
		}
		if (c - 1 > -1 && grid[r][c - 1].equals(LUKE)) {
			isLuke = true;
		}
		if (r + 1 < n && grid[r + 1][c].equals(HYPERSPACE)) {
			checkPath(r + 1, c);
		}
		if (r - 1 > -1 && grid[r - 1][c].equals(HYPERSPACE)) {
			checkPath(r - 1, c);
		}
		if (c + 1 < n && grid[r][c + 1].equals(HYPERSPACE)) {
			count++;
		}
		if (c - 1 > -1 && grid[r][c - 1].equals(HYPERSPACE)) {
			count++;
		}
		return isLuke;
	}
	
	public boolean isIntersection(int r, int c) {
		int count = 0;
		if (r + 1 < n && grid[r + 1][c].equals(HYPERSPACE)) {
			count++;
		}
		if (r - 1 > -1 && grid[r - 1][c].equals(HYPERSPACE)) {
			count++;
		}
		if (c + 1 < n && grid[r][c + 1].equals(HYPERSPACE)) {
			count++;
		}
		if (c - 1 > -1 && grid[r][c - 1].equals(HYPERSPACE)) {
			count++;
		}
		if (count > 1) {
			return true;
		}
		return false;
	}
} */
