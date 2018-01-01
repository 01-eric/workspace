import java.util.*;

public class _01_Knapsack {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int[][] items = new int[scanner.nextInt()][2]; // size, then value
		int[] dp = new int[scanner.nextInt() + 1]; // dp will go up to allowed capacity, w/ 1 extra space for capacity 0
		for (int i = 0; i < items.length; i++) items[i] = new int[] {scanner.nextInt(), scanner.nextInt()};
		scanner.close();
		
		for (int i = 0; i < items.length; i++) {
			int[] copydp = new int[dp.length];
			for (int j = 0; j < copydp.length; j++) copydp[j] = dp[j]; // make deep copy
			for (int j = items[i][0]/*all elements less than size of item are copied*/; j < dp.length; j++) dp[j] = Math.max(copydp[j], copydp[j - items[i][0]] + items[i][1]);
		} System.out.println(dp[dp.length - 1]);
	}

}

/*
4 10
4 15
3 5
7 28
5 19

*/
