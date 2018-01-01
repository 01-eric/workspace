import java.util.*;
public class BaseRound {
	
	static int indexOfFloat;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		for (int runs = 0; runs < 10; runs++) {
			String[] input = scanner.nextLine().split(", ");
			int base = Integer.parseInt(input[1]), place = Integer.parseInt(input[2]);
			indexOfFloat = input[0].indexOf(".");
			if (input[0].length() - indexOfFloat != place + 1) {
				StringBuilder floatNum = new StringBuilder(input[0].substring(0, indexOfFloat + 2 + place));
				if (Integer.parseInt(floatNum.substring(floatNum.length() - 1), base) < Math.ceil((double)base / 2)) System.out.println(floatNum.deleteCharAt(floatNum.length() - 1));
				else System.out.println(increment(floatNum.deleteCharAt(indexOfFloat), floatNum.length() - 2, base).insert(indexOfFloat, ".").deleteCharAt(floatNum.length() - 1).toString().toUpperCase());
			} else System.out.println(input[0]);
		} scanner.close();
	}

	public static StringBuilder increment(StringBuilder sb, int index, int base) {
		if (Integer.parseInt(sb.substring(index, index + 1), base) == base - 1) {
			sb.replace(index, index + 1, "0");
			if (index == 0) {
				sb.insert(index++, "0");
				indexOfFloat++;
			} increment(sb, index - 1, base);
		} else sb.replace(index, index + 1, Integer.toString(Integer.parseInt(sb.substring(index, index + 1), base) + 1, base));
		return sb;
	}
	
}

/*
0.11101, 2, 4
2.021222, 3, 3
3.230120, 4, 2
75.652346, 8, 5
45.45999, 10, 4
17.9AAA, 11, 2
2A.BBB, 12, 2
FF.FFFF, 16, 3
35.4321, 7, 3
ABC.D9CB, 14, 1

*/