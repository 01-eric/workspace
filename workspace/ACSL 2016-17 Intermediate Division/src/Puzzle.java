import java.util.*;

public class Puzzle {

	static boolean[][] grid;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String[] pieces = scanner.nextLine().split(", ");
		for (int i = 0; i < pieces.length; i++) {
			for (int j = 1; j < pieces.length - i; j++) {
				if (getArea(pieces[j - 1]) < getArea(pieces[j]) || getArea(pieces[j - 1]) == getArea(pieces[j]) && pieces[j - 1].charAt(0) < pieces[j].charAt(0)) {
					String temp = pieces[j];
					pieces[j] = pieces[j - 1];
					pieces[j - 1] = temp;
				}
			}
		} for (int runs = 0; runs < 10; runs++) {
			String[] dimensions = scanner.nextLine().split(", ");
			grid = new boolean[Integer.parseInt(dimensions[1])][Integer.parseInt(dimensions[0])];
			LinkedList<String> piecesCopy = new LinkedList<String>();
			for (String s: pieces) piecesCopy.add(s);
			
		}
	}
	
	static int getArea(String piece) {
		if (piece.length() == 2) return (piece.charAt(0) - 48) * (piece.charAt(1) - 48);
		else return (piece.charAt(0) - 48) * (piece.charAt(2) - 48) + piece.charAt(1) - piece.charAt(2);
	}
	
	static void printGrid() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				System.out.print((grid[i][j] ? "1" : "0") + " ");
			} System.out.println();
		}
	}
	
	static int[] getLowestSpace() {
		for (int i = grid.length - 1; i >= 0; i--) for (int j = 0; j < grid[0].length; j++) if (!grid[i][j]) return new int[]{i, j};
		return new int[]{-1, -1};
	}
	
	static boolean fit(String piece, int[] coordinates) {
		for (int i = coordinates[0]; i > coordinates[0] - piece.charAt(1) + 48; i--) {
			for (int j = coordinates[1]; j < coordinates[1] + piece.charAt(0) - 48; j++) {
				System.out.println(i + " " + j);
				if (i < 0 || j >= grid[0].length) return false;
				if (grid[i][j]) return false;
			}
		} return true;
	}
	
	static void place(String piece, int[] coordinates) {
		
	}

}

/*
12, 22, 23, 32, 43, 31, 44, 33, 443, 11
7, 4
6, 3
3, 2
3, 5
2, 2
4, 4
3, 3
5, 3
6, 2
8, 4

 */

