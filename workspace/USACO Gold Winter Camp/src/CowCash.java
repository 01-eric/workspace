import java.util.*;

public class CowCash {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int[] coinValues = new int[scanner.nextInt()];
		long[] dp = new long[scanner.nextInt() + 1];
		for (int i = 0; i < coinValues.length; i++) coinValues[i] = scanner.nextInt();
		scanner.close();
		dp[0] = 1;
		for (int value_i : coinValues) for (int i = value_i; i < dp.length; i++) dp[i] = dp[i] + dp[i - value_i];
		System.out.println(dp[dp.length - 1]);
	}

}

/*
3 10
1
2
5

*/