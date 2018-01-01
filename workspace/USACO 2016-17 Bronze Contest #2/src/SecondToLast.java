import java.io.*;
import java.util.*;

public class SecondToLast {
	public static void main(String[] args) throws IOException {
		File text = new File("notlast.in");
		Scanner scanner = new Scanner(text);
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("notlast.out")));
		// Scanner scanner = new Scanner(System.in);
		int[] amounts = new int[7];
		int n = Integer.parseInt(scanner.nextLine());
		// enter amount of milk produced to each cow's array spot
		for (int i = 0; i < n; i++) {
			String[] input = scanner.nextLine().split(" ");
			if (input[0].equals("Bessie")) {
				amounts[0] += Integer.parseInt(input[1]);
			} else if (input[0].equals("Elsie")) {
				amounts[1] += Integer.parseInt(input[1]);
			} else if (input[0].equals("Daisy")) {
				amounts[2] += Integer.parseInt(input[1]);
			} else if (input[0].equals("Gertie")) {
				amounts[3] += Integer.parseInt(input[1]);
			} else if (input[0].equals("Annabelle")) {
				amounts[4] += Integer.parseInt(input[1]);
			} else if (input[0].equals("Maggie")) {
				amounts[5] += Integer.parseInt(input[1]);
			} else {
				amounts[6] += Integer.parseInt(input[1]);
			}
		} // identify the minimum amount 
		int min = amounts[0];
		for (int i = 1; i < amounts.length; i++) {
			min = Math.min(min, amounts[i]);
		} // add everything greater than the minimum amount to new ArrayList
		ArrayList<Integer> amounts2 = new ArrayList<Integer>(); 
		for (int i = 0; i < amounts.length; i++) {
			if (amounts[i] > min) {
				amounts2.add(amounts[i]);
			}
		} // if the ArrayList is empty then everyone produced minimum amount; print "Tie" 
		if (amounts2.isEmpty()) {
			out.println("Tie");
		} else { // otherwise find new minimum of ArrayList which is second minimum value
			min = amounts2.get(0);
			for (int i = 1; i < amounts2.size(); i++) {
				min = Math.min(min, amounts2.get(i));
			} int count = 0;
			int index = 0;
			for (int i = 0; i < amounts.length; i++) {
				if (amounts[i] == min) {
					count++;
					index = i;
				} // if more than one cow produced this value print "Tie" else print the cow's name
			} if (count > 1) {
				out.println("Tie");
			} else if (index == 0) {
				out.println("Bessie");
			} else if (index == 1) {
				out.println("Elsie");
			} else if (index == 2) {
				out.println("Daisy");
			} else if (index == 3) {
				out.println("Gertie");
			} else if (index == 4) {
				out.println("Annabelle");
			} else if (index == 5) {
				out.println("Maggie");
			} else {
				out.println("Henrietta");
			}
		} scanner.close();
		out.close();
	}
}