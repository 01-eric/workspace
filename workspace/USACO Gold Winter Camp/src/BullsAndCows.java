import java.util.*;

public class BullsAndCows {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		long[] dp = new long[scanner.nextInt() + 1];
		int spacing = scanner.nextInt();
		dp[0] = 1;
		for (int i = 1; i < dp.length; i++) dp[i] = (dp[i - 1] + (i <= spacing ? 1 : dp[i - 1 - spacing])) % 5000011;
		System.out.println(dp[dp.length - 1]);
	}

}
