import java.util.*;

public class Biggest {
	
	Scanner scanner = new Scanner(System.in);
	ArrayList<int[][]> cases = new ArrayList<int[][]>();
	
	public static void main(String[] args) {
		new Biggest();
	}
	
	public Biggest() {
		String[] input = scanner.nextLine().split(", ");
		int target = Integer.parseInt(input[input.length - 1]);
		int longerLength = Math.max(input[0].length(), input[1].length());
		for (int i = 1; i < (int)Math.pow(2, longerLength - 1); i++) cases.add(createCase(pad(input[0], longerLength), pad(input[1], longerLength), pad(Integer.toBinaryString(i), longerLength - 1)));
		int closestSum = 0;
		for (int i = 0; i < cases.size(); i++) {
			for (int j = 0; j < (int)Math.pow(2, cases.get(i)[0].length); j++) {
				String bin = pad(Integer.toBinaryString(j), cases.get(i)[0].length);
				int sum = 0;
				for (int k = 0; k < bin.length(); k++) sum += cases.get(i)[bin.charAt(k) - 48][k];
				if (target - sum < target - closestSum && target >= sum) closestSum = sum;
			}
		} System.out.println(closestSum);
	}
	
	private String pad(String str, int length) {
		StringBuilder temp = new StringBuilder();
		for (int i = 0; i < length - str.length(); i++) temp.append(0);
		return temp.append(str).toString();
	}
	
	private int[][] createCase (String one, String two, String split) {
		int[][] returned = {split(new StringBuilder(one), new StringBuilder(split)), reverse(split(new StringBuilder(two), new StringBuilder(split).reverse()))};
		return returned;
	}
	
	private int[] split(StringBuilder str, StringBuilder split) {
		int[] returned = new int[count1(split.toString()) + 1];
		for (int i = 0; i < returned.length - 1; i++) {
			returned[i] = Integer.parseInt(str.substring(0, split.indexOf("1") + 1));
			str.delete(0, split.indexOf("1") + 1);
			split.delete(0, split.indexOf("1") + 1);
		} returned[returned.length - 1] = Integer.parseInt(str.toString());
		return returned;
	}
	
	private int count1(String str) {
		int count = 0;
		for (int i = 0; i < str.length(); i++) if (str.charAt(i) == '1') count++;
		return count;
	}
	
	private int[] reverse(int[] array) {
		for (int i = 0; i < array.length / 2; i++) {
			int temp = array[array.length - 1 - i];
			array[array.length - 1 - i] = array[i];
			array[i] = temp;
		} return array;
	}
}
