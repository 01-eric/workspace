import java.io.*;
import java.util.Scanner;

public class Blocks {

	public Blocks() throws IOException {
		Scanner scanner = new Scanner(new File("blocks.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("blocks.out")));
		// Scanner scanner = new Scanner(System.in);
		int n = Integer.parseInt(scanner.nextLine());
		String[][] words = new String[n][2];
		for (int i = 0; i < n; i++) {
			String[] input = scanner.nextLine().split(" ");
			words[i][0] = input[0];
			words[i][1] = input[1];
		} for (int i = 97; i < 123; i++) {
			out.println(maxLetter((char)i, words));
		} out.close();
		scanner.close();
	}

	public static void main(String[] args) throws IOException {
		new Blocks();
	}
	
	public int maxLetter(char ch, String[][] array) {
		int count = 0;
		for (int length = 0; length < array.length; length++) {
			count += Math.max(count(ch, array[length][0]), count(ch, array[length][1]));
		} return count;
	}
	
	public int count(char ch, String str) {
		int count = 0;
		for (int length = 0; length < str.length(); length++) {
			if (str.charAt(length) == ch) {
				count++;
			}
		} return count;
	}

}
