import java.util.*;

public class LongestIncreasingSubsequence {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int[] set = new int[scanner.nextInt()], LISEndingAt = new int[set.length];
		for (int i = 0; i < set.length; i++) set[i] = scanner.nextInt();
		scanner.close();
		for (int i = 0; i < LISEndingAt.length; i++) LISEndingAt[i] = 1; // all elements are 1 subsequence
		for (int i = 1; i < set.length; i++) for (int j = i - 1; j >= 0; j--) if (set[j] < set[i]) LISEndingAt[i] = Math.max(LISEndingAt[i], LISEndingAt[j] + 1);
		Arrays.sort(LISEndingAt);
		System.out.println(LISEndingAt[LISEndingAt.length - 1]);
	}

}

/*
12
4
2
1
3
7
4
2
9
11
6
8
4

*/