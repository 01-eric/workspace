import java.util.*;

public class Grazing {
	
	public static void main(String[] args) {
		// initialization code
		Scanner scanner = new Scanner(System.in);
		int N = Integer.parseInt(scanner.nextLine());
		int[][] grazingZones = new int[N][2];
		for (int i = 0; i < N; i++) {
			String[] temp = scanner.nextLine().split(" ");
			grazingZones[i][0] = Integer.parseInt(temp[0]);
			grazingZones[i][1] = Integer.parseInt(temp[1]);
		} grazingZones = twoDimRadixSort(grazingZones); // sort both arrays based on endTimes
		int prev = 0, count = 1; // iterate through all activities keeping track of the previous activity's end time
		// first event always counted; start at event 2 (index 1) initialize count = 1 and current = 1
		for (int current = 1; current < N; current++) {
			if (grazingZones[current][0] >= grazingZones[prev][1]) {
				count++;
				prev = current;
			}
		} System.out.println(count);
		
	}
	
	static int[][] twoDimRadixSort(int[][] input) {
		for (int placeValue = 0; true; placeValue++) {
			int[] count = new int[10];
			int[][] output = new int[input.length][input[0].length];
			for (int[] input_i : input) count[getDigit(input_i[1], placeValue)]++;
			if (count[0] == input.length) break;
			for (int i = 1; i < count.length; i++) count[i] += count[i - 1];
			for (int i = input.length - 1; i >= 0; i--) output[count[getDigit(input[i][1], placeValue)]-- - 1] = input[i];
			input = output;
		} return input;
	}
	
	private static int getDigit(int number, int index) { 
		return (int)(number / Math.pow(10, index)) % 10;
	}


}
