import java.util.*;

public class PPrime {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String[] outputs = new String[Integer.parseInt(scanner.nextLine())];
		for (int i = 0; i < outputs.length; i++) {
			String input = scanner.nextLine();
			outputs[i] = input.equals(new StringBuffer(input).reverse().toString()) && isPrime(Integer.parseInt(input)) && input.length() > 1 ? "yes" : "no";
		} for (String s: outputs) System.out.println(s);
		scanner.close();
	}
	
	static boolean isPrime(int num) {
		for (int i = 2; i <= Math.sqrt(num); i++) if (num % i == 0) return false;
		return true;
	}
	
}
