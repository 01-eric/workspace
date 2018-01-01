import java.awt.Point;
import java.io.*;
import java.util.*;

public class BalancedTeams {
	
	static int minDifference = Integer.MAX_VALUE;
	static int functionCalls = 0;
	
	public static void main(String[] args) throws IOException {
		// Scanner scanner = new Scanner(new File("bteams.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("bteams.out")));
		Scanner scanner = new Scanner(System.in);
		int[] skillLevels = new int[12];
		for (int i = 0; i < skillLevels.length; i++) skillLevels[i] = scanner.nextInt();
		int[] teamCounts = new int[skillLevels.length / 3]; // for each team keep track of how many assigned to each team don't exceed 3 per team
		getMinDifference(new int[]{}, teamCounts, skillLevels);
		System.out.println(minDifference);
		out.close();
	}
	
	static void getMinDifference(int[] assigned, int[] remaining, int[] skillLevels) {
		if (assigned.length == skillLevels.length) { // note all elements of remaining should be 3, if all teams higher by 3 doesn't change difference
			for (int i = 0; i < assigned.length; i++) remaining[assigned[i]] += skillLevels[i]; // use remaining as score sum of each team now
			int maxScore = Math.max(Math.max(remaining[0], remaining[1]), Math.max(remaining[2], remaining[3])), minScore = Math.min(Math.min(remaining[0], remaining[1]), Math.min(remaining[2], remaining[3]));
			minDifference = Math.min(minDifference, maxScore - minScore);
		} else {
			for (int i = 0; i < remaining.length; i++) {
				int[] copyOfAssigned = deepCopy(assigned, 0, assigned.length + 1), copyOfRemaining = deepCopy(remaining, 0, remaining.length);
				copyOfAssigned[copyOfAssigned.length - 1] = i; // i is team number
				if (++copyOfRemaining[i] <= 3) getMinDifference(copyOfAssigned, copyOfRemaining, skillLevels);
			}
		}
	}
	
	static int[] deepCopy(int[] array, int start, int end) { // start inclusive, end exclusive
		int[] newArray = new int[end - start];
		for (int i = start; i < array.length; i++) newArray[i - start] = array[i]; // don't go off end of array
		return newArray;
	}

}

/*
1
2
3
4
5
6
7
8
9
10
11
12

*/