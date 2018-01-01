import java.util.*;
import java.io.*;

public class BalancedPhoto {
	
	static int[] heights;
	
	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(new File("bphoto.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("bphoto.out")));
		// Scanner scanner = new Scanner(System.in);
		int n = Integer.parseInt(scanner.nextLine());
		heights = new int[n];
		for (int i = 0; i < heights.length; i++) {
			heights[i] = Integer.parseInt(scanner.nextLine());
		} int count = 0;
		for (int i = 0; i < heights.length; i++) {
			count += isUnbalanced(i) ? 1 : 0;
		} out.println(count);
		scanner.close();
		out.close();
	}
	
	public static boolean isUnbalanced(int index) {
		int l = 0;
		int r = 0;
		for (int i = 0; i < index; i++) {
			l += heights[i] > heights[index] ? 1 : 0;
		} for (int i = index + 1; i < heights.length; i++) {
			r += heights[i] > heights[index] ? 1 : 0;
		} return  Math.max(l, r) > 2 * Math.min(l, r);
	}
	
}
