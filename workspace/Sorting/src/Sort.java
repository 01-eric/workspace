import java.util.*;
import java.io.*;

public class Sort {
	
	Scanner console = new Scanner(System.in);
	String input;
	BufferedReader file;
	int[] numbers;
	PrintWriter pw;

	public Sort() {
		System.out.println("Which file would you like to sort?\n1 - input1.txt, 2 - input2.txt, input3 - 3.txt, input4 - 4.txt");
		readFromConsole();
		String inputFile = "";
		try { // throws IOException ??
			file = new BufferedReader(new FileReader("input" + input + ".txt"));
			String[] inputStringArray = file.readLine().split(",");
			numbers = new int[inputStringArray.length];
			for (int i = 0; i < inputStringArray.length; i++) numbers[i] = Integer.parseInt(inputStringArray[i]);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		} System.out.println("Which method of sorting would you like to use?\n1 - Bubble, 2 - Selection, 3 - Table, 4 - Quick");
		readFromConsole();
		long startTime = System.currentTimeMillis();
		if (input.equals("1")) bubbleSort(numbers);
		else if (input.equals("2")) selectionSort(numbers);
		else if (input.equals("3")) tableSort(numbers);
		else if (input.equals("4")) quickSort(numbers, 0, numbers.length - 1);
		long totalTime = System.currentTimeMillis() - startTime;
		try { // throws IOException ??
			pw = new PrintWriter(new FileWriter(new File("output.txt")));
			String output = "";
			for (int i = 0; i < numbers.length; i++) output += numbers[i] + ",";
			pw.write(output.substring(0, output.length() - 1) + " Time elapsed: " + totalTime + " ms"); // pw.print(output) ??
			pw.close();
			System.out.println(output.substring(0, output.length() - 1));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		} System.out.printf("Time elapsed: %d ms", totalTime);
	}
	
	public static void main(String[] args) {
		new Sort();
	}
	
	void readFromConsole() {
		input = console.nextLine();
		if (input.length() == 1) {
			if (!(input.charAt(0) >= 49 && input.charAt(0) <= 52)) {
				System.out.println("Please enter a number 1, 2, 3, or 4.");
				readFromConsole();
			}
		} else {
			System.out.println("Please enter a number 1, 2, 3, or 4.");
			readFromConsole();
		}
	}
	
	/*
	 * Sorts the array using bubble sort
	 */
	void bubbleSort(int[] array) { // aren't bubble sort and selection sort both O(n^2)?
		for (int j = 0; j < array.length; j++) {
			for (int i = 1; i < array.length - j; i++) {
				if (array[i - 1] > array[i]) {
					int temp = array[i - 1];
					array[i - 1] = array[i];
					array[i] = temp;
				}
			}
		}
	}
	
	/*
	 * Sorts the array using selection sort
	 */
	void selectionSort(int[] array) {
		for (int j = 0; j < array.length; j++) {
			int min = array[j];
			int minIndex = j;
			for (int i = j; i < array.length; i++) {
				minIndex = array[i] < min ? i : minIndex;
				min = Math.min(min, array[i]);
			} int temp = array[j];
			array[j] = array[minIndex];
			array[minIndex] = temp;
		}
	}
	
	 /*
	  * Sorts the array using table sort
	  */
	void tableSort(int[] array) {
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < array.length; i++) {
			max = Math.max(max, array[i]);
			min = Math.min(min, array[i]);
		} int[] tally = new int[max - min + 1];
		for (int i = 0; i < array.length; i++) tally[array[i] - min]++;
		int index = 0;
		for (int i = 0; i < tally.length; i++) {
			for (int j = 0; j < tally[i]; j++) {
				array[index] = i + min;
				index++;
			}
		}
	}
	
	/*
	 * Sorts the array using quick sort
	 */
	void quickSort(int[] array, int low, int high) {
		if (!(high - low > 0)) return;
		int i = low, j = high - 1;
		int pivot = array[high];
		while (i <= j) {
			while (array[i] <= pivot && i < high) {
				i++;
			} while (array[j] > pivot && j > low) {
				j--;
			} if (i <= j) {
				if (i == 0 && j == 0) break;
				int temp = array[i];
				array[i] = array[j];
				array[j] = temp;
				if (i < high) i++;
				if (j > low) j--;
			}
		} int temp = array[high];
		array[high] = array[i];
		array[i] = temp;
		quickSort(array, low, j);
		quickSort(array, i + 1, high);
	}

}
