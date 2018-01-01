import java.util.*;

public class PartitionSum {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		double n = scanner.nextInt();
		int sum = (int)((n / 2) * (1 + n));
		if (sum % 2 == 0) { // sum is even
			sum /= 2; // find number of ways to make sum/2
			long[] dp = new long[sum];
			dp[0] = 1;
			for (int i = 1; i <= n; i++) {
				long[] copydp = new long[dp.length];
				for (int j = 0; j < copydp.length; j++) copydp[j] = dp[j]; // make deep copy
				for (int j = i; j < dp.length; j++) dp[j] = copydp[j] + copydp[j - i]; // sum of ways to make sum of j and ways to make j - i
			} System.out.println(dp[dp.length - 1] / 2); // last element is ways to make sum/2, divide ways to make it by 2 to account for reverse order
		} else System.out.println(0);
	}

}
