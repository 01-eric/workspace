import java.util.*;

public class MOZ {
	
	public static void main(String[] args) {
		
		int[][] grid = new int[20][16];
		int[] center = {(int)(Math.random() * 12), (int)(Math.random() * 8)};
		center[0] += (center[0] > 5) ? 6 : 2;
		center[1] += (center[0] > 3) ? 6 : 2;
		
		// set all values to 1
		for (int i = 0; i < grid.length; i++) for (int j = 0; j < grid[0].length; j++) grid[i][j] = 1;
		
		// set borders to 3
		for (int i = center[0] - 1; i < center[0] + 2; i++) {
			grid[i][center[1] - 2] = 3;
			grid[i][center[1] + 2] = 3;
		} for (int i = center[1] - 1; i < center[1] + 2; i++) {
			grid[center[0] - 2][i] = 3;
			grid[center[0] + 2][i] = 3;
			// set center areas to 6
			grid[center[0] - 1][i] = 6;
			grid[center[0]][i] = 6;
			grid[center[0] + 1][i] = 6;
		}
		
		// set center to 10
		grid[center[0]][center[1]] = 10;
		
		print(grid);
		
		System.out.println(Arrays.toString(MOR(grid)));
		
	}
	
	static void print(int[][] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) System.out.print(Integer.toHexString(array[i][j]).toUpperCase());
			System.out.println();
		}
	}
	
	static int[] MOR(int[][] grid) {
		PriorityQueue<int[]> drillPoints = new PriorityQueue<int[]>();
		drillPoints.add(new int[]{8, 8});
		// drillPoints.add(new int[]{12, 7});
		drillPoints.add(new int[]{11, 3});
		drillPoints.add(new int[]{7, 4});
		drillPoints.add(new int[]{3, 3});
		drillPoints.add(new int[]{3, 8});
		while (drillPoints.size() > 0) {
			// "drilling" at selected 6 points, return if not 1
			int[] point = drillPoints.poll();
			if (grid[point[1]][point[0]] != 1) return point;
		} return new int[]{5, 6};
	}

}

// center between 7-8,9-10

