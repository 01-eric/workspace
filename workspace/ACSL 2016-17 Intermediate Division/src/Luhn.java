import java.util.*;

public class Luhn {
	
	static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		for (int runs = 0; runs < 10; runs++) {
			String cardNum = scanner.nextLine();
			if (runs < 4) System.out.println(checkSum(cardNum) == cardNum.charAt(cardNum.length() - 1) - 48 ? "Valid" : checkSum(cardNum));
			else if (runs < 6) {
				outer: for (int i = 0; i < cardNum.length() - 1; i++) {
					for (int j = cardNum.charAt(i) - 49; j <= cardNum.charAt(i) - 47; j += 2) {
						if (j == -1) j = 9;
						else if (j == 10) j = 0;
						StringBuilder sb = new StringBuilder(cardNum).replace(i, i + 1, Integer.toString(j));
						if (checkSum(sb.toString()) == cardNum.charAt(cardNum.length() - 1) - 48) {
							System.out.println(sb);
							break outer;
						}
					}
				}
			} else if (runs < 8) {
				for (int i = 0; i < cardNum.length() - 2; i++) {
					StringBuilder sb = new StringBuilder(cardNum).replace(i, i + 2, new StringBuilder(cardNum.substring(i, i + 2)).reverse().toString());
					if (checkSum(sb.toString()) == cardNum.charAt(cardNum.length() - 1) - 48) {
						System.out.println(sb);
						break;
					}
				}
			} else {
				outer: for (int i = 0; i < cardNum.length() - 2; i++) {
					if (cardNum.charAt(i) == cardNum.charAt(i + 1)) {
						for (int j = cardNum.charAt(i) - 49; j <= cardNum.charAt(i) - 47; j += 2) {
							if (j == -1) j = 9;
							else if (j == 10) j = 0;
							StringBuilder sb = new StringBuilder(cardNum).replace(i, i + 2, Integer.toString(j) + j);
							if (checkSum(sb.toString()) == cardNum.charAt(cardNum.length() - 1) - 48) {
								System.out.println(sb);
								break outer;
							}
						}
					}
				}
			}
		}
	}
	
	private static int checkSum(String sequence) {
		StringBuilder sb = new StringBuilder(sequence).deleteCharAt(sequence.length() - 1);
		int sum = 0;
		for (int i = sb.length() - 1; i >= 0; i -= 2) sb.replace(i, i + 1, Integer.toString(sb.charAt(i) - 48 >= 5 ? (sb.charAt(i) - 53) * 2 + 1 : (sb.charAt(i) - 48) * 2));
		for (int i = 0; i < sb.length(); i++) sum += (sb.charAt(i) - 48);
		return sum * 9 % 10;
	}

}
