import java.util.*;

public class RadixSort {
	
	public static void main(String[] args) {
		int[] array = {9631, 14, 1, 7512, 7577, 48395, 21157};
		System.out.println(Arrays.toString(radixSort(array)));
	}
	
	public static int[] radixSort(int[] input) {
		
		// should break before placeValue > 10 for max int size 
		for (int placeValue = 0; true; placeValue++) {
			
			// run counting sort on corresponding place value
			int[] count = new int[10], output = new int[input.length];
			
			// get a count of how many of each 1-10 digits there are at placeValue n
			for (int arr_i: input) count[getDigit(arr_i, placeValue)]++;
			
			// if we are over the maximum sig figs in input data break loop
			// must have return type because pointer setting input = output only
			// modifies input pointer and not pointer of the parameter passed in originally
			if (count[0] == input.length) return input;
			
			// get a cumulative sum at each index for array count
			// converts {0, 2, 0, 3, 4, 2} to {0, 2, 2, 5, 9, 11}
			// allows us to figure out positions of values in sorted array
			for (int i = 1; i < count.length; i++) count[i] += count[i - 1];
			
			// go through input array and place each value correctly, subtracting from count
			// must start from end of array because otherwise the count-- mechanism reverses the order of some elements
			for (int i = input.length - 1; i >= 0; i--) output[count[getDigit(input[i], placeValue)]-- - 1] = input[i];
			
			// set input array equal to reference of output array
			input = output;
		}
	}
	
	/*
	 * gets the nth digit in a number, where
	 * n = index. n = 0 is the ones place.
	 * e.g. getDigit(1735, 2) divides by 10^2 = 100
	 * to get 17, and then 17 % 10 to get 7.
	 */
	private static int getDigit(int number, int index) { 
		return (int)(number / Math.pow(10, index)) % 10;
	}

}
