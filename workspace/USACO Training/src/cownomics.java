import java.io.*;
import java.util.*;

public class cownomics {

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(new File("cownomics.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cownomics.out")));
		// Scanner scanner = new Scanner(System.in);
		String[] input = scanner.nextLine().split(" ");
		int numCows = Integer.parseInt(input[0]), genomeLength = Integer.parseInt(input[1]);
		String[] genomes = new String[numCows * 2];
		for (int i = 0; i < genomes.length; i++) genomes[i] = scanner.nextLine();
		int count = 0;
		for (int i = 0; i < genomeLength; i++) {
			StringBuilder spotted = new StringBuilder(), plain = new StringBuilder();
			for (int j = 0; j < genomes.length / 2; j++) spotted.append(genomes[j].charAt(i));
			for (int j = genomes.length / 2; j < genomes.length; j++) plain.append(genomes[j].charAt(i));
			boolean containsSameLetter = false;
			for (int j = 0; j < spotted.length(); j++) {
				if (plain.toString().contains(Character.toString(spotted.charAt(j)))) {
					containsSameLetter = true;
					break;
				}
			} if (!containsSameLetter) count++;
		} out.println(count);
		scanner.close();
		out.close();
	}
	
}
