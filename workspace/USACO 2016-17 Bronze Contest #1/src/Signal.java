import java.io.*;
import java.util.*;

public class Signal {

	public Signal() throws IOException {
		Scanner scanner = new Scanner(new File("cowsignal.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowsignal.out")));
		// Scanner scanner = new Scanner(System.in);
		String[] firstLine = scanner.nextLine().split(" ");
		int lineCount = Integer.parseInt(firstLine[0]);
		int multiplier = Integer.parseInt(firstLine[2]);
		String[] lines = new String[lineCount];
		for (int i = 0; i < lines.length; i++) {
			lines[i] = scanner.nextLine();
		} for (int i = 0; i < lines.length; i++) {
			for (int j = 0; j < multiplier; j++) {
				out.println(multiply(lines[i], multiplier));
			}
		} scanner.close();
		out.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Signal();
	}
	
	public String multiply(String str, int multiplier) {
		String result = "";
		for (int i = 0; i < str.length(); i++) {
			for (int j = 0; j < multiplier; j++) {
				result += str.substring(i, i + 1);
			}
		} return result;
	}

}
