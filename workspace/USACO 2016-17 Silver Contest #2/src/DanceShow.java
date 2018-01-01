import java.io.*;
import java.util.*;

public class DanceShow {
	public DanceShow() throws IOException {
		File text = new File("cowdance.in");
		Scanner scanner = new Scanner(text);
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowdance.out")));
		// Scanner scanner = new Scanner(System.in);
		String[] inp = scanner.nextLine().split(" ");
		int n = Integer.parseInt(inp[0]);
		int tmax = Integer.parseInt(inp[1]);
		int[] times = new int[n];
		for (int i = 0; i < times.length; i++) {
			times[i] = Integer.parseInt(scanner.nextLine());
		} scanner.close();
		/* int upper = n;
		int lower = 1;
		while (upper - lower > 1) {
			if (showtime(times, (upper + lower) / 2) > tmax) {
				lower = (upper + lower) / 2 + 1;
			} else if (showtime(times, (upper + lower) / 2) < tmax) {
				upper = (upper + lower) / 2 - 1;
			}
		} int k = lower;
		System.out.println(upper + " " + lower);
		while (showtime(times, k) > tmax) {
			k++;
		} System.out.println(k); */
		int k = 1;
		while (showtime(times, k) > tmax) {
			k++;
		} out.println(k);
		out.close();
	}

	public static void main(String[] args) throws IOException {
		new DanceShow();
	}
	
	public int showtime(int[] times, int stagesize) {
		int turn = stagesize;
		int[] onstage = new int[stagesize];
		for (int i = 0; i < stagesize; i++) {
			onstage[i] = times[i];
		} while (turn < times.length) {
			onstage[minIndex(onstage)] += times[turn];
			turn++;
		} return max(onstage);
	}
	
	public int minIndex(int[] array) {
		int min = array[0];
		int index = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] < min) {
				min = array[i];
				index = i;
			}
		} 
		return index;
	}
	
	public int max(int[] array) {
		int max = 0;
		for (int i = 0; i < array.length; i++) {
			max = Math.max(max, array[i]);
		} return max;
	}
}
