import java.io.*;
import java.util.*;

public class Crosswalk {
	
	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(new File("maxcross.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("maxcross.out")));
		// Scanner scanner = new Scanner(System.in);
		boolean[] crosswalks = new boolean[scanner.nextInt()];
		int groupSize = scanner.nextInt(), numBroken = scanner.nextInt();
		for (int i = 0; i < numBroken; i++) crosswalks[scanner.nextInt() - 1] = true;
		scanner.close(); // input finished
		int minBroken = 0, prevBroken; // keep track of minimum broken in a window, and the number broken of previous window
		for (int i = 0; i < groupSize; i++) if (crosswalks[i]) minBroken++; // get num broken of first window
		prevBroken = minBroken;
		for (int i = 1; i + groupSize - 1 < crosswalks.length; i++) { // iterate through all other windows get minimum broken
			// the only changes in each window are the ends; if beginning of old window was broken subtract 1, if end of new window is broken add 1
			prevBroken = prevBroken - (crosswalks[i - 1] ? 1 : 0) + (crosswalks[i + groupSize - 1] ? 1 : 0);
			minBroken = Math.min(minBroken, prevBroken); // keep minimum broken window
		} out.println(minBroken);
		out.close();
	}

}

/*
10 6 5
9
2
10
5
1

|X|X| | |X| | | |X|X|
 _ _ _ _ _ _ 3
   _ _ _ _ _ _ 2
     _ _ _ _ _ _ 1
       _ _ _ _ _ _ 2
         _ _ _ _ _ _ 3
         
(DP algorithm)
Output: 1
*/