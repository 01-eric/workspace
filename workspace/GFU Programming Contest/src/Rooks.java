import java.util.*;

public class Rooks {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		long[] outputs = new long[Integer.parseInt(scanner.nextLine())];
		for (int i = 0; i < outputs.length; i++) {
			long factorial = Integer.parseInt(scanner.nextLine());
			for (int j = (int)factorial - 1; j >= 1; j--) factorial = factorial * j;
			outputs[i] = factorial;
		} for (long l: outputs) System.out.println(l);
		scanner.close();
	}

}
