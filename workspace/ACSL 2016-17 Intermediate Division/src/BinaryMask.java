import java.util.*;

public class BinaryMask {
	
	private static ArrayList<String> perm = new ArrayList<String>();
	private static ArrayList<String> outputs = new ArrayList<String>();
	private static StringBuilder bin = new StringBuilder();
	private static int loopNestCount = 0;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		for (int runs = 0; runs < 10; runs++) {
			String[] input = scanner.nextLine().split(", ");
			bin.delete(0, bin.length());
			for (char c: input[0].toCharArray()) bin.append('0');
			permutationLength("", input[0], Integer.parseInt(input[1]));
			for (int i = 0; i < perm.size(); i++) if (sumChars(perm.get(i)) != Integer.parseInt(input[2])) perm.remove(i--);
			if (perm.size() > 0) {
				for (int i = 0; i < perm.size() - 1; i++) for (int j = i + 1; j < perm.size(); j++) if (sortChars(perm.get(i)).equals(sortChars(perm.get(j)))) perm.remove(j--);
				for (String s: perm) modifyBinMask(input[0], s);
				for (String s: outputs) System.out.println(s);
				System.out.println();
				outputs.clear();
				perm.clear();
			} else System.out.println("NONE");
		}
	}
	
	public static void permutationLength(String prefix, String str, int length) {
		if (prefix.length() == length) perm.add(prefix);
		else for (int i = 0; i < str.length(); i++) permutationLength(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1), length);
	}
	
	public static int sumChars(String str) {
		int sum = 0;
		for (char c: str.toCharArray()) sum += c - 48;
		return sum;
	}
	
	public static String sortChars(String str) {
		char[] chars = str.toCharArray();
		Arrays.sort(chars);
		str = "";
		for (char c: chars) str += c;
		return str;
	}
	
	public static ArrayList<Integer> indeces(String str, String substring) {
		ArrayList<Integer> indeces = new ArrayList<Integer>();
		for (int i = 0; i <= str.length() - substring.length(); i++) if (str.substring(i, i + substring.length()).equals(substring)) indeces.add(i);
		return indeces;
	}
	
	public static int count1s(StringBuilder bin) {
		int count = 0;
		for (int i = 0; i < bin.length(); i++) if (bin.charAt(i) == '1') count++;
		return count;
	}
	
	public static void modifyBinMask(String mask, String perm) {
		System.out.println(loopNestCount);
		for (int i = 0; i < indeces(mask, perm.substring(loopNestCount, loopNestCount + 1)).size(); i++) {
			bin.replace(indeces(mask, perm.substring(loopNestCount, loopNestCount + 1)).get(i), indeces(mask, perm.substring(loopNestCount, loopNestCount + 1)).get(i) + 1, "1");
			if (++loopNestCount < perm.length()) modifyBinMask(mask, perm);
			else {
				System.out.println(bin);
				if (count1s(bin) == perm.length()) outputs.add(bin.toString());
			}
			loopNestCount--;
			bin.replace(indeces(mask, perm.substring(loopNestCount, loopNestCount + 1)).get(i), indeces(mask, perm.substring(loopNestCount, loopNestCount + 1)).get(i) + 1, "0");
		}
	}

}

/*
01236, 2, 8
01247, 2, 5
0012345, 2, 9
001234, 3, 6
12345, 2, 4
01236, 2, 0
01247, 2, 7
0012345, 2, 1
001234, 3, 3
12345, 2, 0

 */
