import java.io.*;
import java.util.*;

public class HPS {
	public static void main(String[] args) throws IOException {
		File text = new File("hps.in");
		Scanner scanner = new Scanner(text);
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));
		// Scanner scanner = new Scanner(System.in);
		int ngames = Integer.parseInt(scanner.nextLine());
		String[][] games = new String[2][ngames];
		for (int i = 0; i < games[0].length; i++) {
			String[] input = scanner.nextLine().split(" ");
			games[0][i] = input[0];
			games[1][i] = input[1];
		} // print out whichever order of "1", "2", and "3" yields the most wins 
		out.println(
		Math.max(wins("1", "2", "3", games), 
		Math.max(wins("1", "3", "2", games), 
		Math.max(wins("2", "3", "1", games), 
		Math.max(wins("2", "1", "3", games), 
		Math.max(wins("3", "1", "2", games), wins("3", "2", "1", games)))))));
		out.close();
		scanner.close();
	}
	
	// will return the amount won by first cow with any String representing Hoof, Paper, or Scissors
	public static int wins(String s, String p, String h, String[][] games) {
		int wins = 0;
		for (int i = 0; i < games[0].length; i++) {
			if (games[0][i].equals(s) && games[1][i].equals(p) || games[0][i].equals(p) && games[1][i].equals(h) || games[0][i].equals(h) && games[1][i].equals(s)) {
				wins++;
			}
		} return wins;
	}
}
