import java.util.*;

public class PieTreasure {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int rows = scanner.nextInt(), columns = scanner.nextInt();
		int[][] grid = new int[rows][columns];
		for (int i = 0; i < rows; i++) for (int j = 0; j < columns; j++) grid[i][j] = scanner.nextInt();
		scanner.close();
		
		int numToTruncate = 0, currentRow = rows;
		while (--currentRow >= 0) {
			for (int i = 0; i < numToTruncate; i++) grid[currentRow][columns - 1 - i] = 0;
			numToTruncate++;
		} System.out.println(Arrays.toString(grid[0]));
		
		
		int totalGold = grid[0][0], prevRow = 0;
		for (int j = 1; j < columns; j++) {
			int maxGold = 0, maxGoldRow = -1;
			for (int i = -1; i <= 1; i++) {
				if (prevRow + i >= 0 && prevRow + i < rows && grid[prevRow + i][j] > maxGold) {
					maxGold = grid[prevRow + i][j];
					maxGoldRow = prevRow + i;
				}
			} totalGold += grid[maxGoldRow][j];
			prevRow = maxGoldRow;
		} System.out.println(totalGold);
	}

}

/*
3 7
6 5 3 7 9 2 7
2 4 3 5 6 8 6
4 9 9 9 1 5 8

*/