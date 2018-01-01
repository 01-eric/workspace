import java.io.*;
import java.util.*;

public class CityStatePairs {

	public CityStatePairs() throws IOException {
		Scanner scanner = new Scanner(new File("citystate.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("citystate.out")));
		// Scanner scanner = new Scanner(System.in);
		int n = Integer.parseInt(scanner.nextLine());
		ArrayList<String> list = new ArrayList<String>(n);
		for (int i = 0; i < n; i++) {
			String s = scanner.nextLine();
			list.add(new StringBuilder(s).delete(2, s.indexOf(" ")).toString());
		} Collections.sort(list);
		n = 0;
		while (list.size() > 0) n += calcNumMatches(list, 0);
		out.println(n);
		scanner.close();
		out.close();
	}
	
	public static void main(String[] args) throws IOException {
		new CityStatePairs();
	}
	
	boolean isPair(String str1, String str2) {
		return str1.substring(0, 2).equals(str2.substring(3, 5)) && str2.substring(0, 2).equals(str1.substring(3, 5));
	}
	
	int calcNumMatches(ArrayList<String> al, int index) {
		int count = 1, count2 = 0, matches = 0; // count represents how many duplicates of al.get(index) there are
		for (int i = index + 1; i < al.size() && al.get(i).equals(al.get(index)); i++) count++;
		String match = al.get(index).substring(3, 5) + " " + al.get(index).substring(0, 2);
		if (match.compareTo(al.get(index)) == 0) matches = factorial(count) / (2 * factorial(count - 2));
		else {
			int index2 = binarySearchCity(al, match);
			if (index2 != -1) {
				for (int i = index2; i < al.size() && al.get(i).equals(al.get(index2)); i++) count2 = i;
				while (index2 >= 0 && al.get(index2).equals(al.get(count2))) index2--;
				count2 -= index2++;
				matches = count2 * count;
				al.subList(index2, index2 + count2).clear();
			}
		} al.subList(index, index + count).clear();
		return matches;
		
	}
	
	int binarySearchCity(ArrayList<String> al, String key) {
		int upper = al.size() - 1, lower = 0;
		while (upper >= lower) {
			int mid = (upper + lower) / 2, compare = key.compareTo(al.get(mid));
			if (compare == 0) return mid;
			else if (compare == -1) upper = mid - 1;
			else lower = mid + 1;
		} return -1;
	}
	
	int factorial(int num) {
		if (num == 0) return 1;
		for (int i = num - 1; i > 1; i--) num *= i;
		return num;
	}

}

/*
17
FLINT MI
MISSOULA FL
FLORENCE MI
MILWAUKEE FL
FLYNT MI
MINATITLAN FL
FLOYD MI
MINGLANILLA FL
FLORENTINA MA
MINGZHOU FJ
MISSOURI FL
MITTENBROOK FL
FLORIDA FL
FLORENCE FL
FLINT FL
FLOYD FL
FLYNT FL

Expected output: 10 + 24 = 34
*/
