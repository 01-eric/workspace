import java.util.*;

public class BuyHay {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int numSupply = scanner.nextInt(), required = scanner.nextInt();
		long[] dp = new long[55001];
		for (int i = 1; i < dp.length; i++) dp[i] = Integer.MAX_VALUE;
		for (int i = 0; i < numSupply; i++) {
			int amount = scanner.nextInt(), cost = scanner.nextInt();
			for (int j = 0; j < required; j++) {
				if (dp[j] != Integer.MAX_VALUE && dp[j] + cost < dp[j + amount])
				dp[j + amount] = dp[j] + cost;
			}
		} long minCost = Integer.MAX_VALUE;
		for (int i = required; i < dp.length; i++) minCost = Math.min(minCost, dp[i]);
		System.out.println(minCost);
	}
	
}

/*
2 15
3 2
5 3


*/