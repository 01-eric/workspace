import java.io.*;
import java.util.*;

public class Haybales {

	public Haybales() throws IOException {
		int[] test = new int[1000];
		for (int i = 0; i < test.length; i++) test[i] = i * 2;
		System.out.println(Arrays.toString(test));
		for (int i = 0; i < test.length; i++) System.out.println(binarySearch(test, i, false));
		Scanner scanner = new Scanner(new File("haybales.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("haybales.out")));
		// Scanner scanner = new Scanner(System.in);
		String[] input = scanner.nextLine().split(" ");
		String[] haybalesinput = scanner.nextLine().split(" ");
		int[] haybales = new int[haybalesinput.length];
		for (int i = 0; i < haybales.length; i++) haybales[i] = Integer.parseInt(haybalesinput[i]);
		Arrays.sort(haybales);
		for (int i = 0; i < Integer.parseInt(input[1]); i++) {
			String[] interval = scanner.nextLine().split(" ");
			int lowerInterval = binarySearch(haybales, Integer.parseInt(interval[0]), true);
			int upperInterval = binarySearch(haybales, Integer.parseInt(interval[1]), false);
			out.println(upperInterval - lowerInterval + 1);
		} out.close();
		scanner.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Haybales();
	}
	
	/*
	 * returns the index of the key being searched
	 * for in sorted array. If not present, will show
	 * index of first integer greater than the key
	 * if hilo == true or first integer less than the
	 * key if hilo == false. problem says integers are
	 * distinct, no need to test for duplicates.
	 */
	public int binarySearch(int[] array, int key, boolean hilo) {
		int lowerbound = 0, upperbound = array.length - 1, middle = (lowerbound + upperbound) / 2;
		// while the upper and lower are not adjacent
		while (upperbound - lowerbound > 1) {
			if (array[middle] < key) lowerbound = middle + 1;
			else if (array[middle] > key) upperbound = middle - 1;
			else return middle;
			middle = (lowerbound + upperbound) / 2;
		} // for the sake of finding higher/lower narrow down to a size one interval then find return value
		if (key < array[lowerbound]) return hilo ? lowerbound : lowerbound - 1;
		else if (key > array[lowerbound] && key < array[upperbound]) return hilo ? upperbound : lowerbound;
		else if (key > array[upperbound]) return hilo ? upperbound + 1 : upperbound;
		else return key == array[lowerbound] ? lowerbound : upperbound;
	}
}

/*
10 1
20 15 14 13 12 11 10 9 5 1
2 17

4 6
2 3 5 7
2 3
2 4
2 5
2 7
4 6
8 10
*/