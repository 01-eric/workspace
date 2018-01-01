import java.util.*;
import java.awt.*;
import java.io.*;

public class Skicourse {
	
	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(new File("skicourse.in"));
		// Scanner scanner = new Scanner(System.in);
		int[][] skicourse = new int[scanner.nextInt()][scanner.nextInt()];
		scanner.nextLine();
		for (int i = 0; i < skicourse.length; i++) {
			char[] input = scanner.nextLine().toCharArray();
			for (int j = 0; j < skicourse[0].length; j++) skicourse[i][j] = input[j] == 'R' ? 1 : 0;
		} scanner.close();
		
		int minStamp = Math.max(skicourse.length, skicourse[0].length);
		while (true) {
			int a = largestStamp(skicourse);
			if (a == -1) break;
			else minStamp = Math.min(minStamp, a);
		} PrintWriter out = new PrintWriter(new FileWriter("skicourse.out"));
		out.println(minStamp);
		out.close();
	}
	
	static int largestStamp(int[][] skicourse) {
		int[][] dp_0 = new int[skicourse.length][skicourse[0].length], dp_1 = new int[skicourse.length][skicourse[0].length];
		int maxStamp_0 = 0, maxStamp_1 = 0;
		Point maxStampIndex_0 = new Point(-1, -1), maxStampIndex_1 = new Point(-1, -1);
		for (int i = skicourse.length - 1; i >= 0; i--) {
			for (int j = skicourse[0].length - 1; j >= 0; j--) {
				int dp_e = j + 1 >= dp_0[0].length ? 0 : dp_0[i][j + 1];
				int dp_se = j + 1 >= dp_0[0].length || i + 1 >= dp_0.length ? 0 : dp_0[i + 1][j + 1];
				int dp_s = i + 1 >= dp_0.length ? 0 : dp_0[i + 1][j];
				if (skicourse[i][j] == 0 || skicourse[i][j] == -1) dp_0[i][j] = 1 + Math.min(Math.min(dp_e, dp_se), dp_s);
				dp_e = j + 1 >= dp_1[0].length ? 0 : dp_1[i][j + 1];
				dp_se = j + 1 >= dp_1[0].length || i + 1 >= dp_1.length ? 0 : dp_1[i + 1][j + 1];
				dp_s = i + 1 >= dp_1.length ? 0 : dp_1[i + 1][j];
				if (Math.abs(skicourse[i][j]) == 1) dp_1[i][j] = 1 + Math.min(Math.min(dp_e, dp_se), dp_s);
				if (dp_0[i][j] > maxStamp_0 && dp_0[i][j] != dp_1[i][j]) {
					maxStamp_0 = dp_0[i][j];
					maxStampIndex_0.setLocation(i, j);
				} if (dp_1[i][j] > maxStamp_1 && dp_0[i][j] != dp_1[i][j]) {
					maxStamp_1 = dp_1[i][j];
					maxStampIndex_1.setLocation(i, j);
				} 
			}
		} 
		
		if (maxStamp_0 == 0 && maxStamp_1 == 0) return -1;
		if (maxStamp_0 < maxStamp_1) {
			maxStampIndex_0.setLocation(maxStampIndex_1); // use maxStampLocation_0 regardless
			maxStamp_0 = maxStamp_1;
		} for (int i = (int)maxStampIndex_0.getX(); i < maxStampIndex_0.getX() + maxStamp_0; i++)
			for (int j = (int)maxStampIndex_0.getY(); j < maxStampIndex_0.getY() + maxStamp_0; j++) skicourse[i][j] = -1;
		return maxStamp_0; 
	}

}

/*
3 6
RSRSSS
RSRSSS
RSRSSS

4 4
SRSS
SRSS
RRRR
SRSS
*/