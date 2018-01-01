import java.util.Arrays;

public class CountingSort {
	
	public static void main(String[] args) {
		int[] array = {-1, 3, -1, 4, 9, 15, 3, 17, 16, 10, 4, 5, 7, 9, 15, 14, 15, -2, 13, 0, 0, -1, 6};
		System.out.println(Arrays.toString(countingSort(array, -2, 17)));
	}
	
	// can add loop to find max and min within method if do not want max and min as parameters
	static int[] countingSort(int[] array, int min, int max) {
		int[] count = new int[max - min + 1], output = new int[array.length];
		for (int array_i: array) count[array_i - min]++;
		for (int i = 1; i < count.length; i++) count[i] += count[i - 1];
		// iterate from end of array if stability is needed (primitive types sorting doesn't matter)
		for (int array_i: array) output[count[array_i - min]-- - 1] = array_i;
		return output;
	}

}
