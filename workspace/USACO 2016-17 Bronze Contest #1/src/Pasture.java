import java.util.*;
import java.io.*;

public class Pasture {

	public Pasture() throws IOException {
		File text = new File("square.in");
		Scanner scanner = new Scanner(text);
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("square.out")));
		// Scanner scanner = new Scanner(System.in);
		String[] first = scanner.nextLine().split(" ");
		String[] second = scanner.nextLine().split(" ");
		int[] x = {Integer.parseInt(first[0]), Integer.parseInt(first[2]), Integer.parseInt(second[0]), Integer.parseInt(second[2])};
		int[] y = {Integer.parseInt(first[1]), Integer.parseInt(first[3]), Integer.parseInt(second[1]), Integer.parseInt(second[3])};
		int minHeight = Math.max(y[0], Math.max(y[1], Math.max(y[2], y[3]))) - Math.min(y[0], Math.min(y[1], Math.min(y[2], y[3])));
		int minWidth = Math.max(x[0], Math.max(x[1], Math.max(x[2], x[3]))) - Math.min(x[0], Math.min(x[1], Math.min(x[2], x[3])));
		out.println(Math.max(minHeight, minWidth) * Math.max(minHeight, minWidth));
		scanner.close();
		out.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Pasture();
	}

}
