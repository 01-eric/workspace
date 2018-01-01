import java.io.*;
import java.util.Scanner;

public class HoofPaperScissors {

	public HoofPaperScissors() throws IOException {
		File text = new File("hps.in"); Scanner scanner = new Scanner(text);
		PrintWriter out = new PrintWriter(new BufferedWriter(new
		FileWriter("hps.out")));
		// Scanner scanner = new Scanner(System.in);
		int n = Integer.parseInt(scanner.nextLine());
		String[] FJ = new String[n];
		int[] hps = { 0, 0, 0 };
		for (int i = 0; i < n; i++) {
			FJ[i] = scanner.nextLine();
			if (FJ[i].equals("H")) {
				hps[0]++;
			} else if (FJ[i].equals("P")) {
				hps[1]++;
			} else {
				hps[2]++;
			}
		} int[] beforehps = { 0, 0, 0 };
		int[] afterhps = { 0, 0, 0 };
		int max = 0;
		for (int i = 0; i < n; i++) {
			int total = 0;
			if (FJ[i].equals("H")) {
				beforehps[0]++;
			} else if (FJ[i].equals("P")) {
				beforehps[1]++;
			} else {
				beforehps[2]++;
			} total += Math.max(beforehps[0], Math.max(beforehps[1], beforehps[2]));
			for (int j = 0; j < 3; j++) {
				afterhps[j] = hps[j] - beforehps[j];
			} total += Math.max(afterhps[0], Math.max(afterhps[1], afterhps[2]));
			if (total > max) {
				max = total;
			}
		} out.println(max);
		out.close();
		scanner.close();
	}

	public static void main(String[] args) throws IOException {
		new HoofPaperScissors();
	}
}
