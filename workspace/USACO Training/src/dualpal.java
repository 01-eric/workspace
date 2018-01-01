/*
 ID: eric.111
 LANG: JAVA
 TASK: dualpal
 */

import java.io.*;
import java.util.*;

public class dualpal {

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(new File("dualpal.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dualpal.out")));
		// Scanner scanner = new Scanner(System.in);
		String[] input = scanner.nextLine().split(" ");
		int answerCount = Integer.parseInt(input[0]), start = Integer.parseInt(input[1]);
		int[] outputs = new int[answerCount];
		for (int testNum = start + 1; answerCount > 0; testNum++) {
			int palindromeCount = 0;
			for (int base = 2; base <= 10; base++) {
				if (Integer.toString(testNum, base).equals(new StringBuilder(Integer.toString(testNum, base)).reverse().toString())) {
					if (++palindromeCount == 2) {
						outputs[outputs.length - answerCount--] = testNum;
						break;
					}
				}
			}
		} for (int output: outputs) out.println(output);
		scanner.close();
		out.close();
	}

}
