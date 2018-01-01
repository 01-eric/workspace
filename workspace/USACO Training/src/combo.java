/*
 ID: eric.111
 LANG: JAVA
 TASK: combo
 */

import java.io.*;
import java.util.*;

/*+
 * 
 * Algorithm: generate all working combos, walk
 * through array of results and count distinct ones.
 * O(5^n) but n=3, where n is the number of numbers
 * in the combination.
 */

public class combo {
	
	static final int COMBO_LENGTH = 3;
	static int lockRange;
	static ArrayList<int[]> solutions = new ArrayList<int[]>(2 * (int)Math.pow(5, COMBO_LENGTH));
	
	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(new File("combo.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("combo.out")));
		// Scanner scanner = new Scanner(System.in);
		lockRange = scanner.nextInt();
		int[] lockCombo = new int[COMBO_LENGTH], masterCombo = new int[COMBO_LENGTH];
		for (int i = 0; i < lockCombo.length; i++) lockCombo[i] = scanner.nextInt();
		for (int i = 0; i < masterCombo.length; i++) masterCombo[i] = scanner.nextInt();
		
		genSolutions(lockCombo, new ArrayList<Integer>());
		genSolutions(masterCombo, new ArrayList<Integer>());
		/* after generating solutions, either sort array and walk through
		 * counting distinct values O(nk) with radix sort where k = 3 (array depth)
		 * or walk through without sorting and remove all instances of recurring
		 * values O(n^2). Either is ok with size 250 array */
		for (int i = 0; i < solutions.size(); i++) {
			// only need to remove future recurrences, keep one unique copy of each (current one)
			for (int j = i + 1; j < solutions.size(); j++) if (arrayCompare(solutions.get(i), solutions.get(j))) solutions.remove(j-- /*decrement if removed*/);
		} out.println(solutions.size());
		out.close();
	}
	
	static void genSolutions(int[] combination, ArrayList<Integer> output) {
		if (output.size() == combination.length) { // add 3 number solution to combo lock
			int[] solution = new int[output.size()];
			for (int i = solution.length - 1; i >= 0; i--) solution[i] = output.remove(i);
			// System.out.println(Arrays.toString(solution));
			solutions.add(solution);
		} else { // 5 function calls per level
			for (int i = -2; i <= 2; i++) {
				ArrayList<Integer>copyOfOutput = new ArrayList<Integer>(output); // must create new copy to output, avoid reference passing
				int num = (combination[copyOfOutput.size()] + i) % lockRange; // assign new number to combination, mod lockRange
				copyOfOutput.add(num < 1 ? lockRange + num : num); // numbers are from [1,lockRange] not [0,lockRange-1] so if num < 1 cycle num to the largest side
				genSolutions(combination, copyOfOutput); // call recursively using new copy of output
			} output = null; // when done with output (from higher level function call) set it to null for garbage collecting
		}
	}
	
	static boolean arrayCompare(int[] array1, int[] array2) {
		if (array1.length != array2.length) return false;
		for (int i = 0; i < array1.length; i++) if (array1[i] != array2[i]) return false;
		return true;
	}
	
	
}