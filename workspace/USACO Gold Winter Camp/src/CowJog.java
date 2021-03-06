import java.util.*;

public class CowJog {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		long[] positions = new long[scanner.nextInt()];
		int elapsedTime = scanner.nextInt();
		for (int i = 0; i < positions.length; i++) positions[i] = scanner.nextInt() + elapsedTime * scanner.nextInt();
		scanner.close();
		int count = 1;
		long closestEnd = positions[positions.length - 1];
		for (int i = positions.length - 2; i >= 0; i--) {
			if (positions[i] < closestEnd) {
				count++;
				closestEnd = positions[i];
			}
		} System.out.println(count);
	}

}
