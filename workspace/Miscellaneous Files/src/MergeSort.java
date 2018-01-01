import java.util.*;

public class MergeSort {
	
	public static void main(String[] args) {
		int[] array = new int[24];
		for (int i = 0; i < array.length; i++) array[i] = (int)Math.round(Math.random() * 200) - 100;
		mergeSort(array, 0, array.length - 1);
		System.out.println(Arrays.toString(array));
	}
	
	/* 
	 * (O(lg n) procedure) recursively calls itself until
	 * all subarrays to sort are size 1. each call of this
	 * function will merge the two subarrays after they are
	 * sorted. each call of mergeSort() will also call the O(n)
	 * subroutine merge(), making mergeSort a O(n lg n) procedure.
	 */
	static void mergeSort(int[] array, int p, int q) {
		if (p < q) { // if subarray[p...q] array is size 2 or greater
			int partitionPoint = (p + q) / 2;
			mergeSort(array, p, partitionPoint); // sort first subarray
			mergeSort(array, partitionPoint + 1, q); // sort second subarray
			merge(array, p, partitionPoint, q); // merge two sorted subarrays
		}
	}
	
	/*
	 * (O(n) procedure) procedure will merge sorted subarrays of array 
	 * from indeces [p...q] and [q+1...r] into one sorted subarray [p...r].
	 */
	private static void merge(int[] array, int p, int q, int r) {
		PriorityQueue<Integer> subarray1 = new PriorityQueue<Integer>(q + 1 - p), subarray2 = new PriorityQueue<Integer>(r - q);
		// copy each of the subarrays
		for (int i = p; i <= q; i++) subarray1.add(array[i]);
		for (int i = q + 1; i <= r; i++) subarray2.add(array[i]);
		// add sentinel values onto ends
		subarray1.add(Integer.MAX_VALUE);
		subarray2.add(Integer.MAX_VALUE);
		// merge the elements of the two copied arrays into array[p...r]
		for (int i = p; i <= r; i++) {
			if (subarray1.peek() <= subarray2.peek()) array[i] = subarray1.poll();
			else array[i] = subarray2.poll();
		}
	}
	
}
