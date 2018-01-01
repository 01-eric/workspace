import java.util.Scanner;

public class CommonSubstring {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String s1 = scanner.nextLine(), s2 = scanner.nextLine();
		int[] dp = new int[s2.length() + 1];
		int longestSubstringLength = 0;
		for (int i = 0; i < s1.length(); i++) {
			int[] copydp = new int[dp.length];
			for (int j = 0; j < copydp.length; j++) copydp[j] = dp[j]; // make deep copy
			for (int j = 0; j < s2.length(); j++) {
				if (s1.charAt(i) == s2.charAt(j)) dp[j + 1] = copydp[j] + 1; // offset cuz 0th spot
				else dp[j + 1] = 0;
				longestSubstringLength = Math.max(longestSubstringLength, dp[j + 1]);
			}
		} System.out.println(longestSubstringLength);
	}

}

/*
ACBAABAACC
CABABAADACCC

*/