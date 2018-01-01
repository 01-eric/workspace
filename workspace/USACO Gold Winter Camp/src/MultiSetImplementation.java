import java.util.*;

public class MultiSetImplementation {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		TreeMap<String, Integer> words = new TreeMap<String, Integer>();
		int numWords = Integer.parseInt(scanner.nextLine());
		for (int i = 0; i < numWords; i++) {
			String input = scanner.nextLine();
			if (!words.containsKey(input)) words.put(input, 0);
			else words.put(input, words.remove(input) + 1);
		} for (Map.Entry<String, Integer> entry: words.entrySet()) if (entry.getValue() == 0) System.out.println(entry.getKey());
	}

}

/*
7
Eric
Jason
Connor
Eric
Jenna
Grace
Jason

Output all names that only occur once
*/