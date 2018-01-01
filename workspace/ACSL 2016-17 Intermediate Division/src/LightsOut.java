import java.util.*;

public class LightsOut {

	public static void main(String[] args) {
		for (int runs = 0; runs < 5; runs++) {
			boolean[][] lit = new boolean[8][8];
			Scanner scanner = new Scanner(System.in);
			String[] input = scanner.nextLine().split(" ");
			for (int i = 1; i < 1 + Integer.parseInt(input[0]); i++) {
				int row = Integer.parseInt(input[i].substring(0, 1)) - 1;
				for (int j = 1; j < input[i].length(); j++) lit[row][Integer.parseInt(input[i].substring(j, j + 1)) - 1] = true;
			} for (int i = 2 + Integer.parseInt(input[0]); i < input.length; i++) {
				int[] coordinates = {Integer.parseInt(input[i].substring(0, 1)) - 1, Integer.parseInt(input[i].substring(1)) - 1};
				invert(lit, coordinates[0], coordinates[1]);
				invert(lit, coordinates[0] + 1, coordinates[1]);
				invert(lit, coordinates[0] + 2, coordinates[1]);
				invert(lit, coordinates[0] - 1, coordinates[1]);
				invert(lit, coordinates[0] - 2, coordinates[1]);
				invert(lit, coordinates[0], coordinates[1] + 1);
				invert(lit, coordinates[0], coordinates[1] + 2);
				invert(lit, coordinates[0], coordinates[1] - 1);
				invert(lit, coordinates[0], coordinates[1] - 2);
				invert(lit, coordinates[0] + 1, coordinates[1] - 1);
				invert(lit, coordinates[0] - 1, coordinates[1] + 1);
				invert(lit, coordinates[0] + 1, coordinates[1] + 1);
				invert(lit, coordinates[0] - 1, coordinates[1] - 1);
			} int count = 0;
			for (int i = 0; i < lit.length; i++) for (int j = 0; j < lit[0].length; j++) count += lit[i][j] ? 1 : 0;
			System.out.println(count);
			scanner.close();
		}
	}
	
	static void invert(boolean[][] array, int row, int column) {
		if (row < array.length && row >= 0 && column < array[0].length && column >= 0) array[row][column] = !array[row][column];
	}
	
}