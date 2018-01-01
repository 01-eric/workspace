/*
 ID: eric.111
 LANG: JAVA
 TASK: crypt1
 */

import java.io.*;
import java.util.*;

/*
 * Algorithm: brute force, no other solution.
 * O(n^5), but n < 10.
 */

public class crypt1 {
	
	// output
	static int count;
	
	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(new File("crypt1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("crypt1.out")));
		//Scanner scanner = new Scanner(System.in);
		int[] set = new int[Integer.parseInt(scanner.nextLine())];
		String[] input = scanner.nextLine().split(" ");
		scanner.close();
		
		for (int i = 0; i < input.length; i++) set[i] = Integer.parseInt(input[i]);
		randomize(set, 0, 0, 0);
		
		System.out.println(count);
		out.close();
	}
	
	// only way is to test all 5 values to go in the factors
	// and see if they produce numbers in the input set. O(n^5)
	// recursive method (nested for loops would be the same complexity)
	// 5 levels with n function calls per level
	static void randomize(int[] set, int recursionLevel, int factorLen3, int factorLen2) {
		if (recursionLevel == 5) { // do the multiplication problem if level 5 reached
			StringBuilder products = new StringBuilder();
			// add the intermediate steps products
			for (int i = 1; i < factorLen2; i *= 10) {
				int intermediateProd = factorLen3 * ((int)(factorLen2 / i) % 10);
				if (intermediateProd < 1000) products.append(Integer.toString(intermediateProd));
				else return; // return if over length cap
			} if (factorLen3 * factorLen2 < 10000) products.append(factorLen3 * factorLen2); // add final product
			else return; // return if over length cap
			for (int i = 0; i < products.length(); i++) if (Arrays.binarySearch(set, products.charAt(i) - 48) < 0) return; // return if numbers are outside of set
			count++; // if all tests are passed increment count
		} else { // otherwise append to factors
			for (int num: set) { // n function calls per level
				if (recursionLevel < 3) randomize(set, recursionLevel + 1, factorLen3 + (int)Math.pow(10, recursionLevel) * num, factorLen2);
				else randomize(set, recursionLevel + 1, factorLen3, factorLen2 + (int)Math.pow(10, recursionLevel - 3) * num);
			}
		}
	}
	
	
	

	
}