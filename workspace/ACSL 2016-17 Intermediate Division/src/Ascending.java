/*
 * Eric Fan
 * Tech For Kids Club
 * ACSL Intermediate
 */

import java.util.*;

public class Ascending {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		for (int runs = 0; runs < 5; runs++) {
			String input = scanner.nextLine();
			String output = "" + Integer.parseInt(input.substring(1, 1 + Integer.parseInt(input.substring(0, 1)))); // Integer.parseInt to prevent duplicate zeros
			input = reverse(input.substring(1 + Integer.parseInt(input.substring(0, 1))));
			long min = Integer.parseInt(output);
			while (true) {
				long next = nextGreatest(min, input);
				if (next == -1) break;
				output += " " + next;
				input = input.substring(Long.toString(next).length());
				min = next;
			} System.out.println(output);
		} scanner.close();
	}
	
	public static String reverse(String str) {
		String reverse = "";
		for (int i = 0; i < str.length(); i++) reverse += str.charAt(str.length() - 1 - i);
		return reverse;
	}
	
	public static long nextGreatest(long lowerBound, String str) {
		for (int i = 1; i <= str.length(); i++) {
			if (Long.parseLong(str.substring(0, i)) > lowerBound) return Long.parseLong(str.substring(0, i));
		} return -1; // case there is no greater number which can be formed
	}
	
}
