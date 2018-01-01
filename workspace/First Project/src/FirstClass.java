import java.io.*;
import java.util.*;

public class FirstClass {
	
	public static void main(String[] args) {
		/* List<Integer> list = new ArrayList<Integer>(40);
		for (int i = 0; i < 40; i++) list.add((int)(Math.random() * 100));
		for (int element: list) System.out.print(element + " ");
		int indexFirstSquare = -1;
		for (int i = 0; i < list.size(); i++) if (Math.sqrt(list.get(i)) == (int)(Math.sqrt(list.get(i)))) indexFirstSquare = i;
		System.out.println("\n" + indexFirstSquare);
		if (indexFirstSquare >= 0) {
			List<Integer> sublist = list.subList(0, indexFirstSquare);
			list.add(list.get(indexFirstSquare));
			list.subList(0, indexFirstSquare + 1).clear();
			list.addAll(sublist);
			for (int element: list) System.out.print(element + " ");
		} */
		
		/*
		 * First line of input will contain 2 distinct ordered pairs separated a space: x y x y
		 * Second line of input will also contain 2 distinct ordered pairs separated a space: x y x y
		 * The first line of input denotes the corners of one rectangle in a coordinate plane
		 * The second line of input denotes the corners of another rectangle in a coordinate plane
		 * Find the area of the smallest square which can enclose the two rectangles
		 * Input: 3 -1 0 0
		 *        -7 7 0 0
		 * Output: 100
		 */
		
		int[] boi = {7, 6, 5, 2, 4, 3, 1};
		insertionSort(boi);
		System.out.println(Arrays.toString(boi));
		
		
	}
	
	public static void insertionSort(int[] array) {
		for (int i = 1; i < array.length; i++) {
			for (int j = i - 1; j >= 0 && array[j] > array[j + 1]; j--) {
				int temp = array[j];
				array[j] = array[j + 1];
				array[j + 1] = temp;
			}
		}
	}

}
/*
 * 2. (use ArrayList) create a list of 40 numbers between 1 and 100, find the first square number if it exists. reflect the array around this number.
 */

