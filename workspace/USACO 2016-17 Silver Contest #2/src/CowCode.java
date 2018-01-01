import java.io.*;
import java.util.*;

public class CowCode {
	public static void main(String[] args) throws IOException {
		File text = new File("cowcode.in");
		Scanner scanner = new Scanner(text);
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowcode.out")));
		// Scanner scanner = new Scanner(System.in);
		String[] input = scanner.nextLine().split(" ");
		long n = Long.parseLong(input[1]);
		long length = input[0].length();
		while (length < n) {
			length += length;
		} while (length > input[0].length()) {
			length = length / 2;
			if (n > length) {
				n = n - length - 1;
				if (n == 0) {
					n = length;
				}
			}
		} out.println(input[0].charAt((int)n - 1));
		out.close();
		scanner.close();
	}
}
