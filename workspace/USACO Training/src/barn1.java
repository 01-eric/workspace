/*
 ID: eric.111
 LANG: JAVA
 TASK: barn1
 */

/*
 * Problem: Given a number M maximum boards that can be purchased (of any length) in range [1, 50],
 * a number S number of stalls in range [1, 200], and a number C number of cows in range [1, S]
 * and then C integers of the stall numbers of the cows, output the least amount of stalls
 * that can be blocked while keeping all cows blocked given the board constraint.
 */

import java.util.*;
import java.io.*;

public class barn1 {
	
	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(new File("barn1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("barn1.out")));
		// Scanner scanner = new Scanner(System.in);
		String[] temp = scanner.nextLine().split(" ");
		int maxBoards = Integer.parseInt(temp[0]), numCows = Integer.parseInt(temp[2]), stallsBlocked = 0;
		int[] spaces = new int[numCows - 1], cows = new int[numCows];
		for (int i = 0; i < cows.length; i++) cows[i] = Integer.parseInt(scanner.nextLine());
		Arrays.sort(cows); // must be sorted to find correct spacing
		for (int i = 0; i < spaces.length; i++) spaces[i] = cows[i + 1] - cows[i];
		Arrays.sort(spaces); // sort spaces for easy access of largest spaces
		// scanner.close();
		Arrays.sort(spaces); // add all except the last M-1 spaces to the sum which should be all but the largest spaces
		for (int i = 0; i <= spaces.length - maxBoards; i++) stallsBlocked += spaces[i];
		stallsBlocked += Math.min(numCows, maxBoards); // replace the last spaces with intervals of 1 instead
		out.println(stallsBlocked);
		out.close();
	}

}

/*
50 200 10
18
69
195
38
73
28
6
172
53
99
 */