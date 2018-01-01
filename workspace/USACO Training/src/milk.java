/*
 ID: eric.111
 LANG: JAVA
 TASK: milk
 */

import java.io.*;
import java.util.*;

/*
 * Algorithm: Greedy, choose from cheapest
 * milk prices until milk quota is filled.
 * O(n) algorithm if n is number of farmers,
 * O(n + k) sorting and O(n) walkthrough of array
 * where k < 1000 and n < 5000
 */

public class milk {
	
	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(new File("milk.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk.out")));
		// Scanner scanner = new Scanner(System.in);
		String[] temp = scanner.nextLine().split(" ");
		int totalMilk = Integer.parseInt(temp[0]), numFarmers = Integer.parseInt(temp[1]), minPrice = Integer.MAX_VALUE, maxPrice = Integer.MIN_VALUE, totalPrice = 0;
		int[][] farmers = new int[numFarmers][2];
		for (int i = 0; i < farmers.length; i++) {
			String[] input = scanner.nextLine().split(" ");
			farmers[i][0] = Integer.parseInt(input[0]);
			farmers[i][1] = Integer.parseInt(input[1]);
			maxPrice = Math.max(maxPrice, farmers[i][0]);
			minPrice = Math.min(minPrice, farmers[i][0]);
		} scanner.close();
		
		farmers = twoDimCountingSort(minPrice, maxPrice, farmers);
		for (int i = 0; i < farmers.length && totalMilk > 0; i++) {
			totalPrice += farmers[i][0] * Math.min(farmers[i][1], totalMilk);
			totalMilk -= farmers[i][1];
		}
		
		/*System.*/out.println(totalPrice);
		out.close();
	}
	
	static int[][] twoDimCountingSort(int min, int max, int[][] array) {
		if (array.length == 0) return array;
		int[] count = new int[max - min + 1];
		int[][] output = new int[array.length][array[0].length];
		for (int i = 0; i < array.length; i++) count[array[i][0] - min]++;
		for (int i = 1; i < count.length; i++) count[i] += count[i - 1];
		for (int i = array.length - 1; i >= 0; i--) output[count[array[i][0] - min]-- - 1] = array[i];
		return output;
	}

}