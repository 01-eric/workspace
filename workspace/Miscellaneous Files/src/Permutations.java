import java.util.*;

public class Permutations {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Find permutations of a certain length in string: ");
		String[] input = scanner.nextLine().split(", ");
		permutationLength("", input[0], Integer.parseInt(input[1]));
		scanner.close();
	}
	
	private static void permutation(String prefix, String str) {
		if (str.isEmpty()) System.out.println(prefix);
		else for (int i = 0; i < str.length(); i++) permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1));
	}
	
	private static void permutationLength(String prefix, String str, int length) {
		if (prefix.length() == length) System.out.println(prefix);
		else for (int i = 0; i < str.length(); i++) permutationLength(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1), length);
	}

}
