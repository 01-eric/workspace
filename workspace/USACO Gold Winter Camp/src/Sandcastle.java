import java.util.*;

public class Sandcastle {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int[] merlons = new int[scanner.nextInt()], newHeights = new int[merlons.length];
		int costUp = scanner.nextInt(), costDown = scanner.nextInt();
		for (int i = 0; i < merlons.length; i++) {
			merlons[i] = scanner.nextInt();
			newHeights[i] = scanner.nextInt();
		} scanner.close();
		Arrays.sort(merlons);
		Arrays.sort(newHeights);
		int sum = 0;
		for (int i = 0; i < merlons.length; i++) {
			int dif = newHeights[i] - merlons[i];
			sum += dif < 0 ? -1 * dif * costDown : dif * costUp;
		} System.out.println(sum);
	}
	
}

/*
3 6 5
3 1
1 2
1 2

*/