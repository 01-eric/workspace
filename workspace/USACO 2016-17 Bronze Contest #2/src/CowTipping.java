import java.io.*;
import java.util.*;

public class CowTipping {
	
	public static boolean[][] pasture;
	
	public static void main(String[] args) throws IOException {
		File text = new File("cowtip.in");
		Scanner scanner = new Scanner(text);
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowtip.out")));
		// Scanner scanner = new Scanner(System.in);
		int n = Integer.parseInt(scanner.nextLine());
		// boolean array: if tipped = true, if not tipped = false
		pasture = new boolean[n][n];
		for (int i = 0; i < pasture.length; i++) {
			String input = scanner.nextLine();
			for (int j = 0; j < pasture[0].length; j++) {
				pasture[i][j] = input.charAt(j) == '1';
			}
		} int count = 0;
		// will keep tipping as long as not all cows are upright
		while(!allFalse()) {
			// tip everything which surrounds the furthest cow
			int[] dimensions = maxTrue();
			tip(dimensions[0], dimensions[1]);
			count++;
		} out.println(count);
		scanner.close();
		out.close();
	}
	
	// this will tip every cow in the rectangle (0, 0) and (x, y)
	public static void tip(int height, int width) {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				pasture[i][j] = !pasture[i][j];
			} 
		}
	} 
	
	// will return true if all values are false (meaning all cows are upright)
	public static boolean allFalse() {
		boolean hasTrue = false;
		for (int i = 0; i < pasture.length; i++) {
			for (int j = 0; j < pasture[0].length; j++) {
				hasTrue = pasture[i][j] || hasTrue;
			} 
		} return !hasTrue;
	}
	
	/*
	 * this method returns the (x, y) position of the furthest cow away which is tipped.
	 * for example: given a 3x3 pasture
	 * 101
	 * 000
	 * 010
	 * it will return the furthest one down at 3 down, 2 across.
	 */
	public static int[] maxTrue() {
		int[] array = {0, 0};
		for (int i = pasture.length - 1; i >= 0; i--) {
			for (int j = pasture[0].length - 1; j >= 0; j--) {
				if (pasture[i][j]) {
					array[0] = i + 1;
					array[1] = j + 1;
					return array;
				}
			}
		} return array;
	}
}
