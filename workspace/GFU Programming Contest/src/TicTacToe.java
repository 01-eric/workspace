import java.util.*;

public class TicTacToe {
	
	static int[][] grid = new int[3][3];

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int runs = Integer.parseInt(scanner.nextLine());
		for (int i = 0; i < runs; i++) {
			int moves = Integer.parseInt(scanner.nextLine());
			for (int j = 0; j < moves; j++) {
				String[] rc = scanner.nextLine().split(" ");
				grid[Integer.parseInt(rc[0])][Integer.parseInt(rc[1])] = j % 2 == 0 ? 2 : 1;
			} for (int j = 0; j < grid.length; j++) {
				for (int k = 0; k < grid[0].length; k++) {
					if (grid[j][k] == 2) System.out.print('X');
					else if (grid[j][k] == 1) System.out.print('O');
					else System.out.print(" ");
					if (k != grid[0].length - 1) System.out.print("|");
				} if (j != grid.length - 1) System.out.println("\n-----");
			} if (win(2)) System.out.println("\nX wins!");
			else if (win(1)) System.out.println("\nO wins!");
			else if (moves == 9) System.out.println("\nTie game!");
			else System.out.println("\nIncomplete");
			for (int j = 0; j < grid.length; j++) for (int k = 0; k < grid[0].length; k++) grid[j][k] = 0;
		} scanner.close();
	}
	
	static boolean win(int player) {
        for (int rows = 0; rows < 3; rows++) if (grid[rows][0] == player && grid[rows][1] == player && grid[rows][2] == player) return true;
        for (int columns = 0; columns < 3; columns++) if (grid[0][columns] == player && grid[1][columns] == player && grid[2][columns] == player) return true;
        if ((grid[0][0] == player && grid[1][1] == player && grid[2][2] == player) || (grid[2][0] == player && grid[1][1] == player && grid[0][2] == player)) return true;
        return false;
    }
	
}
