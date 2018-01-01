import java.util.*;
import java.io.*;

public class ModernArt {

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(new File("art.in"));
		int n = scanner.nextInt();
		int[][] canvas = new int[n][n], pfxsum = new int[n + 1][n + 1];
		boolean[] canComeFirst = new boolean[(int)Math.pow(n, 2)];
		for (int i = 0; i < canComeFirst.length; i++) canComeFirst[i] = true;
		TreeMap<Integer, int[]> corners = new TreeMap<Integer, int[]>(); // for each number, min & max x, then m&m y
		
		for (int i = 0; i < canvas.length; i++) {
			for (int j = 0; j < canvas[0].length; j++) {
				canvas[i][j] = scanner.nextInt();
				if (canvas[i][j] != 0) {
					if (!corners.containsKey(canvas[i][j])) corners.put(canvas[i][j], new int[] {n, 0, n, 0});
					int[] arr = corners.get(canvas[i][j]);
					arr[0] = Math.min(arr[0], j);
					arr[1] = Math.max(arr[1], j + 1); // should be to immediate right of square
					arr[2] = Math.min(arr[2], i);
					arr[3] = Math.max(arr[3], i + 1);
				}
			}
		} scanner.close();
		
		PrintWriter out = new PrintWriter(new FileWriter("art.out"));
		if (corners.size() == 1) out.println(canComeFirst.length - 1);
		else {
		
			for (int[] arr : corners.values()) {
				pfxsum[arr[2]][arr[0]]++; // min x min y
				pfxsum[arr[3]][arr[1]]++; // max x max y
				pfxsum[arr[3]][arr[0]]--; // min x max y 
				pfxsum[arr[2]][arr[1]]--; // max x min y
			}
			
			// vertical column-by-column conversion
			for (int j = 0; j < pfxsum[0].length; j++) {
				int sum = 0;
				for (int i = 0; i < pfxsum.length; i++) {
					sum += pfxsum[i][j];
					pfxsum[i][j] = sum;
				}
			}
			
			// horizontal row-by-row conversion
			for (int i = 0; i < pfxsum.length; i++) {
				int sum = 0;
				for (int j = 0; j < pfxsum.length; j++) {
					sum += pfxsum[i][j];
					pfxsum[i][j] = sum;
					if (pfxsum[i][j] > 1) canComeFirst[canvas[i][j] - 1] = false;
				}
			}
			
			int count = 0;
			for (int i = 0; i < canComeFirst.length; i++) if (canComeFirst[i]) count++;
			out.println(count);
		} out.close();
	}
	
}

/*
4
2 2 3 0
2 7 3 7
2 7 7 7
0 0 0 0

16
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 5 5 5 0 0 0 0 0 0 0 0
0 0 0 0 0 5 5 5 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 4 4 4 0 0 0 0 0 0 0 0
0 0 0 0 0 4 4 4 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0

4
1 2 3 4
5 6 7 8
9 10 11 16
11 12 12 16

*/