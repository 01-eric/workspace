import java.util.*;

public class HayDiet {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int[] dp = new int[scanner.nextInt() + 1] /*all weights below max weight with extra spot for 0 weight*/, baleWeights = new int[scanner.nextInt()];
		for (int i = 0; i < baleWeights.length; i++) baleWeights[i] = scanner.nextInt();
		scanner.close();
		for (int i = 0; i < baleWeights.length; i++) {
			int[] copydp = new int[dp.length];
			for (int j = 0; j < copydp.length; j++) copydp[j] = dp[j]; // make deep copy
			for (int j = baleWeights[i]/*all elements less than weight[i] are copied*/; j < dp.length; j++) dp[j] = Math.max(copydp[j], copydp[j - baleWeights[i]] + baleWeights[i]);
		} System.out.println(dp[dp.length - 1]);
	}
	
}

/*
56 4
15
19
20
21

*/