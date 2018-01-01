/*
 ID: eric.111
 LANG: JAVA
 TASK: palsquare
 */

import java.io.*;
import java.util.*;

public class palsquare {

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(new File("palsquare.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("palsquare.out")));
		// Scanner scanner = new Scanner(System.in);
		int base = Integer.parseInt(scanner.nextLine());
		for (int i = 1; i <= 300; i++) if (Integer.toString((int)Math.pow(i, 2), base).equals(new StringBuilder(Integer.toString((int)Math.pow(i, 2), base)).reverse().toString())) out.println(Integer.toString(i, base).toUpperCase() + " " + Integer.toString((int)Math.pow(i, 2), base).toUpperCase());
		scanner.close();
		out.close();
	}

}
