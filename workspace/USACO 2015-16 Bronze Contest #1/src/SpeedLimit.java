import java.util.*;
import java.io.*;

public class SpeedLimit {
	public static void main(String[] args) throws IOException {
		int[] speedLimit = new int[100];
		int[] speed = new int[100];
		File text = new File("speeding.in");
		Scanner scanner = new Scanner(text);
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("speeding.out")));
		// Scanner scanner = new Scanner(System.in);
		String[] input = scanner.nextLine().split(" ");
		int n = Integer.parseInt(input[0]);
		int m = Integer.parseInt(input[1]);
		int mileCounter = 0; // 0 - 99
		for (int NLines = 0; NLines < n; NLines++) {
			 String[] n_seg = scanner.nextLine().split(" ");
			 int temp = Integer.parseInt(n_seg[0]) + mileCounter;
			 while (mileCounter < temp){
				 speedLimit[mileCounter] = Integer.parseInt(n_seg[1]);
				 mileCounter++;
			 }
		} mileCounter = 0;
		for (int MLines = 0; MLines < m; MLines++) {
			String[] m_seg = scanner.nextLine().split(" ");
			int temp = Integer.parseInt(m_seg[0]) + mileCounter;
			while (mileCounter < temp) {
				speed[mileCounter] = Integer.parseInt(m_seg[1]);
				mileCounter++;
			}
		} int difference = 0;
		for (int i = 0; i < speedLimit.length; i++) {
			difference = Math.max(difference, speed[i] - speedLimit[i]);
		} /*System.*/out.println(difference);
		scanner.close();
		out.close();
	}
}
