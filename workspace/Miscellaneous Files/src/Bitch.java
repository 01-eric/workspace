import java.util.*;

public class Bitch {
	
	public static void main(String[] args) {
		String vowels = "AEIOU";
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		int count = 0;
		for (char c: input.toUpperCase().toCharArray()) if (vowels.contains(Character.toString(c))) count++;
		System.out.println(count);
	}

}