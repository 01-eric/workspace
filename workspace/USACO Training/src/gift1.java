/*
 ID: eric.111
 LANG: JAVA
 TASK: gift1
 */

import java.io.*;
import java.util.*;

public class gift1 {
	
	static String[] names;
	
	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(new File("gift1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("gift1.out")));
		// Scanner scanner = new Scanner(System.in);
		int np = Integer.parseInt(scanner.nextLine());
		names = new String[np];
		for (int i = 0; i < names.length; i++) {
			names[i] = scanner.nextLine();
		} int[] money = new int[names.length];
		for (int i = 0; i < names.length; i++) {
			int giver = index(scanner.nextLine());
			String[] input = scanner.nextLine().split(" ");
			if (Integer.parseInt(input[1]) != 0) { 
				int amount = Integer.parseInt(input[0]) / Integer.parseInt(input[1]);
				for (int j = 0; j < Integer.parseInt(input[1]); j++) {
					int recipient = index(scanner.nextLine());
					money[recipient] += amount;
				} money[giver] -= amount * Integer.parseInt(input[1]);
			} 
		} for (int i = 0; i < names.length; i++) {
			out.println(names[i] + " " + money[i]);
		} scanner.close();
		out.close();
	}
	
	public static int index(String name) {
		for (int i = 0; i < names.length; i++) {
			if (name.equals(names[i])) {
				return i;
			}
		} return -1;
	}
	
}
