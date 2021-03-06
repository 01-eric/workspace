import java.util.*;

public class LongestPrefix {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ArrayList<String> primitives = new ArrayList<String>();
		StringBuilder string = new StringBuilder(200000);
		while (true) {
			String s = scanner.next();
			if (s.equals(".")) break;
			primitives.add(s);
		} while(scanner.hasNext()) string.append(scanner.next());
		int[] dp = new int[string.length() + 1];
		dp[0] = 1;
		int maxPrefixIndex = 0;
		for (int i = 1; i < dp.length; i++) {
			for (String primitive : primitives) if (primitive.length() <= i && string.substring(i - primitive.length(), i).equals(primitive)) dp[i] += dp[i - primitive.length()];
			if (dp[i] != 0) maxPrefixIndex = i;
		} System.out.println(maxPrefixIndex);
	}

}

/*
A AB BA 
CA BBC
.
ABABACA
BAABC

*/
